package football.worldcup.service;

import java.util.List;

import football.worldcup.model.FootballMatch;


/**
 * This is the interface for football scoreboard service
 * @author ankur.maan
 */
public interface FootballScoreboardInterface<T> {

	/**
     * @param match a match to be added into in-memory collection.
    */
    public void startNewGame(final T match);

    /**
     * @param match a currently in progress match to update the score.
    */
    public void updateScore(final T match);
    
    /**
	 * @return summary of matches in desired order.
	 */
    public List<T> getSummary();
}
