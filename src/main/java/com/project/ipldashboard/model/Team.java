package com.project.ipldashboard.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String teamName;
	private long totalMatches;
	private long totalWins;
	private int latestMatchYear;
	
	@Transient
	List<Match> matches;
	
	@Transient
	List<Integer> matchYears;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public long getTotalMatches() {
		return totalMatches;
	}
	public void setTotalMatches(long totalMatches) {
		this.totalMatches = totalMatches;
	}
	public long getTotalWins() {
		return totalWins;
	}
	public void setTotalWins(long totalWins) {
		this.totalWins = totalWins;
	}
	
	public Team(String teamName, long totalMatches) {
		super();
		this.teamName = teamName;
		this.totalMatches = totalMatches;
	}
	public Team() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", totalMatches=" + totalMatches + ", totalWins=" + totalWins + "]";
	}
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	public int getLatestMatchYear() {
		return latestMatchYear;
	}
	public void setLatestMatchYear() {
		if(matches.size()>0) {
			latestMatchYear = Integer.valueOf(matches.get(0).getDate().getYear());
		}
	}
	public List<Integer> getMatchYears() {
		return matchYears;
	}
	public void setMatchYears(List<Integer> matchYears) {
		this.matchYears = matchYears;
	}
	
	
}
