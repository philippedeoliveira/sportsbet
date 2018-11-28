package fr.philippedeoliveira.actions;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.philippedeoliveira.services.IBetService;
import fr.philippedeoliveira.services.dto.RankingLine;
/**
 * Action for displaying rankings
 * 
 * @author waddle
 *
 */
@Component("rankingAction")
@Scope("prototype")
public class RankingAction extends AbstractSportsbetAction {

    @Inject
    private IBetService betService;

    private List<RankingLine> rankings;

    /**
     * Get all ranking lines 
     */
    @Override
    public String execute() throws Exception {
        rankings = betService.getRanking();
        return SUCCESS;
    }

    public List<RankingLine> getRankings() {
        return rankings;
    }

    public void setRankings(List<RankingLine> rankings) {
        this.rankings = rankings;
    }

    public IBetService getBetService() {
        return betService;
    }

    public void setBetService(IBetService betService) {
        this.betService = betService;
    }
}
