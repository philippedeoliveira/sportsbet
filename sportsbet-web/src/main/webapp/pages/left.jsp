<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<td class="leftcol">
    <table class="moduletable">
        <tr>
            <th>Main Menu</th>
        </tr>
        <tr>
            <td>
                <ul>
                    <li <s:if test="actionName == 'listBets'">class="selected"</s:if>>
                        <a href="/sportsbet/pages/listBets.action" class="mainlevel">Bets</a>
                    </li>
                    <li <s:if test="actionName == 'ranking'">class="selected"</s:if>>
                        <a href="/sportsbet/pages/ranking.action" class="mainlevel">Ranking</a>
                    </li>
                    <li <s:if test="actionName == 'buRanking'">class="selected"</s:if>>
                        <a href="/sportsbet/pages/buRanking.action?bu=<s:property value='userBU'/>" class="mainlevel">BU Ranking</a>
                    </li>
                    <li <s:if test="actionName == 'rules'">class="selected"</s:if>>
                        <a href="/sportsbet/pages/rules.action" class="mainlevel" >Rules</a>
                    </li>
                </ul>
            </td>
        </tr>
    </table>
</td>