/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.chatter;

import java.util.Collection;

/**
 *  Description of the Interface
 *
 *@author    Terracotta, Inc.
 */
public interface NodeListProvider {
   Collection<String> getNodeList();
}
