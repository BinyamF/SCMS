/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Billion
 */
@Entity
@Table(name = "purchased_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchasedItem.findAll", query = "SELECT p FROM PurchasedItem p"),
    @NamedQuery(name = "PurchasedItem.findByIdpurchasedItem", query = "SELECT p FROM PurchasedItem p WHERE p.idpurchasedItem = :idpurchasedItem"),
    @NamedQuery(name = "PurchasedItem.findByItemName", query = "SELECT p FROM PurchasedItem p WHERE p.itemName = :itemName"),
    @NamedQuery(name = "PurchasedItem.findByItemNameLike", query = "SELECT p FROM PurchasedItem p WHERE UPPER (p.itemName) LIKE :itemName AND p.status = :status"),
    @NamedQuery(name = "PurchasedItem.findByUnit", query = "SELECT p FROM PurchasedItem p WHERE p.unit = :unit"),
    @NamedQuery(name = "PurchasedItem.findByQtyReceived", query = "SELECT p FROM PurchasedItem p WHERE p.qtyReceived = :qtyReceived"),
    @NamedQuery(name = "PurchasedItem.findByUnitPrice", query = "SELECT p FROM PurchasedItem p WHERE p.unitPrice = :unitPrice"),
    @NamedQuery(name = "PurchasedItem.findByTotalValue", query = "SELECT p FROM PurchasedItem p WHERE p.totalValue = :totalValue"),
    @NamedQuery(name = "PurchasedItem.findByRemark", query = "SELECT p FROM PurchasedItem p WHERE p.remark = :remark"),
    @NamedQuery(name = "PurchasedItem.findByStatus", query = "SELECT p FROM PurchasedItem p WHERE p.status = :status"),
    @NamedQuery(name = "PurchasedItem.findByPurchaseId", query = "SELECT p FROM PurchasedItem p WHERE p.purchaseId = :purchaseId"),
    @NamedQuery(name = "PurchasedItem.findByQtyRemaining", query = "SELECT p FROM PurchasedItem p WHERE p.qtyRemaining > 0.0"),})
public class PurchasedItem implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty_received")
    private Double qtyReceived;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_value")
    private BigDecimal totalValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty_remaining")
    private Double qtyRemaining;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpurchased_item")
    private Integer idpurchasedItem;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "item_name")
    private String itemName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "unit")
    private String unit;
    
    @Basic(optional = false)
    @Column(name = "remark")
    private String remark;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;
    
    
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PurchaseInfo purchaseId;
    
    @JoinColumn(name = "item_type_id", referencedColumnName = "iditem_type")
    @ManyToOne(fetch = FetchType.LAZY)
    private ItemType itemTypeId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchasedFk")
    private List<StoreIssueDetail> storeIssueDetailList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grnId")
    private List<StockCard> stockCardList;
    
    public PurchasedItem() {
    }

    public PurchasedItem(Integer idpurchasedItem) {
        this.idpurchasedItem = idpurchasedItem;
    }

    public PurchasedItem(Integer idpurchasedItem, String itemName, String unit, Double qtyReceived, Double qtyRemaining, BigDecimal unitPrice, BigDecimal totalValue, String remark) {
        this.idpurchasedItem = idpurchasedItem;
        this.itemName = itemName;
        this.unit = unit;
        this.qtyReceived = qtyReceived;
        this.unitPrice = unitPrice;
        this.totalValue = totalValue;
        this.remark = remark;
        this.qtyRemaining = qtyRemaining;
    }

    public Integer getIdpurchasedItem() {
        return idpurchasedItem;
    }

    public void setIdpurchasedItem(Integer idpurchasedItem) {
        this.idpurchasedItem = idpurchasedItem;
    }

    public Double getQtyReceived() {
        return qtyReceived;
    }

    public void setQtyReceived(Double qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public PurchaseInfo getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(PurchaseInfo purchaseId) {
        this.purchaseId = purchaseId;
    }

    public ItemType getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(ItemType itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getQtyRemaining() {
        return qtyRemaining;
    }

    public void setQtyRemaining(Double qtyRemaining) {
        this.qtyRemaining = qtyRemaining;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpurchasedItem != null ? idpurchasedItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchasedItem)) {
            return false;
        }
        PurchasedItem other = (PurchasedItem) object;
        if ((this.idpurchasedItem == null && other.idpurchasedItem != null) || (this.idpurchasedItem != null && !this.idpurchasedItem.equals(other.idpurchasedItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return itemName + " - " + itemTypeId.getItem();
    }

    @XmlTransient
    public List<StoreIssueDetail> getStoreIssueDetailList() {
        return storeIssueDetailList;
    }

    public void setStoreIssueDetailList(List<StoreIssueDetail> storeIssueDetailList) {
        this.storeIssueDetailList = storeIssueDetailList;
    }
    
    @XmlTransient
    public List<StockCard> getStockCardList() {
        return stockCardList;
    }

    public void setStockCardList(List<StockCard> stockCardList) {
        this.stockCardList = stockCardList;
    }
}
