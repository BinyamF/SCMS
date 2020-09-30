/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Billion
 */
@Entity
@Table(name = "non_fiscal_receipt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NonFiscalReceipt.findAll", query = "SELECT n FROM NonFiscalReceipt n"),
    @NamedQuery(name = "NonFiscalReceipt.findByIdOrders", query = "SELECT n FROM NonFiscalReceipt n WHERE n.idOrders = :idOrders"),
    @NamedQuery(name = "NonFiscalReceipt.findByCashier", query = "SELECT n FROM NonFiscalReceipt n WHERE n.cashier = :cashier"),
    @NamedQuery(name = "NonFiscalReceipt.findByWaiter", query = "SELECT n FROM NonFiscalReceipt n WHERE n.waiter = :waiter"),
    @NamedQuery(name = "NonFiscalReceipt.findByNonFicalNo", query = "SELECT n FROM NonFiscalReceipt n WHERE n.nonFicalNo = :nonFicalNo"),
    @NamedQuery(name = "NonFiscalReceipt.findByDate", query = "SELECT n FROM NonFiscalReceipt n WHERE n.rectdate = :redate"),
    @NamedQuery(name = "NonFiscalReceipt.findByReferenceNo", query = "SELECT n FROM NonFiscalReceipt n WHERE n.referenceNo = :referenceNo")})
public class NonFiscalReceipt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idOrders")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrders;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cashier")
    private String cashier;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "waiter")
    private String waiter;
    @Basic(optional = false)
    @NotNull
    @Column(name = "non_fical_no")
    private String nonFicalNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rectdate")
    @Temporal(TemporalType.DATE)
    private Date rectdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reference_no")
    private String referenceNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nonFiscalId")
    private List<Orders> ordersList;

    public NonFiscalReceipt() {
    }

    public NonFiscalReceipt(Integer idOrders) {
        this.idOrders = idOrders;
    }

    public NonFiscalReceipt(Integer idOrders, String cashier, String waiter, String nonFicalNo, Date rectdate, String referenceNo) {
        this.idOrders = idOrders;
        this.cashier = cashier;
        this.waiter = waiter;
        this.nonFicalNo = nonFicalNo;
        this.rectdate = rectdate;
        this.referenceNo = referenceNo;
    }

    public Integer getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(Integer idOrders) {
        this.idOrders = idOrders;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public String getNonFicalNo() {
        return nonFicalNo;
    }

    public void setNonFicalNo(String nonFicalNo) {
        this.nonFicalNo = nonFicalNo;
    }

    public Date getRecdate() {
        return rectdate;
    }

    public void setRecdate(Date rectdate) {
        this.rectdate = rectdate;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public void addOrdersDetail(Orders ordersDetail) {
        if (ordersDetail.getNonFiscalId()!= this) {
            this.getOrdersList().add(ordersDetail);;//
            ordersDetail.setNonFiscalId(this);
        }
    }
    
    @XmlTransient
    public List<Orders> getOrdersList() {
    if(ordersList == null){
        ordersList = new ArrayList();
    }
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
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
        if (!(object instanceof NonFiscalReceipt)) {
            return false;
        }
        NonFiscalReceipt other = (NonFiscalReceipt) object;
        if ((this.idOrders == null && other.idOrders != null) || (this.idOrders != null && !this.idOrders.equals(other.idOrders))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  nonFicalNo;
    }
    
}
