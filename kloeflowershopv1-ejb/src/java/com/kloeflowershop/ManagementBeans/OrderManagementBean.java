/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.OrderEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Terence
 */
@Stateless
public class OrderManagementBean implements OrderManagementBeanLocal, OrderManagementBeanRemote {

    @PersistenceContext
    EntityManager em;

    OrderEntity order;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
