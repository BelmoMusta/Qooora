package com.mustabelmo.qooora.service;

import com.mustabelmo.qooora.types.Channel;
import com.mustabelmo.qooora.types.Frequency;
import com.mustabelmo.qooora.types.Polarization;
import com.mustabelmo.qooora.types.Satellite;
import com.mustabelmo.qooora.types.Transponder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChannelService {
	public static void getSatelliteInformation(Channel channel) {
		if (channel == null) {
			return;
		}
		
		Elements select;
		try {
			Document document = Jsoup.connect("https://www.goalzz.com/?channel=" + channel.getId()).get();
			select = document.select("table tr td[class^=s_]").not("td[class^=s_h]");
		} catch (IOException exception) {
			select = new Elements();
		}
		
		List<Transponder> transponders = new ArrayList<>();
		
		for (int i = 0; i < select.size() - 6; i++) {
			if (i % 7 == 0) {
				Transponder transponder = new Transponder();
				Satellite satellite = new Satellite();
				satellite.setName(select.get(i).text());
				satellite.setPosition(select.get(i + 1).text());
				transponder.setSatellite(satellite);
				Frequency frequency = new Frequency();
				frequency.setValue(Integer.parseInt(select.get(i + 2).text()));
				frequency.setPolarization(Polarization.from(select.get(i + 3).text()));
				frequency.setSymbolRate(Integer.parseInt(select.get(i + 4).text()));
				frequency.setFec(select.get(i + 5).text());
				frequency.setEncryption(select.get(i + 6).text());
				transponder.setFrequency(frequency);
				transponders.add(transponder);
			}
		}
		channel.setTransponders(transponders);
	}
}
