/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.NonFiscalReceipt;
import entity.Orders;
import entity.Products;
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
public class OrdersFacade extends AbstractFacade<Orders> {

    @PersistenceContext(unitName = "SCMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    public List<Orders> findByNonFiscal(NonFiscalReceipt nonFiscalReceipt) {
        Query query = em.createNamedQuery("Orders.findByNonFiscalId");
        query.setParameter("nonFiscalId", nonFiscalReceipt);
        try {
            ArrayList<Orders> orderslist = new ArrayList(query.getResultList());
            return orderslist;
        } catch (Exception ex) {
            return null;
        }
    }
}
