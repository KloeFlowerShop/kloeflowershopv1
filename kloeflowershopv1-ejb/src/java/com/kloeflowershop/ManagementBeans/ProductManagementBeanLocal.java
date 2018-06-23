/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.ManagementBeans;

import com.kloeflowershop.Entity.ProductEntity;
import java.sql.Blob;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author Terence
 */
@Remote
public interface ProductManagementBeanLocal {

    ProductEntity addProduct(String type, String subtype, double cost, String name, String description, String bundleSize, boolean isSubscriptionProduct);
    ProductEntity updateProductImage(ProductEntity product, Blob imageBytes);
    ProductEntity getProduct(Long productId);
    List<ProductEntity> getProductList(String name, String type, String subtype, String bundleSize, double minCost, double maxCost, String isSubscriptionProductString);
    ProductEntity updateProduct(ProductEntity product, String type, String subtype, double cost, String name, String description, String bundleSize, boolean isSubscriptionProduct);

    List<ProductEntity> businessMethod();
    
}
