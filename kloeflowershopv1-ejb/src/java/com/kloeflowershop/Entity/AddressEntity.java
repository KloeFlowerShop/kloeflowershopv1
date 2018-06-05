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
 * @author Terence
 */
@Entity
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String area;
    private String city;
    private String streetName;
    private String extraDetails;

    /**
     * Get the value of extraDetails
     *
     * @return the value of extraDetails
     */
    public String getExtraDetails() {
        return extraDetails;
    }

    /**
     * Set the value of extraDetails
     *
     * @param extraDetails new value of extraDetails
     */
    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }


    /**
     * Get the value of streetName
     *
     * @return the value of streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Set the value of streetName
     *
     * @param streetName new value of streetName
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }


    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Get the value of area
     *
     * @return the value of area
     */
    public String getArea() {
        return area;
    }

    /**
     * Set the value of area
     *
     * @param area new value of area
     */
    public void setArea(String area) {
        this.area = area;
    }


    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    public void setCountry(String country) {
        this.country = country;
    }


    public Long getId() {
        return id;
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
        if (!(object instanceof AddressEntity)) {
            return false;
        }
        AddressEntity other = (AddressEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kloeflowershop.AddressEntity[ id=" + id + " ]";
    }
    
}
