package hackerrank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import football.worldcup.eceptions.MatchAlreadyExistException;
import football.worldcup.model.FootballMatch;
import football.worldcup.service.FootballScoreboardServiceImpl;

/**
 * This is the Junit class for football score-board service 
 * @author ankur.maan
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class FootballScorboardUnitTest {
	private static FootballScoreboardServiceImpl<FootballMatch> footballService;

	@BeforeAll
	public static void instantiate() {
		footballService = new FootballScoreboardServiceImpl();
	}

	/**
	 * This is the test case for a match with invalid team name
	 */
	@Test
	public void _01_invalidMatch() {
		FootballMatch m1 = new FootballMatch("", "Canada");
		FootballMatch m2 = new FootballMatch(null, "Canada");
		footballService.startNewGame(m1);
		footballService.startNewGame(m2);
		assertEquals(0, footballService.getSummary().size());
	}
	
	/**
	 * This is the test case for a match with valid team names
	 */
	@Test
	public void _02_validMatch() {
		FootballMatch m1 = new FootballMatch("Mexico", "Canada");
		footballService.startNewGame(m1);

		assertEquals(1, footballService.getSummary().size());
	}
	
	/**
	 * This is the test case for a match with duplicate home and away team name   
	 */
	@Test
	public void _03_duplicateMatch() {
		FootballMatch match = new FootballMatch("Mexico", "Canada");
		
		Exception exception = assertThrows(MatchAlreadyExistException.class, () -> {
			footballService.startNewGame(match);
		});

		String expectedMessage = match.getHomeTeam() + " VS " + match.getAwayTeam() + " match already exist.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));
	}

}
