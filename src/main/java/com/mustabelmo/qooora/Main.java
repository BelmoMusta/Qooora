package com.mustabelmo.qooora;

import com.mustabelmo.qooora.service.MatchService;
import com.mustabelmo.qooora.types.Channel;
import com.mustabelmo.qooora.types.Match;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
	public static void main(String[] args) throws Exception {
		Optional<Match> matchById = MatchService.getMatchById("2949494");
		List<Match> todayMatches = MatchService.findTodayMatches();
		System.out.println(todayMatches);
		System.out.println(matchById.orElse(null));
		LocalDate when = LocalDate.of(2022, 11, 23);
		List<Channel> channels = MatchService.findChannels("morocco", "croatia", when);
		List<Match> matches = MatchService.findMatches();
		System.out.println(channels);
	}
	
	
}
