/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.PurchasedItem;
import entity.PurchaseInfo;
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
public class PurchasedItemFacade extends AbstractFacade<PurchasedItem> {

    @PersistenceContext(unitName = "SCMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchasedItemFacade() {
        super(PurchasedItem.class);
    }

    public List<PurchasedItem> findItem(PurchasedItem purchasedItem) {
        Query query = em.createNamedQuery("PurchasedItem.findByItemNameLike");
        query.setParameter("itemName", purchasedItem.getItemName().toUpperCase() + '%');
        query.setParameter("status", "In Stock");
        try {
            ArrayList<PurchasedItem> PurchaseItemlist = new ArrayList(query.getResultList());
            return PurchaseItemlist;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public PurchasedItem findPurchasedItem(PurchasedItem purchasedItem) {
        Query query = em.createNamedQuery("PurchasedItem.findByIdpurchasedItem");
        query.setParameter("idpurchasedItem", purchasedItem.getIdpurchasedItem());
        try {
            PurchasedItem PurchasedItem = (PurchasedItem) query.getSingleResult();
            return PurchasedItem;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<PurchasedItem> fetchPurchaseList(PurchaseInfo purchasedInfo){
        Query query = em.createNamedQuery("PurchasedItem.findByPurchaseId");
        query.setParameter("purchaseId", purchasedInfo);
        try {
            ArrayList<PurchasedItem> PurchaseItemlist = new ArrayList(query.getResultList());
            return PurchaseItemlist;
        } catch (Exception ex) {
            throw ex;
        }
    }
    public List<PurchasedItem> fetchItems(){
        Query query = em.createNamedQuery("PurchasedItem.findByQtyRemaining");        
        try {
            ArrayList<PurchasedItem> PurchaseItemlist = new ArrayList(query.getResultList());
            return PurchaseItemlist;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
