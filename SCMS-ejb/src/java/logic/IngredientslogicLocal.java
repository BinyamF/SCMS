/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.Ingredients;
import entity.Products;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface IngredientslogicLocal {
    public void create(Ingredients ingredients);
    public void edit(Ingredients ingredients);
    public List<Ingredients> findByProduct(Products products);
}
