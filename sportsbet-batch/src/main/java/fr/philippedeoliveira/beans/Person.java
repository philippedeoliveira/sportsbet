package fr.philippedeoliveira.beans;


/**
 * Bean used for binding data comming from ldap
 * 
 * @author waddle
 *
 */
public class Person {

    private String buCode;

    private String fullname;

    public String getBuCode() {
        return buCode;
    }

    public void setBuCode(String buCode) {
        this.buCode = buCode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
