package com.test;

import com.negocio.Facade;
import com.negocio.HostABM;

public class TestMac {

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Facade facade= new Facade();
		HostABM sistema= facade.getHostABM();	
			
			//System.out.println(sistema.adpatadorEthernet().get(3));
			
			System.out.println(sistema.mac());
			System.out.println(sistema.traerHost(132));
			System.out.println(sistema.traerHost(sistema.mac()).getHostArea());

	}

}
