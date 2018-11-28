package fr.philippedeoliveira.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.philippedeoliveira.beans.Bet;
import fr.philippedeoliveira.beans.Game;
import fr.philippedeoliveira.beans.NbBettersByGame;
import fr.philippedeoliveira.beans.NbExactScoreByGame;
import fr.philippedeoliveira.beans.NbGoodResultByGame;
import fr.philippedeoliveira.beans.NumberOfPeople;
import fr.philippedeoliveira.beans.PercentageOfPeople;
import fr.philippedeoliveira.beans.PointLogin;
import fr.philippedeoliveira.beans.User;
import fr.philippedeoliveira.dao.IBetDAO;
import fr.philippedeoliveira.dao.IGameDAO;
import fr.philippedeoliveira.dao.IUserDAO;
import fr.philippedeoliveira.services.IBetService;
import fr.philippedeoliveira.services.dto.RankingLine;

@Service
public class BetServiceImpl implements IBetService {

    private static final int PERCENTAGE_PRECISON = 0;

    private static final BigDecimal HUNDRED = new BigDecimal(100);

    /**
     * Defines the number of points won for an exact score
     */
    @Value("#{appProperties.pointPerExactScore}")
    private Integer pointPerExactScore;

    /**
     * Defines the number of points won for a good result
     */
    @Value("#{appProperties.pointPerGoodResult}")
    private Integer pointPerGoodResult;

    /**
     * Defines the number of minutes before the game limiting the bet.
     */
    @Value("#{appProperties.nbMinutesMaxBeforeGame}")
    private Integer nbMinutesMaxBeforeGame;

    /**
     * Defines the number of bonification points when the user got the excact score and there's under 10% of betters
     * that got it
     */
    @Value("#{appProperties.bonificationOnExactScoreUnder10Percent}")
    private Long bonificationOnExactScoreUnder10Percent;

    @Value("#{appProperties.tournamentWinnerBonus}")
    private Long tournamentWinnerBonus;

    @Inject
    private IBetDAO betDao;

    @Inject
    private IGameDAO gameDAO;

    @Inject
    private IUserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public List<RankingLine> getRanking() {
        return getRankingOfUsers(userDAO.findAll());
    }

