package com.mustabelmo.retrofit.types;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Match {
	private String id;
	private String categoryId;
	private LocalDateTime time;
	private List<MatchEvent> homeTeamEvent = new ArrayList<>();
	private List<MatchEvent> awayTeamEvent = new ArrayList<>();
	private String homeGoals;
	private String homePenaltyGoals;
	private String awayGoals;
	private String awayPenaltyGoals;
	private Team homeTeam;
	private Team awayTeam;
	private List<Channel> channels = new ArrayList<>();
	private Stadium stadium;
	
	public String getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public List<MatchEvent> getHomeTeamEvent() {
		return homeTeamEvent;
	}
	
	public void setHomeTeamEvent(List<MatchEvent> homeTeamEvent) {
		this.homeTeamEvent = homeTeamEvent;
	}
	
	public List<MatchEvent> getAwayTeamEvent() {
		return awayTeamEvent;
	}
	
	public void setAwayTeamEvent(List<MatchEvent> awayTeamEvent) {
		this.awayTeamEvent = awayTeamEvent;
	}
	
	public String getHomeGoals() {
		return homeGoals;
	}
	
	public void setHomeGoals(String homeGoals) {
		this.homeGoals = homeGoals;
	}
	
	public String getAwayGoals() {
		return awayGoals;
	}
	
	public void setAwayGoals(String awayGoals) {
		this.awayGoals = awayGoals;
	}
	
	public Team getHomeTeam() {
		return homeTeam;
	}
	
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	
	public Team getAwayTeam() {
		return awayTeam;
	}
	
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	
	public List<Channel> getChannels() {
		return channels;
	}
	
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
	
	@Override
	public String toString() {
		return homeTeam + " vs " + awayTeam;
	}
	
	public Stadium getStadium() {
		return stadium;
	}
	
	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}
	
	public void setAwayPenaltyGoals(String awayPenaltyGoals) {
		this.awayPenaltyGoals = awayPenaltyGoals;
	}
	
	public void setHomePenaltyGoals(String homePenaltyGoals) {
		this.homePenaltyGoals = homePenaltyGoals;
	}
}
