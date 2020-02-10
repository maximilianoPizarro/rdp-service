package com.test;

import com.modelo.User;
import com.negocio.Facade;
import com.negocio.UserABM;

public class TestUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Facade sistema= new Facade();
		
		UserABM usuario= sistema.getUserABM();
		
		try {
		//	System.out.println(usuario.traerUser(1));
		//	System.out.println(usuario.traerUser(1).getUserprofiles());
			
			System.out.println(usuario.existeUsuario("maxpizarro", "123"));
			System.out.println("***");
			
	//		User us=usuario.existeUsuario("maxpizarro", "1234");
			System.out.println();
			
	//		System.out.println(usuario.traerEmailGerencia());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		

	}

}
