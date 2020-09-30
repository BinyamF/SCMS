/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.Ingredients;
import entity.Products;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.IngredientsFacade;

/**
 *
 * @author Billion
 */
@Stateless
public class Ingredientslogic implements IngredientslogicLocal {

    @EJB
    IngredientsFacade ingredientsFacade;
    @Override
    public void create(Ingredients ingredients) {
        ingredientsFacade.create(ingredients);
    }
    
    @Override
    public void edit(Ingredients ingredients) {
        ingredientsFacade.edit(ingredients);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Ingredients> findByProduct(Products products) {
        return ingredientsFacade.findByProducts(products);
    }
}
