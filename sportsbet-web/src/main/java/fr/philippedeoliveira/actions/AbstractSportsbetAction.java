package fr.philippedeoliveira.actions;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.philippedeoliveira.filters.SmileLDAPUserDetails;

/**
 * Root action to be used by any sportsbet action. It's also the default action
 * for Struts
 * 
 * @author waddle
 * 
 */
public class AbstractSportsbetAction extends ActionSupport {
	
    public String getActionName() {
        return ActionContext.getContext().getName();
    }

	/**
	 * Returns the user login
	 * 
	 * @return
	 */
    public String getUserLogin() {
    	return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();
    }

    /**
     * Returns the user BU
     * 
     * @return
     */
    public Integer getUserBU() {
        return new Integer(((SmileLDAPUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal())
                .getBu());
    }
}
