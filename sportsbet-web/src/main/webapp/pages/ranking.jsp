<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div>
	<table class="blog" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top">
				<div>
					<table class="contentpaneopen">
						<tr>
							<s:if test="actionName == 'buRanking'">
                	            <td class="contentheading" width="50%">Classement</td>
                                <td class="contentheading" width="50%">                  
                                    <s:form action="buRanking" method="get">
                                        <s:select list="bus" listKey="id" listValue="%{locationLabel}" name="bu"  value='bu' onchange="this.form.submit()"/>                                       
                                    </s:form>
                                </td>
                             </s:if>
                             <s:else>
                                <td class="contentheading" width="100%">Classement (<a href="#myRank">my rank</a>)</td>
                             </s:else>
						</tr>
					</table>
					<table class="classement" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th colspan="2" width="150">Name</th>
								<th width="30">Pts</th>
								<th width="30">J</th>
                                <th width="30">+7</th>
								<th width="30">+5</th>
								<th width="30">+3</th>
								<th width="30">0</th>
							</tr>
						</thead>
						<s:iterator value="rankings" var="rank" status="stats">
							<tr <s:if test="#rank.login == userLogin">class="votre_position"</s:if>>
       							<td class="right">
                                    <s:if test="#rank.login == userLogin">
   							            <a id="myRank"></a>
                                    </s:if>
                                    <s:if test="#stats.count == 1 ||
                                        !(
                                            #rank.points == #previousRank.points &&
                                            #rank.bonifiedExactScores == #previousRank.bonifiedExactScores &&
                                            #rank.exactScores == #previousRank.exactScores &&
                                            #rank.goodResults == #previousRank.goodResults
                                        )">
                                        <s:set var="rankIndex" value="#stats.count" />
                                    </s:if>
                                    <s:set var="previousRank" value="#rank" />
   							        <s:property value="#rankIndex" />.
                                </td>
								<td class="left"><s:property value='#rank.fullName' /></td>
								<td class="grey"><s:property value='#rank.points' /></td>
								<td><s:property value='#rank.played' /></td>
                                <td><s:property value='#rank.BonifiedExactScores' /></td>
								<td><s:property value='#rank.exactScores' /></td>
								<td><s:property value='#rank.goodResults' /></td>
								<td><s:property value='#rank.badResults' /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</td>
		</tr>
	</table>
</div>
