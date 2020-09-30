/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Billion
 */
@Entity
@Table(name = "stock_card")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockCard.findAll", query = "SELECT s FROM StockCard s"),
    @NamedQuery(name = "StockCard.findByIdstockCard", query = "SELECT s FROM StockCard s WHERE s.idstockCard = :idstockCard"),
    @NamedQuery(name = "StockCard.findByAmount", query = "SELECT s FROM StockCard s WHERE s.amount = :amount")})
public class StockCard implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private Double amount;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idstock_card")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idstockCard;
    
    
    @JoinColumn(name = "grn_id", referencedColumnName = "idpurchased_item")
    @ManyToOne
    private PurchasedItem grnId;
    
    @JoinColumn(name = "siv", referencedColumnName = "idstore_issue_detail")
    @ManyToOne(cascade = CascadeType.ALL)
    private StoreIssueDetail siv;
    
    @JoinColumn(name = "item_id", referencedColumnName = "iditem_type")
    @ManyToOne(optional = false)
    private ItemType itemId;

    public StockCard() {
    }

    public StockCard(Integer idstockCard) {
        this.idstockCard = idstockCard;
    }

    public StockCard(Integer idstockCard, Double amount) {
        this.idstockCard = idstockCard;
        this.amount = amount;
    }

    public Integer getIdstockCard() {
        return idstockCard;
    }

    public void setIdstockCard(Integer idstockCard) {
        this.idstockCard = idstockCard;
    }


    public PurchasedItem getGrnId() {
        return grnId;
    }

    public void setGrnId(PurchasedItem grnId) {
        this.grnId = grnId;
    }

    public StoreIssueDetail getSiv() {
        return siv;
    }

    public void setSiv(StoreIssueDetail siv) {
        this.siv = siv;
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
        hash += (idstockCard != null ? idstockCard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockCard)) {
            return false;
        }
        StockCard other = (StockCard) object;
        if ((this.idstockCard == null && other.idstockCard != null) || (this.idstockCard != null && !this.idstockCard.equals(other.idstockCard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StockCard[ idstockCard=" + idstockCard + " ]";
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
}
