/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipientName;
    private int recipientContact;

    /**
     * Get the value of recipientContact
     *
     * @return the value of recipientContact
     */
    public int getRecipientContact() {
        return recipientContact;
    }

    /**
     * Set the value of recipientContact
     *
     * @param recipientContact new value of recipientContact
     */
    public void setRecipientContact(int recipientContact) {
        this.recipientContact = recipientContact;
    }


    /**
     * Get the value of recipientName
     *
     * @return the value of recipientName
     */
    public String getRecipientName() {
        return recipientName;
    }

    /**
     * Set the value of recipientName
     *
     * @param recipientName new value of recipientName
     */
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
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
        if (!(object instanceof OrderEntity)) {
            return false;
        }
        OrderEntity other = (OrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kloeflowershop.Entity.OrderEntity[ id=" + id + " ]";
    }
    
}
