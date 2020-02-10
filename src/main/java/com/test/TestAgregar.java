package com.test;

import java.util.ArrayList;

import com.modelo.Host;
import com.negocio.Facade;
import com.negocio.HostABM;

public class TestAgregar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Facade sist= new Facade();
		
		HostABM se=sist.getHostABM();
		
		try {
		//	System.out.println(se.traerHost("80-FA-5B-2C-72-49"));
			Host host=se.getHostMemoria();
			System.out.println(host.getMac());
			//host.setObservacion("Alta de Host");
			//host.setLoginultimomov(1);
			//System.out.println(se.agregarHost(host));
		//	System.out.println(se.getHostABM());
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
