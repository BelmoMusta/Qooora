package com.mustabelmo.qooora.types;

import java.util.Locale;

public enum Polarization {
	H,
	V,
	
	;
	
	public static Polarization from(String text) {
		if (text == null) {
			return null;
		}
		if (text.trim().toLowerCase().startsWith("h")) {
			return H;
		}
		if (text.trim().toLowerCase().startsWith("v")) {
			return V;
		}
		return null;
	}
}
