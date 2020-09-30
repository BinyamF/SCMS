/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.StoreIssue;
import entity.StoreIssueDetail;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.StoreIssueDetailFacade;

/**
 *
 * @author Billion
 */
@Stateless
public class StoreIssueDetailBean implements StoreIssueDetailBeanLocal {

    @EJB
    StoreIssueDetailFacade storeIssueDetailFacade;

    @Override
    public List<StoreIssueDetail> fetchStoreDetail(StoreIssue storeIssue) {
        return storeIssueDetailFacade.fetchStoreDetail(storeIssue);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
