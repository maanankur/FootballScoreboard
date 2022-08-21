package football.worldcup.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import football.worldcup.eceptions.InvalidScoreException;
import football.worldcup.eceptions.MatchAlreadyExistException;
import football.worldcup.eceptions.MatchNotExistException;
import football.worldcup.model.FootballMatch;

/**
 * This is the service implementation for football scoreboard
 * @author ankur.maan
 */
public class FootballScoreboardServiceImpl<T extends FootballMatch> implements FootballScoreboardInterface<T> {
    
	private final List<T> matches;

    /**
     * Initialize a new list of Football matches.
     */
    public FootballScoreboardServiceImpl() {
    	matches = new ArrayList<T>();
    }

	@Override
	public List<T> getSummary() {
		Collections.sort(matches);
		return matches;
	}

	@Override
	public void startNewGame(T match) {
		String homeTeam = match.getHomeTeam();
    	String awayTeam = match.getAwayTeam();
    	if(homeTeam == null || awayTeam == null || match.getHomeTeam().isBlank() || match.getAwayTeam().isBlank()) {
    		return;
    	}
    	if(matches.contains(match)) {
    		throw new MatchAlreadyExistException(match.getHomeTeam() + " VS " + match.getAwayTeam() + " match already exist.");
    	}
    	matches.add(match);
	}

	@Override
	public void updateScore(T match) {
		if(!matches.contains(match)) {
    		throw new MatchNotExistException(match.getHomeTeam() + " VS " + match.getAwayTeam() + " match not exist.");
    	}else {
    		int index =  matches.indexOf(match);
    		FootballMatch existMatch = matches.get(index);
    		Integer homeTeamScore = match.getHomeTeamScore();
    		Integer awayTeamScore = match.getAwayTeamScore();
    		if (homeTeamScore == null || awayTeamScore == null || homeTeamScore < 0 || awayTeamScore < 0){
    			throw new InvalidScoreException("Invalid Scores to update");
        	}
    		if (homeTeamScore < existMatch.getHomeTeamScore() || awayTeamScore < existMatch.getAwayTeamScore()){
    			throw new InvalidScoreException("Scores can not be decrease");
        	}
    		existMatch.setHomeTeamScore(match.getHomeTeamScore());
    		existMatch.setAwayTeamScore(match.getAwayTeamScore());
        }
	}

	@Override
	public void finishInProgressGame(FootballMatch match) {
		if(!matches.contains(match)) {
    		throw new MatchNotExistException(match.getHomeTeam() + " VS " + match.getAwayTeam() + " match not exist.");
    	}
		matches.remove(match);
	}

}