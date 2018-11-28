package fr.philippedeoliveira;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import fr.philippedeoliveira.annotations.Log;
import fr.philippedeoliveira.beans.BusinessUnit;
import fr.philippedeoliveira.beans.Person;
import fr.philippedeoliveira.beans.User;
import fr.philippedeoliveira.dao.LDAPPersonDAO;
import fr.philippedeoliveira.services.IBUService;
import fr.philippedeoliveira.services.IUserService;

/**
 * This batch will read the ldap searching for the BU_CODE and set it into the
 * DB.
 * 
 */
// FIXME : this code should be used when a user is registrating
public class App {
    @Inject
    private LDAPPersonDAO personDAO;
    @Inject
    private IUserService userService;
    @Inject
    private IBUService buService;
    @Log
    private Logger logger;

    /**
     * Launch the batch
     */
    public void run() {
        for (User user : userService.getAllUsers()) {
            Person p = personDAO.findUser(user.getUsername());
            if (p != null) {
                if (p.getBuCode() != null) {
                    BusinessUnit bu = buService.findBuByCode(p.getBuCode());
                    if (bu != null) {
                        user.setBusinessUnit(bu);
                        user.setFullName(p.getFullname());
                        userService.saveUser(user);
                        logger.info("Saving user " + user.getUsername() + "(" + user.getFullName() + ") from BU "
                                + user.getBusinessUnit().getId());
                    } else {
                        logger.warn("User " + user.getUsername() + " cannot be saved cause the BU " + p.getBuCode()
                                + " does not exist !");
                    }
                } else {
                    logger.error("No BU code for the user " + user.getUsername());
                }
            } else {
                logger.info("User " + user.getUsername() + " not found in LDAP");
            }
        }
    }

    /**
     * Entry point. Just initialize the container and get this class bean to run
     * the batch.
     * 
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        context.getBean(App.class).run();
    }

    public LDAPPersonDAO getPersonDAO() {
        return personDAO;
    }

    public void setPersonDAO(LDAPPersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public IUserService getUserDAO() {
        return userService;
    }

    public void setUserDAO(IUserService userService) {
        this.userService = userService;
    }
}
