package fr.philippedeoliveira.dao;

import java.util.List;

import fr.philippedeoliveira.beans.Game;

/**
 * Interface for any Game DAO
 * 
 * @author waddle
 *
 */
public interface IGameDAO extends IGenericDAO<Game, Integer> {

	/**
	 * Get all games and sort them by round than date
	 * 
	 * @return
	 */
    List<Game> findGamesAndSort();

    /**
     * Select all teams and sort them by alphabetic order
     * 
     * @return
     */
    List<String> findAllTeams();
}
