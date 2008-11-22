/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.chatter;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
class Message {
   private final String text;
   private final User user;
   private final boolean alreadyDisplayedLocally;

   public Message(User user, String text, boolean displayed) {
      this.user = user;
      this.text = text;
      this.alreadyDisplayedLocally = displayed;
   }

   public String getText() {
      return text;
   }

   public User getUser() {
      return user;
   }

   public boolean wasAlreadyDisplayedLocally() {
      return alreadyDisplayedLocally;
   }
}
