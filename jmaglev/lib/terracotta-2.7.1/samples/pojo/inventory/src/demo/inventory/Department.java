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
public class Department {
   private final String code;
   private final String name;
   private final Product[] products;

   public Department(final String c, final String n, final Product[] p) {
      code = c;
      name = n;
      products = p;
   }

   public final String getCode() {
      return code;
   }

   public final String getName() {
      return name;
   }

   public final Product[] getProducts() {
      return products;
   }
}
