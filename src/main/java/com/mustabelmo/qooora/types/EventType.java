package com.mustabelmo.qooora.types;

public enum EventType {
	GOAL("g"),
	RED_CARD("r"),
	YELLOW_CARD("y"),
	PENALTY("p"),
	MISSED_PENALTY("m"),
	OWN_GOAL("o"),
	
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
