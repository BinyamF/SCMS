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
@Table(name = "store_issue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreIssue.findAll", query = "SELECT s FROM StoreIssue s"),
    @NamedQuery(name = "StoreIssue.findByIdstoreIssue", query = "SELECT s FROM StoreIssue s WHERE s.idstoreIssue = :idstoreIssue"),
    @NamedQuery(name = "StoreIssue.findByDate", query = "SELECT s FROM StoreIssue s WHERE s.reqdate = :reqdate"),
    @NamedQuery(name = "StoreIssue.findByRequester", query = "SELECT s FROM StoreIssue s WHERE s.requester = :requester"),
    @NamedQuery(name = "StoreIssue.findByIssuer", query = "SELECT s FROM StoreIssue s WHERE s.issuer = :issuer"),
    @NamedQuery(name = "StoreIssue.findByItemHolder", query = "SELECT s FROM StoreIssue s WHERE s.itemHolder  = :itemHolder"),
    @NamedQuery(name = "StoreIssue.findByIssueNo", query = "SELECT s FROM StoreIssue s WHERE s.issueNo = :issueNo")})
public class StoreIssue implements Serializable {    
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstore_issue")
    private Integer idstoreIssue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "issue_no")
    private String issueNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "item_holder")
    private String itemHolder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reqdate")
    @Temporal(TemporalType.DATE)
    private Date reqdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "requester")
    private String requester;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "issuer")
    private String issuer;    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeIssueFk")
    private List<StoreIssueDetail> storeIssueDetailList;
    
    public StoreIssue() {
    }

    public StoreIssue(Integer idstoreIssue) {
        this.idstoreIssue = idstoreIssue;
    }

    public StoreIssue(Integer idstoreIssue, Date reqdate, String requester, String issuer, String issueNo) {
        this.idstoreIssue = idstoreIssue;
        this.reqdate = reqdate;
        this.requester = requester;
        this.issuer = issuer;
        this.issueNo = issueNo;
    }

    public Integer getIdstoreIssue() {
        return idstoreIssue;
    }

    public void setIdstoreIssue(Integer idstoreIssue) {
        this.idstoreIssue = idstoreIssue;
    }

    public String getItemHolder() {
        return itemHolder;
    }

    public void setItemHolder(String itemHolder) {
        this.itemHolder = itemHolder;
    }

    public Date getReqdate() {
        return reqdate;
    }

    public void setReqdate(Date reqdate) {
        this.reqdate = reqdate;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstoreIssue != null ? idstoreIssue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreIssue)) {
            return false;
        }
        StoreIssue other = (StoreIssue) object;
        if ((this.idstoreIssue == null && other.idstoreIssue != null) || (this.idstoreIssue != null && !this.idstoreIssue.equals(other.idstoreIssue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return issueNo;
    }
    
    public void addStoreIssueDetail(StoreIssueDetail storeIssueDetail) {
        if (storeIssueDetail.getStoreIssueFk()!= this) {
            this.getStoreIssueDetailList().add(storeIssueDetail);
            storeIssueDetail.setStoreIssueFk(this);
        }
    }

    @XmlTransient
    public List<StoreIssueDetail> getStoreIssueDetailList() {
        if(storeIssueDetailList == null){
            storeIssueDetailList = new ArrayList();
        }
        return storeIssueDetailList;
    }

    public void setStoreIssueDetailList(List<StoreIssueDetail> storeIssueDetailList) {
        this.storeIssueDetailList = storeIssueDetailList;
    }

}
