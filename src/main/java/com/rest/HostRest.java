package com.rest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;
import com.modelo.Host;
import com.modelo.HostUpdate;

public class HostRest {

	//private static String url = "http://10.68.11.218:8080/rdp/abm-host";
	private static String url = "/abm-host";
	private static Properties log4jProp = new Properties();


	private HttpClient httpclient;

	private void iniciaOperacion() throws FileNotFoundException, IOException {
		InputStream inputStream=getClass().getClassLoader().getResourceAsStream("application.properties");		
		log4jProp.load(inputStream);
		//System.out.print(log4jProp.getProperty("server")+":"+log4jProp.getProperty("port"));
		log4jProp.setProperty("log4j.rootLogger", "WARN");
		PropertyConfigurator.configure(log4jProp);
		httpclient = HttpClients.createDefault();

	}

	public int agregar(Host objeto, String ip) throws IOException {
		int id = 0;
		try {
			iniciaOperacion();
			
			HttpPost httppost = new HttpPost("https://"+log4jProp.getProperty("server")+url +"/host-alta/");
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("host", new Gson().toJson(objeto)));
			params.add(new BasicNameValuePair("ip", ip));

			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);

			if (statusResponse(response.getStatusLine().getStatusCode())) {
				String json = EntityUtils.toString(response.getEntity());
				id = Integer.valueOf(json);
			}

		} catch (IOException e) {
			manejaExcepcion(e);
		}

		return id;
	}

	public Host traerHostId(int idHost) throws IOException {

		Host objeto = null;
		try {
			iniciaOperacion();
			HttpPost httppost = new HttpPost("http://"+log4jProp.getProperty("server")+":"+log4jProp.getProperty("port")+url+"/host/");
			List<NameValuePair> params = new ArrayList<NameValuePair>(1);
			params.add(new BasicNameValuePair("id", String.valueOf(idHost)));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			HttpResponse response = httpclient.execute(httppost);
			if (statusResponse(response.getStatusLine().getStatusCode())) {

				String json = EntityUtils.toString(response.getEntity());
				objeto = new Gson().fromJson(json, Host.class);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			manejaExcepcion(e);
		}

		return objeto;

	}

	public Host traerHost(String mac) throws IOException {
		Host objeto = null;
		try {
			iniciaOperacion();
			HttpPost httppost = new HttpPost("http://"+log4jProp.getProperty("server")+":"+log4jProp.getProperty("port")+url+"/host-mac/");
			List<NameValuePair> params = new ArrayList<NameValuePair>(1);
			params.add(new BasicNameValuePair("mac", mac));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			HttpResponse response = httpclient.execute(httppost);
			if (statusResponse(response.getStatusLine().getStatusCode())) {

				String json = EntityUtils.toString(response.getEntity());
				objeto = new Gson().fromJson(json, Host.class);
			}
		} catch (IOException e) {
			manejaExcepcion(e);
		}

		return objeto;
	}

	private void manejaExcepcion(IOException he) throws IOException {
		throw new IOException("ERROR en la capa de comunicaciones", he);
	}

	public boolean statusResponse(int httpResponse) {
		return (httpResponse == 200 || httpResponse == 301 || httpResponse == 302);
	}

	public boolean updateHost(Host objeto) throws IOException {
		boolean respuesta = false;
		try {
			iniciaOperacion();
			HttpPost httppost = new HttpPost("http://"+log4jProp.getProperty("server")+":"+log4jProp.getProperty("port")+url +"/host-update/");
			List<NameValuePair> params = new ArrayList<NameValuePair>(1);
			params.add(new BasicNameValuePair("host", new Gson().toJson(objeto)));

			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);

			if (statusResponse(response.getStatusLine().getStatusCode())) {
				String json = EntityUtils.toString(response.getEntity());
				respuesta = Boolean.valueOf(json);
			}

		} catch (IOException e) {
			manejaExcepcion(e);
		}
		
		return respuesta;
	}
	
	public boolean lastUpdateHost(HostUpdate objeto) throws IOException {
		boolean respuesta = false;
		try {
			iniciaOperacion();
			HttpPost httppost = new HttpPost("http://"+log4jProp.getProperty("server")+":"+log4jProp.getProperty("port")+url+ "/host-last-update/");
			List<NameValuePair> params = new ArrayList<NameValuePair>(1);
			params.add(new BasicNameValuePair("host", new Gson().toJson(objeto)));

			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);

			if (statusResponse(response.getStatusLine().getStatusCode())) {
				String json = EntityUtils.toString(response.getEntity());
				respuesta = Boolean.valueOf(json);
			}

		} catch (IOException e) {
			manejaExcepcion(e);
		}
		
		return respuesta;
	}

	public void enviarEmail(Host hostSinModificar, Host hostMemoria) throws IOException {
		try {
			iniciaOperacion();
			HttpPost httppost = new HttpPost("http://"+log4jProp.getProperty("server")+":"+log4jProp.getProperty("port")+url +"/enviar-email-update/");
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("hostSinModificar", new Gson().toJson(hostSinModificar)));
			params.add(new BasicNameValuePair("hostMemoria", new Gson().toJson(hostMemoria)));

			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);

			if (statusResponse(response.getStatusLine().getStatusCode())) {
				String json = EntityUtils.toString(response.getEntity());
				System.out.println("Estado de envio de email: "+json);	
			}

		} catch (IOException e) {
			manejaExcepcion(e);
		}
	}

}
