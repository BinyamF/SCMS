/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Ingredients;
import entity.ItemType;
import entity.Products;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import logic.IngredientslogicLocal;
import logic.ItemTypelogicLocal;
import logic.ProductslogicLocal;

/**
 *
 * @author Billion
 */
@Named(value = "ingriedientscontroller")
@ViewScoped
public class Ingredientscontroller implements Serializable {

    @Inject
    Ingredients ingredients;
    @Inject
    Products products;
    @Inject
    ItemType itemType;
    @EJB
    IngredientslogicLocal ingredientslogicLocal;
    @EJB
    ProductslogicLocal productslogicLocal;
    @EJB
    ItemTypelogicLocal itemTypeLogic;
    List<Ingredients> ingredientsList;
    DataModel<Ingredients> ingredientsDataModel;

    public Ingredients getIngredients() {
        if(ingredients == null){
            ingredients = new Ingredients();
        }
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public Products getProducts() {
        if (products == null){
            products = new Products();
        }
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public List<Ingredients> getIngredientsList() {
        if (ingredientsList == null){
            ingredientsList = new ArrayList<>();
        }
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public ItemType getItemType() {
        if(itemType == null){
            itemType = new ItemType();
        }
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public DataModel<Ingredients> getIngredientsDataModel() {
        if(ingredientsDataModel == null){
            ingredientsDataModel = new ArrayDataModel<>();
        } 
        return ingredientsDataModel;
    }

    public void setIngredientsDataModel(DataModel<Ingredients> ingredientsDataModel) {
        this.ingredientsDataModel = ingredientsDataModel;
    }
    
    public Ingredientscontroller() {
    }

    public void create() {
        for (int i = 0; i <= ingredientsList.size(); i++) {
            ingredientslogicLocal.create(ingredientsList.get(i));
        }
    }

    public List<Products> findProducts() {
        return productslogicLocal.findAll();
    }

    public List<ItemType> findItem() {
        return itemTypeLogic.findAll();
    }
    
    public void fetchProductInfo(ValueChangeEvent event){
        products.setIdproducts(Integer.parseInt(event.getNewValue().toString()));
        products = productslogicLocal.fetchProductsInfo(products);
        ingredientsList = ingredientslogicLocal.findByProduct(products);
        addToDataModel();
    }
    
    public void fetchItemInfo(ValueChangeEvent event){
        itemType.setIditemType(Integer.parseInt(event.getNewValue().toString()));
        itemType = itemTypeLogic.fetchItemInfo(itemType);
    }

    public void add() {
        ingredients.setItemtypeid(itemType);
        ingredients.setProductid(products);
        getIngredientsList().add(ingredients);
        addToDataModel();
        ingredients = null;
    }
    
    public void addToDataModel() {
        ingredientsDataModel = new ListDataModel(new ArrayList(ingredientsList));
        itemType = null;
    }
    
    public void Save(){
        ingredientsList.stream().forEach((ingredientsList1) -> {
            ingredientslogicLocal.edit(ingredientsList1);
        });
    }

}
