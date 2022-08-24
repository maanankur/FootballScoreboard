# FootballScoreboard
--------------------

FootballScoreboard is a java library implementation of the Live Football World Cup Score Board.

## Requirements
---------------
* Start a new game, assuming initial score 0 – 0 and adding it the scoreboard. This should capture following parameters:
	a. Home team
	b. Away team
* Finish game currently in progress. This removes a match from the scoreboard.
* Update score. This should receive a pair of absolute scores: home team score and away team score.
* Get a summary of games in progress ordered by their total score. The games with the same total score will be returned ordered by the most recently started match in the scoreboard. 

## Installation
---------------

* Use the maven to install [mvn](https://maven.apache.org/download.cgi) to install FootballScoreboard.

```bash
mvn clean install
```

* Consume the library generated from above step in an application

## Usage
--------

FootballScoreboardInterface<FootballMatch> service = new FootballScoreboardServiceImpl<>(); 
FootballMatch match = new FootballMatch.Builder("Mexico", "Canada");
service.startNewGame(match); // StartNewGame operation
match.setHomeTeamScore(4);
match.setAwayTeamScore(6);
service.updateScore(match); // UpdateScore operation
service.finishInProgressGame(match); // FinishGame operation
service.getSummary(); // GetSummary operation

## Test
-------

Use the maven to run the test cases :

```bash
mvn test
```

## What's Next ?
----------------

* to enhance the library we can follow some design patterns/princliples like Builder pattern implemented in the last commit
* for the equality of two matches we can use unique identifier attach to a match instead of home/away team's name
* library can be upgrade to store and list Cards, Extra time, Penalty shootout, Substitution, Points gained etc happens in the match
* to expose these functionalities as REST end-points to be consume by a front-end UI for better user experience.

## Assumptions
--------------

* to mark timestamp of a match Java Virtual Machine's high-resolution time source in nanoseconds is used assumng matches are starting in not below than nanoseconds time gap

## Dependencies
---------------
* Junit 5 related libraries.
* jdk 1.8


## Contributing
---------------

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.