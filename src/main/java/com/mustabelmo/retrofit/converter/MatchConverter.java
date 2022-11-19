package com.mustabelmo.retrofit.converter;

import com.mustabelmo.retrofit.types.Channel;
import com.mustabelmo.retrofit.types.Commentator;
import com.mustabelmo.retrofit.types.EventType;
import com.mustabelmo.retrofit.types.FullMatchList;
import com.mustabelmo.retrofit.types.Match;
import com.mustabelmo.retrofit.types.MatchEvent;
import com.mustabelmo.retrofit.types.Player;
import com.mustabelmo.retrofit.types.Stadium;
import com.mustabelmo.retrofit.types.Team;
import com.mustabelmo.retrofit.utils.Splitter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class MatchConverter {
	public static List<Match> covertToMatches(FullMatchList fullMatchList) {
		List<Match> matches = new ArrayList<>();
		String[] matchesList = fullMatchList.getMatchesList();
		
		for (int i = 0; i < matchesList.length - 19; i += 20) {
			Match match = new Match();
			match.setId(matchesList[i + 3]);
			String time = matchesList[4];
			long timeInSecondes = Long.parseLong(time);
			match.setTime(LocalDateTime.ofEpochSecond(timeInSecondes,0, ZoneOffset.UTC));
			match.setCategoryId(matchesList[i]);
			Team home = new Team();
			home.setName(matchesList[i + 9]);
			match.setHomeTeamEvent(getEvents(matchesList[i + 10]));
			Splitter splitter = Splitter.of(matchesList[i + 11], "\\|");
			splitter.getAndUse(0, match::setHomeGoals);
			splitter.getAndUse(1, possibleAwayGoals -> {
				int indexOfTild = possibleAwayGoals.indexOf('~');
				if (indexOfTild > 0) {
					match.setAwayGoals(possibleAwayGoals.substring(0,indexOfTild));
					String penaltiesResult = possibleAwayGoals.substring(indexOfTild+1);
					penaltiesResult = penaltiesResult.replaceAll("&nbsp;","");
					Splitter penoSplitter = Splitter.of(penaltiesResult, ":");
					penoSplitter.getAndUse(0, match::setHomePenaltyGoals);
					penoSplitter.getAndUse(1, match::setAwayPenaltyGoals);
				} else {
					match.setAwayGoals(possibleAwayGoals);
				}
				
			});
			
			match.setAwayTeamEvent(getEvents(matchesList[i + 15]));
			match.setHomeTeam(home);
			Team away = new Team();
			away.setName(matchesList[i + 14]);
			match.setAwayTeam(away);
			match.setStadium(getStadium(matchesList[i + 19]));
			match.setChannels(getChannels(matchesList[i + 19]));
			matches.add(match);
		}
		
		return matches;
	}
	
	private static List<Channel> getChannels(String channelsInfo) {
		Splitter splitter = Splitter.of(channelsInfo, "~l\\|");
		List<Channel> channels = new ArrayList<>();
		for (int i = 1; i < splitter.size(); i += 1) {
			Channel channel = new Channel();
			String subInfoSplitter = splitter.getOrDefault(i, "");
			Splitter subInfo = Splitter.of(subInfoSplitter, "\\|");
			Commentator commentator = new Commentator();
			subInfo.getAndUse(0, channel::setId);
			subInfo.getAndUse(1, channel::setName);
			subInfo.getAndUse(2, commentator::setId);
			subInfo.getAndUse(3, commentator::setName);
			channel.setCommentator(commentator);
			channels.add(channel);
		}
		return channels;
	}
	
	private static Stadium getStadium(String stadiumInfo) {
		Stadium stadium = new Stadium();
		Splitter info = Splitter.of(stadiumInfo, "~a\\|");
		String temp = info.getOrDefault(1,"");
		Splitter split = Splitter.of(temp,"\\|");
		stadium.setId(split.getOrNull(0));
		stadium.setCountryId(split.getOrNull(1));
		stadium.setName(split.getOrNull(2));
		return stadium;
	}
	
	private static List<MatchEvent> getEvents(String matchesList) {
		List<MatchEvent> list = new ArrayList<>();
		Splitter split = Splitter.of(matchesList, "\\|");
		for (int i = 0; i < split.size() - 3; i += 4) {
			MatchEvent event = new MatchEvent();
			EventType eventType = EventType.fromValue(split.getOrNull(i));
			String minute = split.getOrNull(i + 1);
			Player player = new Player();
			player.setId(split.getOrNull(i + 2));
			player.setName(split.getOrNull(i + 3));
			event.setMinute(minute);
			event.setType(eventType);
			event.setPlayer(player);
			list.add(event);
		}
		return list;
	}
}
