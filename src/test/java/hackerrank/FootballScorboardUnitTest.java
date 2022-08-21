package hackerrank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import football.worldcup.eceptions.MatchAlreadyExistException;
import football.worldcup.eceptions.MatchNotExistException;
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
	
	/**
	 * This is the test case for one valid and other invalid match
	 */
	@Test
	public void _04_validInvalidMatch() {
		FootballMatch m1 = new FootballMatch("Itly", "Spain");
		FootballMatch m2 = new FootballMatch(null, "Canada");
		footballService.startNewGame(m1);
		footballService.startNewGame(m2);

		assertEquals(2, footballService.getSummary().size());
	}

	/**
	 * This is the test case for two valid matches
	 */
	@Test
	public void _05_validValidMatch() {
		FootballMatch m1 = new FootballMatch("France", "Germany");
		FootballMatch m2 = new FootballMatch("England", "Portugal");
		footballService.startNewGame(m1);
		footballService.startNewGame(m2);

		assertEquals(4, footballService.getSummary().size());
	}
	
	/**
	 * This is the test case to validate the initial score of a existing match
	 */
	@Test
	public void _06_validateInitialScore() {
		assertEquals((Integer)0, footballService.getSummary().get(0).getHomeTeamScore());
		assertEquals((Integer)0, footballService.getSummary().get(0).getAwayTeamScore());
	}
	
	/**
	 * This is the test case to update valid score for a valid match 
	 */
	@Test
	public void _07_updateValidMatch() {
		FootballMatch m1 = new FootballMatch("Belgium", "Austrailia");
		footballService.startNewGame(m1);
		assertEquals(5, footballService.getSummary().size());
		FootballMatch m2 = new FootballMatch("Belgium", "Austrailia", 6 , 7);
		footballService.updateScore(m2);
		assertEquals((Integer)6, footballService.getSummary().get(0).getHomeTeamScore());
		assertEquals((Integer)7, footballService.getSummary().get(0).getAwayTeamScore());
	}
	
	/**
	 * This is the test case to validate update scores for a invalid match 
	 */
	@Test
	public void _08_updateInvalidMatch() {
		FootballMatch match = new FootballMatch("Wales", "Denmark");
		Exception exception = assertThrows(MatchNotExistException.class, () -> {
			footballService.updateScore(match);
		});

		String expectedMessage = match.getHomeTeam() + " VS " + match.getAwayTeam() + " match not exist.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));
	}
	
}
