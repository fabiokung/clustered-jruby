/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.tasklist.service;

import java.util.ArrayList;

/**
 *  DataKeeper keeps track of the current state of the task list. All
 *  modifications to the task list are made by calling DataKeeper's methods.
 *
 *@author    Terracotta, Inc.
 */
public class DataKeeper {

   private ArrayList userList;

   public DataKeeper() {
      userList = new ArrayList();
   }

   public int getListSize() {
      if (userList == null) {
         return 0;
      }
      return userList.size();
   }

   public String getListItem(int index) {
      return (String) userList.get(index);
   }

   public ArrayList getList() {
      return userList;
   }

   public void addListItem(String newListItem) {
      if (newListItem != null) {
         userList.add(newListItem);
      }
   }

   public void deleteListItems(String[] itemsForDelete) {
      if (itemsForDelete != null) {
         for (int i = 0; i < itemsForDelete.length; i++) {
            userList.remove(itemsForDelete[i]);
         }
      }
   }

}
