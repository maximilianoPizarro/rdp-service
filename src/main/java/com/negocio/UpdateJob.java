package com.negocio;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.modelo.Host;



public class UpdateJob implements Job
{
	@SuppressWarnings("static-access")
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		

		Facade sist = new Facade();
		HostABM serviceHost = sist.getHostABM();		
		try {	
						
			Host hostBase=serviceHost.traerHost(serviceHost.mac());
			Host hostSinModificar=serviceHost.traerHost(serviceHost.mac());
			Host hostMemoria=serviceHost.getHostMemoria();
			
			serviceHost.lastUpdateHost(hostBase);
			
			if(serviceHost.updateHost(hostBase,hostMemoria)){
				serviceHost.enviarEmail(hostSinModificar,hostMemoria);
	//			serviceEmail.enviarEmail(serviceUser.traerEmailGerencia(), hostSinModificar);
				System.out.println("algo cambio");
			}else
				System.out.println("todo igual");
			//se.traerHost(85);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
 
	
}