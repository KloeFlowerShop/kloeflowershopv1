/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kloeflowershopappclient;

import com.kloeflowershop.Entity.CustomerEntity;
import com.kloeflowershop.Entity.ProductEntity;
import com.kloeflowershop.ManagementBeans.CustomerManagementBeanLocal;
import com.kloeflowershop.ManagementBeans.ProductManagementBeanLocal;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Terence
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static final Scanner sc = new Scanner(System.in);

    @EJB(name = "CustomerManagementBeanLocal")
    private static CustomerManagementBeanLocal customerMBL;
    @EJB(name = "ProductManagementBeanLocal")
    private static ProductManagementBeanLocal productMBL;

    private static List<CustomerEntity> customerList;
    private static List<ProductEntity> productList;
    private static CustomerEntity customer;
    private static ProductEntity product;
    private static String tempString;

    public static void main(String[] args) {
        optionsList();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println();
            if (input.equals("0")) {
                break;
            } else if (input.equals("1")) {
                addCustomer();
            } else if (input.equals("2")) {
                viewCustomerList();
            } else if (input.equals("3")) {
                addProduct();
            } else if (input.equals("4")) {
                viewProduct();
            } else if (input.equals("5")) {
                updateProductImage();
            }
            System.out.println();
            optionsList();
        }
    }

    private static void optionsList() {
        System.out.println("INPUT OPTION NUMBER BELOW");
        System.out.println("0. End");
        System.out.println("1. Add Customer");
        System.out.println("2. View Customer");
        System.out.println("3. Add Product");
        System.out.println("4. View Product");
        System.out.println("5. Update Product Image");
        System.out.println("6. Add Subscription");
        System.out.println("7. Add Delivery");
        System.out.println("8. Add Address");
        System.out.println("9. Add Order");
        System.out.println();
    }

    private static void addCustomer() {
        System.out.println("INPUT CUSTOMER DETAILS");
        System.out.print("Email? ");
        String email = sc.nextLine();
        System.out.print("Name? ");
        String name = sc.nextLine();
        System.out.print("Mobile Number? ");
        int mobileNumber = Integer.parseInt(sc.nextLine());
        System.out.print("Gender?(M/F) ");
        String gender = sc.nextLine();
        System.out.print("Password? ");
        String passwordString = sc.nextLine();
        System.out.print("Country? ");
        String country = sc.nextLine();
        System.out.print("Area? ");
        String area = sc.nextLine();
        System.out.print("City? ");
        String city = sc.nextLine();
        System.out.print("Street Name? ");
        String streetName = sc.nextLine();
        System.out.print("Extra Details? ");
        String extraDetails = sc.nextLine();
        System.out.println(email + name + mobileNumber + gender + passwordString + country + area + city + streetName + extraDetails);
        customerMBL.addCustomer(email, name, mobileNumber, gender, passwordString, country, area, city, streetName, extraDetails);
        viewCustomerList();
    }

    private static void viewCustomerList() {
        customerList = customerMBL.getCustomerList();
        System.out.println("Below is the list of all customers in the system");
        System.out.println();
        for (CustomerEntity customer : customerList) {
            System.out.print(customer.getId() + "     ");
            System.out.print(customer.getEmail() + "     ");
            System.out.print(customer.getName() + "     ");
            System.out.println(customerMBL.getAddress(customer.getPrimaryAddressID()).getCountry());
        }
    }

    private static void addProduct() {
        System.out.println("Enter Product Detail");
        System.out.print("Type? ");
        String type = sc.nextLine();
        System.out.print("Subtype? ");
        String subtype = sc.nextLine();
        System.out.print("Cost? ");
        double cost = (double) Double.parseDouble(sc.nextLine());
        System.out.print("Name? ");
        String name = sc.nextLine();
        System.out.print("Description? ");
        String description = sc.nextLine();
        System.out.print("Bundle Size? ");
        String bundleSize = sc.nextLine();
        System.out.print("Is it a subscription product? ");
        boolean isSubscriptionProduct = sc.nextBoolean();
        product = productMBL.addProduct(type, subtype, cost, name, description, bundleSize, isSubscriptionProduct);
    }

    private static void viewProduct() {
        System.out.print("View all products?(Yes/No) ");
        if (sc.nextLine().equalsIgnoreCase("Yes")) {
            productList = productMBL.getProductList(null, null, null, null, -1, -1, null);
        } else {
            System.out.println("Enter Product Detail");
            System.out.print("Type? ");
            String type = sc.nextLine();
            if (type.equals("ALL")) {
                type = null;
            }
            System.out.print("Subtype? ");
            String subtype = sc.nextLine();
            if (subtype.equals("ALL")) {
                subtype = null;
            }
            System.out.print("Minimum Cost? ");
            tempString = sc.nextLine();
            double minCost;
            if ("ALL".equals(tempString)) {
                minCost = -1;
            } else {
                minCost = (double) Double.parseDouble(tempString);
            }
            System.out.print("Maximum Cost? ");
            tempString = sc.nextLine();
            double maxCost;
            if ("ALL".equals(tempString)) {
                maxCost = -1;
            } else {
                maxCost = (double) Double.parseDouble(tempString);
            }
            System.out.print("Name? ");
            String name = sc.nextLine();
            if (name.equals("ALL")) {
                name = null;
            }
            System.out.print("Bundle Size? ");
            String bundleSize = sc.nextLine();
            if (bundleSize.equals("ALL")) {
                bundleSize = null;
            }
            System.out.print("Is it a subscription product? ");
            String isSubscriptionProductString = sc.nextLine();
            productList = productMBL.getProductList(name, type, subtype, bundleSize, minCost, maxCost, isSubscriptionProductString);
        }
        System.out.println();
        System.out.println("Below is the list of all products in the system that fit the category");
        System.out.println();
        for (ProductEntity product : productList) {
            System.out.print(product.getId() + "     ");
            System.out.print(product.getName() + "     ");
            System.out.print(product.getSubtype() + "     ");
        }
    }

    private static byte[] convertFileContentToBlob(String filePath) throws IOException {
        byte[] fileContent = null;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " + e.getMessage());
        }
        return fileContent;
    }

    private static void updateProductImage() {
        System.out.println("Update Product Image using Filepath");
        System.out.print("Prodct ID? ");
        long productId = Long.parseLong(sc.nextLine());
        product = productMBL.getProduct(productId);
        if (product.equals(null)) {
            System.out.println("No products fit the ProductId.");
        } else {
            System.out.print("Image File Path? ");
            try {
                byte[] imageBytes = convertFileContentToBlob(sc.nextLine());
                Blob imageBlob = new SerialBlob(imageBytes);
                product.setImageBytes(imageBlob);
            } catch (IOException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Image updated");
        }
    }
}
