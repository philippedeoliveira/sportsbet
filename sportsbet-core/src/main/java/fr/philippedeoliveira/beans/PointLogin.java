package fr.philippedeoliveira.beans;

/**
 * Use by hibernate queries to return the score (in good bet number) of a user
 * 
 * @author phdeo
 * 
 */
public class PointLogin {
    private Long goodBets;
    private String login;

    public PointLogin(String login, long points) {
        this.login = login;
        this.goodBets = points;
    }

    public Long getGoodBets() {
        return goodBets;
    }

    public void setGoodBets(Long goodBets) {
        this.goodBets = goodBets;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
