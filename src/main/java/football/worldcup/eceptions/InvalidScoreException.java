package football.worldcup.eceptions;

import java.sql.Timestamp;
import java.util.Date;

/**
 * This is the model class for InvalidScoreException
 * @author ankur.maan
 */
public class InvalidScoreException extends RuntimeException {
	
	/**
	 * This is the constructor for InvalidScoreException
	 * @param errorMessage
	 */
    public InvalidScoreException(String errorMessage) {
        super(errorMessage);
    }
}