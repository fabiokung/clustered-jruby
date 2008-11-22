/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.jmx;

import org.springframework.beans.factory.BeanNameAware;

/**
 *  Simple service bean
 *
 *@author    Terracotta, Inc.
 */
public class Counter
       implements BeanNameAware, ICounter {
   private volatile int counter = 0;
   private String name;

   public void setBeanName(String name) {
      this.name = name;
   }

   public int getCurrent() {
      return counter;
   }

   public String getName() {
      return this.name;
   }

   public int next() {
      synchronized (this) {
         return counter++;
      }
   }
}

