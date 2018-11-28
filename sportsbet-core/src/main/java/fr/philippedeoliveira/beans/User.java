package fr.philippedeoliveira.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Bean reprensenting a user of the application. This bean is used either if auth is made against LDAP or DB.
 * 
 * The 'password' field is only use if auth is made agains DB.
 * 
 * @author waddle
 * 
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username")
    private String username;
    
    @ManyToOne
    @JoinColumn(name = "buId")
    private BusinessUnit businessUnit;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "tournamentWinnerBet")
    private String tournamentWinnerBet;

    public String getTournamentWinnerBet() {
        return tournamentWinnerBet;
    }

    public void setTournamentWinnerBet(String tournamentWinnerBet) {
        this.tournamentWinnerBet = tournamentWinnerBet;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
