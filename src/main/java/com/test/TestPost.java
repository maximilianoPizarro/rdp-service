package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.PropertyConfigurator;

public class TestPost {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		
		Properties log4jProp = new Properties();
		log4jProp.setProperty("log4j.rootLogger", "WARN");
		PropertyConfigurator.configure(log4jProp);
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8080/RDP/enviarmail");
		 
		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("ssoId", "maximiliano.pizarro.5@gmail.com"));
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		//Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream = entity.getContent();
		    try {
		    	System.out.println(response.toString());
		        // do something useful
		    } finally {
		        instream.close();
		    }
		}
		
		
		

	}

}
