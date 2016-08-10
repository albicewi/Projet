package com.natixis.appdynamics.traitements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.natixis.appdynamics.beans.BeanMachineDivers;

public class UpdateFileMachineDivers{
		
	static String file = GetParamApplication.MachineDivers;
	
	public UpdateFileMachineDivers(Map<String, Object> mapMachineDivers) {
		UpdateFileMachineDivers.update(mapMachineDivers);
	}
			
	public static void update (Map<String, Object> mapMachineDivers) {
		FileWriter fstream = null;
		BufferedWriter out = null;
        try {
			fstream = new FileWriter(file);
			out = new BufferedWriter(fstream);
			Iterator<Entry<String, Object>> it = mapMachineDivers.entrySet().iterator();
			//On récupère chaque bean mapMachineDivers pour écrire une ligne correspondante dans le fichier MachineDivers.txt
			while(it.hasNext()) {
				Entry<String, Object> pairs = it.next();
				BeanMachineDivers myBean = (BeanMachineDivers) pairs.getValue();
				String text = myBean.getMachine()+","+myBean.getAgentNode()+","+myBean.getProdAppli()+","+myBean.getEmail()+","+myBean.getCodeIua()+","+myBean.getDomaine();
				out.write(text+"\n");
			}
			out.close();
			fstream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			Logger.getLogger(UpdateFileMachineDivers.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
            try {
            	out.close();
    			fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(UpdateFileMachineDivers.class.getName()).log(Level.SEVERE, null, ex);
            }
        }		
    }            
}


