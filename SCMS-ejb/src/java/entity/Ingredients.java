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
@Table(name = "ingredients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredients.findAll", query = "SELECT i FROM Ingredients i"),
    @NamedQuery(name = "Ingredients.findByIdingredients", query = "SELECT i FROM Ingredients i WHERE i.idingredients = :idingredients"),
    @NamedQuery(name = "Ingredients.findByProductId", query = "SELECT i FROM Ingredients i WHERE i.productid = :productid"),
    @NamedQuery(name = "Ingredients.findByAmount", query = "SELECT i FROM Ingredients i WHERE i.amount = :amount")})
public class Ingredients implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private Double amount;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idingredients")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idingredients;
    @JoinColumn(name = "productid", referencedColumnName = "idproducts")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Products productid;
    @JoinColumn(name = "itemtypeid", referencedColumnName = "iditem_type")
    @ManyToOne(optional = false)
    private ItemType itemtypeid;

    public Ingredients() {
    }

    public Ingredients(Integer idingredients) {
        this.idingredients = idingredients;
    }

    public Ingredients(Integer idingredients, Double amount) {
        this.idingredients = idingredients;
        this.amount = amount;
    }

    public Integer getIdingredients() {
        return idingredients;
    }

    public void setIdingredients(Integer idingredients) {
        this.idingredients = idingredients;
    }


    public Products getProductid() {
        return productid;
    }

    public void setProductid(Products productid) {
        this.productid = productid;
    }

    public ItemType getItemtypeid() {
        return itemtypeid;
    }

    public void setItemtypeid(ItemType itemtypeid) {
        this.itemtypeid = itemtypeid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idingredients != null ? idingredients.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredients)) {
            return false;
        }
        Ingredients other = (Ingredients) object;
        if ((this.idingredients == null && other.idingredients != null) || (this.idingredients != null && !this.idingredients.equals(other.idingredients))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ingredients[ idingredients=" + idingredients + " ]";
    }    
}
