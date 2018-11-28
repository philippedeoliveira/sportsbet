package fr.philippedeoliveira.filters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomJdbcDaoImpl extends JdbcDaoSupport implements UserDetailsService {

    public static final String DEF_USERS_BY_USERNAME_QUERY = "select username,password " + "from users "
            + "where username = ?";
    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select username,authority " + "from authorities "
            + "where username = ?";

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private String authoritiesByUsernameQuery;
    private String usersByUsernameQuery;
    private String rolePrefix = "";
    private boolean usernameBasedPrimaryKey = true;
    private boolean enableAuthorities = true;
    private boolean enableGroups;

    public CustomJdbcDaoImpl() {
        usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;
        authoritiesByUsernameQuery = DEF_AUTHORITIES_BY_USERNAME_QUERY;
    }

    public String getUsersByUsernameQuery() {
        return usersByUsernameQuery;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        List<UserDetails> users = loadUsersByUsername(username);

        UserDetails user = users.get(0);

        Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
        dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));

        List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);

        return createUserDetails(username, user, dbAuths);
    }

    /**
     * Executes the SQL <tt>usersByUsernameQuery</tt> and returns a list of
     * UserDetails objects. There should normally only be one matching user.
     */
    protected List<UserDetails> loadUsersByUsername(String username) {
        return getJdbcTemplate().query(usersByUsernameQuery, new String[] { username }, new RowMapper<UserDetails>() {
            public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                String username = rs.getString(1);
                String password = rs.getString(2);
                return new User(username, password, true, true, true, true, AuthorityUtils.NO_AUTHORITIES);
            }

        });
    }

    /**
     * Loads authorities by executing the SQL from
     * <tt>authoritiesByUsernameQuery</tt>.
     * 
     * @return a list of GrantedAuthority objects for the user
     */
    protected List<GrantedAuthority> loadUserAuthorities(String username) {
        return getJdbcTemplate().query(authoritiesByUsernameQuery, new String[] { username },
                new RowMapper<GrantedAuthority>() {
                    public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String roleName = rolePrefix + rs.getString(2);
                        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
                        return authority;
                    }
                });
    }

    /**
     * Can be overridden to customize the creation of the final
     * UserDetailsObject which is returned by the <tt>loadUserByUsername</tt>
     * method.
     * 
     * @param username
     *            the name originally passed to loadUserByUsername
     * @param userFromUserQuery
     *            the object returned from the execution of the
     * @param combinedAuthorities
     *            the combined array of authorities from all the authority
     *            loading queries.
     * @return the final UserDetails which should be used in the system.
     */
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
            List<GrantedAuthority> combinedAuthorities) {
        String returnUsername = userFromUserQuery.getUsername();

        if (!usernameBasedPrimaryKey) {
            returnUsername = username;
        }

        return new User(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(), true, true,
                true, combinedAuthorities);
    }

    /**
     * Allows the default query string used to retrieve authorities based on
     * username to be overridden, if default table or column names need to be
     * changed. The default query is {@link #DEF_AUTHORITIES_BY_USERNAME_QUERY};
     * when modifying this query, ensure that all returned columns are mapped
     * back to the same column names as in the default query.
     * 
     * @param queryString
     *            The SQL query string to set
     */
    public void setAuthoritiesByUsernameQuery(String queryString) {
        authoritiesByUsernameQuery = queryString;
    }

    protected String getAuthoritiesByUsernameQuery() {
        return authoritiesByUsernameQuery;
    }

    /**
     * Allows a default role prefix to be specified. If this is set to a
     * non-empty value, then it is automatically prepended to any roles read in
     * from the db. This may for example be used to add the <tt>ROLE_</tt>
     * prefix expected to exist in role names (by default) by some other Spring
     * Security classes, in the case that the prefix is not already present in
     * the db.
     * 
     * @param rolePrefix
     *            the new prefix
     */
    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

    protected String getRolePrefix() {
        return rolePrefix;
    }

    /**
     * If <code>true</code> (the default), indicates the
     * {@link #getUsersByUsernameQuery()} returns a username in response to a
     * query. If <code>false</code>, indicates that a primary key is used
     * instead. If set to <code>true</code>, the class will use the
     * database-derived username in the returned <code>UserDetails</code>. If
     * <code>false</code>, the class will use the
     * {@link #loadUserByUsername(String)} derived username in the returned
     * <code>UserDetails</code>.
     * 
     * @param usernameBasedPrimaryKey
     *            <code>true</code> if the mapping queries return the username
     *            <code>String</code>, or <code>false</code> if the mapping
     *            returns a database primary key.
     */
    public void setUsernameBasedPrimaryKey(boolean usernameBasedPrimaryKey) {
        this.usernameBasedPrimaryKey = usernameBasedPrimaryKey;
    }

    protected boolean isUsernameBasedPrimaryKey() {
        return usernameBasedPrimaryKey;
    }

    /**
     * Allows the default query string used to retrieve users based on username
     * to be overridden, if default table or column names need to be changed.
     * The default query is {@link #DEF_USERS_BY_USERNAME_QUERY}; when modifying
     * this query, ensure that all returned columns are mapped back to the same
     * column names as in the default query. If the 'enabled' column does not
     * exist in the source database, a permanent true value for this column may
     * be returned by using a query similar to
     * 
     * <pre>
     * &quot;select username,password,'true' as enabled from users where username = ?&quot;
     * </pre>
     * 
     * @param usersByUsernameQueryString
     *            The query string to set
     */
    public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
        this.usersByUsernameQuery = usersByUsernameQueryString;
    }

    protected boolean getEnableAuthorities() {
        return enableAuthorities;
    }

    /**
     * Enables loading of authorities (roles) from the authorities table.
     * Defaults to true
     */
    public void setEnableAuthorities(boolean enableAuthorities) {
        this.enableAuthorities = enableAuthorities;
    }

    protected boolean getEnableGroups() {
        return enableGroups;
    }

    /**
     * Enables support for group authorities. Defaults to false
     * 
     * @param enableGroups
     */
    public void setEnableGroups(boolean enableGroups) {
        this.enableGroups = enableGroups;
    }
}