package fr.philippedeoliveira.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fr.philippedeoliveira.beans.Bet;
import fr.philippedeoliveira.beans.NbBettersByGame;
import fr.philippedeoliveira.beans.NbExactScoreByGame;
import fr.philippedeoliveira.beans.NbGoodResultByGame;
import fr.philippedeoliveira.beans.NumberOfPeople;
import fr.philippedeoliveira.beans.PointLogin;
import fr.philippedeoliveira.dao.IBetDAO;

/**
 * DAO for Bet bean using Hibernate
 * 
 * @author waddle
 * 
 */
@Repository
public class HibernateBetDAO extends GenericHibernateDAO<Bet, Integer> implements IBetDAO {

    public HibernateBetDAO() {
        super(Bet.class);
    }

    @Override
    public List<Bet> getBetsByPlayer(String login) {
        return getCurrentSession().getNamedQuery("bet.getBetsByPlayer").setParameter("login", login).list();
    }

    @Override
    public List<PointLogin> getExactScoreBets() {
        return getCurrentSession().getNamedQuery("bet.getExactScoreBets").list();
    }

    @Override
    public List<PointLogin> getWinningBets() {
        return getCurrentSession().getNamedQuery("bet.getWinningBets").list();
    }

    @Override
    public Integer getNumberOfBetsByPlayerOnPlayedGames(String login) {
        return ((Long) getCurrentSession().getNamedQuery("bet.getNumberOfBetsByPlayerOnPlayedGames")
                .setParameter("login", login).uniqueResult()).intValue();
    }

    @Override
    public List<Integer> getBonifiedGames() {
        List<Integer> bonifiedGamesId = new ArrayList<>();
        List<NbBettersByGame> nbBettersByGame = getNbBettersByGame();
        List<NbExactScoreByGame> nbExactScoreByGame = getNbExactScoreByGame();

        for (NbBettersByGame nbBetter : nbBettersByGame) {
            for (NbExactScoreByGame nbExactScore : nbExactScoreByGame) {
                if (nbBetter.getGameId().equals(nbExactScore.getGameId())) {
                    if (nbExactScore.getNbExactScore() != 0
                            && nbExactScore.getNbExactScore().floatValue() / nbBetter.getNbBetters().floatValue() < 0.1f) {
                        bonifiedGamesId.add(nbBetter.getGameId());
                    }
                }
            }
        }
        return bonifiedGamesId;
    }

    @Override
    public List<NumberOfPeople> getNumberOfPeopleThatBetSameResult(String userLogin) {
        return getCurrentSession().getNamedQuery("bet.getNumberOfPeopleThatBetSameResult")
                .setParameter("login", userLogin).list();
    }

    @Override
    public List<NumberOfPeople> getNumberOfPeopleThatBetSameScore(String userLogin) {
        return getCurrentSession().getNamedQuery("bet.getNumberOfPeopleThatBetSameScore")
                .setParameter("login", userLogin).list();
    }

    @Override
    public List<NbBettersByGame> getNbBettersByGame() {
        return getCurrentSession().getNamedQuery("bet.getNbBettersByGame").list();
    }

    @Override
    public List<NbExactScoreByGame> getNbExactScoreByGame() {
        return getCurrentSession().getNamedQuery("bet.nbExactScoreByGame").list();
    }

    @Override
    public List<NbGoodResultByGame> getNbGoodResultByGame() {
        return getCurrentSession().getNamedQuery("bet.nbGoodResultByGame").list();
    }
}
