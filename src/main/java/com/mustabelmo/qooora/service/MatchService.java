package com.mustabelmo.qooora.service;

import com.mustabelmo.qooora.client.QoooraClient;
import com.mustabelmo.qooora.converter.FullMatchConverter;
import com.mustabelmo.qooora.converter.MatchConverter;
import com.mustabelmo.qooora.types.Channel;
import com.mustabelmo.qooora.types.FullMatchList;
import com.mustabelmo.qooora.types.Match;
import com.mustabelmo.qooora.utils.CompletableFutureResolver;
import retrofit2.Retrofit;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
	
	public static Optional<Match> getMatchById(String matchId) {
		final QoooraClient qoooraClient = getClient();
		final FullMatchList resolved = CompletableFutureResolver.resolve(qoooraClient.getSingleMatch(matchId, 1));
		final List<Match> matches = MatchConverter.covertToMatches(resolved);
		return matches
				.stream()
				.findFirst();
	}
	
	public static List<Match> findTodayMatches() {
		return findMatches(LocalDate.now());
	}
	
	public static List<Match> findMatches(LocalDate when) {
		FullMatchList fullMatchList = getMatchList(when);
		return MatchConverter.covertToMatches(fullMatchList);
	}
	
	public static List<Match> findMatches() {
		return findMatches(null);
	}
	
	public static List<Match> findMatches(LocalDate when, String home, String away) {
		FullMatchList fullMatchList = getMatchList(when);
		List<Match> matches = MatchConverter.covertToMatches(fullMatchList);
		Predicate<Match> homeTeamHome = m -> m.getHomeTeam().isLike(home);
		Predicate<Match> homeTeamAway = m -> m.getHomeTeam().isLike(away);
		Predicate<Match> awayTeamAway = m -> m.getAwayTeam().isLike(away);
		Predicate<Match> awayTeamHome = m -> m.getAwayTeam().isLike(home);
		Predicate<Match> predicate = homeTeamHome
				.or(awayTeamAway)
				.or(homeTeamAway)
				.or(awayTeamHome);
		return matches.stream().filter(predicate)
				.collect(Collectors.toList());
	}
	
	private static FullMatchList getMatchList(LocalDate when) {
		final FullMatchList fullMatchList;
		if (when == null) { // means today
			fullMatchList = CompletableFutureResolver.resolve(getClient().getFullMatchList("-1",
					"0",
					"1"));
		} else {
			fullMatchList = CompletableFutureResolver.resolve(getClient().getFullMatchList("-1",
					"0",
					"1",
					when.getDayOfMonth(),
					when.getMonth().getValue(),
					when.getYear()));
		}
		return fullMatchList;
	}
	
	public static List<Channel> findChannels(String home, String away, LocalDate when) {
		final List<Match> matches = findMatches(when, home, away);
		return matches.stream()
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
	
}
