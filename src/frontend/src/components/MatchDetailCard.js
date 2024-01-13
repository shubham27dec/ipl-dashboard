import { React } from 'react';
import { Link } from 'react-router-dom';

import "./MatchDetailCard.scss";

export const MatchDetailCard = ({teamName, match}) => {
  if (!match){
    return null;
  }
  
  const otherTeam = match.team1===teamName ? match.team2:match.team1; 
  const otherTeamRoute = `/teams/${otherTeam}`;
  const isMatchWon = match.matchWinner===teamName;
  
  return (
    <div className={isMatchWon? 'MatchDetailCard won-card' : 'MatchDetailCard lost-card'}>
      <div className='match-details'>
        <h1 className='other-team'>vs <Link to={otherTeamRoute}> {otherTeam} </Link></h1>
        <h2 className="match-date">{match.date}</h2>
        <h2 className="match-venue">{match.venue}, {match.city}.</h2>
        <h2 className='toss-result'>{match.tossWinner} won the toss and decided to {match.tossDecision} first.</h2>
        <h2 className='match-result'>{match.matchWinner} won by {match.resultMargin} {match.result}.</h2>
      </div>
      <div className='additional-details'>
      	<div className='first-innings'>
      	  <h2>First Innings</h2>
      	  <h3>{match.team1}</h3></div>
      	<div className='second-innings'>
      	  <h2>Second Innings</h2>
      	  <h3>{match.team2}</h3></div>
      	<div className='player-of-match'>
      	  <h2>Man of the Match</h2>
      	  <h3>{match.playerOfMatch}</h3></div>
      	<div className='umpires'>
      	  <h2>Umpires</h2>
      	  <h3>{match.umpire1}, {match.umpire2}</h3></div>
      </div>
    </div>
  );
}
