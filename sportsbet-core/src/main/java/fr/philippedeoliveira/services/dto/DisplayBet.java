package fr.philippedeoliveira.services.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Bean representing a bet line on the bets page
 * 
 * @author waddle
 * 
 */
public class DisplayBet {
    /** receiver team name */
    private String receiver;
    /** foreigner team name */
    private String foreigner;
    /** receiver team score */
    private Integer receiverScore;
    /** foreigner team score */
    private Integer foreignerScore;
    /** game start date */
    private Date gameLimitBetDate;
    /** true is bet is closed on this game */
    private Boolean betClosed;
    /** id of the game concerned by this bet */
    private Integer gameId;
    /** round name of the game */
    private String round;
    /** round number of the game */
    private Integer roundNumber;
    /** result of the game for the receiving team */
    private Integer receiverScoreResult;
    /** result of the game for the foreigner team */
    private Integer foreignerScoreResult;
    /** percentage of people that bet same score */
    private BigDecimal percentageThatBetSameScore = BigDecimal.ZERO;
    /** percentage of people that bet same result */
    private BigDecimal percentageThatBetSameResult = BigDecimal.ZERO;
    /** percentage of people that bet same score */
    private BigDecimal percentageThatBetGoodScore = BigDecimal.ZERO;
    /** percentage of people that bet same result */
    private BigDecimal percentageThatBetGoodResult = BigDecimal.ZERO;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getForeigner() {
        return foreigner;
    }

    public void setForeigner(String foreigner) {
        this.foreigner = foreigner;
    }

    public Integer getReceiverScore() {
        return receiverScore;
    }

    public void setReceiverScore(Integer receiverScore) {
        this.receiverScore = receiverScore;
    }

    public Integer getForeignerScore() {
        return foreignerScore;
    }

    public void setForeignerScore(Integer foreignerScore) {
        this.foreignerScore = foreignerScore;
    }

    public Date getGameLimitBetDate() {
        return gameLimitBetDate;
    }

    public void setGameLimitBetDate(Date gameDate) {
        this.gameLimitBetDate = gameDate;
    }

    public Boolean getBetClosed() {
        return betClosed;
    }

    public void setBetClosed(Boolean betClosed) {
        this.betClosed = betClosed;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Integer getReceiverScoreResult() {
        return receiverScoreResult;
    }

    public void setReceiverScoreResult(Integer receiverScoreResult) {
        this.receiverScoreResult = receiverScoreResult;
    }

    public Integer getForeignerScoreResult() {
        return foreignerScoreResult;
    }

    public void setForeignerScoreResult(Integer foreignerScoreResult) {
        this.foreignerScoreResult = foreignerScoreResult;
    }

    public BigDecimal getPercentageThatBetSameScore() {
        return percentageThatBetSameScore;
    }

    public void setPercentageThatBetSameScore(BigDecimal percentageThatBetSameScore) {
        this.percentageThatBetSameScore = percentageThatBetSameScore;
    }

    public BigDecimal getPercentageThatBetSameResult() {
        return percentageThatBetSameResult;
    }

    public void setPercentageThatBetSameResult(BigDecimal percentageThatBetSameResult) {
        this.percentageThatBetSameResult = percentageThatBetSameResult;
    }

    public BigDecimal getPercentageThatBetGoodScore() {
        return percentageThatBetGoodScore;
    }

    public void setPercentageThatBetGoodScore(BigDecimal percentageThatBetGoodScore) {
        this.percentageThatBetGoodScore = percentageThatBetGoodScore;
    }

    public BigDecimal getPercentageThatBetGoodResult() {
        return percentageThatBetGoodResult;
    }

    public void setPercentageThatBetGoodResult(BigDecimal percentageThatBetGoodResult) {
        this.percentageThatBetGoodResult = percentageThatBetGoodResult;
    }

}
