package com.test;

import com.modelo.Host;
import com.modelo.User;
import com.negocio.Facade;
import com.negocio.HostABM;
import com.negocio.SendEmail;

public class TestReporte {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Facade sist = new Facade();

		HostABM se = sist.getHostABM();
		
		SendEmail sendemail=sist.getSendEmail();
		
		try {
			sendemail.enviarEmail("maximiliano.pizarro.5@gmail.com", se.traerHost("80-FA-5B-2C-72-49"), se.traerHost("80-FA-5B-2C-72-49"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
