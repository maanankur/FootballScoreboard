package hackerrank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import football.worldcup.eceptions.InvalidScoreException;
import football.worldcup.eceptions.MatchAlreadyExistException;
import football.worldcup.eceptions.MatchNotExistException;
import football.worldcup.model.FootballMatch;
import football.worldcup.service.FootballScoreboardServiceImpl;

/**
 * This is the Junit class for football score-board service 
 * @author ankur.maan
 */
@TestMethodOrder(MethodOrderer.DisplayName.class)
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
		FootballMatch m1 = new FootballMatch.Builder("", "Canada").build();
		FootballMatch m2 = new FootballMatch.Builder(null, "Canada").build();
		footballService.startNewGame(m1);
		footballService.startNewGame(m2);
		assertEquals(0, footballService.getSummary().size());
	}
	
	/**
	 * This is the test case for a match with valid team names
	 */
	@Test
	public void _02_validMatch() {
		FootballMatch m1 = new FootballMatch.Builder("Mexico", "Canada").build();
		footballService.startNewGame(m1);

		assertEquals(1, footballService.getSummary().size());
	}
	
	/**
	 * This is the test case for a match with duplicate home and away team name   
	 */
	@Test
	public void _03_duplicateMatch() {
		FootballMatch match = new FootballMatch.Builder("Mexico", "Canada").build();
		
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
		FootballMatch m1 = new FootballMatch.Builder("Itly", "Spain").build();
		FootballMatch m2 = new FootballMatch.Builder(null, "Canada").build();
		footballService.startNewGame(m1);
		footballService.startNewGame(m2);

		assertEquals(2, footballService.getSummary().size());
	}

	/**
	 * This is the test case for two valid matches
	 */
	@Test
	public void _05_validValidMatch() {
		FootballMatch m1 = new FootballMatch.Builder("France", "Germany").build();
		FootballMatch m2 = new FootballMatch.Builder("England", "Portugal").build();
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
		FootballMatch m1 = new FootballMatch.Builder("Belgium", "Austrailia").build();
		footballService.startNewGame(m1);
		assertEquals(5, footballService.getSummary().size());
		FootballMatch m2 = new FootballMatch.Builder("Belgium", "Austrailia").setHomeTeamScore(6).setAwayTeamScore(7).build();
		footballService.updateScore(m2);
		assertEquals((Integer)6, footballService.getSummary().get(0).getHomeTeamScore());
		assertEquals((Integer)7, footballService.getSummary().get(0).getAwayTeamScore());
	}
	
	/**
	 * This is the test case to validate update scores for a invalid match 
	 */
	@Test
	public void _08_updateInvalidMatch() {
		FootballMatch match = new FootballMatch.Builder("Wales", "Denmark").build();
		Exception exception = assertThrows(MatchNotExistException.class, () -> {
			footballService.updateScore(match);
		});

		String expectedMessage = match.getHomeTeam() + " VS " + match.getAwayTeam() + " match not exist.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));
	}
	
	/**
	 * This is the test case to update the invalid scores for a valid match.
	 */
	@Test
	public void _09_updateInvalidScores() {
		String expectedMessage = "Invalid Scores to update";
		FootballMatch m1 = new FootballMatch.Builder("Mexico", "Canada").setHomeTeamScore(null).setAwayTeamScore(5).build();
		Exception exception = assertThrows(InvalidScoreException.class, () -> {
			footballService.updateScore(m1);
		});

		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));
		
		FootballMatch m2 = new FootballMatch.Builder("Mexico", "Canada").setHomeTeamScore(-2).setAwayTeamScore(5).build();
		exception = assertThrows(InvalidScoreException.class, () -> {
			footballService.updateScore(m2);
		});

		actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));
	}
	
	/**
	 * This is the test case to update the non ascending scores for a valid match.  
	 */
	@Test
	public void _10_updateNonAscendingScores() {
		String expectedMessage = "Scores can not be decrease";
		FootballMatch m1 = new FootballMatch.Builder("Belgium", "Austrailia").setHomeTeamScore(4).setAwayTeamScore(5).build();
		Exception exception = assertThrows(InvalidScoreException.class, () -> {
			footballService.updateScore(m1);
		});

		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));
	}
	
	/**
	 * This is the test case to finish a in progress match.
	 */
	@Test
	public void _11_finishMatch() {
		FootballMatch m1 = new FootballMatch.Builder("Uruguay", "Brazil").build();
		footballService.startNewGame(m1);
		assertEquals(6, footballService.getSummary().size());
		footballService.finishInProgressGame(m1);
		assertEquals(5, footballService.getSummary().size());
	}

	/**
	 * This is the test case to finish a in valid match 
	 */
	@Test
	public void _12_finishInvalidMatch() {
		FootballMatch match = new FootballMatch.Builder("India", "Pakistan").build();
		Exception exception = assertThrows(MatchNotExistException.class, () -> {
			footballService.finishInProgressGame(match);
		});

		String expectedMessage = match.getHomeTeam() + " VS " + match.getAwayTeam() + " match not exist.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.equals(expectedMessage));
	}
	
	/**
	 * This is the test case to validate the desired order  
	 */
	@Test
	public void _13_validateOrder() {
		List<FootballMatch> matches = footballService.getSummary();
		FootballMatch match = matches.get(0);
		assertTrue(match.getHomeTeam().equals("Belgium"));
		assertTrue(match.getAwayTeam().equals("Austrailia"));
		assertEquals(13, match.getHomeTeamScore() + match.getAwayTeamScore());
		
		match = matches.get(matches.size() - 1);
		assertTrue(match.getHomeTeam().equals("Mexico"));
		assertTrue(match.getAwayTeam().equals("Canada"));
		assertEquals(0, match.getHomeTeamScore() + match.getAwayTeamScore());
	}
}
