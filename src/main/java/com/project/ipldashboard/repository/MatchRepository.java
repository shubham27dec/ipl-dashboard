package com.project.ipldashboard.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

	List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2,Pageable pageable);
	
	List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String teamName, LocalDate startDate1, LocalDate endDate1, String teamName2, LocalDate startDate2, LocalDate endDate2);
	
	default List<Match> findLatestMatchesByTeamFromYear(String teamName, int year){
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year+1, 1, 1);
		return getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName, startDate, endDate, teamName, startDate, endDate);
	}

	default List<Match> findLatestMatchesByTeam(String teamName, int count){
		return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
	}
	
	@Query("select date from Match m where (m.team1 = :teamName or m.team2 = :teamName) ")
    List<LocalDate> findAllMatchDatesForTeam(@Param("teamName") String teamName);
	
	default List<Integer> findAllMatchYearsForTeam(String teamName){
		Set<Integer> matchYears = new TreeSet<>();
		List<Integer> matchYearsList = new ArrayList<>();
		List<LocalDate> matchDates = findAllMatchDatesForTeam(teamName);
		for(LocalDate matchDate : matchDates) {
			matchYears.add(matchDate.getYear());
		}
		for(int matchYear : matchYears) {
			matchYearsList.add(matchYear);
		}
		return matchYearsList;
	}
}
