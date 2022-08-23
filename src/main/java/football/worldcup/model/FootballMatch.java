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
	public FootballMatch(Builder builder) {
		Date date = new Date(System.nanoTime());
        Timestamp ts=new Timestamp(date.getTime());  
		this.homeTeam = builder.homeTeam;
		this.awayTeam = builder.awayTeam;
		this.homeTeamScore = builder.homeTeamScore;
		this.awayTeamScore = builder.awayTeamScore;
		this.startTime = ts;
	}

	public String getHomeTeam() {
		return homeTeam;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public Integer getHomeTeamScore() {
		return homeTeamScore;
	}
	public Integer getAwayTeamScore() {
		return awayTeamScore;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	 // Setter methods
    public void setHomeTeam(String homeTeam)
    {
        this.homeTeam = homeTeam;
    }
    public void setAwayTeam(String awayTeam)
    {
        this.awayTeam = awayTeam;
    }
    public void setHomeTeamScore(Integer homeTeamScore)
    {
        this.homeTeamScore = homeTeamScore;
    }
    public void setAwayTeamScore(Integer awayTeamScore)
    {
        this.awayTeamScore = awayTeamScore;
    }

	public static class Builder {
		  
        /// instance fields
		private String homeTeam;
		private String awayTeam;
		private Integer homeTeamScore;
		private Integer awayTeamScore;
		private Timestamp startTime;
		
        public Builder(String homeTeam, String awayTeam) {
        	this.homeTeam = homeTeam;
    		this.awayTeam = awayTeam;
    		this.homeTeamScore = 0;
    		this.awayTeamScore = 0;
        }
  
        // Setter methods
        public Builder setHomeTeam(String homeTeam)
        {
            this.homeTeam = homeTeam;
            return this;
        }
        public Builder setAwayTeam(String awayTeam)
        {
            this.awayTeam = awayTeam;
            return this;
        }
        public Builder setHomeTeamScore(Integer homeTeamScore)
        {
            this.homeTeamScore = homeTeamScore;
            return this;
        }
        public Builder setAwayTeamScore(Integer awayTeamScore)
        {
            this.awayTeamScore = awayTeamScore;
            return this;
        }
  
        // build method to deal with outer class
        // to return outer instance
        public FootballMatch build()
        {
            return new FootballMatch(this);
        }
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
