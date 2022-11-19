package com.mustabelmo.qooora.client;

import com.mustabelmo.qooora.types.FullMatchList;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

public interface QoooraClient {
	@GET("/")
	@Headers("accept: application/json")
	CompletableFuture<FullMatchList> getFullMatchList(@Query("region") String region, @Query("area") String area,
													  @Query("ajax") String ajax, @Query("dd") int day,
													  @Query("mm") int month, @Query("yy") int year);
	
	@GET("/")
	@Headers("accept: application/json")
	CompletableFuture<FullMatchList> getFullMatchList(@Query("region") String region,
													  @Query("area") String area,
													  @Query("ajax") String ajax);
	
	@GET("/")
	@Headers("accept: application/json")
	CompletableFuture<FullMatchList> getSingleMatch(@Query("m") String matchId,
													@Query("ajax") int ajax);
	
}