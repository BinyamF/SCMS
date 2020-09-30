/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.StoreIssue;
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
public class StoreIssueFacade extends AbstractFacade<StoreIssue> {

    @PersistenceContext(unitName = "SCMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StoreIssueFacade() {
        super(StoreIssue.class);
    }

    public List<StoreIssue> fetchItemsInFreshBabazar() {
        Query query = em.createNamedQuery("StoreIssue.findByItemHolder");
        query.setParameter("itemHolder", "Fresh Bazar");
        try {
            List<StoreIssue> itemslist = new ArrayList(query.getResultList());
            return itemslist;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public StoreIssue fetchStoreIssueInfo(StoreIssue storeIssue) {
        Query query = em.createNamedQuery("StoreIssue.findByIdstoreIssue");
        query.setParameter("idstoreIssue", storeIssue.getIdstoreIssue());
        try {
            StoreIssue storeData = (StoreIssue) query.getSingleResult();
            return storeData;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public StoreIssue fetchMax() {
        Query query = em.createNativeQuery("SELECT MAX(s.IDSTORE_ISSUE) FROM bris.store_issue s ",StoreIssue.class);
        try {
            StoreIssue storeData = (StoreIssue) query.getSingleResult();
            return storeData;
        } catch (Exception ex) {
            throw ex;
        }
    }

}
