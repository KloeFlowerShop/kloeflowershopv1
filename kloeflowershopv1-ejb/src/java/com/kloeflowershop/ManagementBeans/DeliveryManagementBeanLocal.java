/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.DeliveryEntity;
import com.kloeflowershop.Entity.OrderEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Terence
 */
@Local
public interface DeliveryManagementBeanLocal {

    DeliveryEntity addDelivery(String lastModifiedDateTimeString, OrderEntity order);

    DeliveryEntity updateDelivery(String lastModifiedDateTimeString, OrderEntity order, DeliveryEntity delivery);

    List<DeliveryEntity> getDeliveries(int assignedICContact);

    DeliveryEntity getDelivery(Long deliveryId);

    DeliveryEntity updateDelivery(boolean isDelivered, DeliveryEntity delivery, int assignedICContact, String assignedIC);
    
}
