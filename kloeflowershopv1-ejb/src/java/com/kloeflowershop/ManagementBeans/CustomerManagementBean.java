/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.CustomerEntity;
import com.kloeflowershop.Misc.PasswordEncryptionService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateful
public class CustomerManagementBean implements CustomerManagementBeanLocal {

    @PersistenceContext
    EntityManager em;
    
    CustomerEntity customer;
    PasswordEncryptionService pes = new PasswordEncryptionService();
    
    public boolean loginCustomer(String email, String attemptedPassword) {
        Query query = em.createQuery("SELECT c FROM CustomerEntity c WHERE c.email=:email AND c.password");
        query.setParameter("email", email);
        List<CustomerEntity> customers = query.getResultList();
        for (CustomerEntity currCustomer : customers) {
            try {
                if(pes.Authenticate(attemptedPassword, currCustomer.getPassword(), 
                        currCustomer.getPasswordSalt())) {
                    customer = currCustomer;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(CustomerManagementBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(customer != null) {
            return true;
        }
        return false;
    }
    
    public CustomerEntity validateLogin() {
        return customer;
    }
    
    public boolean addCustomer(String email, String name, Long primaryAddressId, int mobileNumber, 
            String gender, String passwordString) {
        customer = new CustomerEntity();
        customer.setEmail(email);
        customer.setName(name);
        customer.setGender(gender);
        customer.setMobileNumber(mobileNumber);
        customer.setPrimaryAddressID(primaryAddressId);
        byte[] passwordSalt = null;
        byte[] password = null;
        try {
            passwordSalt = pes.generateSalt();
            password = pes.getEncryptedPassword(passwordString, passwordSalt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(CustomerManagementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        customer.setPasswordSalt(passwordSalt);
        customer.setPassword(password);
        if(passwordSalt != null && password != null) {
            em.persist(customer);
            em.flush();
            return true;
        }
        em.flush();
        return false;
    }
    
    public boolean updateCustomerInfo(String email, String name, Long primaryAddressId, 
            int mobileNumber, String gender) {
        customer.setEmail(email);
        customer.setGender(gender);
        customer.setId(primaryAddressId);
        customer.setMobileNumber(mobileNumber);
        customer.setName(name);
        customer.setPrimaryAddressID(primaryAddressId);
        em.merge(customer);
        em.flush();
        return true;
    }
    
    public boolean updateCustomerPassword(String oldPasswordString, String newPasswordString) {
        try {
            if(pes.Authenticate(oldPasswordString, customer.getPassword(), customer.getPasswordSalt())) {
                byte[] newPasswordSalt = pes.generateSalt();
                byte[] newPassword = pes.getEncryptedPassword(newPasswordString, newPasswordSalt);
                customer.setPassword(newPassword);
                customer.setPasswordSalt(newPasswordSalt);
                em.merge(customer);
                em.flush();
                return true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(CustomerManagementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.flush();
        return false;
    }
}
