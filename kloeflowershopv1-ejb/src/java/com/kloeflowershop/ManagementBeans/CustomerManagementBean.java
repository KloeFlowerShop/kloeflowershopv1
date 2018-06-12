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
import com.kloeflowershop.Misc.PasswordEncryptionService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Terence
 */
@Stateless
public class CustomerManagementBean implements CustomerManagementBeanLocal, CustomerManagementBeanRemote {

    @PersistenceContext
    EntityManager em;

    CustomerEntity customer;
    List<CustomerEntity> customerList;
    AddressEntity address;
    List<AddressEntity> addressList;
    SubscriptionEntity subscription;
    List<SubscriptionEntity> subscriptions;
    OrderEntity order;
    List<OrderEntity> orders;
    PasswordEncryptionService pes = new PasswordEncryptionService();
    ZoneId zoneId = ZoneId.of("Asia/Shanghai");
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public CustomerEntity loginCustomer(String email, String attemptedPassword) {
        Query query = em.createQuery("SELECT c FROM CustomerEntity c WHERE c.email=:email");
        query.setParameter("email", email);
        List<CustomerEntity> customers = query.getResultList();
        customer = null;
        for (CustomerEntity currCustomer : customers) {
            try {
                if (pes.Authenticate(attemptedPassword, currCustomer.getPassword(),
                        currCustomer.getPasswordSalt())) {
                    customer = currCustomer;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(CustomerManagementBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return customer;
    }

    @Override
    public CustomerEntity addCustomer(String email, String name, Long primaryAddressId, int mobileNumber,
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
        em.persist(customer);
        em.flush();
        return customer;
    }

    @Override
    public CustomerEntity updateCustomerInfo(CustomerEntity customer, String email, String name, Long primaryAddressId, int mobileNumber, String gender) {
        customer.setEmail(email);
        customer.setGender(gender);
        customer.setId(primaryAddressId);
        customer.setMobileNumber(mobileNumber);
        customer.setName(name);
        customer.setPrimaryAddressID(primaryAddressId);
        em.merge(customer);
        em.flush();
        return customer;
    }

    @Override
    public boolean updateCustomerPassword(CustomerEntity customer, String oldPasswordString, String newPasswordString) {
        try {
            if (customer != null && pes.Authenticate(oldPasswordString, customer.getPassword(), customer.getPasswordSalt())) {
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

    @Override
    public CustomerEntity getCustomer(Long customerId) {
        Query query = em.createQuery("SELECT c FROM CustomerEntity c WHERE c.id=:id");
        query.setParameter("id", customerId);
        customer = (CustomerEntity) query.getSingleResult();
        return customer;
    }

    @Override
    public List<CustomerEntity> getCustomerList() {
        Query query = em.createQuery("SELECT * FROM CustomerEntity");
        customerList = query.getResultList();
        return customerList;
    }

    @Override
    public List<AddressEntity> getAddressList(CustomerEntity customer) {
        addressList = customer.getAddressList();
        return addressList;
    }

    @Override
    public AddressEntity getPrimaryAddress(CustomerEntity customer) {
        Long primaryAddressId = customer.getPrimaryAddressID();
        address = getAddress(primaryAddressId);
        return address;
    }

    @Override
    public AddressEntity getAddress(Long addressId) {
        Query query = em.createQuery("SELECT a FROM AddressEntity a WHERE a.id=:id");
        query.setParameter("id", addressId);
        address = (AddressEntity) query.getSingleResult();
        return address;
    }

    @Override
    public AddressEntity addAddress(String country, String area, String city, String streetName, String extraDetails, boolean isPrimary, CustomerEntity customer) {
        address = new AddressEntity();
        address.setArea(area);
        address.setCity(city);
        address.setCountry(country);
        address.setExtraDetails(extraDetails);
        address.setStreetName(streetName);
        addressList = customer.getAddressList();
        addressList.add(address);
        customer.setAddressList(addressList);
        if (isPrimary) {
            customer.setPrimaryAddressID(address.getId());
        }
        em.persist(address);
        em.merge(customer);
        em.flush();
        return address;
    }

    @Override
    public AddressEntity setPrimaryAddress(AddressEntity address, CustomerEntity customer) {
        customer.setPrimaryAddressID(address.getId());
        em.merge(customer);
        em.flush();
        return address;
    }

    @Override
    public SubscriptionEntity updateSubscription(SubscriptionEntity subscription, String frequency, String remarks, HashMap<ProductEntity, Integer> productQuantity, List<ProductEntity> products, AddressEntity address, boolean isDeleted) {
        subscription.setAddress(address);
        subscription.setCustomer(customer);
        subscription.setFrequency(frequency);
        subscription.setIsDeleted(isDeleted);
        subscription.setProductQuantity(productQuantity);
        subscription.setProducts(products);
        subscription.setRemarks(remarks);
        em.merge(subscription);
        em.flush();
        return subscription;
    }

    @Override
    public SubscriptionEntity AddSubscription(CustomerEntity customer, String frequency, String remarks, HashMap<ProductEntity, Integer> productQuantity, List<ProductEntity> products, AddressEntity address) {
        subscription = new SubscriptionEntity();
        subscription.setAddress(address);
        subscription.setCustomer(customer);
        subscription.setFrequency(frequency);
        subscription.setIsDeleted(false);
        subscription.setProductQuantity(productQuantity);
        subscription.setProducts(products);
        subscription.setRemarks(remarks);
        em.persist(subscription);
        subscriptions = customer.getSubscriptions();
        subscriptions.add(subscription);
        customer.setSubscriptions(subscriptions);
        em.merge(customer);
        em.flush();
        return subscription;
    }

    @Override
    public List<SubscriptionEntity> getSubscriptionList(CustomerEntity customer) {
        return customer.getSubscriptions();
    }

    @Override
    public SubscriptionEntity getSubscription(Long subscriptionId) {
        Query query = em.createQuery("SELECT s FROM SubscriptionEntity s WHERE s.id=:id");
        query.setParameter("id", subscriptionId);
        subscription = (SubscriptionEntity) query.getSingleResult();
        return subscription;
    }

    @Override
    public OrderEntity addOrder(CustomerEntity customer, String recipientName, int recipientContact, String remarks, HashMap<ProductEntity, Integer> productQuantity, AddressEntity address, Date deliveryDateTime, List<ProductEntity> products) {
        order = new OrderEntity();
        order.setAddress(address);
        order.setDateTimeOfOrder(LocalDateTime.now(zoneId));
        order.setIsDeleted(false);
        order.setProductQuantity(productQuantity);
        order.setProducts(products);
        order.setRecipientContact(recipientContact);
        order.setRecipientName(recipientName);
        order.setRemarks(remarks);
        em.persist(order);
        em.flush();
        return order;
    }

    @Override
    public OrderEntity updateOrder(String recipientName, int recipientContact, String remarks, HashMap<ProductEntity, Integer> productQuantity, boolean isDeleted, AddressEntity address, List<ProductEntity> products, OrderEntity order) {
        order.setAddress(address);
        order.setDateTimeOfOrder(LocalDateTime.now(zoneId));
        order.setIsDeleted(isDeleted);
        order.setProductQuantity(productQuantity);
        order.setProducts(products);
        order.setRecipientContact(recipientContact);
        order.setRecipientName(recipientName);
        order.setRemarks(remarks);
        em.merge(order);
        em.flush();
        return order;
    }

    @Override
    public OrderEntity getOrder(Long orderId) {
        Query query = em.createQuery("SELECT o FROM OrderEntity o WHERE o.id=:id");
        query.setParameter("id", orderId);
        order = (OrderEntity)query.getSingleResult();
        return order;
    }

    @Override
    public List<OrderEntity> getOrderList(CustomerEntity customer) {
        Query query = em.createQuery("SELECT o FROM OrderEntity o WHERE o.customer=:customer");
        query.setParameter("customer", customer);
        orders = query.getResultList();
        return orders;
    }
}
