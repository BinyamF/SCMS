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
@Table(name = "bin_card")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BinCard.findAll", query = "SELECT b FROM BinCard b"),
    @NamedQuery(name = "BinCard.findByIdbinCard", query = "SELECT b FROM BinCard b WHERE b.idbinCard = :idbinCard"),
    @NamedQuery(name = "BinCard.findByItemType", query = "SELECT b FROM BinCard b WHERE b.itemId = :itemId"),
    @NamedQuery(name = "BinCard.findByAvailableItems", query = "SELECT b FROM BinCard b WHERE b.amount > 0"),
    @NamedQuery(name = "BinCard.findByAmount", query = "SELECT b FROM BinCard b WHERE b.amount = :amount")})
public class BinCard implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private Double amount;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idbin_card")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idbinCard;
    @JoinColumn(name = "item_id", referencedColumnName = "iditem_type")
    @ManyToOne(optional = false)
    private ItemType itemId;

    public BinCard() {
    }

    public BinCard(Integer idbinCard) {
        this.idbinCard = idbinCard;
    }

    public BinCard(Integer idbinCard, Double amount) {
        this.idbinCard = idbinCard;
        this.amount = amount;
    }

    public Integer getIdbinCard() {
        return idbinCard;
    }

    public void setIdbinCard(Integer idbinCard) {
        this.idbinCard = idbinCard;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
        hash += (idbinCard != null ? idbinCard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BinCard)) {
            return false;
        }
        BinCard other = (BinCard) object;
        if ((this.idbinCard == null && other.idbinCard != null) || (this.idbinCard != null && !this.idbinCard.equals(other.idbinCard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BinCard[ idbinCard=" + idbinCard + " ]";
    }
    
}
