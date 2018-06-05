/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.ProductEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Terence
 */
@Stateless
public class ProductManagementBean implements ProductManagementBeanLocal, ProductManagementRemoteBean {

    @PersistenceContext
    EntityManager em;
    
    ProductEntity product;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
