package football.worldcup.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		return matches;
	}

	@Override
	public void startNewGame(T match) {
		String homeTeam = match.getHomeTeam();
    	String awayTeam = match.getAwayTeam();
    	matches.add(match);
	}

}