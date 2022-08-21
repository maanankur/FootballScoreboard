package football.worldcup.eceptions;

/**
 * This is the model class for MatchAlreadyExistException
 * @author ankur.maan
 */
public class MatchAlreadyExistException extends RuntimeException {
	
	/**
	 * This is the constructor for MatchAlreadyExistException
	 * @param errorMessage
	 */
	public MatchAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
