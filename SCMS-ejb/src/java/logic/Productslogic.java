/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.Products;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.ProductsFacade;

/**
 *
 * @author Billion
 */
@Stateless
public class Productslogic implements ProductslogicLocal {

    @Inject
    Products products;
    @EJB
    ProductsFacade productsfacade;

    @Override
    public void create(Products products) {
        productsfacade.create(products);
    }

    @Override
    public void edit(Products products) {
        productsfacade.edit(products);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Products> findAll() {
        return productsfacade.findAll();
    }

    @Override
    public Products fetchProductsInfo(Products products) {
        return productsfacade.fetchProductsInfo(products);
    }
}
