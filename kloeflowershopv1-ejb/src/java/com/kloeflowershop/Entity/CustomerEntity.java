/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author User
 */
@Entity
public class CustomerEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private Long primaryAddressID;
    private int mobileNumber;
    private String gender;
    private byte[] password;
    private byte[] passwordSalt;
    
    @OneToMany(cascade={CascadeType.ALL})
    private List<AddressEntity> addressList = new ArrayList<AddressEntity>();

    /**
     * Get the value of addressList
     *
     * @return the value of addressList
     */
    public List<AddressEntity> getAddressList() {
        return addressList;
    }

    /**
     * Set the value of addressList
     *
     * @param addressList new value of addressList
     */
    public void setAddressList(List<AddressEntity> addressList) {
        this.addressList = addressList;
    }

    
    /**
     * Get the value of passwordSalt
     *
     * @return the value of passwordSalt
     */
    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    /**
     * Set the value of passwordSalt
     *
     * @param passwordSalt new value of passwordSalt
     */
    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }


    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public byte[] getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(byte[] password) {
        this.password = password;
    }


    /**
     * Get the value of gender
     *
     * @return the value of gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the value of gender
     *
     * @param gender new value of gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


    /**
     * Get the value of mobileNumber
     *
     * @return the value of mobileNumber
     */
    public int getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Set the value of mobileNumber
     *
     * @param mobileNumber new value of mobileNumber
     */
    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    /**
     * Get the value of primaryAddressID
     *
     * @return the value of primaryAddressID
     */
    public Long getPrimaryAddressID() {
        return primaryAddressID;
    }

    /**
     * Set the value of primaryAddressID
     *
     * @param primaryAddressID new value of primaryAddressID
     */
    public void setPrimaryAddressID(Long primaryAddressID) {
        this.primaryAddressID = primaryAddressID;
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
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof CustomerEntity)) {
            return false;
        }
        CustomerEntity other = (CustomerEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kloeflowershop.Entity.CustomerEntity[ id=" + id + " ]";
    }
    
}
