package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

import com.funciones.Funciones;

public class TestBackPostgres {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
//		String escritorio = "\"C:\\Users\\Max\\Desktop\\"+Funciones.traerFechaConGuiones(new GregorianCalendar())+"-backup_infraesctructura.backup\"";
	//	String escritorio = "\"C:\\Users\\Max\\Desktop\\backup_infraesctructura.backup\"";
		String escritorio = "\"D:\\back_up_rdp\\"+Funciones.traerFechaConGuiones(new GregorianCalendar())+"-backup_infraesctructura.backup\"";


		String user="postgres";
		String pass="root";
		String dbname="infraesctructura";
		
		String pg_dump="pg_dump --dbname=postgresql://"+user+":"+pass+"@127.0.0.1:5432/"+dbname+" -F c -b -v -f "+escritorio;
		//pg_dump --dbname=postgresql://postgres:root@127.0.0.1:5432/infraesctructura -F c -b -v -f "C:\Users\Max\Desktop\backup_infraesctructura.backup"
	
		//Process start = Runtime.getRuntime().exec(pg_dump);
		
		System.out.println(pg_dump);
	
	
	}
	
}
