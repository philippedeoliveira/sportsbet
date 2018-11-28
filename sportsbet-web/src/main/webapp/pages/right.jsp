<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<td class="rightcol">
    <div class="login">Login : <sec:authentication property="principal.username" /></div>
</td>