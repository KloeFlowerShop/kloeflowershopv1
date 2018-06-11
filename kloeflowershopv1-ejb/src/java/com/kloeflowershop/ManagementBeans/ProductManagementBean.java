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
public class ProductManagementBean implements ProductManagementBeanLocal, ProductManagementRemoteBean {

    @PersistenceContext
    EntityManager em;
    
    ProductEntity product;
    List<ProductEntity> productList = new ArrayList<ProductEntity>();
    String defaultImgFolder = "";

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public ProductEntity addProduct(String type, String subtype, double cost, String name, String description, String bundleSize) {
        product = new ProductEntity();
        product.setBundleSize(bundleSize);
        product.setDescription(description);
        product.setName(name);
        product.setSubtype(subtype);
        product.setType(type);
        product.setCost(cost);
        em.persist(product);
        em.flush();
        return product;
    }

    @Override
    public ProductEntity updateProduct(ProductEntity product, String type, String subtype, double cost, String name, String description, String bundleSize) {
        product.setBundleSize(bundleSize);
        product.setDescription(description);
        product.setName(name);
        product.setSubtype(subtype);
        product.setType(type);
        product.setCost(cost);
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
        product = (ProductEntity)query.getSingleResult();
        return product;
    }

    //If no param for anything, set as null for respective param
    @Override
    public List<ProductEntity> getProductList(String name, String type, String subtype, String bundleSize, Double minCost, Double maxCost) {
        String queryString = "SELECT p FROM ProductEntity p WHERE ";
        if(name != null) {
            queryString += "p.name=:name AND ";
        }
        if(type != null) {
            queryString += "p.type=:type AND ";
        }
        if(subtype != null) {
            queryString += "p.subtype=:subtype AND ";
        }
        if(bundleSize != null) {
            queryString += "p.bundleSize=:bundleSize AND ";
        }
        if(minCost == null) {
            minCost = 0.0;
        }
        if(maxCost == null) {
            maxCost = 100.0;
        }
        queryString += "p.cost BETWEEN :minCost AND :maxCost";
        Query query = em.createQuery(queryString);
        query.setParameter("minCost", minCost);
        query.setParameter("maxCost", maxCost);
        if(queryString.contains(name)) {
            query.setParameter("name", name);
        }
        if(queryString.contains(type) && !queryString.contains(subtype)) {
            query.setParameter("type", type);
        }
        if(queryString.contains(subtype)) {
            query.setParameter("subtype", subtype);
        }
        if(queryString.contains(bundleSize)) {
            query.setParameter("bundleSize", bundleSize);
        }
        productList = query.getResultList();
        return productList;
    }
}
