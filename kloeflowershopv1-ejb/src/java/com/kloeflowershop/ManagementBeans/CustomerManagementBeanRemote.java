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
    
    public CustomerEntity loginCustomer(String email, String attemptedPassword);
    public CustomerEntity addCustomer(String email, String name, Long primaryAddressId, int mobileNumber, 
            String gender, String passwordString);
    public CustomerEntity updateCustomerInfo(CustomerEntity customer, String email, String name, Long primaryAddressId, int mobileNumber, String gender);
    public boolean updateCustomerPassword(CustomerEntity customer, String oldPasswordString, String newPasswordString);
    public CustomerEntity getCustomer(Long customerId);
    public List<CustomerEntity> getCustomerList();
    
}
