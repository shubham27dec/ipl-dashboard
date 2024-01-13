package com.project.ipldashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ipldashboard.model.Match;
import com.project.ipldashboard.model.Team;
import com.project.ipldashboard.repository.MatchRepository;
import com.project.ipldashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@GetMapping("/team")
	public Iterable<Team> getAllTeams() {
		Iterable<Team> teams = teamRepository.findAll();
		for(Team team : teams) {
			team.setMatches(matchRepository.findLatestMatchesByTeam(team.getTeamName(), 4));
			team.setLatestMatchYear();
			team.setMatchYears(matchRepository.findAllMatchYearsForTeam(team.getTeamName()));
		}
		return teams;
	}
	
	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = teamRepository.findByTeamName(teamName);
		team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));
		team.setLatestMatchYear();
		team.setMatchYears(matchRepository.findAllMatchYearsForTeam(teamName));
		return team;
	}
	
	@GetMapping("/team/{teamName}/matches")
	public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
		return matchRepository.findLatestMatchesByTeamFromYear(teamName, year);
	}
}
