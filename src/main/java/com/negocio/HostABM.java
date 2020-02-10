package com.negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.modelo.Host;
import com.modelo.HostUpdate;
import com.rest.HostRest;

public class HostABM {

	private HostRest restA = new HostRest();

	public Host traerHost(int idHost) throws Exception {

		Host a = restA.traerHostId(idHost);

		if (a == null) {
			throw new Exception("No existe Host con ese numero de id");
		}
		return a;
	}

	public Host traerHost(String mac) throws Exception {

		Host a = restA.traerHost(mac);

		if (a == null) {
			throw new Exception("No existe Host con ese numero de mac");
		}
		return a;
	}

	public int agregarHost(Host host) throws Exception {
		int a = 0;
		Host existe = restA.traerHost(host.getMac());
		if (existe == null)
			a = restA.agregar(host, ip());
		if (existe != null) {
			throw new Exception("Ya se encuentra registrado en el servidor");
		}
		if (a == 0) {
			throw new Exception("No se pudo agregar el Host");
		}

		return a;
	}

	public ArrayList<String> getHost() {

		File diskSize = new File("/");
		ArrayList<String> lista = cpu();
		String mac = mac();
		if (mac.contains("on")) {
			lista.add("mac=" + mac.substring(0, 18));
		} else {
			lista.add("mac=" + macWifi());
		}
		lista.addAll(so());
		lista.addAll(cpuSys());
		lista.add("usuario=" + System.getProperty("user.name"));
		lista.add("java_version=" + System.getProperty("java.version"));
		lista.add("hdd=" + bytesToGb(diskSize.getTotalSpace()));

		return lista;
	}

	@SuppressWarnings("unused")
	public Host getHostMemoria() throws Exception {

		Host host = new Host();

		for (String lista : getHost()) {
			String[] parts = lista.split("=");
			if (parts[0].compareTo("cpu_fisicas") == 0)
				host.setCpu_fisicas(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("cpu_mhz") == 0)
				host.setCpu_mhz(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("cpu_modelo") == 0)
				host.setCpu_modelo(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("cpu_nucleos") == 0)
				host.setCpu_nucleos(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("cpu_vendedor") == 0)
				host.setCpu_vendedor(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("java_version") == 0)
				host.setJava_version(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("mac") == 0)
				host.setMac(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("red_host") == 0)
				host.setRed_host(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("so_arquitectura") == 0)
				host.setSo_arquitectura(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("so_fabricante") == 0)
				host.setSo_fabricante(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("so_version") == 0)
				host.setSo_version(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("usuario") == 0)
				host.setUsuario(lista.substring(lista.lastIndexOf('=') + 1));
			if (parts[0].compareTo("hdd") == 0)
				host.setHdd(Long.valueOf((lista.substring(lista.lastIndexOf('=') + 1))));
			if (parts[0].compareTo("ram") == 0)
				host.setRam(Long.valueOf((lista.substring(lista.lastIndexOf('=') + 1))));

		}

		if (host == null)
			throw new Exception("No se pudo convertir el host");
		return host;
	}

