/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.jmx;

/**
 *  IHistory interface used to expose history data to JMX
 *
 *@author    Terracotta, Inc.
 */
public interface IHistory {
   String[] getHistory();

   boolean getEnabled();

   void setEnabled(boolean enabled);

   void reset();
}
