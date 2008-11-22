/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.townsend.service;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *  DataKeeper keeps track of the current state of the user's list. All
 *  modifications to the user's list are made by calling DataKeeper's methods.
 *
 *@author    Terracotta, Inc.
 */
public class DataKeeper {

   private final int MAX_NUM = 5;
   private ArrayList userList;

   public DataKeeper() {
      userList = new ArrayList();
   }

   public int getListSize() {
      return userList.size();
   }

   public ArrayList getList() {
      return userList;
   }

   public Product getCurrent() {
      if (getListSize() > 0) {
         return (Product) userList.get(0);
      }

      return null;
   }

   public void addListItem(Product newProd) {
      for (Iterator iter = userList.iterator(); iter.hasNext(); ) {
         if (((Product) iter.next()).getId().equals(newProd.getId())) {
            iter.remove();
         }
      }

      userList.add(0, newProd);

      if (userList.size() > MAX_NUM) {
         userList.remove(MAX_NUM);
      }
   }

}