	public boolean updateHost(Host hostBase, Host hostMemoria) throws Exception {
		boolean cambio = false;
		try {

			if (!hostBase.equals(hostMemoria) && hostBase != null && hostMemoria != null) {
				// hostBase.setCpu_fisicas(hostMemoria.getCpu_fisicas());
				// hostBase.setCpu_mhz(hostMemoria.getCpu_mhz());
				hostBase.setRed_host(hostMemoria.getRed_host());
				hostBase.setCpu_modelo(hostMemoria.getCpu_modelo());
				// hostBase.setCpu_nucleos(hostMemoria.getCpu_nucleos());
				hostBase.setObservacion("Log: " + hostBase);
				hostBase.setSo_arquitectura(hostMemoria.getSo_arquitectura());
				hostBase.setSo_fabricante(hostMemoria.getSo_fabricante());
				hostBase.setSo_version(hostMemoria.getSo_version());
				hostBase.setRam(hostMemoria.getRam());
				hostBase.setHdd(hostMemoria.getHdd());
				hostBase.setJava_version(hostMemoria.getJava_version());
				hostBase.setLoginultimomov(hostBase.getLoginultimomov()); // usuario
																			// del
																			// sistema
				restA.updateHost(hostBase);
				cambio = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cambio;
	}

	public boolean lastUpdateHost(Host objeto) throws Exception {
		boolean respuesta = false;
		HostUpdate hostUpdate = new HostUpdate(null, objeto.getIdhost(), ip());
		respuesta = restA.lastUpdateHost(hostUpdate);
		return respuesta;
	}

	// traer la info desde la consola

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

					if (parts[0].compareTo("Manufacturer") == 0)
						lista.add("cpu_vendedor=" + line.substring(line.lastIndexOf('=') + 1));
					if (parts[0].compareTo("SystemName") == 0)
						lista.add("red_host=" + line.substring(line.lastIndexOf('=') + 1));
					if (parts[0].compareTo("Name") == 0)
						lista.add("cpu_modelo=" + line.substring(line.lastIndexOf('=') + 1));
					if (parts[0].compareTo("MaxClockSpeed") == 0)
						lista.add("cpu_mhz=" + line.substring(line.lastIndexOf('=') + 1));
					if (parts[0].compareTo("NumberOfEnabledCore") == 0)
						lista.add("cpu_fisicas=" + line.substring(line.lastIndexOf('=') + 1));
					if (parts[0].compareTo("NumberOfLogicalProcessors") == 0)
						lista.add("cpu_nucleos=" + line.substring(line.lastIndexOf('=') + 1));
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
		String linea = null;

		try {
			Process start = Runtime.getRuntime().exec("getmac /fo table /nh /v ");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));

			while ((linea = r.readLine()) != null) {
				if (linea.contains("Ethernet")) {
					mac = linea.substring(32, 50); // numero
					if (linea.contains("Medios desconectados")) {
						mac = mac + "off"; //
					} else {
						mac = mac + "on";
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mac;
	}

	public static String macWifi() {
		String macWifi = "S/D";
		String linea = null;

		try {
			Process start = Runtime.getRuntime().exec("getmac /fo table /nh /v ");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));

			while ((linea = r.readLine()) != null) {
				if (linea.contains("Wi-Fi"))
					macWifi = linea.substring(32, 50);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return macWifi;
	}

	public static String ip() {
		String ip = "S/D";
		String linea = null;

		try {
			Process start = Runtime.getRuntime().exec("netsh interface ip show addresses \"Ethernet\" ");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));

			while ((linea = r.readLine()) != null) {
				if (linea.contains("IP"))
					ip = linea.substring(linea.lastIndexOf(':')).substring(28);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return ip;
	}

	public static String ipWifi() {
		String ip = "S/D";
		String linea = null;

		try {
			Process start = Runtime.getRuntime().exec("netsh interface ip show addresses \"Wi-Fi\" ");
			BufferedReader r = new BufferedReader(new InputStreamReader(start.getInputStream()));

			while ((linea = r.readLine()) != null) {
				if (linea.contains("IP"))
					ip = linea.substring(linea.lastIndexOf(':')).substring(28);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return ip;
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

					if (line.compareTo("Adaptador de Ethernet Ethernet:") == 0
							|| line.compareTo("Adaptador de Ethernet Ethernet 2:") == 0) {
						adaptador = true;
						i++;
					}
					if (i > 0 && i < 18 && adaptador == true) {
						i++;
						lista.add(line);
					}
				}
			}

		} catch (IOException e) {
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
				if (!line.isEmpty()) {
					String[] parts = line.split("=");

					if (parts[0].compareTo("TotalPhysicalMemory") == 0)
						lista.add("ram=" + bytesToGb(Long.parseLong(line.substring(line.lastIndexOf('=') + 1))));
					if (parts[0].compareTo("SystemType") == 0)
						lista.add("so_arquitectura=" + line.substring(line.lastIndexOf('=') + 1).substring(0, 3));
					// if(parts[0].compareTo("PrimaryOwnerName")==0)
					// lista.add("usuario="+line.substring(line.lastIndexOf('=')
					// + 1));
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

	public void enviarEmail(Host hostSinModificar, Host hostMemoria) {
		try {
			restA.enviarEmail(hostSinModificar, hostMemoria);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
