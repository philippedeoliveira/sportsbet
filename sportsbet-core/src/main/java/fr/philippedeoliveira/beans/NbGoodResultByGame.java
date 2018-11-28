package fr.philippedeoliveira.beans;

public class NbGoodResultByGame {
    private Integer gameId;
    private Long nbExactScores;

    public NbGoodResultByGame(Integer gameId, long nbExactScore) {
        this.gameId = gameId;
        this.nbExactScores = nbExactScore;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Long getNbExactScore() {
        return nbExactScores;
    }

    public void setNbExactScore(Long nbExactScore) {
        this.nbExactScores = nbExactScore;
    }
}
