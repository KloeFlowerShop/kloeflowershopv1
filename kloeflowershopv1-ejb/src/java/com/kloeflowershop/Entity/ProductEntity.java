/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.Entity;

import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Terence
 */
@Entity
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String subtype;
    private double cost;
    private String name;
    private String description;
    private String bundleSize;
    private Blob imageBytes;

    /**
     * Get the value of imageBytes
     *
     * @return the value of imageBytes
     */
    public Blob getImageBytes() {
        return imageBytes;
    }

    /**
     * Set the value of imageBytes
     *
     * @param imageBytes new value of imageBytes
     */
    public void setImageBytes(Blob imageBytes) {
        this.imageBytes = imageBytes;
    }


    /**
     * Get the value of bundleSize
     *
     * @return the value of bundleSize
     */
    public String getBundleSize() {
        return bundleSize;
    }

    /**
     * Set the value of bundleSize
     *
     * @param bundleSize new value of bundleSize
     */
    public void setBundleSize(String bundleSize) {
        this.bundleSize = bundleSize;
    }


    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Get the value of cost
     *
     * @return the value of cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Set the value of cost
     *
     * @param cost new value of cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }


    /**
     * Get the value of Subtype
     *
     * @return the value of Subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * Set the value of Subtype
     *
     * @param Subtype new value of Subtype
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }


    /**
     * Get the value of Type
     *
     * @return the value of Type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of Type
     *
     * @param Type new value of Type
     */
    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof ProductEntity)) {
            return false;
        }
        ProductEntity other = (ProductEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kloeflowershop.Entity.ProductEntity[ id=" + id + " ]";
    }
    
}
