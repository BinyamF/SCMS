/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.StoreIssue;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface StoreIssueBeanLocal {
    public void create(StoreIssue storeIssue);
    public void edit(StoreIssue storeIssue);
    public List<StoreIssue> fetchItemsInFreshBabazar();
    public List<StoreIssue> findAll();
    public StoreIssue fetchStoreIssueInfo(StoreIssue storeIssue);
    public StoreIssue fetchMax();
}
