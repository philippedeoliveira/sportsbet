package fr.philippedeoliveira.filters;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SmileLDAPUserDetails implements UserDetails {

    private UserDetails decoratedUserDetails;

    private Integer bu;

    private String fullname;

    public SmileLDAPUserDetails(UserDetails ldapUserDetails) {
        this.decoratedUserDetails = ldapUserDetails;
    }

    public Integer getBu() {
        return bu;
    }

    public void setBu(Integer buId) {
        this.bu = buId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.decoratedUserDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.decoratedUserDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return this.decoratedUserDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.decoratedUserDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.decoratedUserDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.decoratedUserDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.decoratedUserDetails.isEnabled();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
