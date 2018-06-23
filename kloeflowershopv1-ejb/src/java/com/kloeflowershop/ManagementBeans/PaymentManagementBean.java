/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.PaymentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Terence
 */
@Stateless
public class PaymentManagementBean implements PaymentManagementBeanLocal {

    PaymentEntity payment;
    List<PaymentEntity> paymentList = new ArrayList<PaymentEntity>();
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public PaymentManagementBean() {
    }
}
