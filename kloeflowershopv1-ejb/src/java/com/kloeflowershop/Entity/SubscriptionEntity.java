/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author yy
 */
@Entity
public class SubscriptionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Weekly, Fortnightly, Monthly only
    private String frequency;
    private boolean isDeleted = false;
    private String remarks;
    private HashMap<ProductEntity, Integer> productQuantity = new HashMap<ProductEntity, Integer>();
    
    @OneToMany(cascade = {CascadeType.ALL})
    private List<OrderEntity> orders = new ArrayList<OrderEntity>();
    @ManyToOne(cascade = {CascadeType.ALL})
    private CustomerEntity customer;
    @OneToOne(cascade = {CascadeType.ALL})
    private AddressEntity address;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ProductEntity> products = new ArrayList<ProductEntity>();

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


    /**
     * Get the value of products
     *
     * @return the value of products
     */
    public List<ProductEntity> getProducts() {
        return products;
    }

    /**
     * Set the value of products
     *
     * @param products new value of products
     */
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
    

    /**
     * Get the value of remarks
     *
     * @return the value of remarks
     */
    public String getRemarks() {
        return remarks;
    }


    /**
     * Set the value of remarks
     *
     * @param remarks new value of remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    
    /**
     * Get the value of isDeleted
     *
     * @return the value of isDeleted
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * Set the value of isDeleted
     *
     * @param isDeleted new value of isDeleted
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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
     * Get the value of orders
     *
     * @return the value of orders
     */
    public List<OrderEntity> getOrders() {
        return orders;
    }

    /**
     * Set the value of orders
     *
     * @param orders new value of orders
     */
    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }


    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    /**
     * Get the value of frequency
     *
     * @return the value of frequency
     */
    public String getFrequency() {
        return frequency;
    }


    /**
     * Set the value of frequency
     *
     * @param frequency new value of frequency
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
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
        if (!(object instanceof SubscriptionEntity)) {
            return false;
        }
        SubscriptionEntity other = (SubscriptionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kloeflowershop.Entity.SubscriptionEntity[ id=" + id + " ]";
    }
    
}
