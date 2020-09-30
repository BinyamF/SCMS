/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.StockCard;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Billion
 */
@Stateless
public class StockCardFacade extends AbstractFacade<StockCard> {
    @PersistenceContext(unitName = "SCMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockCardFacade() {
        super(StockCard.class);
    }
    
}
