package fr.philippedeoliveira.dao;

import java.util.List;

import fr.philippedeoliveira.beans.Bet;
import fr.philippedeoliveira.beans.NbBettersByGame;
import fr.philippedeoliveira.beans.NbExactScoreByGame;
import fr.philippedeoliveira.beans.NbGoodResultByGame;
import fr.philippedeoliveira.beans.NumberOfPeople;
import fr.philippedeoliveira.beans.PointLogin;

/**
 * Interface for any Bet DAO
 * 
 * @author waddle
 * 
 */
public interface IBetDAO extends IGenericDAO<Bet, Integer> {

    /**
     * Get all bets for a player login
     * 
     * @param login
     * @return
     */
    List<Bet> getBetsByPlayer(String login);

    /**
     * Get bets of a user when he indicated the good final game direction (1, N or 2)
     * 
     * @return
     */
    // FIXME : should return only an int
    List<PointLogin> getWinningBets();

    /**
     * Get bets of a user when he indicated the exact final score !
     * 
     * @return
     */
    // FIXME : should return only an int
    List<PointLogin> getExactScoreBets();

    /**
     * Get the number of bets done by a player on games already played (i.e. started)
     * 
     * @param login
     * @return
     */
    Integer getNumberOfBetsByPlayerOnPlayedGames(String login);

    /**
     * Returns the list of games id where there's less than 10% of betters with the excact score
     * 
     * @return
     */
    List<Integer> getBonifiedGames();

    /**
     * Returns the number of people that bet the same result as a user for each game
     * 
     * @param userLogin
     * @return
     */
    List<NumberOfPeople> getNumberOfPeopleThatBetSameResult(String userLogin);

    /**
     * Returns the number of people that bet the same score as a user for each game
     * 
     * @param userLogin
     * @return
     */
    List<NumberOfPeople> getNumberOfPeopleThatBetSameScore(String userLogin);

    /**
     * Returns the number of people that bet for each game
     * 
     * @return
     */
    List<NbBettersByGame> getNbBettersByGame();

    /**
     * Returns the number of people that bet the exact score for each game
     * 
     * @return
     */
    List<NbExactScoreByGame> getNbExactScoreByGame();

    /**
     * Returns the number of people that bet the good result for each game
     * 
     * @return
     */
    List<NbGoodResultByGame> getNbGoodResultByGame();

}
