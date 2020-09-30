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
@Table(name = "purchase_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseInfo.findAll", query = "SELECT p FROM PurchaseInfo p"),
    @NamedQuery(name = "PurchaseInfo.findById", query = "SELECT p FROM PurchaseInfo p WHERE p.id = :id"),
    @NamedQuery(name = "PurchaseInfo.findByPurcaseRequisitionNo", query = "SELECT p FROM PurchaseInfo p WHERE p.purcaseRequisitionNo = :purcaseRequisitionNo"),
    @NamedQuery(name = "PurchaseInfo.findByPurcaseRequisitionNoLike", query = "SELECT p FROM PurchaseInfo p WHERE p.purcaseRequisitionNo LIKE :purcaseRequisitionNo"),
    @NamedQuery(name = "PurchaseInfo.findByArticleDepartment", query = "SELECT p FROM PurchaseInfo p WHERE p.articleDepartment = :articleDepartment"),
    @NamedQuery(name = "PurchaseInfo.findByArticleDepartmentLike", query = "SELECT p FROM PurchaseInfo p WHERE p.articleDepartment LIKE :articleDepartment"),
    @NamedQuery(name = "PurchaseInfo.findBySupplier", query = "SELECT p FROM PurchaseInfo p WHERE p.supplier = :supplier"),
    @NamedQuery(name = "PurchaseInfo.findByPurchaseDate", query = "SELECT p FROM PurchaseInfo p WHERE p.purchaseDate = :purchaseDate"),
    @NamedQuery(name = "PurchaseInfo.findByReceiptNo", query = "SELECT p FROM PurchaseInfo p WHERE p.receiptNo = :receiptNo")})
public class PurchaseInfo implements Serializable {    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable=false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "article_department")
    private String articleDepartment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "supplier")
    private String supplier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "purchase_date")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "purcase_requisition_no")
    private String purcaseRequisitionNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "receipt_no")
    private String receiptNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseId")
    private List<PurchasedItem> purchasedItemList;

    public PurchaseInfo() {
    }

    public PurchaseInfo(Integer id) {
        this.id = id;
    }

    public PurchaseInfo(Integer id, String purcaseRequisitionNo, String articleDepartment, String supplier, String receiptNo, Date purchaseDate) {
        this.id = id;
        this.purcaseRequisitionNo = purcaseRequisitionNo;
        this.articleDepartment = articleDepartment;
        this.supplier = supplier;
        this.receiptNo = receiptNo;
        this.purchaseDate = purchaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurcaseRequisitionNo() {
        return purcaseRequisitionNo;
    }

    public void setPurcaseRequisitionNo(String purcaseRequisitionNo) {
        this.purcaseRequisitionNo = purcaseRequisitionNo;
    }

    public String getArticleDepartment() {
        return articleDepartment;
    }

    public void setArticleDepartment(String articleDepartment) {
        this.articleDepartment = articleDepartment;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }    
    
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    } 

    
    public void addPurchaseItemDetail(PurchasedItem purchaseDetail) {
        if (purchaseDetail.getPurchaseId()!= this) {
            this.getPurchasedItemList().add(purchaseDetail);
            purchaseDetail.setPurchaseId(this);
        }
    }

    @XmlTransient
    public List<PurchasedItem> getPurchasedItemList() {
        if(purchasedItemList == null){
            purchasedItemList = new ArrayList();
        }
        return purchasedItemList;
    }

    public void setPurchasedItemList(List<PurchasedItem> purchasedItemList) {
        this.purchasedItemList = purchasedItemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseInfo)) {
            return false;
        }
        PurchaseInfo other = (PurchaseInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return purcaseRequisitionNo;
    }

}
