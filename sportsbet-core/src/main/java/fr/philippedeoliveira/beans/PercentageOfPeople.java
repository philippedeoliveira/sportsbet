package fr.philippedeoliveira.beans;

import java.math.BigDecimal;

public class PercentageOfPeople {
    private Integer gameId;
    private BigDecimal percentage;

    public PercentageOfPeople(Integer gameId, BigDecimal percentage) {
        this.gameId = gameId;
        this.percentage = percentage;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

}
