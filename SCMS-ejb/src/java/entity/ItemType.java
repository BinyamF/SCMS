/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "item_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemType.findAll", query = "SELECT i FROM ItemType i"),
    @NamedQuery(name = "ItemType.findByIditemType", query = "SELECT i FROM ItemType i WHERE i.iditemType = :iditemType"),
    @NamedQuery(name = "ItemType.findByItemType", query = "SELECT i FROM ItemType i WHERE i.itemType = :itemType"),
    @NamedQuery(name = "ItemType.findByUsers", query = "SELECT i FROM ItemType i WHERE i.users = :users"),
    @NamedQuery(name = "ItemType.findByUnit", query = "SELECT i FROM ItemType i WHERE i.unit = :unit")})
public class ItemType implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "iditem_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iditemType;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "item")
    private String item;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "item_type")
    private String itemType;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "unit")
    private String unit;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "users")
    private String users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private List<StoreIssueDetail> storeIssueDetailList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemtypeid")
    private List<Ingredients> ingredientsList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemTypeId")
    private List<PurchasedItem> purchasedItemList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private List<StockCard> stockCardList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId")
    private List<BinCard> binCardList;
    
    public ItemType() {
    }

    public ItemType(Integer iditemType) {
        this.iditemType = iditemType;
    }

    public ItemType(Integer iditemType, String itemType, String itemBrand, String unit, String users) {
        this.iditemType = iditemType;
        this.itemType = itemType;
        this.unit = unit;
        this.users = users;
    }

    public Integer getIditemType() {
        return iditemType;
    }

    public void setIditemType(Integer iditemType) {
        this.iditemType = iditemType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }    

    @XmlTransient
    public List<PurchasedItem> getPurchasedItemList() {
        return purchasedItemList;
    }

    public void setPurchasedItemList(List<PurchasedItem> purchasedItemList) {
        this.purchasedItemList = purchasedItemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iditemType != null ? iditemType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemType)) {
            return false;
        }
        ItemType other = (ItemType) object;
        if ((this.iditemType == null && other.iditemType != null) || (this.iditemType != null && !this.iditemType.equals(other.iditemType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return itemType + " - " + item;
    }

    @XmlTransient
    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @XmlTransient
    public List<StoreIssueDetail> getStoreIssueDetailList() {
        return storeIssueDetailList;
    }

    public void setStoreIssueDetailList(List<StoreIssueDetail> storeIssueDetailList) {
        this.storeIssueDetailList = storeIssueDetailList;
    }

    @XmlTransient
    public List<BinCard> getBinCardList() {
        return binCardList;
    }

    public void setBinCardList(List<BinCard> binCardList) {
        this.binCardList = binCardList;
    }

    @XmlTransient
    public List<StockCard> getStockCardList() {
        return stockCardList;
    }

    public void setStockCardList(List<StockCard> stockCardList) {
        this.stockCardList = stockCardList;
    }

}
