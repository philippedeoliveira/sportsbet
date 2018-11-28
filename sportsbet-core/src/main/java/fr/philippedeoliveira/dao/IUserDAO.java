package fr.philippedeoliveira.dao;

import java.util.List;

import fr.philippedeoliveira.beans.Game;
import fr.philippedeoliveira.beans.User;

/**
 * Interface for any User DAO
 * 
 * @author waddle
 *
 */
public interface IUserDAO extends IGenericDAO<User, String> {

    /**
     * Return a list of all users from the BU given as argument
     * 
     * @param bu
     * @return
     */
    List<User> getUsersOfBU(Integer buId);

    /**
     * Returns the games where the user got the excact score
     * 
     * @param username
     * @return
     */
    List<Game> getExactScoreGameBetByUser(String username);

    /**
     * Returns true if a user has found the winner of the tournament
     * 
     * @param username
     * @return
     */
    boolean hasFoundWinner(String username);
}
