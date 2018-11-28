<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!doctype html>
<html>
<head>
    <title><tiles:insertAttribute name="title" /></title>
    <meta name="description" content="<tiles:insertAttribute name="description" />">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <link rel="stylesheet" href="/sportsbet/css/main.css" type="text/css">
    <link rel="shortcut icon" href="/sportsbet/img/favicon.png">
</head>
<body>
    <div class="all">
        <div id="logo"></div>
        <div id="container">
            <table>
                <tr>
                    <tiles:insertAttribute name="left" />
                    
                    <td class="maincol">
                        <tiles:insertAttribute name="body" />
                    </td>
                    
                    <tiles:insertAttribute name="right" />
                </tr>
            </table>
        </div>
        <div class="footer_bg"></div>
    </div>
</body>
</html>