/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.StoreIssue;
import entity.StoreIssueDetail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface StoreIssueDetailBeanLocal {
    public List<StoreIssueDetail>fetchStoreDetail(StoreIssue storeIssue);
}
