/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entity.PurchaseInfo;
import entity.PurchasedItem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Billion
 */
@Local
public interface PurchasedItemlogicLocal {
    public void create(PurchasedItem purchasedItem);
    public void edit (PurchasedItem purchasedItem);
    public void delete(PurchasedItem purchasedItem);
    public List<PurchasedItem> findAll();
    public List<PurchasedItem> findItem(PurchasedItem purchasedItem);
    public PurchasedItem fetchPurchaseInfo(PurchasedItem purchasedItem);
    public List<PurchasedItem> fetchPurchaseList(PurchaseInfo purchaseInfo);
    public List<PurchasedItem> fetchItems();
}
