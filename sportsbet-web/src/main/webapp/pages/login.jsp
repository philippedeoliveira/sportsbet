<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="moduletable login">
    <tr>
        <th>Login Form</th>
    </tr>
    <tr>
        <td>
            <form action="j_spring_security_check" method="post">
                <table>
                    <tr>
                        <td><label for="mod_login_username">Login</label></td>
                        <td>
                            <input type="text" id="mod_login_username" name="j_username" class="inputbox" size="10" />
                        </td>
                    </tr>
                    <tr>
                        <td><label for="mod_login_password">Password</label></td>
                        <td>
                            <input type="password" id="mod_login_password" name="j_password" class="inputbox" size="10" />
                        </td>
                    </tr>
                    <tr>
                        <td><label for="mod_login_password">Remember me ?</label></td>
                        <td><input type="checkbox" name="_spring_security_remember_me"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="Submit" class="button"
                            value="Login" /></td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>