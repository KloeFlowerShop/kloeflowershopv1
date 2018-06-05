/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.CustomerEntity;
import java.util.List;

/**
 *
 * @author Terence
 */
public interface CustomerManagementBeanRemote {
    
    public boolean loginCustomer(String email, String attemptedPassword);
    public boolean addCustomer(String email, String name, Long primaryAddressId, int mobileNumber, 
            String gender, String passwordString);
    public boolean updateCustomerInfo(Long customerId, String email, String name, Long primaryAddressId, 
            int mobileNumber, String gender);
    public boolean updateCustomerPassword(Long customerId, String oldPasswordString, String newPasswordString);
    public CustomerEntity getSingleCustomer(Long customerId);
    public List<CustomerEntity> getCustomerList();
    
}
