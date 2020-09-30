/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.Kommon.addFatalMessage;
import entity.BinCard;
import entity.ItemType;
import entity.PurchaseInfo;
import entity.PurchasedItem;
import entity.StockCard;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import logic.BinCardLogicLocal;
import logic.ItemTypelogicLocal;
import logic.PurchaseInfologicLocal;
import logic.PurchasedItemlogicLocal;
import logic.StockCardLogicLocal;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Billion
 */
@Named(value = "goodsreceivedcontroller")
@ViewScoped
public class GoodsReceivedController implements Serializable {

    @Inject
    PurchaseInfo purchaseInfo;
    @Inject
    PurchasedItem purchaseItem;
    @Inject
    ItemType itemType;
    @Inject
    BinCard binCard;
    @Inject
    StockCard stockCard;

    @EJB
    PurchaseInfologicLocal purchaseInfoLogic;
    @EJB
    PurchasedItemlogicLocal purchasedItemlogicLocal;
    @EJB
    ItemTypelogicLocal itembrandlogicLocal;
    @EJB
    PurchaseInfologicLocal purchaseInfologicLocal;
    @EJB
    BinCardLogicLocal binCardLogicLocal;
    @EJB
    StockCardLogicLocal stockCardLogicLocal;        
    
    DataModel<PurchasedItem> purchasedItemModel;
    PurchasedItem selectedPurch;

    int jumper = 0;
    int nonduplicationFlag = 0;
    int qtyRecAjaxFlag = 0;
    int popEditFlag = 0;
    int updateStat = 0;

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

    public int getQtyRecAjaxFlag() {
        return qtyRecAjaxFlag;
    }

    public void setQtyRecAjaxFlag(int qtyRecAjaxFlag) {
        this.qtyRecAjaxFlag = qtyRecAjaxFlag;
    }

    public int getJumper() {
        return jumper;
    }

    public void setJumper(int jumper) {
        this.jumper = jumper;
    }

    public int getNonduplicationFlag() {
        return nonduplicationFlag;
    }

    public void setNonduplicationFlag(int nonduplicationFlag) {
        this.nonduplicationFlag = nonduplicationFlag;
    }

    public StockCard getStockCard() {
        if(stockCard == null){
            stockCard = new StockCard();
        }
        return stockCard;
    }

    public void setStockCard(StockCard stockCard) {
        this.stockCard = stockCard;
    }
    
    public BinCard getBinCard() {
        if (binCard == null) {
            binCard = new BinCard();
        }
        return binCard;
    }

    public void setBinCard(BinCard binCard) {
        this.binCard = binCard;
    }

    public PurchasedItem getSelectedPurch() {
        if (selectedPurch == null) {
            selectedPurch = new PurchasedItem();
        }
        return selectedPurch;
    }

