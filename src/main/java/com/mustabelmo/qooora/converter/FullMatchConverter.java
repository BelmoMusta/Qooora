package com.mustabelmo.qooora.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class FullMatchConverter extends Converter.Factory {
	ObjectMapper mapper = new ObjectMapper();
	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		JavaType javaType = this.mapper.getTypeFactory().constructType(type);
		ObjectReader reader = this.mapper.readerFor(javaType);
		return new JacksonResponseBodyConverter<>(reader);
	}
}
