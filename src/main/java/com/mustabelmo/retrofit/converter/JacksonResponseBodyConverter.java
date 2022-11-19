package com.mustabelmo.retrofit.converter;

import com.fasterxml.jackson.databind.ObjectReader;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.io.Reader;

final class JacksonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
	private final ObjectReader adapter;
	
	JacksonResponseBodyConverter(ObjectReader adapter) {
		this.adapter = adapter;
	}
	
	public T convert(ResponseBody value) throws IOException {
		T var2;
		try {
			Reader src = value.charStream();
			var2 = this.adapter.readValue(src);
		} finally {
			value.close();
		}
		return var2;
	}
}
