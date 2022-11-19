package com.mustabelmo.retrofit.types;

public class Team extends NamedId {
public boolean isLike(String teamName){
	return getName().toLowerCase().contains(teamName.toLowerCase());
}
}
