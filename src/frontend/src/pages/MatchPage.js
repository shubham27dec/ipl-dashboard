import { React,useEffect, useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { useParams, Link } from 'react-router-dom';

import './MatchPage.scss';
import { YearSelector } from '../components/YearSelector';

export const MatchPage = () => {
  
  const [matches, setMatches] = useState([]);
  const [team, setTeam] = useState({matches:[]});
  const {teamName, year} = useParams();
  
  useEffect(
	() => {
		const fetchMatches = async () => {
			const response1 = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}/matches?year=${year}`);
			const data1 = await response1.json();
			setMatches(data1);
		};
		fetchMatches();
	}  ,[teamName,year]
  );
  
  useEffect(
	() => {
	const fetchLatestMatches = async() => {
		const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}`);
		const data = await response.json();
		setTeam(data);
	};
	fetchLatestMatches();	
	
	} ,[teamName]
  );

  const TeamLogo = "/" + team.teamName + ".png";
  const LogoName = team.teamName + " Logo";
 
  
  return (
    <div className="MatchPage">
      <div className='year-selector'>
      	 <h1 className='select-year'>Select Year</h1>
         <YearSelector teamName={teamName} />
         <h4 className="home-page-link-section"><Link to={`/`}> Home {`>`}</Link></h4>
      </div>
      <div className='match-cards'>    
	   <h1 className='team-name'><img src={TeamLogo} width={200} height={100} alt={LogoName} /> <Link to={`/teams/${teamName}`}>{teamName}</Link> matches in {year}</h1>
	   {matches.map(match => <MatchDetailCard key={match.id} teamName={teamName} match={match} />)}	  
	  </div>
    </div>
  )
}
