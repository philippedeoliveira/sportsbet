package fr.philippedeoliveira.mappers;

import java.util.Collection;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import fr.philippedeoliveira.filters.SmileLDAPUserDetails;
import fr.philippedeoliveira.services.IBUService;

@Component("userContextMapper")
public class SmileLDAPUserContextMapper extends LdapUserDetailsMapper implements UserDetailsContextMapper {

    @Inject
    private IBUService buService;

    @Override
    public UserDetails mapUserFromContext(DirContextOperations contextAttributes, String username,
            Collection<? extends GrantedAuthority> ga) {
        UserDetails ldapUserDetails = super.mapUserFromContext(contextAttributes, username, ga);
        SmileLDAPUserDetails userDetails = new SmileLDAPUserDetails(ldapUserDetails);
        try {
            String buCode = (String) contextAttributes.getAttributes().get("primaryDepartmentNumber").get();
            userDetails.setBu(buService.findBuByCode(buCode).getId());
            userDetails.setFullname((String) contextAttributes.getAttributes().get("cn").get());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return userDetails;
    }

    @Override
    public void mapUserToContext(UserDetails arg0, DirContextAdapter arg1) {
        super.mapUserToContext(arg0, arg1);
    }

    public void setBuService(IBUService buService) {
        this.buService = buService;
    }
}
