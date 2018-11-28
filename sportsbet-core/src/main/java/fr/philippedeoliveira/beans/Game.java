package fr.philippedeoliveira.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bean representing a game with a receiver and a foreigner team
 * 
 * @author waddle
 *
 */
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "receiver")
    private String receiver;
    @Column(name = "foreigner")
    private String foreigner;
    @Column(name = "receiverScore")
    private Integer receiverScore;
    @Column(name = "foreignerScore")
    private Integer foreignerScore;
    @Column(name = "gameDate")
    private Date gameDate;
    @Column(name = "round")
    private String round;
    @Column(name = "roundNumber")
    private Integer roundNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
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

}
