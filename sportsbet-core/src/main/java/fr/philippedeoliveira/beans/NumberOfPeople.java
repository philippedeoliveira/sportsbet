package fr.philippedeoliveira.beans;

public class NumberOfPeople {
    private Integer gameId;
    private Long number;

    public NumberOfPeople(Integer gameId, Long number) {
        this.gameId = gameId;
        this.number = number;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

}
