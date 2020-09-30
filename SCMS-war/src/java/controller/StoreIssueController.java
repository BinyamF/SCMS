/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.Kommon.addFatalMessage;
import static controller.Kommon.addSuccessMessage;
import entity.BinCard;
import entity.ItemType;
import entity.PurchasedItem;
import entity.StockCard;
import entity.StoreIssue;
import entity.StoreIssueDetail;
import logic.StoreIssueBeanLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import logic.BinCardLogicLocal;
import logic.PurchasedItemlogicLocal;
import logic.StockCardLogicLocal;
import logic.StoreIssueDetailBeanLocal;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Billion
 */
@Named(value = "storeIssueController")
@ViewScoped
public class StoreIssueController implements Serializable {

    @Inject
    StoreIssue storeIssue;
    @Inject
    StoreIssueDetail storeIssueDetail;
    @Inject
    PurchasedItem purchasedItem;
    @Inject
    ItemType itemType;
    @Inject
    BinCard binCard;
    @Inject
    StockCard stockCard;

    @EJB
    StoreIssueBeanLocal storeIssueBeanLocal;
    @EJB
    StoreIssueDetailBeanLocal storeIssueDetailBeanLocal;
    @EJB
    PurchasedItemlogicLocal purchasedItemlogicLocal;
    @EJB
    BinCardLogicLocal binCardLogicLocal;
    @EJB
    StockCardLogicLocal stockCardLogicLocal;

    StoreIssueDetail selectedStrIssDtl;
    List<StoreIssue> storeIssueList;
    DataModel<StoreIssueDetail> storeIssueDataModel;
    List<PurchasedItem> purchasedItemList;
    List<PurchasedItem> availableItems = new ArrayList<>();

    int jumper = 0;
    int nonduplicationFlag = 0;
    int popEditFlag = 0;

    public StoreIssueController() {
    }

    public int getPopEditFlag() {
        return popEditFlag;
    }

    public void setPopEditFlag(int popEditFlag) {
        this.popEditFlag = popEditFlag;
    }

    public int getJumper() {
        return jumper;
    }

    public void setJumper(int jumper) {
        this.jumper = jumper;
    }

    public StoreIssueDetail getSelectedStrIssDtl() {
        return selectedStrIssDtl;
    }

    public void setSelectedStrIssDtl(StoreIssueDetail selectedStrIssDtl) {
        this.selectedStrIssDtl = selectedStrIssDtl;
    }

    public List<PurchasedItem> getAvailableItems() {
        if (availableItems == null) {
            availableItems = new ArrayList<>();
        }
        return availableItems;
    }

    public void setAvailableItems(List<PurchasedItem> availableItems) {
        this.availableItems = availableItems;
    }

