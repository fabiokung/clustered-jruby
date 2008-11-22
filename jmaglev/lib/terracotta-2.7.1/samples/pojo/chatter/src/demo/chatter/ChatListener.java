/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.chatter;

/**
 *  Description of the Interface
 *
 *@author    Terracotta, Inc.
 */
interface ChatListener {
   public void newUser(String username);

   public void newMessage(Message message);
}
