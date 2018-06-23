/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.AddressEntity;
import com.kloeflowershop.Entity.CustomerEntity;
import com.kloeflowershop.Entity.OrderEntity;
import com.kloeflowershop.Entity.ProductEntity;
import com.kloeflowershop.Entity.SubscriptionEntity;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Terence
 */
@Remote
public interface CustomerManagementBeanLocal {
    
    public CustomerEntity loginCustomer(String email, String attemptedPassword);
    public CustomerEntity addCustomer(String email, String name, int mobileNumber, String gender, String passwordString, String country, String area, String city, String streetName, String extraDetails);
    public CustomerEntity updateCustomerInfo(CustomerEntity customer, String email, String name, Long primaryAddressId, int mobileNumber, String gender);
    public boolean updateCustomerPassword(CustomerEntity customer, String oldPasswordString, String newPasswordString);
    public CustomerEntity getCustomer(Long customerId);
    public List<CustomerEntity> getCustomerList();

    List<AddressEntity> getAddressList(CustomerEntity customer);
    AddressEntity getPrimaryAddress(CustomerEntity customer);
    AddressEntity getAddress(Long addressId);
    AddressEntity addAddress(String country, String area, String city, String streetName, String extraDetails, boolean isPrimary, CustomerEntity customer);
    AddressEntity setPrimaryAddress(AddressEntity address, CustomerEntity customer);
    
    SubscriptionEntity AddSubscription(CustomerEntity customer, String frequency, String remarks, HashMap<ProductEntity, Integer> productQuantity, List<ProductEntity> products, AddressEntity address);
    List<SubscriptionEntity> getSubscriptionList(CustomerEntity customer);
    SubscriptionEntity getSubscription(Long subscriptionId);
    SubscriptionEntity updateSubscription(SubscriptionEntity subscription, String frequency, String remarks, HashMap<ProductEntity, Integer> productQuantity, List<ProductEntity> products, AddressEntity address, boolean isDeleted);
    
    OrderEntity addOrder(CustomerEntity customer, String recipientName, int recipientContact, String remarks, HashMap<ProductEntity, Integer> productQuantity, AddressEntity address, Date deliveryDateTime, List<ProductEntity> products);
    OrderEntity updateOrder(String recipientName, int recipientContact, String remarks, HashMap<ProductEntity, Integer> productQuantity, boolean isDeleted, AddressEntity address, List<ProductEntity> products, OrderEntity order);
    OrderEntity getOrder(Long orderId);
    List<OrderEntity> getOrderList(CustomerEntity customer);
}
