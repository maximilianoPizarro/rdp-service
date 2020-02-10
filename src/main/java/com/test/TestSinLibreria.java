package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.controlador.AppController;

public class TestSinLibreria extends Thread {
	// https://norfipc.com/comandos/informacion-pc-uso-wmic.html
	public static void main(String[] args) {
		// Thread thread = new TestSinLibreria();
		// thread.start();
		 ArrayList<String> lista = cpu();
		//ArrayList<String> lista = cpuSys();
		// ArrayList<String> lista = so();
		// ArrayList<String> lista = red();
		//ArrayList<String> lista = programas();

		for (String l : lista) {
			System.out.println(l);
		}

		// so();
	}

	// CPU
	public static ArrayList<String> cpu() {

		ArrayList<String> lista = new ArrayList<String>();
		;

		try {

			Process start = Runtime.getRuntime().exec("WMIC /Output:STDOUT CPU get /all /format:LIST");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));
			String line = null;
			while ((line = r.readLine()) != null) {
				if (!line.isEmpty()) {
					lista.add(line);
				}
				// lista.add(line.substring(line.lastIndexOf('=') + 1)); esto
				// para el sub string derecha
				// String[] parts = line.split("="); esto para el string de la
				// izquierda
				// lista.add(parts[0]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// SO
	public static ArrayList<String> cpuSys() {

		ArrayList<String> lista = new ArrayList<String>();
		;

		try {

			Process start = Runtime.getRuntime().exec("WMIC /Output:STDOUT COMPUTERSYSTEM get /all /format:LIST");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));
			String line = null;
			while ((line = r.readLine()) != null) {
				if (!line.isEmpty())
				lista.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static ArrayList<String> so() {

		ArrayList<String> lista = new ArrayList<String>();
		;

		try {

			Process start = Runtime.getRuntime().exec("WMIC /Output:STDOUT RECOVEROS get /all /format:LIST");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));
			String line = null;
			while ((line = r.readLine()) != null) {
				if (!line.isEmpty()) {
				lista.add(line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// RED
	public static ArrayList<String> red() {
		ArrayList<String> lista = new ArrayList<String>();
		;

		try {

			Process start = Runtime.getRuntime()
					.exec("wmic cpu get caption, deviceid, name, numberofcores, maxclockspeed, status /format:LIST");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));
			String line = null;
			while ((line = r.readLine()) != null) {
				if (!line.isEmpty()) {
					lista.add(line);
				}
				// lista.add(line.substring(line.lastIndexOf('=') + 1)); esto
				// para el sub string derecha
				// String[] parts = line.split("="); esto para el string de la
				// izquierda
				// lista.add(parts[0]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// PROGRAMAS
	public static ArrayList<String> programas() {
		ArrayList<String> lista = new ArrayList<String>();
		boolean adaptador = false;
		int i = 0;
		try {

			Process start = Runtime.getRuntime().exec("ipconfig /all");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));
			String line = null;
			while ((line = r.readLine()) != null) {
				if (!line.isEmpty()) {

					if (line.compareTo("Adaptador de Ethernet Ethernet:") == 0 ) {
						adaptador = true;
						i++;
					}

					if (i > 0 && i < 18 && adaptador==true) {
						i++;
						lista.add(line);
					}
				}
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	

}

/*
 * 
 * Toda la Informaci�n sobre tu CPU WMIC /Output:STDOUT CPU get /all
 * /format:LIST
 * 
 * Toda la Informaci�n sobre tu CDROM WMIC /Output:STDOUT CDROM get /all
 * /format:LIST
 * 
 * Toda la Informaci�n sobre REDES WMIC /Output:STDOUT NICCONFIG get /all
 * /format:LIST
 * 
 * Toda la Informaci�n sobre tu sistema WMIC /Output:STDOUT COMPUTERSYSTEM get
 * /all /format:LIST
 * 
 * Toda la Informaci�n sobre tus discos WMIC /Output:STDOUT DISKDRIVE get /all
 * /format:LIST
 * 
 * Toda la Informaci�n sobre discos logicos WMIC /Output:STDOUT LOGICALDISK get
 * /all /format:LIST
 * 
 * Toda la Informaci�n sobre tu memoria WMIC /Output:STDOUT MEMPHYSICAL get /all
 * /format:LIST
 * 
 * Toda la Informaci�n sobre los servicios WMIC /Output:STDOUT SERVICE get /all
 * /format:LIST
 * 
 * Toda la Informaci�n sobre los procesos WMIC /Output:STDOUT PROCESS get /all
 * /format:LIST
 * 
 * Toda la Informaci�n sobre los programas en el inicio de Windows WMIC
 * /Output:STDOUT STARTUP get /all /format:LIST
 * 
 * Toda la Informaci�n sobre los dispositivos en tu motherboard WMIC
 * /Output:STDOUT ONBOARDDEVICE get /all /format:LIST
 * 
 * Toda la Informaci�n sobre errores del sistema operativo WMIC /Output:STDOUT
 * RECOVEROS get /all /format:LIST
 * 
 * 
 */
