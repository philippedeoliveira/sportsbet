package fr.philippedeoliveira.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.philippedeoliveira.beans.Game;
import fr.philippedeoliveira.dao.IGameDAO;

/**
 * DAO for Game bean using Hibernate
 * 
 * @author waddle
 *
 */
@Repository
public class HibernateGameDAO extends GenericHibernateDAO<Game, Integer> implements IGameDAO {

    public HibernateGameDAO() {
        super(Game.class);
    }

    @Override
    public List<Game> findGamesAndSort() {
        return getCurrentSession().getNamedQuery("game.findGamesAndSort").list();
    }

    @Override
    public List<String> findAllTeams() {
        return getCurrentSession().getNamedQuery("game.findAllTeams").list();
    }
}
