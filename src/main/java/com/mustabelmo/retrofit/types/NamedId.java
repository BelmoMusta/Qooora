package com.mustabelmo.retrofit.types;

public abstract class NamedId {
	private String id;
	private String name;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
