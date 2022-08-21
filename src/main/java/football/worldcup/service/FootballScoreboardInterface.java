package football.worldcup.service;

import java.util.List;


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
	 * @return summary of matches in desired order.
	 */
    public List<T> getSummary();
}
