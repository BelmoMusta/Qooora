package com.mustabelmo.retrofit.types;

public class Channel extends NamedId {
	private Commentator commentator;
	
	public Commentator getCommentator() {
		return commentator;
	}
	
	public void setCommentator(Commentator commentator) {
		this.commentator = commentator;
	}
}
