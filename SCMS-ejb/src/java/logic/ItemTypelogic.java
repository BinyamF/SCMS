/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.ItemType;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import model.ItemTypeFacade;

/**
 *
 * @author Billion
 */
@Stateless
public class ItemTypelogic implements ItemTypelogicLocal {

    @Inject
    ItemType itemType;
    
    @EJB
    ItemTypeFacade itemTypeFacade;
    
    @Override
    public void create(ItemType itemType) {
        itemTypeFacade.create(itemType);
    }

    @Override
    public void edit(ItemType itemType) {
        itemTypeFacade.edit(itemType);
    }

    @Override
    public void delete(ItemType itemType) {
        itemTypeFacade.remove(itemType);
    }

    @Override
    public List<ItemType> findAll() {
       return itemTypeFacade.findAll();
    }

    @Override
    public ItemType fetchItemInfo(ItemType itemType) {
        return itemTypeFacade.fetchItemInfo(itemType);
    }

    

    
}
