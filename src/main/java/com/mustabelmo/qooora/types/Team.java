package com.mustabelmo.qooora.types;

public class Team extends NamedId {
	public boolean isLike(String teamName) {
		return teamName == null ||
				getName().toLowerCase()
						.contains(teamName.toLowerCase());
	}
}
