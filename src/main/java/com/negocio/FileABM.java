package com.negocio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileABM {

	public String crearInicio() throws IOException {
		String mensaje = "";
		String escritorio = System.getProperty("user.home") + "\\Desktop";

		if (!existeInicio()) {
			try {
				File source = new File(escritorio + "\\" + "Agente.lnk");
				File startup = new File("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\StartUp\\");
				Files.copy(source.toPath(), startup.toPath().resolve(source.getName()));
			} catch (IOException e) {
				manejaExcepcion(e);
			}
		} else {
			mensaje = "El servicio ya se encuentra iniciado";
		}
		mensaje="Servicio creado exitosamente!!";
		return mensaje;
	}

	public boolean existeInicio() {
		File startup = new File("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\StartUp\\Agente.lnk");
		return startup.exists();
	}
	
	public String estadoServicio() {
		String respuesta="Desactivado";
		if(existeInicio()) respuesta="Activo";
		return respuesta;
	}

	public void manejaExcepcion(IOException he) throws IOException {
		throw new IOException("ejecutar con privilegios de administrador", he);
	}

}
