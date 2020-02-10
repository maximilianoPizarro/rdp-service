package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TestHost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * idhost cpu_vendedor "Intel" cpu_modelo
		 * "Core(TM) i5-7400T CPU @ 2.40GHz" cpu_mhz "2400" cpu_fisicas "4"
		 * cpu_nucleos "16" mac "0A:00:27:00:00:05" red_host "DESKTOP-PDQ7FOA"
		 * so_fabricante "Microsoft " so_arquitectura "10.0" so_version "x64"
		 * java_version "1.8.0_171" usuario "Max" hostarea_id ram hdd
		 */
		// Manufacturer=GenuineIntel cpu_vendedor
		// Name =Intel(R) Core(TM) i5-7400T CPU @ 2.40GHz cpu_modelo
		// MaxClockSpeed=2401 cpu_mhz
		// NumberOfEnabledCore=4 cpu_fisicas
		// NumberOfLogicalProcessors=4 cpu_nucleos
		// MAC=80-FA-5B-2C-72-49 mac
		// SystemName=DESKTOP-PDQ7FOA red_host
		// PrimaryOwnerName=Max usuario
		// SystemType=x64-based PC so_arquitectura
		// so_fabricante=Microsoft Windows so_fabricante
		// so_version=10 so_version
		// TotalPhysicalMemory=8495988736 ram
		File diskSize = new File("/");		
		ArrayList<String> lista = cpu();
		lista.add("mac=" + mac());
		lista.addAll(so());
		lista.addAll(cpuSys());
		lista.add("java_version=" + System.getProperty("java.version"));
		lista.add("hdd=" + bytesToGb(diskSize.getTotalSpace()+diskSize.getFreeSpace()+diskSize.getUsableSpace())+"GB");

		for (String l : lista) {
			System.out.println(l);
		}

	}

	// CPU
	public static ArrayList<String> cpu() {

		ArrayList<String> lista = new ArrayList<String>();
		try {

			Process start = Runtime.getRuntime().exec(
					"wmic cpu get  Manufacturer,SystemName, Name, maxclockspeed ,NumberOfEnabledCore, NumberOfLogicalProcessors  /format:LIST");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));
			String line = null;
			while ((line = r.readLine()) != null) {
				if (!line.isEmpty()) {
					
					String[] parts = line.split("=");
					
					if(parts[0].compareTo("Manufacturer")==0)					
					lista.add("cpu_vendedor="+line.substring(line.lastIndexOf('=') + 1));
					if(parts[0].compareTo("SystemName")==0)					
					lista.add("red_host="+line.substring(line.lastIndexOf('=') + 1));
					if(parts[0].compareTo("Name")==0)					
					lista.add("cpu_modelo="+line.substring(line.lastIndexOf('=') + 1));
					if(parts[0].compareTo("MaxClockSpeed")==0)					
					lista.add("cpu_mhz="+line.substring(line.lastIndexOf('=') + 1));
					if(parts[0].compareTo("NumberOfEnabledCore")==0)					
					lista.add("cpu_fisicas="+line.substring(line.lastIndexOf('=') + 1));
					if(parts[0].compareTo("NumberOfLogicalProcessors")==0)					
					lista.add("cpu_nucleos="+line.substring(line.lastIndexOf('=') + 1));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// CPU
	public static String mac() {

		String mac = "S/D";

		for (String listaAdaptador : adpatadorEthernet()) {
			String[] parts = listaAdaptador.split(":");
			// System.out.println(parts[0]);
			if (parts[0].compareTo("   Direcci¢n f¡sica. . . . . . . . . . . . . ") == 0) {
				mac = listaAdaptador.substring(listaAdaptador.lastIndexOf(':') + 1).substring(1);
			}
		}
		return mac;
	}

	public static ArrayList<String> adpatadorEthernet() {
		ArrayList<String> lista = new ArrayList<String>();
		boolean adaptador = false;
		int i = 0;
		try {

			Process start = Runtime.getRuntime().exec("ipconfig /all");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));
			String line = null;
			while ((line = r.readLine()) != null) {
				if (!line.isEmpty()) {

					if (line.compareTo("Adaptador de Ethernet Ethernet:") == 0) {
						adaptador = true;
						i++;
					}

					if (i > 0 && i < 18 && adaptador == true) {
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

	public static ArrayList<String> cpuSys() {

		ArrayList<String> lista = new ArrayList<String>();

		try {

			Process start = Runtime.getRuntime().exec(
					"WMIC /Output:STDOUT COMPUTERSYSTEM get TotalPhysicalMemory,SystemType,PrimaryOwnerName /format:LIST");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));
			String line = null;
			while ((line = r.readLine()) != null) {										
				if (!line.isEmpty())
				{
					String[] parts = line.split("=");
					
					if(parts[0].compareTo("TotalPhysicalMemory")==0)					
					lista.add("ram="+bytesToGb(Long.parseLong(line.substring(line.lastIndexOf('=') + 1)))+"GB");
					if(parts[0].compareTo("SystemType")==0)					
					lista.add("so_arquitectura="+line.substring(line.lastIndexOf('=') + 1).substring(0, 3));
					if(parts[0].compareTo("PrimaryOwnerName")==0)					
					lista.add("usuario="+line.substring(line.lastIndexOf('=') + 1));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public static ArrayList<String> so() {

		ArrayList<String> lista = new ArrayList<String>();

		try {

			Process start = Runtime.getRuntime().exec("WMIC /Output:STDOUT RECOVEROS get Name /format:LIST");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));
			String line = null;
			while ((line = r.readLine()) != null) {

				if (!line.isEmpty()) {
					String[] parts = line.split(":");
					int longitud = parts[0].length();
					lista.add("so_fabricante=" + line.substring(0, longitud - 9).substring(5));
					lista.add("so_version=" + line.substring(0, longitud - 2).replaceAll("[^0-9]", ""));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	private static final long GIGABYTE = 1024L * 1024L * 1000L;

	public static long bytesToGb(long bytes) {
		return bytes / GIGABYTE;
	}

}
