package football.worldcup.model;

import java.sql.Timestamp;
import java.util.Date;



/**
 * The FootballMatch is model class for a football match
 * @author ankur.maan
 */
public class FootballMatch implements Comparable{
	private String homeTeam;
	private String awayTeam;
	private Integer homeTeamScore;
	private Integer awayTeamScore;
	private Timestamp startTime;
	
	/**
	 * Parameterized footballmatch constructor
	 * @param homeTeam name of the home team
	 * @param awayTeam name of the away team
	 */
	public FootballMatch(String homeTeam, String awayTeam) {
		Date date = new Date(System.nanoTime());
        Timestamp ts=new Timestamp(date.getTime());  
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamScore = 0;
		this.awayTeamScore = 0;
		this.startTime = ts;
	}
	
	/**
	 * Parameterized footballmatch constructor with home and away team all information
	 * @param homeTeam
	 * @param awayTeam
	 * @param homeTeamScore
	 * @param awayTeamScore
	 */
	public FootballMatch(String homeTeam, String awayTeam, Integer homeTeamScore, Integer awayTeamScore) {
		Date date = new Date(System.nanoTime());
        Timestamp ts=new Timestamp(date.getTime());  
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
		this.startTime = ts;
	}

	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}
	public Integer getHomeTeamScore() {
		return homeTeamScore;
	}
	public void setHomeTeamScore(Integer homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}
	public Integer getAwayTeamScore() {
		return awayTeamScore;
	}
	public void setAwayTeamScore(Integer awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getHomeTeam() + " " + getHomeTeamScore() + " - " + getAwayTeam() + " " + getAwayTeamScore();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FootballMatch other = (FootballMatch) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equalsIgnoreCase(other.awayTeam))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equalsIgnoreCase(other.homeTeam))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object obj) {
		int sumCurrent = getHomeTeamScore() + getAwayTeamScore();
		int sumPassed = ((FootballMatch) obj).getHomeTeamScore() + ((FootballMatch) obj).getAwayTeamScore();
		if(sumCurrent > sumPassed)  
			return -1;  
		else if(sumCurrent < sumPassed)  
			return 1;  
		else
			return -getStartTime().compareTo(((FootballMatch) obj).getStartTime()); 
	}
}
