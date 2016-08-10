package com.natixis.appdynamics.traitements;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.natixis.appdynamics.beans.BeanInfoLicense;

public class GetInfoLicense {
	
	public Map <String, Object> RetrieveInfoLicense() throws ClientProtocolException, IOException {
		Map <String, Object> myMapInfoLicense = new HashMap<String, Object>();	  
	  
		HttpClient client = new DefaultHttpClient();
	  	  
		String [] chaineUrl = GetParamApplication.urlApm.split("/"+"/");
		String hostApm = chaineUrl[1];
	  
		URI uri = null;
		try {
			uri = new URIBuilder()
		      .setScheme("http").setHost(hostApm).setPath(GetParamApplication.uriInfoLicense)
		      .setParameter("startdate", GetDateInfoLicence())
		      .setParameter("enddate", GetDateInfoLicence())
		      .build();
		} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		((DefaultHttpClient)client).getCredentialsProvider().setCredentials(
			  new AuthScope(hostApm, 80),
			  new UsernamePasswordCredentials(GetParamApplication.userApm, GetParamApplication.passwordApm)			  
			  );	  	  
		HttpGet request = new HttpGet(uri);
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String jsonContent = EntityUtils.toString(entity);
		Reader stringReader = new StringReader(jsonContent);
		JsonReader rdr = Json.createReader(stringReader);
		JsonObject obj = rdr.readObject();
		JsonArray results = obj.getJsonArray("usages");
		
		for (JsonObject result : results.getValuesAs(JsonObject.class)) {
		  BeanInfoLicense myBeanInfoLicense = new BeanInfoLicense(result.getString("agentType"), result.getInt("avgUnitsAllowed"), result.getInt("avgUnitsUsed"));
		  myMapInfoLicense.put(result.getString("agentType"), myBeanInfoLicense);
		  //System.out.println(result.getString("agentType")+","+result.getInt("avgUnitsAllowed")+","+result.getInt("avgUnitsUsed"));
		}
		
		return myMapInfoLicense;
	}
	
	public String GetDateInfoLicence() {
		String dateInfoLicence = null;
		SimpleDateFormat formater = null;
		
		//Valeur qui sera retranché de l'heure courante
		Integer nbHour=-4;
		
		Date aujourdhui = new Date();
		Calendar cal = Calendar.getInstance();		
		cal.setTime(aujourdhui);
		// On prend Heure courante -4H pour être sûr d'avoir des données.
		cal.add(Calendar.HOUR, nbHour);
		// Le format pour les paramêtres startdate et enddate est de la forme 2016-07-22T11:00:00Z
		formater = new SimpleDateFormat("yyyy-MM-dd'T'HH':00:00Z'");
		//System.out.println(formater.format(cal.getTime()));
		dateInfoLicence = formater.format(cal.getTime());		
		return dateInfoLicence;
	}	
}