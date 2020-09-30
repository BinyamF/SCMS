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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Billion
 */
@Entity
@Table(name = "store_issue_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreIssueDetail.findAll", query = "SELECT s FROM StoreIssueDetail s"),
    @NamedQuery(name = "StoreIssueDetail.findByIdstoreIssueDetail", query = "SELECT s FROM StoreIssueDetail s WHERE s.idstoreIssueDetail = :idstoreIssueDetail"),
    @NamedQuery(name = "StoreIssueDetail.findByRequestedQty", query = "SELECT s FROM StoreIssueDetail s WHERE s.requestedQty = :requestedQty"),
    @NamedQuery(name = "StoreIssueDetail.findByUnitPrice", query = "SELECT s FROM StoreIssueDetail s WHERE s.unitPrice = :unitPrice"),
    @NamedQuery(name = "StoreIssueDetail.findByTotalPrice", query = "SELECT s FROM StoreIssueDetail s WHERE s.totalPrice = :totalPrice"),
    @NamedQuery(name = "StoreIssueDetail.findByPurchasedFk", query = "SELECT s FROM StoreIssueDetail s WHERE s.purchasedFk = :purchasedFk"),
    @NamedQuery(name = "StoreIssueDetail.findBystoreIssueFk", query = "SELECT s FROM StoreIssueDetail s WHERE s.storeIssueFk = :storeIssueFk"),
    @NamedQuery(name = "StoreIssueDetail.findByItemId", query = "SELECT s FROM StoreIssueDetail s WHERE s.itemId = :itemId")})
public class StoreIssueDetail implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "requested_qty")
    private Double requestedQty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idstore_issue_detail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idstoreIssueDetail;

    @JoinColumn(name = "purchased_fk", referencedColumnName = "idpurchased_item")
    @ManyToOne(optional = false)
    private PurchasedItem purchasedFk;

    @JoinColumn(name = "item_id", referencedColumnName = "iditem_type")
    @ManyToOne(optional = false)
    private ItemType itemId;

    @JoinColumn(name = "store_issue_fk", referencedColumnName = "idstore_issue")
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    private StoreIssue storeIssueFk;

    @OneToMany(mappedBy = "siv")
    private List<StockCard> stockCardList;

    public StoreIssueDetail() {
    }

    public StoreIssueDetail(Integer idstoreIssueDetail) {
        this.idstoreIssueDetail = idstoreIssueDetail;
    }

    public StoreIssueDetail(Integer idstoreIssueDetail, Double requestedQty, BigDecimal unitPrice, BigDecimal totalPrice) {
        this.idstoreIssueDetail = idstoreIssueDetail;
        this.requestedQty = requestedQty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;

    }

    public Integer getIdstoreIssueDetail() {
        return idstoreIssueDetail;
    }

    public void setIdstoreIssueDetail(Integer idstoreIssueDetail) {
        this.idstoreIssueDetail = idstoreIssueDetail;
    }

    public Double getRequestedQty() {
        return requestedQty;
    }

    public void setRequestedQty(Double requestedQty) {
        this.requestedQty = requestedQty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public StoreIssue getStoreIssueFk() {
        return storeIssueFk;
    }

    public void setStoreIssueFk(StoreIssue storeIssueFk) {
        this.storeIssueFk = storeIssueFk;
    }

    public PurchasedItem getPurchasedFk() {
        return purchasedFk;
    }

    public void setPurchasedFk(PurchasedItem purchasedFk) {
        this.purchasedFk = purchasedFk;
    }

    public ItemType getItemId() {
        return itemId;
    }

    public void setItemId(ItemType itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstoreIssueDetail != null ? idstoreIssueDetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreIssueDetail)) {
            return false;
        }
        StoreIssueDetail other = (StoreIssueDetail) object;
        if ((this.idstoreIssueDetail == null && other.idstoreIssueDetail != null) || (this.idstoreIssueDetail != null && !this.idstoreIssueDetail.equals(other.idstoreIssueDetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StoreIssueDetail[ idstoreIssueDetail=" + idstoreIssueDetail + " ]";
    }

    @XmlTransient
    public List<StockCard> getStockCardList() {
        return stockCardList;
    }

    public void setStockCardList(List<StockCard> stockCardList) {
        this.stockCardList = stockCardList;
    }

}
