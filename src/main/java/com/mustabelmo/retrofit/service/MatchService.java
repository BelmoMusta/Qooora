package com.mustabelmo.retrofit.service;

import com.mustabelmo.retrofit.client.QoooraClient;
import com.mustabelmo.retrofit.converter.FullMatchConverter;
import com.mustabelmo.retrofit.converter.MatchConverter;
import com.mustabelmo.retrofit.types.Channel;
import com.mustabelmo.retrofit.types.FullMatchList;
import com.mustabelmo.retrofit.types.Match;
import com.mustabelmo.retrofit.utils.CompletableFutureResolver;
import retrofit2.Retrofit;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MatchService {
	
	public static final String URL = "https://www.goalzz.com";
	public static QoooraClient CLIENT = null;
	
	private static QoooraClient getClient() {
		if (CLIENT == null) {
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(URL)
					.addConverterFactory(new FullMatchConverter())
					.build();
			CLIENT = retrofit.create(QoooraClient.class);
		}
		return CLIENT;
	}
	
	public static Match getMatchById(String matchId) {
		QoooraClient qoooraClient = getClient();
		CompletableFuture<FullMatchList> singleMatchNot = qoooraClient.getSingleMatchNot(matchId, 1);
		FullMatchList resolved = CompletableFutureResolver.resolve(singleMatchNot);
		List<Match> matches = MatchConverter.covertToMatches(resolved);
		if (!matches.isEmpty()) {
			return matches.get(0);
		}
		return null;
	}
	
	public static List<Match> findMatches(LocalDate when) {
		final CompletableFuture<FullMatchList> fullMatchList;
		if (when == null) { // means today
			fullMatchList = getClient().getFullMatchList("-1",
					"0",
					"1");
		} else {
			fullMatchList = getClient().getFullMatchList("-1",
					"0",
					"1",
					when.getDayOfMonth(),
					when.getMonth().getValue(),
					when.getYear());
		}
		FullMatchList resolve = CompletableFutureResolver.resolve(fullMatchList);
		return MatchConverter.covertToMatches(resolve);
	}
	
	public static List<Channel> findChannels(String home, String away, LocalDate when) {
		List<Match> matches = findMatches(when);
		Predicate<Match> homeTeamHome = m -> m.getHomeTeam().isLike(home);
		Predicate<Match> homeTeamAway = m -> m.getHomeTeam().isLike(away);
		Predicate<Match> awayTeamAway = m -> m.getAwayTeam().isLike(away);
		Predicate<Match> awayTeamHome = m -> m.getAwayTeam().isLike(home);
		
		Predicate<Match> predicate = homeTeamHome
				.or(awayTeamAway)
				.or(homeTeamAway)
				.or(awayTeamHome);
		return matches.stream()
				.filter(predicate)
				.flatMap((Match match) -> match.getChannels().stream())
				.collect(Collectors.toList());
		
	}
	
	public static List<Channel> findChannels(String team, LocalDate when) {
		return findChannels(team, team, when);
	}
	
	public static List<Channel> findChannels(String team) {
		return findChannels(team, team, null);
	}
	
	public static List<Channel> findChannels(String home, String away) {
		return findChannels(home, away, null);
	}
	
	public static void main(String[] args) {
		Match matchById = getMatchById("2766009");
		System.out.println(matchById);
		LocalDate when = LocalDate.of(2022, 11, 23);
		List<Channel> morocco = findChannels("morocco", "cro", when);
		System.out.println(morocco);
	}
}
