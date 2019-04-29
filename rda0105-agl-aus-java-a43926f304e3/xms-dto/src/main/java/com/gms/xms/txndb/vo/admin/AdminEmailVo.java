package com.gms.xms.txndb.vo.admin;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Date;

/**
 * @author TANDT
 */
public class AdminEmailVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private Integer id;
    private String email;
    private Date actionDate;
    private Date sortingDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Date getSortingDate() {
        return sortingDate;
    }

    public void setSortingDate(Date sortingDate) {
        this.sortingDate = sortingDate;
    }

    @Override
    public String toString() {
        return "AdminEmailVo [id=" + id + ", email=" + email + ", actionDate=" + actionDate + ", sortingDate=" + sortingDate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actionDate == null) ? 0 : actionDate.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((sortingDate == null) ? 0 : sortingDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AdminEmailVo other = (AdminEmailVo) obj;
        if (actionDate == null) {
            if (other.actionDate != null)
                return false;
        } else if (!actionDate.equals(other.actionDate))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (sortingDate == null) {
            if (other.sortingDate != null)
                return false;
        } else if (!sortingDate.equals(other.sortingDate))
            return false;
        return true;
    }

}
