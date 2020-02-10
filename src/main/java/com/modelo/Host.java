package com.modelo;

import java.util.Set;

public class Host {
	
	  private int idhost;
	  private String cpu_vendedor;
	  private String cpu_modelo;
	  private String cpu_mhz;
	  private String cpu_fisicas;
	  private String cpu_nucleos;
	  private String mac;
	  private String red_host;
	  private String so_fabricante;
	  private String so_arquitectura;
	  private String so_version;
	  private String java_version;
	  private long ram;
	  private long hdd;
	  private String usuario;
	  private HostArea hostArea;
	  private String observacion;
	  private long loginultimomov;

	//  private Set<HostUpdate> hostUpdate;
	  
	  public Host(){};  
	  
	public Host(String cpu_vendedor, String cpu_modelo, String cpu_mhz, String cpu_fisicas, String cpu_nucleos,
			String mac, String red_host, String so_fabricante, String so_arquitectura, String so_version,
			String java_version, String usuario,HostArea hostArea,long ram, long hdd,String observacion,long loginultimomov) {
		super();
		this.cpu_vendedor = cpu_vendedor;
		this.cpu_modelo = cpu_modelo;
		this.cpu_mhz = cpu_mhz;
		this.cpu_fisicas = cpu_fisicas;
		this.cpu_nucleos = cpu_nucleos;
		this.mac = mac;
		this.red_host = red_host;
		this.so_fabricante = so_fabricante;
		this.so_arquitectura = so_arquitectura;
		this.so_version = so_version;
		this.java_version = java_version;
		this.usuario = usuario;
		this.hostArea=hostArea;
		this.ram=ram;
		this.hdd=hdd;
		this.observacion=observacion;
		this.loginultimomov=loginultimomov;
	
	}

	public int getIdhost() {
		return idhost;
	}

	protected void setIdhost(int idhost) {
		this.idhost = idhost;
	}

	public String getCpu_vendedor() {
		return cpu_vendedor;
	}

	public void setCpu_vendedor(String cpu_vendedor) {
		this.cpu_vendedor = cpu_vendedor;
	}

	public String getCpu_modelo() {
		return cpu_modelo;
	}

	public void setCpu_modelo(String cpu_modelo) {
		this.cpu_modelo = cpu_modelo;
	}

	public String getCpu_mhz() {
		return cpu_mhz;
	}

	public void setCpu_mhz(String cpu_mhz) {
		this.cpu_mhz = cpu_mhz;
	}

	public String getCpu_fisicas() {
		return cpu_fisicas;
	}

	public void setCpu_fisicas(String cpu_fisicas) {
		this.cpu_fisicas = cpu_fisicas;
	}

	public String getCpu_nucleos() {
		return cpu_nucleos;
	}

