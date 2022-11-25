package com.mustabelmo.qooora.types;

public class Frequency {
	private int value;
	private Polarization polarization;
	private int symbolRate;
	private String fec;
	private String encryption;
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public Polarization getPolarization() {
		return polarization;
	}
	
	public void setPolarization(Polarization polarization) {
		this.polarization = polarization;
	}
	
	public int getSymbolRate() {
		return symbolRate;
	}
	
	public void setSymbolRate(int symbolRate) {
		this.symbolRate = symbolRate;
	}
	
	public String getFec() {
		return fec;
	}
	
	public void setFec(String fec) {
		this.fec = fec;
	}
	
	public String getEncryption() {
		return encryption;
	}
	
	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}
}
