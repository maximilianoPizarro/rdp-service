package com.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestCopiarShortCutIcon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String escritorio=System.getProperty("user.home") +"\\Desktop";
		
		
		
		try {
			File source= new File(escritorio+"\\"+"Agente.lnk");
			//File startup=new File("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\StartUp\\");
			File startup=new File("E:\\");
			
			Files.copy(source.toPath(), startup.toPath().resolve(source.getName()));
			//Runtime.getRuntime().exec(escritorio+"\\"+"Agente.lnk");
			//Runtime.getRuntime().exec("C:\\Users\\Max\\AppData\\Local\\Agente\\Agente.exe");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
