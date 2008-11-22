/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.chatter;

import java.util.Map;
import java.util.TreeMap;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
class ChatManager {

   private final Map<String, User> users;
   private transient volatile ChatListener listener;

   public ChatManager() {
      this.users = new TreeMap<String, User>();
      init();
   }

   public void setLocalListener(ChatListener listener) {
      this.listener = listener;
   }

   public User[] getCurrentUsers() {
      synchronized (users) {
         return users.values().toArray(new User[]{});
      }
   }

   public void send(Message msg) {
      sendNewMessageEvent(msg);
   }

   public void registerUser(User user) {
      synchronized (users) {
         users.put(user.getNodeId(), user);
      }
      sendNewUserEvent(user.getName());
   }

   public void removeUser(String nodeId) {
      synchronized (users) {
         users.remove(nodeId);
      }
   }

   /**
    *  Normally the user list is maintained via JMX notifications received in
    *  each node. This method will ensure that the list is consistent even if
    *  all clients crash simultaneously
    *
    *@param  listProvider  Description of Parameter
    */
   public void retainNodes(NodeListProvider listProvider) {
      synchronized (users) {
         users.keySet().retainAll(listProvider.getNodeList());
      }
   }

   private void init() {
      this.listener = new NullChatListener();
   }

   private void sendNewUserEvent(String username) {
      this.listener.newUser(username);
   }

   private void sendNewMessageEvent(Message message) {
      this.listener.newMessage(message);
   }

   /**
    *  Description of the Class
    *
    *@author    Terracotta, Inc.
    */
   private static class NullChatListener implements ChatListener {
      public void newMessage(Message message) {
         //
      }

      public void newUser(String username) {
         //
      }
   }

}