    public StockCard getStockCard() {
        if (stockCard == null) {
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

    public StoreIssue getStoreIssue() {
        if (storeIssue == null) {
            storeIssue = new StoreIssue();
        }
        return storeIssue;
    }

    public void setStoreIssue(StoreIssue storeIssue) {
        this.storeIssue = storeIssue;
    }

    public StoreIssueDetail getStoreIssueDetail() {
        if (storeIssueDetail == null) {
            storeIssueDetail = new StoreIssueDetail();
        }
        return storeIssueDetail;
    }

    public void setStoreIssueDetail(StoreIssueDetail storeIssueDetail) {
        this.storeIssueDetail = storeIssueDetail;
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

    public DataModel<StoreIssueDetail> getStoreIssueDataModel() {
        if (storeIssueDataModel == null) {
            storeIssueDataModel = new ListDataModel();
        }
        return storeIssueDataModel;
    }

    public void setStoreIssueDataModel(DataModel<StoreIssueDetail> storeIssueDataModel) {
        this.storeIssueDataModel = storeIssueDataModel;
    }

    public List<StoreIssue> getStoreIssueList() {
        if (storeIssueList == null) {
            storeIssueList = new ArrayList<>();
        }
        return storeIssueList;
    }

    public void setStoreIssueList(List<StoreIssue> storeIssueList) {
        this.storeIssueList = storeIssueList;
    }

    public List<PurchasedItem> getStoreIssueDetailList() {
        if (purchasedItemList == null) {
            purchasedItemList = new ArrayList<>();
        }
        return purchasedItemList;
    }

    public void setPurchasedItemList(List<PurchasedItem> purchasedItemList) {
        this.purchasedItemList = purchasedItemList;
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

    public List<PurchasedItem> findItem(String purchasedItemName) {
        purchasedItem.setItemName(purchasedItemName);
        return purchasedItemlogicLocal.findItem(purchasedItem);
    }

    @PostConstruct
    public void init() {
        fetchItems();
    }

    public List<PurchasedItem> fetchItems() {
        availableItems = purchasedItemlogicLocal.fetchItems();
        setPopEditFlag(1);
        return availableItems;
    }

    public void fetchPurchaseInfo(ValueChangeEvent event) {
        purchasedItem.setIdpurchasedItem(Integer.parseInt(event.getNewValue().toString()));
        purchasedItem = purchasedItemlogicLocal.fetchPurchaseInfo(purchasedItem);
        storeIssueDetail.setItemId(purchasedItem.getItemTypeId());
        storeIssueDetail.setUnitPrice(purchasedItem.getUnitPrice());
        storeIssueDetail.setTotalPrice(null);
        storeIssueDetail.setRequestedQty(null);
        setPopEditFlag(0);
    }

    public void totalPrice() {
        storeIssueDetail.setTotalPrice(null);
        if ((storeIssueDetail.getRequestedQty().compareTo(purchasedItem.getQtyRemaining()) <= 0) && ((purchasedItem.getUnit().equalsIgnoreCase("Piece") || (purchasedItem.getUnit().equalsIgnoreCase("Pack"))) && (new BigDecimal(storeIssueDetail.getRequestedQty()).scale() <= 0))) {
            storeIssueDetail.setTotalPrice(purchasedItem.getUnitPrice().multiply(new BigDecimal(storeIssueDetail.getRequestedQty())));
            if ((storeIssueDetail.getRequestedQty().compareTo(purchasedItem.getQtyRemaining()) == 0)) {
                purchasedItem.setStatus("Out of Stock");
            }
        }
        if ((storeIssueDetail.getRequestedQty().compareTo(purchasedItem.getQtyRemaining()) <= 0) && (purchasedItem.getUnit().equalsIgnoreCase("Killo"))) {
            storeIssueDetail.setTotalPrice(purchasedItem.getUnitPrice().multiply(new BigDecimal(storeIssueDetail.getRequestedQty())));
            if ((storeIssueDetail.getRequestedQty().compareTo(purchasedItem.getQtyRemaining()) == 0)) {
                purchasedItem.setStatus("Out of Stock");
            }
        }
    }

    public void checkDuplication() {
        if (!(storeIssue.getStoreIssueDetailList() == null)) {
            if (!(storeIssue.getStoreIssueDetailList().isEmpty())) {     //If item is NOT EMPTY --> Get In The Loop!!!
                for (int i = 0; i < storeIssue.getStoreIssueDetailList().size(); i++) {
                    if (storeIssueDetail.getItemId().equals(storeIssue.getStoreIssueDetailList().get(i).getItemId()) && getPopEditFlag() == 0) {
                        addFatalMessage("Duplicated items are not allowed!");
                        nonduplicationFlag++;
                        setJumper(1);
                    }
                }
            }
            setPopEditFlag(0);
            if (getJumper() == 0) {
                addStoreIssueDetail();
                nonduplicationFlag = 1;
            }
            if (nonduplicationFlag == 0) {
                addStoreIssueDetail();
            }
        }
        nonduplicationFlag = 0;
    }

    public void addStoreIssueDetail() {
        if ((storeIssueDetail.getRequestedQty().compareTo(purchasedItem.getQtyRemaining()) <= 0)) {
            if (!(storeIssueDetail.getTotalPrice() == null)) {
                if (!storeIssueDetail.getTotalPrice().equals(BigDecimal.ZERO)) {
                    if (storeIssueDetail.getTotalPrice() != null) {
                        storeIssueDetail.setPurchasedFk(purchasedItem);
                        storeIssueDetail.setItemId(purchasedItem.getItemTypeId());
                        storeIssue.addStoreIssueDetail(storeIssueDetail);

                        purchasedItem.setQtyRemaining(purchasedItem.getQtyRemaining() - (storeIssueDetail.getRequestedQty()));
                        getStoreIssueDetailList().add(purchasedItem);
                        addToDataModel();
                        clearStoreIssueDetail();
                    }
                }
            }
        }
    }

    public void addToDataModel() {
        storeIssueDataModel = null;
        storeIssueDataModel = new ListDataModel(new ArrayList<>(storeIssue.getStoreIssueDetailList()));
    }

    public void clearStoreIssueDetail() {
        storeIssueDetail = new StoreIssueDetail();
        purchasedItem = new PurchasedItem();
        itemType = new ItemType();
    }

    public List<StoreIssue> findAll() {
        return storeIssueList = storeIssueBeanLocal.findAll();
    }

    public void getStoreIssueData(ValueChangeEvent event) {
        storeIssue.setIdstoreIssue(Integer.parseInt(event.getNewValue().toString()));
        storeIssue = storeIssueBeanLocal.fetchStoreIssueInfo(storeIssue);
        addToDataModel();
    }

    public void populate(SelectEvent event) {
        storeIssueDetail = (StoreIssueDetail) event.getObject();
        purchasedItem = storeIssueDetail.getPurchasedFk();
        storeIssue = storeIssueDetail.getStoreIssueFk();
        setPopEditFlag(1);
    }

    public void save() {
        storeIssueBeanLocal.edit(storeIssue);
//        storeIssue = new StoreIssue();
//        storeIssue = storeIssueBeanLocal.fetchMax();

        purchasedItemList.stream().forEach((purchasedItemList1) -> {
            purchasedItemlogicLocal.edit(purchasedItemList1);
        });

        for (int i = 0; i < storeIssue.getStoreIssueDetailList().size(); i++) {
            binCard = new BinCard();
            binCard.setItemId(storeIssue.getStoreIssueDetailList().get(i).getItemId());
            binCard = binCardLogicLocal.fetchBinCard(binCard);

            if (binCard == null) {
                binCard = new BinCard();
                binCard.setItemId(storeIssue.getStoreIssueDetailList().get(i).getItemId());
                binCard.setAmount(binCard.getAmount() - storeIssue.getStoreIssueDetailList().get(i).getRequestedQty());
                binCardLogicLocal.edit(binCard);
            } else {
                binCard.setItemId(storeIssue.getStoreIssueDetailList().get(i).getItemId());
                binCard.setAmount(binCard.getAmount() - storeIssue.getStoreIssueDetailList().get(i).getRequestedQty());
                binCardLogicLocal.edit(binCard);
            }
        }
        System.out.println("size   "+ storeIssue.getStoreIssueDetailList().size());
        for (int i = 0; i < storeIssue.getStoreIssueDetailList().size(); i++) {
            stockCard = new StockCard();
            stockCard.setGrnId(null);
            stockCard.setSiv(storeIssue.getStoreIssueDetailList().get(i));
            stockCard.setItemId(storeIssue.getStoreIssueDetailList().get(i).getItemId());
            stockCard.setAmount(storeIssue.getStoreIssueDetailList().get(i).getRequestedQty());
            stockCardLogicLocal.edit(stockCard);
        }
        addSuccessMessage("Data is successfully saved.");
    }
}
