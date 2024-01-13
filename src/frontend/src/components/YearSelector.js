import { React,useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

import './YearSelector.scss';

export const YearSelector = ({teamName}) => {
	
	const [team, setTeam] = useState({matches:[],matchYears:[]});
	
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
	
	return (
	  team.matchYears.map(year => (<div className='year' key={year}> <Link to={`/teams/${teamName}/matches/${year}`}><h2 className='year'>{year}</h2></Link></div>))
   )
}