package com.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.funciones.Funciones;

public class HostUpdate {
	
	private Integer id;
	private Calendar fechahoramov;
	private Integer fkhost;
	private String ip;
	
	public HostUpdate(){}
	
	

	public HostUpdate(Calendar fechahoramov, Integer fkhost, String ip) {
		this.fechahoramov = fechahoramov;
		this.fkhost = fkhost;
		this.ip=ip;
	}



	public Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}

	public Calendar getFechahoramov() {
		return fechahoramov;
	}

	public void setFechahoramov(Calendar fechahoramov) {
		this.fechahoramov = fechahoramov;
	}

	public Integer getFkhost() {
		return fkhost;
	}

	public void setFkhost(Integer fkhost) {
		this.fkhost = fkhost;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "HostUpdate [id=" + id + ", fechahoramov=" + Funciones.traerFechaCorta((GregorianCalendar)fechahoramov) + ", fkhost=" + fkhost + "]";
	}


	
	

}
