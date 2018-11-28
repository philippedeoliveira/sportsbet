package fr.philippedeoliveira.services;

import java.util.Date;
import java.util.List;

import fr.philippedeoliveira.beans.Bet;
import fr.philippedeoliveira.beans.Game;
import fr.philippedeoliveira.beans.PercentageOfPeople;
import fr.philippedeoliveira.services.dto.RankingLine;

/**
 * Services available for Bet
 * 
 * @author waddle
 * 
 */
public interface IBetService {

    /**
     * Returns the bets made by a player sorted by round
     * 
     * @param login
     * @return
     */
    List<Bet> getPlayerBets(String login);

    /**
     * 
     * Field <i>nbMinutesMaxBeforeGame</i> defines the number of minutes before the game limiting the bet.
     * 
     * So if the game is at 8:00 pm and <i>nbMinutesMaxBeforeGame</i> value is 120, then this methods returns true after
     * 6:00 pm
     * 
     * @param gameDate
     * @return
     */
    Boolean isBetClosed(Date gameDate);

    /**
     * Tests is a bet is closed on a specific game
     * 
     * @param gameId
     */
    Boolean isBetClosed(Integer gameId);

    /**
     * Saves a bet, update it if it already exists
     * 
     * @param gameId
     * @param integer
     * @param integer2
     */
    void saveOrUpdate(String login, Integer gameId, Integer integer, Integer integer2);

    /**
     * Return the ranking table
     * 
     * @return
     */
    List<RankingLine> getRanking();

    /**
     * Returns the ranking for the user based on given BU
     * 
     * @param login
     * 
     * @return
     */
    List<RankingLine> getBuRanking(Integer buId);

    /**
     * Returns the limit date for betting on this game
     * 
     * @param game
     * @return
     */
    Date getGameLimitBetDate(Game game);

    /**
     * Returns the percentage of people that bet the same score as a user for each game
     * 
     * @param userLogin
     * 
     * @return
     */
    List<PercentageOfPeople> getPercentageOfPeopleThatBetSameScore(String userLogin);

    /**
     * Returns the percentage of people that bet the same result as a user for each game
     * 
     * @param userLogin
     * 
     * @return
     */
    List<PercentageOfPeople> getPercentageOfPeopleThatBetSameResult(String userLogin);

    /**
     * Returns the percentage of people that bet the good score for each game
     * 
     * @param userLogin
     * 
     * @return
     */
    List<PercentageOfPeople> getPercentageOfPeopleThatBetGoodScore();

    /**
     * Returns the percentage of people that bet the good result for each game
     * 
     * @param userLogin
     * 
     * @return
     */
    List<PercentageOfPeople> getPercentageOfPeopleThatBetGoodResult();
}
