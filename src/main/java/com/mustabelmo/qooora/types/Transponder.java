package com.mustabelmo.qooora.types;

public class Transponder {
	private Satellite satellite;
	private Frequency frequency;
	
	public Satellite getSatellite() {
		return satellite;
	}
	
	public void setSatellite(Satellite satellite) {
		this.satellite = satellite;
	}
	
	public Frequency getFrequency() {
		return frequency;
	}
	
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
}
