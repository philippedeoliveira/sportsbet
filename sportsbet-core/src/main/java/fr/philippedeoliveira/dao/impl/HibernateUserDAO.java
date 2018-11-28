package fr.philippedeoliveira.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.philippedeoliveira.beans.Game;
import fr.philippedeoliveira.beans.User;
import fr.philippedeoliveira.dao.IUserDAO;

/**
 * DAO for User bean using Hibernate
 * 
 * @author waddle
 *
 */
@Repository
public class HibernateUserDAO extends GenericHibernateDAO<User, String> implements IUserDAO {

    public HibernateUserDAO() {
        super(User.class);
    }

    @Override
    public List<User> getUsersOfBU(Integer buId) {
        return getCurrentSession().getNamedQuery("user.getUsersOfBu").setParameter("buId", buId).list();
    }

    @Override
    public List<Game> getExactScoreGameBetByUser(String username) {
        return getCurrentSession().getNamedQuery("user.getExactScoreGameBetByUser").setParameter("username", username)
                .list();
    }

    @Override
    public boolean hasFoundWinner(String username) {
        return getCurrentSession().getNamedQuery("user.hasFoundWinner").setParameter("username", username).list()
                .size() > 0;
    }
}
