/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.PurchaseInfo;
import entity.PurchasedItem;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.PurchasedItemFacade;

/**
 *
 * @author Billion
 */
@Stateless
public class PurchasedItemlogic implements PurchasedItemlogicLocal {

    @EJB
    PurchasedItemFacade purchasedItemFacade;
    
    @Override
    public void create(PurchasedItem purchasedItem) {
        purchasedItemFacade.create(purchasedItem);
    }

    @Override
    public void edit(PurchasedItem purchasedItem) {
        purchasedItemFacade.edit(purchasedItem);
    }

    @Override
    public void delete(PurchasedItem purchasedItem) {
        purchasedItemFacade.remove(purchasedItem);
    }

    @Override
    public List<PurchasedItem> findAll() {
        return purchasedItemFacade.findAll();
    }

    @Override
    public List<PurchasedItem> findItem(PurchasedItem purchasedItem) {
        return purchasedItemFacade.findItem(purchasedItem);
    }

    @Override
    public PurchasedItem fetchPurchaseInfo(PurchasedItem purchasedItem) {
        return purchasedItemFacade.findPurchasedItem(purchasedItem);
    }

    @Override
    public List<PurchasedItem> fetchPurchaseList(PurchaseInfo purchaseInfo) {
        return purchasedItemFacade.fetchPurchaseList(purchaseInfo);
    }
    
    @Override
    public List<PurchasedItem> fetchItems( ){
        return purchasedItemFacade.fetchItems();
    }
}
