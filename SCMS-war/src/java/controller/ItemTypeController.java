/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.ItemType;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import logic.ItemTypelogicLocal;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Billion
 */
@Named(value = "itemTypeController")
@ViewScoped
public class ItemTypeController implements Serializable {

    @Inject
    ItemType itemType;
    @EJB
    ItemTypelogicLocal itemTypelogicLocal;
    ItemType itemTypeSelected;
    int updateFlag = 0;
    
    public ItemTypeController() {
    }

    public int getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(int updateFlag) {
        this.updateFlag = updateFlag;
    }

    public ItemType getItemTypeSelected() {
        return itemTypeSelected;
    }

    public void setItemTypeSelected(ItemType itemTypeSelected) {
        this.itemTypeSelected = itemTypeSelected;
    }
        
    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public ItemTypelogicLocal getItemTypelogicLocal() {
        return itemTypelogicLocal;
    }

    public void setItemTypelogicLocal(ItemTypelogicLocal itemTypelogicLocal) {
        this.itemTypelogicLocal = itemTypelogicLocal;
    }

    public void populate(SelectEvent event) {
        itemType = (ItemType) event.getObject();
        setUpdateFlag(1);
    }

    public void create() {
        if (getUpdateFlag() == 0) {
            itemTypelogicLocal.create(itemType);
        } else {
            itemTypelogicLocal.edit(itemType);
        }
        itemType = new ItemType();
        setUpdateFlag(0);
    }
    
    public void edit(){
        itemTypelogicLocal.edit(itemType);
    }
    
    public void delete(){
        itemTypelogicLocal.delete(itemType);
    }
    
    public List<ItemType> findAll( ){
        return itemTypelogicLocal.findAll();
    }
   
    
}
