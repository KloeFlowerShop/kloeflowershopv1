/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kloeflowershopappclient;

import java.util.Scanner;

/**
 *
 * @author Terence
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        optionsList();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            if (sc.nextLine().equalsIgnoreCase("0")) {
                System.out.println();
                break;
            }
            System.out.println();
            optionsList();
        }
    }

    private static void optionsList() {
        System.out.println("INPUT OPTION NUMBER BELOW");
        System.out.println("0. End");
        System.out.println("1. Add Customer");
        System.out.println("2. Add Delivery");
        System.out.println("3. Add Product");
        System.out.println("4. Add Address");
        System.out.println("5. Add Order");
        System.out.println("6. Add Subscription");
        System.out.println();
    }

}
