package hackerrank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import football.worldcup.FootballMatch;
import football.worldcup.service.FootballScoreboardServiceImpl;

/**
 * This is the Junit class for football score-board service 
 * @author ankur.maan
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class FootballScorboardUnitTest {
	private static FootballScoreboardServiceImpl footballService;

	@BeforeAll
	public static void instantiate() {
		footballService = new FootballScoreboardServiceImpl();
	}

	/**
	 * This is the test case for a match with invalid team name
	 */
	@Test
	public void _01_invalidMatch() {
		footballService.startNewGame(null);
		footballService.startNewGame(null);
		assertEquals(0, footballService.getSummary().size());
	}

}
