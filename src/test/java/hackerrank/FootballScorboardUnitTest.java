package hackerrank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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
		assertEquals(2, footballService.getSummary().size());
	}

}
