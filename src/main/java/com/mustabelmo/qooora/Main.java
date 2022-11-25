package com.mustabelmo.qooora;

import com.mustabelmo.qooora.service.ChannelService;
import com.mustabelmo.qooora.service.MatchService;
import com.mustabelmo.qooora.types.Channel;
import com.mustabelmo.qooora.types.Match;

import java.util.Optional;

public class Main {
	public static void main(String[] args) throws Exception {
		Optional<Match> matchById = MatchService.getMatchById("2653671");
		Channel channel = matchById.map(Match::getChannels)
				.filter(channels -> !channels.isEmpty())
				.map(l -> l.get(1))
				.orElse(null);
		
		ChannelService.getSatelliteInformation(channel);
		
	}
	
	
}
