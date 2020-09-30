/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.StoreIssue;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.StoreIssueFacade;
/**
 *
 * @author Billion
 */
@Stateless
public class StoreIssueBean implements StoreIssueBeanLocal {

    @EJB
    StoreIssueFacade storeIssueFacade;    

    @Override
    public void create(StoreIssue storeIssue) {
        storeIssueFacade.create(storeIssue);
    }
    
    @Override
    public void edit(StoreIssue storeIssue) {
        storeIssueFacade.edit(storeIssue);
    }

    @Override
    public List<StoreIssue> fetchItemsInFreshBabazar() {
        return storeIssueFacade.fetchItemsInFreshBabazar();
    }

    @Override
    public List<StoreIssue> findAll() {
        return storeIssueFacade.findAll();
    }

    @Override
    public StoreIssue fetchStoreIssueInfo(StoreIssue storeIssue) {
        return storeIssueFacade.fetchStoreIssueInfo(storeIssue);
    }
    
    @Override
    public StoreIssue fetchMax() {
        return storeIssueFacade.fetchMax();
    }    
}
