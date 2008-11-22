/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.townsend.service;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class Product {

   private final String id;
   private final int quantity;
   private final String name;
   private final String details;

   public Product(String id, int quantity, String name, String details) {
      this.id = id;
      this.quantity = quantity;
      this.name = name;
      this.details = details;
   }

   public String getId() {
      return id;
   }

   public int getQuantity() {
      return quantity;
   }

   public String getName() {
      return name;
   }

   public String getDetails() {
      return details;
   }
}
