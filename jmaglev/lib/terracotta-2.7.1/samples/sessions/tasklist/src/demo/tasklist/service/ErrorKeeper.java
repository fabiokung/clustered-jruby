/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.tasklist.service;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class ErrorKeeper {
   private String errorMsg;

   public ErrorKeeper(String error) {
      errorMsg = error;
   }

   public String getErrorMsg() {
      return errorMsg;
   }

}