    public void setSelectedPurch(PurchasedItem selectedPurch) {
        this.selectedPurch = selectedPurch;
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

    public ItemType getItemType() {
        if (itemType == null) {
            itemType = new ItemType();
        }
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public PurchasedItem getPurchaseItem() {
        if (purchaseItem == null) {
            purchaseItem = new PurchasedItem();
        }
        return purchaseItem;
    }

    public void setPurchaseItem(PurchasedItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public DataModel<PurchasedItem> getPurchasedItemModel() {
        return purchasedItemModel;
    }

    public void setPurchasedItemModel(DataModel<PurchasedItem> purchasedItemModel) {
        this.purchasedItemModel = purchasedItemModel;
    }

    public PurchaseInfologicLocal getPurchaseInfoLogic() {
        return purchaseInfoLogic;
    }

    public void setPurchaseInfoLogic(PurchaseInfologicLocal purchaseInfoLogic) {
        this.purchaseInfoLogic = purchaseInfoLogic;
    }

    public PurchasedItemlogicLocal getPurchasedItemlogicLocal() {
        return purchasedItemlogicLocal;
    }

    public void setPurchasedItemlogicLocal(PurchasedItemlogicLocal purchasedItemlogicLocal) {
        this.purchasedItemlogicLocal = purchasedItemlogicLocal;
    }

    public GoodsReceivedController() {
    }

// ******************************       IMPLEMENTATION STARTS HERE       ******************************
    public List<ItemType> findItemType() {
        return itembrandlogicLocal.findAll();
    }

    public void fetchItemInfo(ValueChangeEvent event) {
        itemType.setIditemType(Integer.parseInt(event.getNewValue().toString()));
        itemType = itembrandlogicLocal.fetchItemInfo(itemType);
        purchaseItem.setItemTypeId(itemType);
        purchaseItem.setQtyReceived(null);
        purchaseItem.setQtyRemaining(null);
        purchaseItem.setUnitPrice(null);
        purchaseItem.setTotalValue(null);
        purchaseItem.setRemark(null);
        setPopEditFlag(0);
    }

    public List<PurchaseInfo> findAll() {
        return purchaseInfologicLocal.findAll();
    }

    public void fetchPurchaseInfo(ValueChangeEvent event) {
        try {
            purchaseInfo.setId(Integer.parseInt(event.getNewValue().toString()));
            purchaseInfo = purchaseInfologicLocal.fetchPurchaseInfo(purchaseInfo);
            addToDataModel();
            setUpdateStat(1);
        } catch (NullPointerException ex) {
            throw ex;
        }
    }

    public void totalPrice() {
        if (!purchaseItem.getUnitPrice().equals(BigDecimal.ZERO) && !(purchaseItem.getQtyReceived() == 0)) {
            purchaseItem.setTotalValue(purchaseItem.getUnitPrice().multiply(new BigDecimal(purchaseItem.getQtyReceived())));
        }
    }

    public void valueReset() {
        purchaseItem.setUnitPrice(null);
        setQtyRecAjaxFlag(0);
    }

    public void populate(SelectEvent event) {
        purchaseItem = (PurchasedItem) event.getObject();
        itemType = purchaseItem.getItemTypeId();
        setQtyRecAjaxFlag(1);
        setPopEditFlag(1);
    }

    public void checkDuplication() {
        if (!(purchaseItem.getTotalValue() == null)) {
            if (!(purchaseInfo.getPurchasedItemList().isEmpty())) {     //If item is NOT EMPTY --> Get In The Loop!!!
                for (int i = 0; i < purchaseInfo.getPurchasedItemList().size(); i++) {
                    if (purchaseItem.getItemTypeId().equals(purchaseInfo.getPurchasedItemList().get(i).getItemTypeId()) && getPopEditFlag() == 0) {
                        addFatalMessage("Duplicated items are not allowed!");
                        nonduplicationFlag++;
                        setJumper(1);
                    }
                }
            }
            setPopEditFlag(0);
            if (getJumper() == 0) {
                addPurchasedItemDetail();
                setNonduplicationFlag(1);
            }
            if (getNonduplicationFlag() == 0) {
                addPurchasedItemDetail();
            }
        }
        setNonduplicationFlag(0);
    }

    public void addPurchasedItemDetail() {
        purchaseItem.setItemName(itemType.getItemType());
        purchaseItem.setUnit(itemType.getUnit());
        purchaseItem.setStatus("In Stock");
        purchaseItem.setQtyRemaining(purchaseItem.getQtyReceived());
        purchaseInfo.addPurchaseItemDetail(purchaseItem);
        addToDataModel();
        clearPurchaseItems();
    }

    private void addToDataModel() {
        purchasedItemModel = null;
        purchasedItemModel = new ListDataModel(new ArrayList<>(purchaseInfo.getPurchasedItemList()));
    }

    private void clearPurchaseItems() {
        purchaseItem = new PurchasedItem();
        itemType = new ItemType();
    }

    public void create() {
        if (getUpdateStat() == 1) {
            purchaseInfoLogic.edit(purchaseInfo);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Good Received Note ", "Data successfully updated."));
        } else {
            purchaseInfoLogic.create(purchaseInfo);

            for (int i = 0; i < purchaseInfo.getPurchasedItemList().size(); i++) {
                binCard = new BinCard();
                binCard.setItemId(purchaseInfo.getPurchasedItemList().get(i).getItemTypeId());
                binCard = binCardLogicLocal.fetchBinCard(binCard);
                
                if (binCard == null) {
                    binCard = new BinCard();
                    binCard.setItemId(purchaseInfo.getPurchasedItemList().get(i).getItemTypeId());
                    binCard.setAmount(purchaseInfo.getPurchasedItemList().get(i).getQtyReceived());
                    binCardLogicLocal.edit(binCard);
                } else {
                    binCard.setItemId(purchaseInfo.getPurchasedItemList().get(i).getItemTypeId());
                    binCard.setAmount(binCard.getAmount() + purchaseInfo.getPurchasedItemList().get(i).getQtyReceived());
                    binCardLogicLocal.edit(binCard);
                }
            }
            
            for (int i = 0; i < purchaseInfo.getPurchasedItemList().size(); i++) {
                stockCard = new StockCard();
                stockCard.setGrnId(purchaseInfo.getPurchasedItemList().get(i));
                stockCard.setSiv(null);
                stockCard.setItemId(purchaseInfo.getPurchasedItemList().get(i).getItemTypeId());
                stockCard.setAmount(purchaseInfo.getPurchasedItemList().get(i).getQtyReceived());
                stockCardLogicLocal.create(stockCard);
            }
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Good Received Note ", "Data is successfully saved."));
        }
    }

}
