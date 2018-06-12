/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private LocalDateTime deliveryDateTime;
    private String assignedIC;
    private int assignedICContact;
    private boolean isDelivered = false;

    /**
     * Get the value of isDelivered
     *
     * @return the value of isDelivered
     */
    public boolean isIsDelivered() {
        return isDelivered;
    }

    /**
     * Set the value of isDelivered
     *
     * @param isDelivered new value of isDelivered
     */
    public void setIsDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }



    /**
     * Get the value of assignedICContact
     *
     * @return the value of assignedICContact
     */
    public int getAssignedICContact() {
        return assignedICContact;
    }

    /**
     * Set the value of assignedICContact
     *
     * @param assignedICContact new value of assignedICContact
     */
    public void setAssignedICContact(int assignedICContact) {
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
    public LocalDateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    /**
     * Set the value of Datetime
     *
     * @param Datetime new value of Datetime
     */
    public void setDeliveryDateTime(LocalDateTime Datetime) {
        this.deliveryDateTime = Datetime;
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
