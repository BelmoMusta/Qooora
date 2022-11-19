package com.mustabelmo.retrofit.types;

public class MatchEvent {
	private EventType type;
	Player player;
	String minute;
	
	public EventType getType() {
		return type;
	}
	
	public void setType(EventType type) {
		this.type = type;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public String getMinute() {
		return minute;
	}
	
	public void setMinute(String minute) {
		this.minute = minute;
	}
}
