package fr.philippedeoliveira.actions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.philippedeoliveira.beans.BusinessUnit;
import fr.philippedeoliveira.services.IBUService;
import fr.philippedeoliveira.services.IBetService;
import fr.philippedeoliveira.services.IUserService;
import fr.philippedeoliveira.services.dto.RankingLine;
/**
 * Action for displaying rankings
 * 
 * @author waddle
 *
 */
@Component("buRankingAction")
@Scope("prototype")
public class BuRankingAction extends AbstractSportsbetAction {

    @Inject
    private IBetService betService;

    @Inject
    private IUserService userService;

    @Inject
    private IBUService buService;

    private List<RankingLine> rankings;

    private List<BusinessUnit> bus;

    private Integer bu;

    /**
     * Get all ranking lines 
     */
    @Override
    public String execute() throws Exception {
        bus = buService.getAllBus();
        Collections.sort(bus, new Comparator<BusinessUnit>() {
            @Override
            public int compare(BusinessUnit bu1, BusinessUnit bu2) {
                return bu1.getLocationLabel().compareTo(bu2.getLocationLabel());
            }
        });
        if (bu == null) {
            bu = super.getUserBU();
            rankings = betService.getBuRanking(this.getUserBU());
        } else {
            rankings = betService.getBuRanking(this.bu);
        }
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

    public List<BusinessUnit> getBus() {
        return bus;
    }

    public void setBus(List<BusinessUnit> bus) {
        this.bus = bus;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public Integer getBu() {
        return bu;
    }

    public void setBu(Integer bu) {
        this.bu = bu;
    }

}
