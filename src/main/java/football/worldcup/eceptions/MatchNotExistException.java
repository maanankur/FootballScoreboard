package football.worldcup.eceptions;

import java.sql.Timestamp;
import java.util.Date;

/**
 * This is the model class for MatchNotExistException
 * @author ankur.maan
 */
public class MatchNotExistException extends RuntimeException {
	
	/**
	 * This is the constructor for MatchNotExistException
	 * @param errorMessage
	 */
    public MatchNotExistException(String errorMessage) {
        super(errorMessage);
    }
}