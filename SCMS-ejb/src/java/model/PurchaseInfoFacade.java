/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.PurchaseInfo;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Billion
 */
@Stateless
public class PurchaseInfoFacade extends AbstractFacade<PurchaseInfo> {
    @PersistenceContext(unitName = "SCMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseInfoFacade() {
        super(PurchaseInfo.class);
    }
    
    public ArrayList<PurchaseInfo> searchPurchaseInfo(PurchaseInfo purchaseInfo) {
        Query query = em.createNamedQuery("PurchaseInfo.findByPurcaseRequisitionNoLike");
        query.setParameter("purcaseRequisitionNo", purchaseInfo.getPurcaseRequisitionNo()+ '%' );
        try {
            ArrayList<PurchaseInfo> PurchaseInfolist = new ArrayList(query.getResultList());
            return PurchaseInfolist;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<PurchaseInfo> fetchPurchaseList(PurchaseInfo purchaseInfo) {
        Query query = em.createNamedQuery("PurchaseInfo.findByPurcaseRequisitionNoLike");
        query.setParameter("purcaseRequisitionNo", purchaseInfo.getPurcaseRequisitionNo()+ '%' );
        try {
            ArrayList<PurchaseInfo> PurchaseInfolist = new ArrayList(query.getResultList());
            return PurchaseInfolist;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public PurchaseInfo fetchPurchaseInfo(PurchaseInfo purchaseInfo) {
        Query query = em.createNamedQuery("PurchaseInfo.findById");
        query.setParameter("id", purchaseInfo.getId());
        try {
            PurchaseInfo purchase = (PurchaseInfo) (query.getResultList().get(0));
            return purchase;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
