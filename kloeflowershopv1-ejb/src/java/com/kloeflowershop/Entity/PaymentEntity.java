/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Terence
 */
@Entity
public class PaymentEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean isPaid = false;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTime;

    /**
     * Get the value of dateTime
     *
     * @return the value of dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Set the value of dateTime
     *
     * @param dateTime new value of dateTime
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }


    /**
     * Get the value of isPaid
     *
     * @return the value of isPaid
     */
    public boolean isIsPaid() {
        return isPaid;
    }

    /**
     * Set the value of isPaid
     *
     * @param isPaid new value of isPaid
     */
    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof PaymentEntity)) {
            return false;
        }
        PaymentEntity other = (PaymentEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kloeflowershop.Entity.PaymentEntity[ id=" + id + " ]";
    }
    
}
