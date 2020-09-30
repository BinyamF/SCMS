/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.ItemType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Billion
 */
@Stateless
public class ItemTypeFacade extends AbstractFacade<ItemType> {
    @PersistenceContext(unitName = "SCMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemTypeFacade() {
        super(ItemType.class);
    }
    
     public ItemType fetchItemInfo(ItemType itemType) {
        Query query = em.createNamedQuery("ItemType.findByIditemType");
        query.setParameter("iditemType", itemType.getIditemType());
        try {
            ItemType item = (ItemType) (query.getResultList().get(0));
            return item;
        } catch (Exception ex) {
            throw ex;            
        }
    }
}
