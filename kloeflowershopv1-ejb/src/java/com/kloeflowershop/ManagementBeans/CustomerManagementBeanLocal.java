/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.CustomerEntity;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface CustomerManagementBeanLocal {
    
    public boolean loginCustomer(String email, String attemptedPassword);
    public CustomerEntity validateLogin();
    public boolean addCustomer(String email, String name, Long primaryAddressId, int mobileNumber, 
            String gender, String passwordString);
    public boolean updateCustomerInfo(String email, String name, Long primaryAddressId, 
            int mobileNumber, String gender);
    public boolean updateCustomerPassword(String oldPasswordString, String newPasswordString);
    
}
