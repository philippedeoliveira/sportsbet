package fr.philippedeoliveira.actions;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import fr.philippedeoliveira.services.IUserService;

/**
 * Action for admins Only !
 * 
 * @author waddle
 *
 */
@Component("adminAction")
@Scope("prototype")
public class AdminAction extends AbstractSportsbetAction {

    @Inject
    private IUserService userService;

    private Integer userId;

    /**
     * Drop user bets
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public String execute() throws Exception {
        userService.deleteUserBets(userId);
        return SUCCESS;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