    @Override
    public Date getGameLimitBetDate(Game game) {
        Calendar gameDateCalendar = Calendar.getInstance();
        gameDateCalendar.setTime(game.getGameDate());
        gameDateCalendar.add(Calendar.MINUTE, -(this.nbMinutesMaxBeforeGame));
        return gameDateCalendar.getTime();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RankingLine> getBuRanking(Integer bu) {
        return getRankingOfUsers(userDAO.getUsersOfBU(bu));
    }

    private List<RankingLine> getRankingOfUsers(List<User> users) {
        List<PointLogin> winningBetPointsLogin = betDao.getWinningBets();
        List<PointLogin> exactScoreBetPointsLogin = betDao.getExactScoreBets();
        List<Integer> bonifiedGamesId = betDao.getBonifiedGames();
        List<RankingLine> rankingLines = new ArrayList<>();

        for (User user : users) {
            RankingLine rankingLine = new RankingLine();
            rankingLine.setLogin(user.getUsername());
            rankingLine.setFullName(user.getFullName());
            rankingLine.setExactScores(0);
            rankingLine.setGoodResults(0);
            rankingLine.setBonifiedExactScores(0);
            rankingLine.setPlayed(betDao.getNumberOfBetsByPlayerOnPlayedGames(user.getUsername()));
            Long points = 0L;
            for (PointLogin winning : winningBetPointsLogin) {
                if (winning.getLogin().equals(user.getUsername())) {
                    points += (winning.getGoodBets() * pointPerGoodResult);
                    rankingLine.setGoodResults(winning.getGoodBets().intValue());
                    break;
                }
            }
            for (PointLogin winning : exactScoreBetPointsLogin) {
                if (winning.getLogin().equals(user.getUsername())) {
                    points += (winning.getGoodBets() * pointPerExactScore);
                    rankingLine.setExactScores(winning.getGoodBets().intValue());
                    break;
                }
            }
            List<Game> exactScoreGameBetByUser = userDAO.getExactScoreGameBetByUser(user.getUsername());
            for (Game exactScoreGame : exactScoreGameBetByUser) {
                for (Integer bonifiedGame : bonifiedGamesId) {
                    if (exactScoreGame.getId().equals(bonifiedGame)) {
                        points += bonificationOnExactScoreUnder10Percent;
                        rankingLine.setBonifiedExactScores(rankingLine.getBonifiedExactScores() + 1);
                        rankingLine.setExactScores(rankingLine.getExactScores() - 1);
                    }
                }
            }

            if (userDAO.hasFoundWinner(user.getUsername())) {
                points += tournamentWinnerBonus;
            }

            rankingLine.setPoints(points);
            rankingLine.setBadResults(rankingLine.getPlayed() - rankingLine.getExactScores()
                    - rankingLine.getGoodResults() - rankingLine.getBonifiedExactScores());
            rankingLines.add(rankingLine);
        }
        Collections.sort(rankingLines, Collections.reverseOrder());
        return rankingLines;
    }

    @Override
    @Transactional
    public void saveOrUpdate(String login, Integer gameId, Integer receiverScore, Integer foreignerScore) {
        Bet bet = new Bet();
        bet.setBetter(userDAO.getById(login));
        bet.setGame(gameDAO.getById(gameId));
        bet.setReceiverScore(receiverScore);
        bet.setForeignerScore(foreignerScore);
        betDao.makePersistent(bet);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bet> getPlayerBets(String login) {
        return betDao.getBetsByPlayer(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isBetClosed(Integer gameId) {
        return isBetClosed(gameDAO.getById(gameId).getGameDate());
    }

    @Override
    public Boolean isBetClosed(Date gameDate) {
        Calendar gameDateCalendar = Calendar.getInstance();
        gameDateCalendar.setTime(gameDate);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, this.nbMinutesMaxBeforeGame);
        return calendar.after(gameDateCalendar);
    }

    // FIXME: we shouldn't use List of Object and "instanceof". Perhaps we should create an Interface? Or merge these
    // objects (as they all are beans with a bet id and a Long number)?
    private List<PercentageOfPeople> toPercentages(List<? extends Object> numberOfPeopleThatBetSame) {
        List<NbBettersByGame> nbBettersByGames = betDao.getNbBettersByGame();

        List<PercentageOfPeople> result = new ArrayList<PercentageOfPeople>();

        for (Object same : numberOfPeopleThatBetSame) {
            Integer gameId = null;
            Long number = null;
            if (same instanceof NumberOfPeople) {
                gameId = ((NumberOfPeople) same).getGameId();
                number = ((NumberOfPeople) same).getNumber();
            } else if (same instanceof NbExactScoreByGame) {
                gameId = ((NbExactScoreByGame) same).getGameId();
                number = ((NbExactScoreByGame) same).getNbExactScore();
            } else if (same instanceof NbGoodResultByGame) {
                gameId = ((NbGoodResultByGame) same).getGameId();
                number = ((NbGoodResultByGame) same).getNbExactScore();
            } else {
                throw new RuntimeException("Unmanaged type");
            }
            for (NbBettersByGame nbBetters : nbBettersByGames) {
                if (gameId.equals(nbBetters.getGameId())) {
                    BigDecimal percentage = new BigDecimal(number).multiply(HUNDRED).divide(
                            new BigDecimal(nbBetters.getNbBetters()), PERCENTAGE_PRECISON, RoundingMode.HALF_UP);
                    result.add(new PercentageOfPeople(gameId, percentage));
                }
            }
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PercentageOfPeople> getPercentageOfPeopleThatBetSameScore(String userLogin) {
        List<NumberOfPeople> numberOfPeopleThatBetSameScore = betDao.getNumberOfPeopleThatBetSameScore(userLogin);
        return toPercentages(numberOfPeopleThatBetSameScore);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PercentageOfPeople> getPercentageOfPeopleThatBetSameResult(String userLogin) {
        List<NumberOfPeople> numberOfPeopleThatBetSameResult = betDao.getNumberOfPeopleThatBetSameResult(userLogin);
        return toPercentages(numberOfPeopleThatBetSameResult);
    }

    @Override
    public List<PercentageOfPeople> getPercentageOfPeopleThatBetGoodScore() {
        List<NbExactScoreByGame> nbExactScoreByGames = betDao.getNbExactScoreByGame();
        return toPercentages(nbExactScoreByGames);
    }

    @Override
    public List<PercentageOfPeople> getPercentageOfPeopleThatBetGoodResult() {
        List<NbGoodResultByGame> nbGoodResultByGames = betDao.getNbGoodResultByGame();
        return toPercentages(nbGoodResultByGames);
    }

    public void setBetDao(IBetDAO betDao) {
        this.betDao = betDao;
    }

    public Integer getNbMinutesMaxBeforeGame() {
        return nbMinutesMaxBeforeGame;
    }

    public void setNbMinutesMaxBeforeGame(Integer nbMinutesMaxBeforeGame) {
        this.nbMinutesMaxBeforeGame = nbMinutesMaxBeforeGame;
    }

    public IGameDAO getGameDAO() {
        return gameDAO;
    }

    public void setGameDAO(IGameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Integer getPointPerExactScore() {
        return pointPerExactScore;
    }

    public void setPointPerExactScore(Integer pointPerExactScore) {
        this.pointPerExactScore = pointPerExactScore;
    }

    public Integer getPointPerGoodResult() {
        return pointPerGoodResult;
    }

    public void setPointPerGoodResult(Integer pointPerGoodResult) {
        this.pointPerGoodResult = pointPerGoodResult;
    }

}
