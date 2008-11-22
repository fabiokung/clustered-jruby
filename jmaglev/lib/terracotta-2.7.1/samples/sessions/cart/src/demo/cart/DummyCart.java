/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.cart;

import javax.servlet.http.*;
import java.util.Vector;
import java.util.Enumeration;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class DummyCart {
   Vector v = new Vector();
   String submit = null;
   String item = null;

   public void setItem(String name) {
      item = name;
   }

   public void setSubmit(String s) {
      submit = s;
   }

   public String[] getItems() {
      String[] s = new String[v.size()];
      v.copyInto(s);
      return s;
   }

   public void processRequest(HttpServletRequest request) {
      // null value for submit - user hit enter instead of clicking on
      // "add" or "remove"
      if (submit != null && item != null) {
         if (submit.equals("add")) {
            addItem(item);
         }
         else if (submit.equals("remove")) {
            removeItem(item);
         }
      }

      // reset at the end of the request
      reset();
   }

   private void addItem(String name) {
      v.addElement(name);
   }

   private void removeItem(String name) {
      v.removeElement(name);
   }

   // reset
   private void reset() {
      submit = null;
      item = null;
   }
}
