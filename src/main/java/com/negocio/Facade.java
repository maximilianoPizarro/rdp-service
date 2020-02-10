package com.negocio;


public class Facade {

	public HostABM getHostABM() {
		return new HostABM();
	}
	
	public UserABM getUserABM() {
		return new UserABM();
	}
	
	public FileABM getFileABM() {
		return new FileABM();
	}
	
}