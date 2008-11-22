/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.chatter;

import java.util.Random;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class User {
   private final String name;
   private final String nodeId;

   private static final Random random = new Random();

   private static final String[] FIRST_NAMES = {"Miles", "Ella", "Nina",
         "Duke", "Charlie", "Billie", "Louis", "Fats", "Thelonious", "Dizzy"};

   private static final String[] LAST_NAMES = {"Davis", "Fitzgerald",
         "Simone", "Ellington", "Parker", "Holiday", "Armstrong", "Waller",
         "Monk", "Gillespie"};

   public User(String nodeId) {
      this.name = generateChatname();
      this.nodeId = nodeId;
   }

   public String getName() {
      return name;
   }

   public String getNodeId() {
      return nodeId;
   }

   public String toString() {
      return name + ", " + nodeId;
   }

   private static String generateChatname() {
      return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)]
            + LAST_NAMES[random.nextInt(LAST_NAMES.length)];
   }

}
