package com.test;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.function.Consumer;

import javax.swing.filechooser.FileSystemView;

public class TestMemoriaYDisco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Max Memory   : " + bytesToMeg(Runtime.getRuntime().maxMemory()) + " MB");
		System.out.println("Total Memory : " + bytesToMeg(Runtime.getRuntime().totalMemory()) + " MB");
		System.out.println("Free Memory  : " + bytesToMeg(Runtime.getRuntime().freeMemory()) + " MB");

		@SuppressWarnings("restriction")
		long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
				.getTotalPhysicalMemorySize();

		File diskSize = new File("/");

		System.out.println("Memoria Fisica Total : " + bytesToGb(memorySize) + " GB");

		System.out.println("Total Disco  : " + bytesToGb(diskSize.getTotalSpace()) + " GB");
		System.out.println("Disco Libre : " + bytesToGb(diskSize.getFreeSpace()) + " GB");
		System.out.println("Disco Usado : " + bytesToGb(diskSize.getUsableSpace()) + " GB");
        
        long hddtotal=0;
        long hddusado=0;
        
        FileSystem fs = FileSystems.getDefault();
        
        for(FileStore store:fs.getFileStores()){
        	
        	
        	try {
        		hddtotal=hddtotal+store.getTotalSpace();
				hddusado=hddusado+store.getUsableSpace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        }
        
        System.out.println("HDD TOTAL: "+bytesToGb(hddtotal)+" GB");
        System.out.println("HDD USADO: "+bytesToGb(hddusado)+" GB");
        
        

	}

	private static final long MEGABYTE = 1024L * 1024L;
	private static final long GIGABYTE = 1024L * 1024L * 1000L;

	public static long bytesToMeg(long bytes) {
		return bytes / MEGABYTE;
	}

	public static long bytesToGb(long bytes) {
		return bytes / GIGABYTE;
	}

}
