/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.Kommon.addFatalMessage;
import static controller.Kommon.addSuccessMessage;
import entity.Ingredients;
import entity.ItemType;
import entity.Products;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import logic.IngredientslogicLocal;
import logic.ItemTypelogicLocal;
import logic.ProductslogicLocal;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Billion
 */
@Named(value = "productscontroller")
@ViewScoped
public class Productscontroller implements Serializable {

    @Inject
    Products products;
    @Inject
    Ingredients ingredients;
    @Inject
    ItemType itemType;
    @EJB
    ProductslogicLocal productslogicLocal;
    @EJB
    IngredientslogicLocal ingredientslogicLocal;
    @EJB
    ItemTypelogicLocal itemTypeLogic;

    DataModel<Ingredients> ingredientsDataModel;
    Ingredients selectedIngredient;

    int updateStat = 0;
    int jumper = 0;    
    int nonDuplicationFlag = 0;
    int popEditFlag = 0;

    public ProductslogicLocal getProductslogicLocal() {
        return productslogicLocal;
    }

    public void setProductslogicLocal(ProductslogicLocal productslogicLocal) {
        this.productslogicLocal = productslogicLocal;
    }

    public int getJumper() {
        return jumper;
    }

    public void setJumper(int jumper) {
        this.jumper = jumper;
    }

    public int getPopEditFlag() {
        return popEditFlag;
    }

    public void setPopEditFlag(int popEditFlag) {
        this.popEditFlag = popEditFlag;
    }

    public int getUpdateStat() {
        return updateStat;
    }

    public void setUpdateStat(int updateStat) {
        this.updateStat = updateStat;
    }

    public Ingredients getSelectedIngredient() {
        if (selectedIngredient == null) {
            selectedIngredient = new Ingredients();
        }
        return selectedIngredient;
    }

    public void setSelectedIngredient(Ingredients selectedIngredient) {
        this.selectedIngredient = selectedIngredient;
    }

    public Products getProducts() {
        if (products == null) {
            products = new Products();
        }
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Ingredients getIngredients() {
        if (ingredients == null) {
            ingredients = new Ingredients();
        }
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public DataModel<Ingredients> getIngredientsDataModel() {
        if (ingredientsDataModel == null) {
            ingredientsDataModel = new ListDataModel();
        }
        return ingredientsDataModel;
    }

    public void setIngredientsDataModel(DataModel<Ingredients> ingredientsDataModel) {
        this.ingredientsDataModel = ingredientsDataModel;
    }

    public ItemType getItemType() {
        if (itemType == null) {
            itemType = new ItemType();
        }
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Productscontroller() {
    }

    public List<Products> findProducts() {
        return productslogicLocal.findAll();
    }

    public List<ItemType> findItem() {
        return itemTypeLogic.findAll();
    }

    public void fetchProductInfo(ValueChangeEvent event) {
        products.setIdproducts(Integer.parseInt(event.getNewValue().toString()));
        products = productslogicLocal.fetchProductsInfo(products);
        ingredients = new Ingredients();
        itemType = new ItemType();
        addToDataModel();
        updateStat = 1;
    }

    public void populate(SelectEvent event) {
        ingredients = (Ingredients) event.getObject();
        itemType = ingredients.getItemtypeid();
        setPopEditFlag(1);
    }

    public void checkDuplication() {
        if (!(products.getIngredientsList() == null)) {
            if (!(products.getIngredientsList().isEmpty())) {     //If item is NOT EMPTY --> Get In The Loop!!!
                for (int i = 0; i < products.getIngredientsList().size(); i++) {
                    if (ingredients.getItemtypeid().equals(products.getIngredientsList().get(i).getItemtypeid()) && getPopEditFlag() == 0) {
                        addFatalMessage("Duplicated items are not allowed!");
                        nonDuplicationFlag++;
                        setJumper(1);
                    }
                }
            }
            setPopEditFlag(0);
            if (getJumper() == 0) {
                addIngredientDetail();
                nonDuplicationFlag = 1;
            }
            if (nonDuplicationFlag == 0) {
                addIngredientDetail();
            }
        }
        nonDuplicationFlag = 0;
    }

    public void addIngredientDetail() {
        products.addIngredientDetail(ingredients);
        addToDataModel();
        clearIngredients();
    }

    public void addToDataModel() {
        ingredientsDataModel = new ListDataModel<>();
        ingredientsDataModel = new ListDataModel(products.getIngredientsList());
    }

    public void clearIngredients() {
        ingredients = new Ingredients();
        itemType = new ItemType();
    }

    public void fetchItemInfo(ValueChangeEvent event) {
        itemType.setIditemType(Integer.parseInt(event.getNewValue().toString()));
        itemType = itemTypeLogic.fetchItemInfo(itemType);
        ingredients.setItemtypeid(itemType);
        ingredients.setAmount(null);
        setPopEditFlag(0);
    }

    public void create() {
        if (updateStat == 1) {
            productslogicLocal.edit(products);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Product ", "Data successfully updated."));
            products = new Products();

        } else {
            productslogicLocal.create(products);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Product ", "Data successfully saved."));
            products = new Products();
        }
    }

}
