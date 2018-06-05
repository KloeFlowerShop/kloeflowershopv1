/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.AddressEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Terence
 */
@Stateless
public class AddressManagementBean implements AddressManagementBeanLocal, AddressManagementBeanRemote {

    @PersistenceContext
    EntityManager em;
    
    AddressEntity address;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
