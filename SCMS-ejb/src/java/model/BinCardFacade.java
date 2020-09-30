/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.BinCard;
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
public class BinCardFacade extends AbstractFacade<BinCard> {

    @PersistenceContext(unitName = "SCMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BinCardFacade() {
        super(BinCard.class);
    }

    public BinCard fetchBinCard(BinCard binCard) {
        Query query = em.createNamedQuery("BinCard.findByItemType");
        query.setParameter("itemId", binCard.getItemId());
        try {
            if (!query.getResultList().isEmpty()) {
                BinCard binCardData = (BinCard) query.getResultList().get(0);
                return binCardData;
            } else {
                return null;
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<BinCard> fetchItems() {
        Query query = em.createNamedQuery("BinCard.findByAvailableItems");
        try {
            if (!query.getResultList().isEmpty()) {
                ArrayList availableItems = new ArrayList(query.getResultList());
                return availableItems;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
}
