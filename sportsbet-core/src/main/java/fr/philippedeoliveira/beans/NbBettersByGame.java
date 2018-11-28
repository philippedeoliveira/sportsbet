package fr.philippedeoliveira.beans;

public class NbBettersByGame {
    private Integer gameId;
    private Long nbBetters;

    public NbBettersByGame(Integer gameId, long nbBetters) {
        this.gameId = gameId;
        this.nbBetters = nbBetters;
    }

    public Long getNbBetters() {
        return nbBetters;
    }

    public void setNbBetters(Long nbBetters) {
        this.nbBetters = nbBetters;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}
