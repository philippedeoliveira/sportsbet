package fr.philippedeoliveira.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Bean representing a user bet
 * 
 * @author waddle
 *
 */
@Entity
@Table(name = "userbets")
public class Bet implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "better")
    private User better;
    @Id
    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @Column(name = "receiverScore")
    private Integer receiverScore;
    @Column(name = "foreignerScore")
    private Integer foreignerScore;

    public User getBetter() {
        return better;
    }

    public void setBetter(User better) {
        this.better = better;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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

}
