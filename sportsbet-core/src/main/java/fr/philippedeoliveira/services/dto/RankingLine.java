package fr.philippedeoliveira.services.dto;

/**
 * Bean reprensenting a line in the ranking table
 * 
 * @author waddle
 *
 */
public class RankingLine implements Comparable<RankingLine> {
	/** login of the user */
    private String login;
    /** User full name */
    private String fullName;
    /** number of points of the user */
    private Long points;
    /** number of played games (game must have start) */
    private Integer played;
    /** number of exact scores bet by the player */
    private Integer exactScores;
    /** number of good results bet by the player (1, N or 2) */
    private Integer goodResults;
    /** bad results bet by the player */
    private Integer badResults;
    /** Number of exact score with less than 10% of betters that bet it */
    private Integer bonifiedExactScores;

    @Override
    public int compareTo(RankingLine o) {
        if (o.getPoints() == points) {
            if (o.getBonifiedExactScores() == bonifiedExactScores) {
                if (o.getExactScores() == exactScores) {
                    return goodResults.compareTo(o.getGoodResults());
                } else {
                    return exactScores.compareTo(o.getExactScores());
                }
            } else {
                return bonifiedExactScores.compareTo(o.getBonifiedExactScores());
            }
        } else {
            return points.compareTo(o.getPoints());
        }
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((points == null) ? 0 : points.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RankingLine other = (RankingLine) obj;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        return true;
    }

    public Integer getPlayed() {
        return played;
    }

    public void setPlayed(Integer played) {
        this.played = played;
    }

    public Integer getExactScores() {
        return exactScores;
    }

    public void setExactScores(Integer exactScores) {
        this.exactScores = exactScores;
    }

    public Integer getGoodResults() {
        return goodResults;
    }

    public void setGoodResults(Integer goodResults) {
        this.goodResults = goodResults;
    }

    public Integer getBadResults() {
        return badResults;
    }

    public void setBadResults(Integer badResults) {
        this.badResults = badResults;
    }

    public Integer getBonifiedExactScores() {
        return bonifiedExactScores;
    }

    public void setBonifiedExactScores(Integer bonifiedExactScores) {
        this.bonifiedExactScores = bonifiedExactScores;
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
