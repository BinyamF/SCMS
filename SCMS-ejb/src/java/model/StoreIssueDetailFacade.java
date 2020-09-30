/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.StoreIssue;
import entity.StoreIssueDetail;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Billion
 */
@Stateless
public class StoreIssueDetailFacade extends AbstractFacade<StoreIssueDetail> {
    @PersistenceContext(unitName = "SCMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StoreIssueDetailFacade() {
        super(StoreIssueDetail.class);
    }
    
    public List<StoreIssueDetail>fetchStoreDetail(StoreIssue storeIssue){
        Query query = em.createNamedQuery("StoreIssueDetail.findBystoreIssueFk");
        query.setParameter("storeIssueFk", storeIssue);
        try{
            ArrayList storeDetail = new ArrayList(query.getResultList());
            return storeDetail;
        }catch(Exception ex){
            throw ex;
        }
    }
}
