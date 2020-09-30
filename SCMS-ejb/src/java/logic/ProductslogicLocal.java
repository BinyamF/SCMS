/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.Products;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface ProductslogicLocal {
    public void create(Products products);
    public void edit(Products products);
    public List<Products> findAll();
    public Products fetchProductsInfo(Products products);
}
