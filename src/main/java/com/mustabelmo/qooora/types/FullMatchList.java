package com.mustabelmo.qooora.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FullMatchList {
	@JsonProperty("showrate")
	String showRate;
	@JsonProperty("matches_list")
	private String [] matchesList;
	@JsonProperty("matches_comps")
	private String [] matchesComps;
	
	public void setMatchesComps(String[] matchesComps) {
		this.matchesComps = matchesComps;
	}
	
	public String[] getMatchesComps() {
		return matchesComps;
	}
	
	public String[] getMatchesList() {
		return matchesList;
	}
	
	public void setMatchesList(String[] matchesList) {
		this.matchesList = matchesList;
	}
	
	public void setShowRate(String showRate) {
		this.showRate = showRate;
	}
	
	public String getShowRate() {
		return showRate;
	}
}
