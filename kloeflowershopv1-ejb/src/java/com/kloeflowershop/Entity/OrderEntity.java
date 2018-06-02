/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
    private Date dateTimeOfOrder;
    private boolean isSubscription; 
    private String remark;
    @OneToOne(cascade={CascadeType.ALL})
    private AddressEntity address;
    @OneToOne(cascade={CascadeType.ALL})
    private DeliveryEntity delivery;
    @OneToOne(cascade={CascadeType.ALL})
    private SubscriptionEntity subscription;
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="PRODUCT_ORDER")
    private List<ProductEntity> products = new ArrayList<ProductEntity>();
    private HashMap<ProductEntity, Integer> productQuantity = new HashMap<ProductEntity, Integer>();

    /**
     * Get the value of productQuantity
     *
     * @return the value of productQuantity
     */
    public HashMap<ProductEntity, Integer> getProductQuantity() {
        return productQuantity;
    }

    /**
     * Set the value of productQuantity
     *
     * @param productQuantity new value of productQuantity
     */
    public void setProductQuantity(HashMap<ProductEntity, Integer> productQuantity) {
        this.productQuantity = productQuantity;
    }


    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    
    /**
     * Get the value of subscription
     *
     * @return the value of subscription
     */
    public SubscriptionEntity getSubscription() {
        return subscription;
    }

    /**
     * Set the value of subscription
     *
     * @param subscription new value of subscription
     */
    public void setSubscription(SubscriptionEntity subscription) {
        this.subscription = subscription;
    }

    
     /**
     * Set the value of remark
     *
     * @param remark new value of remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    

    /**
     * Get the value of delivery
     *
     * @return the value of delivery
     */
    public DeliveryEntity getDelivery() {
        return delivery;
    }

    /**
     * Set the value of delivery
     *
     * @param delivery new value of delivery
     */
    public void setDelivery(DeliveryEntity delivery) {
        this.delivery = delivery;
    }
/**
     * Get the value of remark
     *
     * @return the value of remark
     */
    public String getRemark() {
        return remark;
    }
    

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public AddressEntity getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(AddressEntity address) {
        this.address = address;
    }


   
   

    /**
     * Get the value of isSubscription
     *
     * @return the value of isSubscription
     */
    public boolean isIsSubscription() {
        return isSubscription;
    }

    /**
     * Set the value of isSubscription
     *
     * @param isSubscription new value of isSubscription
     */
    public void setIsSubscription(boolean isSubscription) {
        this.isSubscription = isSubscription;
    }


    /**
     * Get the value of dateTimeOfOrder
     *
     * @return the value of dateTimeOfOrder
     */
    public Date getDateTimeOfOrder() {
        return dateTimeOfOrder;
    }

    /**
     * Set the value of dateTimeOfOrder
     *
     * @param dateTimeOfOrder new value of dateTimeOfOrder
     */
    public void setDateTimeOfOrder(Date dateTimeOfOrder) {
        this.dateTimeOfOrder = dateTimeOfOrder;
    }


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
