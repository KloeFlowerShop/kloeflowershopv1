/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.ProductEntity;
import java.sql.Blob;
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
public class ProductManagementBean implements ProductManagementBeanLocal {

    @PersistenceContext
    EntityManager em;
    
    ProductEntity product = null;
    List<ProductEntity> productList = new ArrayList<ProductEntity>();
    String defaultImgFolder = "";

    // Add business logic below. (Right-click in editor and choose
    
    public ProductManagementBean() {
    }

    // "Insert Code > Add Business Method")
    @Override
    public ProductEntity addProduct(String type, String subtype, double cost, String name, String description, String bundleSize, boolean isSubscriptionProduct) {
        product = new ProductEntity();
        System.out.println("New Product");
        product.setBundleSize(bundleSize);
        product.setDescription(description);
        product.setName(name);
        product.setSubtype(subtype);
        product.setType(type);
        product.setCost(cost);
        product.setIsSubscriptionProduct(isSubscriptionProduct);
        em.persist(product);
        em.flush();
        return product;
    }

    @Override
    public ProductEntity updateProduct(ProductEntity product, String type, String subtype, double cost, String name, String description, String bundleSize, boolean isSubscriptionProduct) {
        product.setBundleSize(bundleSize);
        product.setDescription(description);
        product.setName(name);
        product.setSubtype(subtype);
        product.setType(type);
        product.setCost(cost);
        product.setIsSubscriptionProduct(isSubscriptionProduct);
        em.merge(product);
        em.flush();
        return product;
    }

    @Override
    public ProductEntity updateProductImage(ProductEntity product, Blob imageBytes) {
        product.setImageBytes(imageBytes);
        em.merge(product);
        em.flush();
        return product;
    }

    @Override
    public ProductEntity getProduct(Long productId) {
        Query query = em.createQuery("SELECT p FROM ProductEntity p WHERE p.id=:id");
        query.setParameter("id", productId);
        try {
        product = (ProductEntity)query.getSingleResult();
        } catch (Exception ex) {
            product = null;
        }
        return product;
    }

    //If no param for anything, set as null for respective param
    @Override
    public List<ProductEntity> getProductList(String name, String type, String subtype, String bundleSize, double minCost, double maxCost, String isSubscriptionProductString) {
        String queryString = "SELECT p FROM ProductEntity p WHERE ";
        if(!"\n".equals(name)) {
            queryString += "p.name=:name AND ";
        }
        if(!"\n".equals(type)) {
            queryString += "p.type=:type AND ";
        }
        if(!"\n".equals(subtype)) {
            queryString += "p.subtype=:subtype AND ";
        }
        if(!"\n".equals(bundleSize)) {
            queryString += "p.bundleSize=:bundleSize AND ";
        }
        if("true".equals(isSubscriptionProductString) || "false".equals(isSubscriptionProductString)) {
            queryString += "p.isSubscriptionProduct=:isSubscriptionProduct AND ";
        }
        if(minCost == -1) {
            minCost = 0.0;
        }
        if(maxCost == -1) {
            maxCost = 100.0;
        }
        queryString += "p.cost BETWEEN :minCost AND :maxCost";
        System.out.println(queryString);
        Query query = em.createQuery(queryString);
        query.setParameter("minCost", minCost);
        query.setParameter("maxCost", maxCost);
        if(queryString.contains("p.isSubscriptionProduct")) {
            query.setParameter("isSubscriptionProduct", Boolean.valueOf(isSubscriptionProductString));
        }
        if(queryString.contains("p.name")) {
            query.setParameter("name", name);
        }
        if(queryString.contains("p.type")) {
            query.setParameter("type", type);
        }
        if(queryString.contains("p.subtype")) {
            query.setParameter("subtype", subtype);
        }
        if(queryString.contains("p.bundleSize")) {
            query.setParameter("bundleSize", bundleSize);
        }
        productList = query.getResultList();
        return productList;
    }

    @Override
    public List<ProductEntity> businessMethod() {
        Query query = em.createQuery("SELECT p FROM ProductEntity p");
        productList = query.getResultList();
        return productList;
    }
}
