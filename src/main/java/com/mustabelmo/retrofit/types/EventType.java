package com.mustabelmo.retrofit.types;

public enum EventType {
	GOAL("g"),
	RED_CARD("r"),
	YELLOW_CARD("y"),
	PENALTY("p"),
	MISSED_PENALTY("r"), // todo
	OWN_GOAL("r"),
	
	;
	
	private final String value;
	
	EventType(String value) {
		this.value = value;
	}
	
	public static EventType fromValue(String value) {
		String sanitized = value;
		if(value.startsWith("~")){
			sanitized = value.substring(1);
		}
		for (EventType eventType : values()) {
			if (eventType.value.equals(sanitized)) {
				return eventType;
			}
		}
		
		return null;
	}
}
