package com.mustabelmo.qooora.types;

import java.util.List;

public class Channel extends NamedId {
	private Commentator commentator;
	private List<Transponder> transponders;
	
	public Commentator getCommentator() {
		return commentator;
	}
	
	public void setCommentator(Commentator commentator) {
		this.commentator = commentator;
	}
	
	public List<Transponder> getTransponders() {
		return transponders;
	}
	
	public void setTransponders(List<Transponder> transponders) {
		this.transponders = transponders;
	}
}
