/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.ItemType;
import entity.PurchaseInfo;
import entity.PurchasedItem;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import logic.ItemTypelogicLocal;
import logic.PurchaseInfologicLocal;
import logic.PurchasedItemlogicLocal;

/**
 *
 * @author Billion
 */
@Named(value = "goodsdetailController")
@ViewScoped
public class GoodsdetailController implements Serializable {

    @Inject
    PurchaseInfo purchaseInfo;
    @Inject
    PurchasedItem purchasedItem;
    @Inject
    ItemType itemType;
    @EJB
    PurchasedItemlogicLocal purchasedItemlogicLocal;
    @EJB
    PurchaseInfologicLocal purchaseInfologicLocal;
    @EJB
    ItemTypelogicLocal itembrandlogicLocal;
    List<PurchasedItem> purchasedItemList = new ArrayList<>();
    DataModel<PurchasedItem> purchasedModel;

    public List<PurchasedItem> getPurchasedItemList() {
        if (purchasedItemList == null) {
            purchasedItemList = new ArrayList<>();
        }
        return purchasedItemList;
    }

    public void setPurchasedItemList(List<PurchasedItem> purchasedItemList) {
        this.purchasedItemList = purchasedItemList;
    }

    public DataModel<PurchasedItem> getPurchasedModel() {
        if (purchasedModel == null) {
            purchasedModel = new ListDataModel<>();
        }
        return purchasedModel;
    }

    public void setPurchasedModel(DataModel<PurchasedItem> purchasedModel) {
        this.purchasedModel = purchasedModel;
    }

    public PurchaseInfo getPurchaseInfo() {
        if (purchaseInfo == null) {
            purchaseInfo = new PurchaseInfo();
        }
        return purchaseInfo;
    }

    public void setPurchaseInfo(PurchaseInfo purchaseInfo) {
        this.purchaseInfo = purchaseInfo;
    }

    public PurchasedItem getPurchasedItem() {
        if (purchasedItem == null) {
            purchasedItem = new PurchasedItem();
        }
        return purchasedItem;
    }

    public void setPurchasedItem(PurchasedItem purchasedItem) {
        this.purchasedItem = purchasedItem;
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

    public PurchasedItemlogicLocal getPurchasedItemlogicLocal() {
        return purchasedItemlogicLocal;
    }

    public void setPurchasedItemlogicLocal(PurchasedItemlogicLocal purchasedItemlogicLocal) {
        this.purchasedItemlogicLocal = purchasedItemlogicLocal;
    }

    public PurchaseInfologicLocal getPurchaseInfologicLocal() {
        return purchaseInfologicLocal;
    }

    public void setPurchaseInfologicLocal(PurchaseInfologicLocal purchaseInfologicLocal) {
        this.purchaseInfologicLocal = purchaseInfologicLocal;
    }

    public GoodsdetailController() {
    }

//    public ArrayList<PurchaseInfo> SearchPurchaseInfo(Integer requisitionNo) {
//        ArrayList<PurchaseInfo> infoList = null;
//        purchaseInfo.setPurcaseRequisitionNo(requisitionNo);
//        infoList = purchaseInfologicLocal.searchPurchaseInfo(purchaseInfo);
//        return infoList;
//    }
    public void fetchPurchaseInfo(ValueChangeEvent event) {
        purchaseInfo.setPurcaseRequisitionNo(event.getNewValue().toString());
        purchaseInfo = purchaseInfologicLocal.fetchPurchaseInfo(purchaseInfo);
        purchasedItemList = purchasedItemlogicLocal.fetchPurchaseList(purchaseInfo);
        addToDataModel();
    }

    public void fetchItemInfo(ValueChangeEvent event) {
        itemType.setIditemType(Integer.parseInt(event.getNewValue().toString()));
        itemType = itembrandlogicLocal.fetchItemInfo(itemType);
        purchasedItem.setItemTypeId(itemType);
    }

    public List<PurchaseInfo> findAll() {
        return purchaseInfologicLocal.findAll();
    }

    public List<ItemType> findItemType() {
        return itembrandlogicLocal.findAll();
    }

    public void totalPrice() {
        if (!purchasedItem.getUnitPrice().equals(BigDecimal.ZERO) || !purchasedItem.getQtyReceived().equals(0) ) {
            purchasedItem.setTotalValue(purchasedItem.getUnitPrice().multiply(new BigDecimal(purchasedItem.getQtyReceived())));
        }
    }

    public void add() {
        try {
            if (!purchasedItem.getTotalValue().equals(BigDecimal.ZERO)) {
                purchasedItem.setItemName(itemType.getItemType());
                purchasedItem.setUnit(itemType.getUnit());
                purchasedItem.setPurchaseId(purchaseInfo);
                purchasedItem.setStatus("In Stock");
                purchasedItem.setQtyRemaining(purchasedItem.getQtyReceived());
                purchasedItemList.add(purchasedItem);
                addToDataModel();
            }
        }catch(NullPointerException nullx){ 
            throw nullx;
        }
    }

    public void addToDataModel() {
        purchasedModel = new ListDataModel(new ArrayList(purchasedItemList));
        itemType = new ItemType();
        purchasedItem = new PurchasedItem();
    }

    public void save() {
        purchasedItemList.stream().forEach((purchasedItemList1) -> {
            purchasedItemlogicLocal.edit(purchasedItemList1);
        });
    }

}
