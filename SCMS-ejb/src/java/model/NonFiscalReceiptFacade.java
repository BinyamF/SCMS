/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.NonFiscalReceipt;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Billion
 */
@Stateless
public class NonFiscalReceiptFacade extends AbstractFacade<NonFiscalReceipt> {
    @PersistenceContext(unitName = "SCMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NonFiscalReceiptFacade() {
        super(NonFiscalReceipt.class);
    }
    
    public NonFiscalReceipt fetchNonFiscalReceiptInfo(NonFiscalReceipt nonFiscalReceipt){
        Query query = em.createNamedQuery("NonFiscalReceipt.findByIdOrders");
        query.setParameter("idOrders", nonFiscalReceipt.getIdOrders());
                try {
           NonFiscalReceipt receipt = (NonFiscalReceipt)  query.getSingleResult();
            return receipt;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
}
