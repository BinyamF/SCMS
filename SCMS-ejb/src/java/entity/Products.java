/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByIdproducts", query = "SELECT p FROM Products p WHERE p.idproducts = :idproducts"),
    @NamedQuery(name = "Products.findByProductName", query = "SELECT p FROM Products p WHERE p.productName = :productName"),
    @NamedQuery(name = "Products.findByProductPrice", query = "SELECT p FROM Products p WHERE p.productPrice = :productPrice")})
public class Products implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_price")
    private BigDecimal productPrice;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idproducts", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproducts;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "product_name")
    @NotNull
    private String productName;
    @OneToMany(mappedBy = "productid")
    private List<Ingredients> ingredientsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productsId")
    private List<Orders> ordersList;

    public Products() {
    }

    public Products(Integer idproducts) {
        this.idproducts = idproducts;
    }

    public Products(Integer idproducts, String productName, BigDecimal productPrice) {
        this.idproducts = idproducts;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Integer getIdproducts() {
        return idproducts;
    }

    public void setIdproducts(Integer idproducts) {
        this.idproducts = idproducts;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @XmlTransient
    public List<Ingredients> getIngredientsList() {
        if(ingredientsList == null){
            ingredientsList = new ArrayList<>();
        }
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducts != null ? idproducts.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.idproducts == null && other.idproducts != null) || (this.idproducts != null && !this.idproducts.equals(other.idproducts))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return productName;
    }
    
    public void addIngredientDetail(Ingredients ingredientsDetail){
        if(ingredientsDetail.getProductid() != this){
            this.getIngredientsList().add(ingredientsDetail);
            ingredientsDetail.setProductid(this);
        }
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }    

}
