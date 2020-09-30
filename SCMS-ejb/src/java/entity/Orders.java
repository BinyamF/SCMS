/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByNonFiscalId", query = "SELECT o FROM Orders o WHERE o.nonFiscalId = :nonFiscalId"),
    @NamedQuery(name = "Orders.findByIdOrders", query = "SELECT o FROM Orders o WHERE o.idOrders = :idOrders")})
public class Orders implements Serializable {    
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private Double amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idOrders")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrders;
    @JoinColumn(name = "products_id", referencedColumnName = "idproducts")
    @ManyToOne(optional = false)
    private Products productsId;
    @JoinColumn(name = "non_fiscal_id", referencedColumnName = "idOrders")
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private NonFiscalReceipt nonFiscalId;

    public Orders() {
    }

    public Orders(Integer idOrders) {
        this.idOrders = idOrders;
    }

    public Integer getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(Integer idOrders) {
        this.idOrders = idOrders;
    }

    public Products getProductsId() {
        return productsId;
    }

    public void setProductsId(Products productsId) {
        this.productsId = productsId;
    }

    public NonFiscalReceipt getNonFiscalId() {
        return nonFiscalId;
    }

    public void setNonFiscalId(NonFiscalReceipt nonFiscalId) {
        this.nonFiscalId = nonFiscalId;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrders != null ? idOrders.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.idOrders == null && other.idOrders != null) || (this.idOrders != null && !this.idOrders.equals(other.idOrders))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nonFiscalId.getReferenceNo();
    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
