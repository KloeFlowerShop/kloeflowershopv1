/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.DeliveryEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Terence
 */
@Stateless
public class DeliveryManagementBean implements DeliveryManagementBeanLocal, DeliveryManagementBeanRemote {

    @PersistenceContext
    EntityManager em;
    
    DeliveryEntity delivery;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
