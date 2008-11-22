/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.inventory;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class Product {
   private double price;
   private final String name;
   private final String sku;

   public Product(String n, double p, String s) {
      name = n;
      price = p;
      sku = s;
   }

   public synchronized void setPrice(double p) {
      price = p;
   }

   public final String getName() {
      return name;
   }

   public final String getSKU() {
      return sku;
   }

   public synchronized double getPrice() {
      return price;
   }

   public int hashCode() {
      return sku.hashCode();
   }
}
