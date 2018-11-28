package fr.philippedeoliveira.services;

import java.util.List;

import fr.philippedeoliveira.beans.Game;

/**
 * Services available for Game
 * 
 * @author waddle
 *
 */
public interface IGameService {
    /**
     * Return all games sorted by 1) round desc 2) date asc
     * 
     * @return
     */
    List<Game> getAllGames();

    /**
     * Return the list of all teams
     * 
     * @return
     */
    List<String> getAllTeams();
}
