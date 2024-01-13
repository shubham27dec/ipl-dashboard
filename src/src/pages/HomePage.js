import { React,useEffect, useState } from 'react';

import { Link } from 'react-router-dom';

import './HomePage.scss';

export const HomePage = () => {

  const [teams, setTeams] = useState([]);
  	
  useEffect(
	() => {
	const fetchTeams = async() => {
		const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team`);
		const data = await response.json();
		setTeams(data);
	};
	fetchTeams();	
	} ,[]
  );
  
  if(teams.length===0){
	  return <h1>Teams Not Found</h1>;
  }
  
  return (
    <div className="HomePage">
      <div className='header-section'>
        <img src="/IPL.png" alt="ipl-logo" />
      </div>
      <div className='all-teams-section'>
        {teams.map((team) => {
            const TeamLogo = "/" + team.teamName + ".png";
            const LogoName = team.teamName + " Logo";
            return(<div key={team.id} className="individual-team-section">
            <img src={TeamLogo} width={200} height={100} alt={LogoName} /> 
            <h1 className='team-name-section'>
              <Link to={`/teams/${team.teamName}`}>{team.teamName}</Link>
            </h1>
          </div>)
          }
         )
        }
      </div>
    </div>
  );
}
