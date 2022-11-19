package com.mustabelmo.retrofit.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Splitter {
	private final List<String> splitted;
	
	public Splitter(String value, String delimiter) {
		splitted = Arrays.asList(value.split(delimiter));
	}
	
	public static Splitter of(String value, String delimiter) {
		return new Splitter(value, delimiter);
	}
	
	public Optional<String> get(int index) {
		String result = null;
		if (index >= 0 && splitted.size() > index) {
			result = splitted.get(index);
		}
		return Optional.ofNullable(result);
	}
	
	public void getAndUse(int index, Consumer<String> consumer) {
		String result = getOrDefault(index, "");
		consumer.accept(result);
	}
	
	public <T> Optional<T> get(int index, Function<String, T> mapper) {
		return get(index)
				.map(mapper);
	}
	
	public String getOrNull(int index) {
		return get(index).orElse(null);
	}
	
	public String getOrDefault(int index, String defaultValue) {
		return get(index).orElse(defaultValue);
	}
	
	public <T> T getOrDefault(int index, Function<String, T> mapper, T defaultValue) {
		return get(index)
				.map(mapper)
				.orElse(defaultValue);
	}
	
	public int size() {
		return splitted.size();
	}
}
