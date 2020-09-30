/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Products;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Billion
 */
@Stateless
public class ProductsFacade extends AbstractFacade<Products> {
    @PersistenceContext(unitName = "SCMS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }
    
    public Products fetchProductsInfo(Products products){
        Query query = em.createNamedQuery("Products.findByIdproducts");
        query.setParameter("idproducts", products.getIdproducts());
                try {
           Products PurchasedItem = (Products)  query.getSingleResult();
            return PurchasedItem;
        } catch (Exception ex) {
            return null;
        }
    }
}
