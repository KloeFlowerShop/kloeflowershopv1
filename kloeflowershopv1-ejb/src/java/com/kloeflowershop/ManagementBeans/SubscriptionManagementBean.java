/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.SubscriptionEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Terence
 */
@Stateless
public class SubscriptionManagementBean implements SubscriptionManagementBeanLocal, SubscriptionManagementBeanRemote {

    @PersistenceContext
    EntityManager em;
    
    SubscriptionEntity subscription;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
