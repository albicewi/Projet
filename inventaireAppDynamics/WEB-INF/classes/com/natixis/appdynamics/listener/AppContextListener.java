package com.natixis.appdynamics.listener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.natixis.appdynamics.traitements.GetParamApplication;
import com.natixis.appdynamics.traitements.LaunchShellInventaire;

@WebListener
public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {		
		System.out.println("Application Listener has been shutdown");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("TimerTask " + new Date().toString() +": Initialisation des paramètres pour les jobs de mise à jours");
		//Initialisation des paramètres applicatifs
		new GetParamApplication();
		
		//On lance la servlet MakeInventory 1 secondes après le démarrage et ensuite tous les 24H (interval)
		Timer timer = new Timer();		
		Integer interval = GetParamApplication.intervalActualisationHeure * 1000 * 60 * 60;		
		timer.schedule(new VodTimerTaskMakeInventory(), 1000, interval);
		
		//On lance le script shell à 22h00 tous les soirs par exemple
		String[] Tab_heureLancementScriptShell = GetParamApplication.heureLancementScriptShell.split(":");
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(Tab_heureLancementScriptShell[0])); 
		calendar.set(Calendar.MINUTE, Integer.parseInt(Tab_heureLancementScriptShell[1])); 
		calendar.set(Calendar.SECOND, Integer.parseInt(Tab_heureLancementScriptShell[2])); 
		Date time = calendar.getTime();		
		timer.scheduleAtFixedRate(new VodTimerTaskScriptShell(), time, 86400000);
		
		System.out.println("TimerTask " + new Date().toString() +": Intervalle d'appel de la servlet "+GetParamApplication.urlMakeInventory+": "+GetParamApplication.intervalActualisationHeure+ "H");
		System.out.println("TimerTask " + new Date().toString() +": Heure de lancement du script Shell: "+GetParamApplication.heureLancementScriptShell+".");
	}

	class VodTimerTaskMakeInventory extends TimerTask {
		@Override
		public void run() {	
			//Lancement inventaire ISA
			System.out.println("TimerTask " + new Date().toString() + ": Appel servlet "+GetParamApplication.urlMakeInventory+".");
			URL MakeInventory = null;
			// On lance la servlet d'invenvaire qui se connecte au controller appdynamics						
		    try {		    	
		    	MakeInventory = new URL(GetParamApplication.urlMakeInventory);
		        URLConnection servletConnection = MakeInventory.openConnection();		        
		        servletConnection.getContent();
		    } catch (MalformedURLException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }		    
		    System.out.println("TimerTask " + new Date().toString() + ": Fin Appel servlet "+GetParamApplication.urlMakeInventory+".");
		}
	}
	
	class VodTimerTaskScriptShell extends TimerTask {
		@Override
		public void run() {	
			//Lancement inventaire ISA
			System.out.println("TimerTask " + new Date().toString() + ": Lancement script shell "+GetParamApplication.scriptShell);
			new LaunchShellInventaire();
			System.out.println("TimerTask " + new Date().toString() + ": Fin script shell "+GetParamApplication.scriptShell);			
		}
	}	
}

