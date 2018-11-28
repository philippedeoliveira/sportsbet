<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<s:if test='"ok".equals(#parameters["save"][0])'>
    <div style="margin-left:155px;color:#FF0000;font-weight: bold;">Your bets are Saved !</div>
</s:if>
<s:form action="bet" method="post" cssClass="contentpaneopen" theme="simple">
    <table>
        <tr>
            <td colspan="6">
                <table class="contentpaneopen">
                    <tr>
                        <td class="contentheading" style="text-align: left">Bets</td>
                        <td  style="text-align: right"><input
                            value="Bet !" class="button"
                            style="width: 50px;" type="submit" /></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <table class="contentpaneopen">
                    <tr>
                        <td class="contentheading" style="text-align: center; padding: 10px">Winner</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr class="bet">
            <td class="receiverCountry" colspan="6" style="text-align: center; padding: 10px">
                <s:if test="tournamentWinnerBetClosed == true">
                    <s:property value="tournamentWinnerBet" />
                </s:if>
                <s:else>
                    <s:select list="teams" name="tournamentWinnerBet"/>
                </s:else>
            </td>
        </tr>
        <s:set value="0" var="currentRound" />
        <s:iterator value="bets" var="bet">
            <s:if test="#bet.roundNumber != #currentRound">
                <tr>
                    <td colspan="6" class="contentheading" style="text-align: center; padding: 10px">
                        <s:property value="#bet.round" />
                    </td>
                </tr>
            </s:if>
            <s:set value="#bet.roundNumber" var="currentRound" />
            <tr class="bet">
                <td class="receiverCountry">
                    <s:property value="#bet.receiver" />
                    <img class="receiverFlag" src="/sportsbet/img/countries/<s:property value='#bet.receiver.replace(" ", "")' />.png" alt="<s:property value='#bet.receiver'/>">
                </td>
                <td class="receiverScore">
                    <s:if test="%{#bet.betClosed == true}">
                        <input style="text-align: center"
                            class="disabledinputbox" size="1" maxLength="2"
                            disabled
                            value="<s:property value='#bet.receiverScore'/>"
                            name="receiverScores[<s:property value='#bet.gameId' />]" />
                    </s:if>
                    <s:else>
                        <input style="text-align: center" class="inputbox"
                            size="1" maxLength="2"
                            value="<s:property value='#bet.receiverScore'/>"
                            name="receiverScores[<s:property value='#bet.gameId' />]" />
                    </s:else>
                </td>
                <td class="dash">-</td>
                <td class="foreignerScore">
                    <s:if test="%{#bet.betClosed == true}">
                        <input style="text-align: center"
                            class="disabledinputbox" size="1" maxLength="2"
                            disabled
                            value="<s:property value='#bet.foreignerScore'/>"
                            name="foreignerScores[<s:property value='#bet.gameId' />]" />
                    </s:if>
                    <s:else>
                        <input style="text-align: center" class="inputbox"
                            size="1" maxLength="2"
                            value="<s:property value='#bet.foreignerScore'/>"
                            name="foreignerScores[<s:property value='#bet.gameId' />]" />
                    </s:else></td>
                <td class="foreignerCountry">
                    <img class="foreignerFlag" src="/sportsbet/img/countries/<s:property value='#bet.foreigner.replace(" ", "")' />.png" alt="<s:property value='#bet.foreigner' />">
                    <s:property value="#bet.foreigner" />
                </td>
                <s:if test="%{#bet.betClosed == true}">
                    <td class="sameScore">
                        <s:property value="#bet.percentageThatBetSameScore"/>% bet same score
                    </td>
                </s:if>
                <s:else>
                    <td class="limitDate"><s:date name="#bet.gameLimitBetDate" format="H:mm '&nbsp;&nbsp;' dd/MM/yyyy" /></td>
                </s:else>
            </tr>
            <s:if test="%{#bet.betClosed == true}">
                <tr class="gameInfos">
                    <td class="scoreGuess">
                        <s:if test="#bet.receiverScoreResult != null">
                            <s:property value="#bet.percentageThatBetGoodScore"/>% guessed score
                        </s:if>
                    </td>
                    <td class="receiverScore">(<s:property value="#bet.receiverScoreResult"/>&nbsp;</td>
                    <td class="dash">-</td>
                    <td class="foreignerScore">&nbsp;<s:property value="#bet.foreignerScoreResult"/>)</td>
                    <td class="resultGuess">
                        <s:if test="#bet.receiverScoreResult != null">
                            <s:property value="#bet.percentageThatBetGoodResult"/>% guessed result
                        </s:if>
                    </td>
                    <td class="sameResult">
                        <s:property value="#bet.percentageThatBetSameResult"/>% bet same result
                    </td>
                 </tr>
             </s:if>
        </s:iterator>
    </table>
</s:form>