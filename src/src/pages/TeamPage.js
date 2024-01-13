import { React,useEffect, useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { useParams, Link } from 'react-router-dom';
import { PieChart } from 'react-minimal-pie-chart';



import './TeamPage.scss';

export const TeamPage = () => {

  const [team, setTeam] = useState({matches:[]});
  
  const {teamName} = useParams();
  	
  useEffect(
	() => {
	const fetchMatches = async() => {
		const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}`);
		const data = await response.json();
		setTeam(data);
	  };
	  fetchMatches();	
	} ,[teamName]
  );
  
  const TeamLogo = "/" + team.teamName + ".png";
  const LogoName = team.teamName + " Logo";


  if(!team || !team.teamName){
	  return <h1>Team Not Found</h1>
  }
  
  return (
    <div className="TeamPage">
      <div className="team-name-section">
        <img src={TeamLogo} width={200} height={100} alt={LogoName} />
        <h1 className='team-name-heading'>{team.teamName}</h1>
        <h2>Total Matches : {team.totalMatches}</h2>
        <h2>Matches Won : {team.totalWins}</h2>
        <h2>Matches Lost : {team.totalMatches - team.totalWins}</h2>
      </div>
      <div className="win-loss-section"><h2 >Wins / Losses</h2>
      	<PieChart
		  data={[
		    { title: 'Losses', value:team.totalMatches-team.totalWins, color: '#a34d5d' },
		    { title: 'Wins ', value:team.totalWins, color: '#4da375' },
		  ]}
		/>
      </div>
      <div className="match-details-section">
        <h2 className='latest-matches'>Latest Matches</h2>
        <MatchDetailCard teamName={team.teamName} match={team.matches[0]} /> 
      </div>
      {team.matches.slice(1).map(match => <div key={match.id} className="match-small-section"><MatchSmallCard teamName={team.teamName} match={match} /></div>)}
      <div className='links-section'> 
        <h4 className="more-link-section"><Link to={`/teams/${team.teamName}/matches/${team.latestMatchYear}`}>More {`>`}</Link></h4>
        <h4 className="home-page-link-section"><Link to={`/`}> Home {`>`}</Link></h4>
      </div>
    </div>
  );
}
