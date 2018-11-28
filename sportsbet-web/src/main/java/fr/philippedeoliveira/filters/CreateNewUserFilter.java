package fr.philippedeoliveira.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.philippedeoliveira.beans.User;
import fr.philippedeoliveira.services.IBUService;
import fr.philippedeoliveira.services.IUserService;

/**
 * HTTP filter that will create a user the first time he ever sign into the
 * application
 * 
 * @author waddle
 * 
 */
@Component
@Qualifier("createNewUserFilter")
public class CreateNewUserFilter extends OncePerRequestFilter {
    private static final String USER_ALREADY_REGISTRATED = "USER_ALREADY_REGISTRATED";

    @Inject
    private IUserService userService;
    @Inject
    private IBUService buService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            // Authenticated user
            if (req.getSession().getAttribute(USER_ALREADY_REGISTRATED) == null) {
                SmileLDAPUserDetails userDetails = ((SmileLDAPUserDetails) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal());
                User user = userService.getById(userDetails.getUsername());
                if (user == null) {
                    User newUser = new User();
                    newUser.setUsername(userDetails.getUsername());
                    newUser.setBusinessUnit(buService.getBuById(userDetails.getBu()));
                    newUser.setEnabled(true);
                    newUser.setFullName(userDetails.getFullname());
                    userService.saveUser(newUser);
                }
                // New or not, we add a key into the session to avoid
                // re-asking
                // each time if the user already exists
                req.getSession().setAttribute(USER_ALREADY_REGISTRATED, true);
            }
        }
        chain.doFilter(req, resp);
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

}
