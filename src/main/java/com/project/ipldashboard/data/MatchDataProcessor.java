package com.project.ipldashboard.data;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import com.project.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match>{

	
	@Override
	public Match process(final MatchInput matchInput) throws Exception {
		
		Match match = new Match();
		match.setId(Long.valueOf(matchInput.getId()));
		match.setCity(matchInput.getCity());
		match.setDate(LocalDate.parse(matchInput.getDate()));
		match.setPlayerOfMatch(matchInput.getPlayer_of_match());
		match.setVenue(matchInput.getVenue());
		String teamBattingFirst, teamBattingSecond;
		if(matchInput.getToss_decision().equals("bat")) {
			teamBattingFirst = matchInput.getToss_winner();
			teamBattingSecond = matchInput.getToss_winner().equals(matchInput.getTeam1())?matchInput.getTeam2():matchInput.getTeam1();
		}
		else {
			teamBattingSecond = matchInput.getToss_winner();
			teamBattingFirst = matchInput.getToss_winner().equals(matchInput.getTeam1())?matchInput.getTeam2():matchInput.getTeam1();
		}
		match.setTeam1(teamBattingFirst);
		match.setTeam2(teamBattingSecond);
		match.setTossWinner(matchInput.getToss_winner());
		match.setTossDecision(matchInput.getToss_decision());
		match.setMatchWinner(matchInput.getWinner());
		match.setResult(matchInput.getResult());
		match.setResultMargin(matchInput.getResult_margin());
		match.setUmpire1(matchInput.getUmpire1());
		match.setUmpire2(matchInput.getUmpire2());
		return match;
	}

}