	public void setCpu_nucleos(String cpu_nucleos) {
		this.cpu_nucleos = cpu_nucleos;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRed_host() {
		return red_host;
	}

	public void setRed_host(String red_host) {
		this.red_host = red_host;
	}

	public String getSo_fabricante() {
		return so_fabricante;
	}

	public void setSo_fabricante(String so_fabricante) {
		this.so_fabricante = so_fabricante;
	}

	public String getSo_arquitectura() {
		return so_arquitectura;
	}

	public void setSo_arquitectura(String so_arquitectura) {
		this.so_arquitectura = so_arquitectura;
	}

	public String getSo_version() {
		return so_version;
	}

	public void setSo_version(String so_version) {
		this.so_version = so_version;
	}

	public String getJava_version() {
		return java_version;
	}

	public void setJava_version(String java_version) {
		this.java_version = java_version;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}	

	public long getRam() {
		return ram;
	}

	public void setRam(long ram) {
		this.ram = ram;
	}

	public long getHdd() {
		return hdd;
	}

	public void setHdd(long hdd) {
		this.hdd = hdd;
	}

	public HostArea getHostArea() {
		return hostArea;
	}

	public void setHostArea(HostArea hostArea) {
		this.hostArea = hostArea;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public long getLoginultimomov() {
		return loginultimomov;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpu_fisicas == null) ? 0 : cpu_fisicas.hashCode());
		result = prime * result + ((cpu_mhz == null) ? 0 : cpu_mhz.hashCode());
		result = prime * result + ((cpu_modelo == null) ? 0 : cpu_modelo.hashCode());
		result = prime * result + ((cpu_nucleos == null) ? 0 : cpu_nucleos.hashCode());
		result = prime * result + ((cpu_vendedor == null) ? 0 : cpu_vendedor.hashCode());
		result = prime * result + (int) (hdd ^ (hdd >>> 32));
		result = prime * result + ((java_version == null) ? 0 : java_version.hashCode());
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		result = prime * result + (int) (ram ^ (ram >>> 32));
		result = prime * result + ((red_host == null) ? 0 : red_host.hashCode());
		result = prime * result + ((so_arquitectura == null) ? 0 : so_arquitectura.hashCode());
		result = prime * result + ((so_fabricante == null) ? 0 : so_fabricante.hashCode());
		result = prime * result + ((so_version == null) ? 0 : so_version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Host other = (Host) obj;
		if (cpu_fisicas == null) {
			if (other.cpu_fisicas != null)
				return false;
		} else if (!cpu_fisicas.equals(other.cpu_fisicas))
			return false;
		if (cpu_mhz == null) {
			if (other.cpu_mhz != null)
				return false;
		} else if (!cpu_mhz.equals(other.cpu_mhz))
			return false;
		if (cpu_modelo == null) {
			if (other.cpu_modelo != null)
				return false;
		} else if (!cpu_modelo.equals(other.cpu_modelo))
			return false;
		if (cpu_nucleos == null) {
			if (other.cpu_nucleos != null)
				return false;
		} else if (!cpu_nucleos.equals(other.cpu_nucleos))
			return false;
		if (cpu_vendedor == null) {
			if (other.cpu_vendedor != null)
				return false;
		} else if (!cpu_vendedor.equals(other.cpu_vendedor))
			return false;
		if (hdd != other.hdd)
			return false;
		if (java_version == null) {
			if (other.java_version != null)
				return false;
		} else if (!java_version.equals(other.java_version))
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (ram != other.ram)
			return false;
		if (red_host == null) {
			if (other.red_host != null)
				return false;
		} else if (!red_host.equals(other.red_host))
			return false;
		if (so_arquitectura == null) {
			if (other.so_arquitectura != null)
				return false;
		} else if (!so_arquitectura.equals(other.so_arquitectura))
			return false;
		if (so_fabricante == null) {
			if (other.so_fabricante != null)
				return false;
		} else if (!so_fabricante.equals(other.so_fabricante))
			return false;
		if (so_version == null) {
			if (other.so_version != null)
				return false;
		} else if (!so_version.equals(other.so_version))
			return false;
		return true;
	}

	public void setLoginultimomov(long loginultimomov) {
		this.loginultimomov = loginultimomov;
	}


/*
	public Set<HostUpdate> getHostUpdate() {
		return hostUpdate;
	}

	public void setHostUpdate(Set<HostUpdate> hostUpdate) {
		this.hostUpdate = hostUpdate;
	}
*/
	@Override
	public String toString() {
		return "[ CPU="+ cpu_modelo +", "  
				+ " SO=" +so_fabricante+" "+so_version+" "+so_arquitectura+
				", JAVA=" + java_version + ", RAM=" + ram + ", DISCO=" + hdd + "]";
	}
	
	public String toStringMail(){
		return "Sistema Operativo: "+so_fabricante+" "+so_version+" "+so_arquitectura+", "+
			   "Procesador: "+cpu_modelo+", "+
			   "Memoria RAM: "+ram+"GB"+", "+
			   "Disco Rigido: "+hdd+"GB";
	}






	
	

/*
	public HostArea getHostArea() {
		return hostArea;
	}
	public void setHostArea(HostArea hostArea) {
		this.hostArea = hostArea;
	}
	
	public long getRam() {
		return ram;
	}
	public void setRam(long ram) {
		this.ram = ram;
	}
	public long getHdd() {
		return hdd;
	}
	public void setHdd(long hdd) {
		this.hdd = hdd;
	}
*/
	
	
	  
	  

}