/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.jmx;

/**
 *  Counter interface used for AOP and JMX proxy
 *
 *@author    Terracotta, Inc.
 */
public interface ICounter {
   public int next();

   public int getCurrent();

   public String getName();
}
