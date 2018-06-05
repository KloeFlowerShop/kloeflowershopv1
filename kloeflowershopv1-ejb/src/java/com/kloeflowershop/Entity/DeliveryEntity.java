/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author yy
 */
@Entity
public class DeliveryEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTime;
    private String assignedIC;
    private String assignedICContact;
    @OneToOne(cascade = {CascadeType.ALL})
    private OrderEntity order;

    /**
     * Get the value of order
     *
     * @return the value of order
     */
    public OrderEntity getOrder() {
        return order;
    }

    /**
     * Set the value of order
     *
     * @param order new value of order
     */
    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    /**
     * Get the value of assignedICContact
     *
     * @return the value of assignedICContact
     */
    public String getAssignedICContact() {
        return assignedICContact;
    }

    /**
     * Set the value of assignedICContact
     *
     * @param assignedICContact new value of assignedICContact
     */
    public void setAssignedICContact(String assignedICContact) {
        this.assignedICContact = assignedICContact;
    }


    /**
     * Get the value of assignedIC
     *
     * @return the value of assignedIC
     */
    public String getAssignedIC() {
        return assignedIC;
    }

    /**
     * Set the value of assignedIC
     *
     * @param assignedIC new value of assignedIC
     */
    public void setAssignedIC(String assignedIC) {
        this.assignedIC = assignedIC;
    }

    

    /**
     * Get the value of Datetime
     *
     * @return the value of Datetime
     */
    public Date getDatetime() {
        return dateTime;
    }

    /**
     * Set the value of Datetime
     *
     * @param Datetime new value of Datetime
     */
    public void setDatetime(Date Datetime) {
        this.dateTime = Datetime;
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
        if (!(object instanceof DeliveryEntity)) {
            return false;
        }
        DeliveryEntity other = (DeliveryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kloeflowershop.Entity.DeliveryEntity[ id=" + id + " ]";
    }
    
}
