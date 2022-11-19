package com.mustabelmo.qooora.utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureResolver {
	public static <T> T resolve(CompletableFuture<T> completableFuture){
		try {
			return completableFuture.get();
		} catch (ExecutionException | InterruptedException interruptedException){
			throw new RuntimeException(interruptedException);
		}
	}
}
