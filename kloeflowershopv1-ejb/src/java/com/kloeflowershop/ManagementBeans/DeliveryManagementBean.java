/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.DeliveryEntity;
import com.kloeflowershop.Entity.OrderEntity;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Terence
 */
@Stateless
public class DeliveryManagementBean implements DeliveryManagementBeanLocal, DeliveryManagementBeanRemote {

    @PersistenceContext
    EntityManager em;

    DeliveryEntity delivery;
    List<DeliveryEntity> deliveries = new ArrayList<DeliveryEntity>();
    ZoneId zoneId = ZoneId.of("Asia/Shanghai");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public DeliveryEntity addDelivery(String deliveryDateTime, OrderEntity order) {
        if (order.getAddress().getCountry().equalsIgnoreCase("Singapore")) {
            delivery = new DeliveryEntity();
            delivery.setAssignedIC("");
            delivery.setAssignedICContact(00000000);
            delivery.setIsDelivered(false);
            //Formatting string to LocalDateTime (String format "yyyy-MM-dd HH:mm:ss")
            delivery.setDeliveryDateTime(LocalDateTime.parse(deliveryDateTime, dtf));
            em.persist(delivery);
            order.setDelivery(delivery);
            em.merge(order);
        }
        em.flush();
        return delivery;
    }

    //Customer Side
    @Override
    public DeliveryEntity updateDelivery(String deliveryDateTime, OrderEntity order, DeliveryEntity delivery) {
        if (order.getAddress().getCountry().equalsIgnoreCase("Singapore")) {
            //Formatting string to LocalDateTime (String format "yyyy-MM-dd HH:mm:ss")
            delivery.setDeliveryDateTime(LocalDateTime.parse(deliveryDateTime, dtf));
            em.merge(delivery);
        }
        em.flush();
        return delivery;
    }

    //For Deliver Side
    @Override
    public List<DeliveryEntity> getDeliveries(int assignedICContact) {
        Query query = em.createQuery("SELECT d FROM DeliveryEntity d WHERE d.assignedICContact=:assignedICContact");
        query.setParameter("assignedICContact", assignedICContact);
        deliveries = query.getResultList();
        return deliveries;
    }

    @Override
    public DeliveryEntity getDelivery(Long deliveryId) {
        Query query = em.createQuery("SELECT d FROM DeliveryEntity d WHERE d.id=:id");
        query.setParameter("id", deliveryId);
        delivery = (DeliveryEntity)query.getSingleResult();
        return delivery;
    }

    @Override
    public DeliveryEntity updateDelivery(boolean isDelivered, DeliveryEntity delivery, int assignedICContact, String assignedIC) {
        if(isDelivered) {
            delivery.setDeliveryDateTime(LocalDateTime.now(zoneId));
        }
        delivery.setIsDelivered(isDelivered);
        delivery.setAssignedIC(assignedIC);
        delivery.setAssignedICContact(assignedICContact);
        em.merge(delivery);
        em.flush();
        return delivery;
    }
}
