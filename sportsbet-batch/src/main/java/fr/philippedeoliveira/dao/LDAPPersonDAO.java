package fr.philippedeoliveira.dao;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;

import fr.philippedeoliveira.beans.Person;


/**
 * Data access objet of the LDAP
 * 
 * @author waddle
 *
 */
public class LDAPPersonDAO {
    private LdapTemplate ldapTemplate;

    public Person findUser(String login) {
        try {
        return (Person) getLdapTemplate().lookup("uid=" + login + ",ou=smile,ou=users", new AttributesMapper() {

            public Object mapFromAttributes(Attributes ldapAttributes) throws NamingException {
                Person person = new Person();
                person.setBuCode((String) ldapAttributes.get("primaryDepartmentNumber").get());
                person.setFullname((String) ldapAttributes.get("cn").get());
                return person;
            }
        });
        } catch (NameNotFoundException e) {
            return null;
        }
    }
    
    public LdapTemplate getLdapTemplate() {
        return ldapTemplate;
    }

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }
}
