/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.sharedqueue;

import java.util.Random;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class Job {

   private final int duration;

   private final String producer;

   private Worker consumer;

   private final int type;

   private int state;

   private String id;

   private static final int STATE_READY = 0;

   private static final int STATE_PROCESSING = 1;

   private static final int STATE_COMPLETE = 2;

   private static final int STATE_ABORTED = 3;

   public Job(String producer, int id) {
      Random random = new Random();
      this.state = STATE_READY;
      this.consumer = null;
      this.producer = producer;
      this.duration = random.nextInt(3) + 3;
      this.type = random.nextInt(3) + 1;
      this.id = Integer.toString(id);
      while (this.id.length() < 3) {
         this.id = "0" + this.id;
      }
   }

   public final void run(Worker consumer) {
      synchronized (this) {
         this.state = STATE_PROCESSING;
         this.consumer = consumer;
         try {
            Thread.sleep(duration * 1000L);
            this.state = STATE_COMPLETE;
         }
         catch (InterruptedException ie) {
            this.state = STATE_ABORTED;
         }
      }
   }

   public final String toXml() {
      return "<job>" + "<id>" + id + "</id>" + "<type>" + type + "</type>"
            + "<state>" + state + "</state>" + "<producer>" + producer
            + "</producer>" + "<consumer>" + getConsumer() + "</consumer>"
            + "<duration>" + duration + "</duration>" + "</job>";
   }

   private final String getConsumer() {
      return consumer == null ? "" : consumer.getName();
   }
}
