package fr.philippedeoliveira.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.philippedeoliveira.beans.Bet;
import fr.philippedeoliveira.beans.Game;
import fr.philippedeoliveira.beans.PercentageOfPeople;
import fr.philippedeoliveira.beans.User;
import fr.philippedeoliveira.services.IBetService;
import fr.philippedeoliveira.services.IGameService;
import fr.philippedeoliveira.services.IUserService;
import fr.philippedeoliveira.services.dto.DisplayBet;

/**
 * Struts action for manipulating bets
 * 
 * @author waddle
 * 
 */
@Component("betAction")
@Scope("prototype")
public class BetAction extends AbstractSportsbetAction {

    @Inject
    private IBetService betService;
    @Inject
    private IGameService gameService;
    @Inject
    private IUserService userService;

    /**
     * Use to display user bets
     */
    private List<DisplayBet> bets = new ArrayList<>();

    /**
     * HTTP parameter representing receiver scores
     */
    private Map<Integer, Integer> receiverScores = new HashMap<>();
    /**
     * HTTP parameter representing foreigner scores
     */
    private Map<Integer, Integer> foreignerScores = new HashMap<>();

    /**
     * List of all teams use to choose the tournament winner
     */
    private List<String> teams = new ArrayList<>();

    /**
     * Tournament winner choosen by the user
     */
    private String tournamentWinnerBet = "";

    /**
     * Lists all bets for the current user
     * 
     * @return
     * @throws Exception
     */
    public String listPlayerBets() throws Exception {
        this.teams = gameService.getAllTeams();
        this.tournamentWinnerBet = userService.getById(getUserLogin()).getTournamentWinnerBet();
        List<Game> games = gameService.getAllGames();
        List<Bet> userBets = betService.getPlayerBets(getUserLogin());
        List<PercentageOfPeople> betsSameScore = betService.getPercentageOfPeopleThatBetSameScore(getUserLogin());
        List<PercentageOfPeople> betsSameResult = betService.getPercentageOfPeopleThatBetSameResult(getUserLogin());
        List<PercentageOfPeople> betsGoodScore = betService.getPercentageOfPeopleThatBetGoodScore();
        List<PercentageOfPeople> betsGoodResult = betService.getPercentageOfPeopleThatBetGoodResult();

        for (Game game : games) {
            DisplayBet displayBet = new DisplayBet();
            displayBet.setReceiver(game.getReceiver());
            displayBet.setForeigner(game.getForeigner());
            displayBet.setGameLimitBetDate(betService.getGameLimitBetDate(game));
            displayBet.setGameId(game.getId());
            displayBet.setReceiverScoreResult(game.getReceiverScore());
            displayBet.setForeignerScoreResult(game.getForeignerScore());
            displayBet.setRound(game.getRound());
            displayBet.setRoundNumber(game.getRoundNumber());
            displayBet.setBetClosed(betService.isBetClosed(game.getGameDate()));
            for (Bet bet : userBets) {
                if (bet.getGame().getId().equals(game.getId())) {
                    displayBet.setReceiverScore(bet.getReceiverScore());
                    displayBet.setForeignerScore(bet.getForeignerScore());
                }
            }
            for (PercentageOfPeople percentage : betsSameScore) {
                if (percentage.getGameId().equals(game.getId())) {
                    displayBet.setPercentageThatBetSameScore(percentage.getPercentage());
                }
            }
            for (PercentageOfPeople percentage : betsSameResult) {
                if (percentage.getGameId().equals(game.getId())) {
                    displayBet.setPercentageThatBetSameResult(percentage.getPercentage());
                }
            }
            for (PercentageOfPeople percentage : betsGoodScore) {
                if (percentage.getGameId().equals(game.getId())) {
                    displayBet.setPercentageThatBetGoodScore(percentage.getPercentage());
                }
            }
            for (PercentageOfPeople percentage : betsGoodResult) {
                if (percentage.getGameId().equals(game.getId())) {
                    displayBet.setPercentageThatBetGoodResult(percentage.getPercentage());
                }
            }
            bets.add(displayBet);
        }
        return SUCCESS;
    }

    /**
     * Saves all user bets
     * 
     * @return
     * @throws Exception
     */
    public String bet() throws Exception {
        for (Integer gameId : receiverScores.keySet()) {
            if (!betService.isBetClosed(gameId)
                    && receiverScores.get(gameId) != null
                    && foreignerScores.get(gameId) != null) {
                betService.saveOrUpdate(
                        getUserLogin(),
                        gameId,
                        receiverScores.get(gameId),
                        foreignerScores.get(gameId));
            }
        }
        if (!this.getTournamentWinnerBetClosed()) {
            User u = userService.getById(getUserLogin());
            u.setTournamentWinnerBet(tournamentWinnerBet);
            userService.saveUser(u);
        }
        return SUCCESS;
    }

    /**
     * True is the bet on the tournament winner is closed
     * 
     * @return
     * @throws ParseException
     */
    public Boolean getTournamentWinnerBetClosed() throws ParseException {
        return new Date().after(new SimpleDateFormat("HH:mm dd/MM/yyyy").parse("17:00 14/06/2018"));
    }

    /*
     * Accessors
     */

    public void setBetService(IBetService betService) {
        this.betService = betService;
    }

    public List<DisplayBet> getBets() {
        return bets;
    }

    public void setBets(List<DisplayBet> bets) {
        this.bets = bets;
    }

    public void setGameService(IGameService gameService) {
        this.gameService = gameService;
    }

    public Map<Integer, Integer> getReceiverScores() {
        return receiverScores;
    }

    public void setReceiverScores(Map<Integer, Integer> submitedBets) {
        this.receiverScores = submitedBets;
    }

    public Map<Integer, Integer> getForeignerScores() {
        return foreignerScores;
    }

    public void setForeignerScores(Map<Integer, Integer> foreignerScores) {
        this.foreignerScores = foreignerScores;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }

    public String getTournamentWinnerBet() {
        return tournamentWinnerBet;
    }

    public void setTournamentWinnerBet(String tournamentWinnerBet) {
        this.tournamentWinnerBet = tournamentWinnerBet;
    }
}
