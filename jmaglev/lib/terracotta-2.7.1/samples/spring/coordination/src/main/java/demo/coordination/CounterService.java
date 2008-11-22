/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.coordination;

import java.text.MessageFormat;
import java.util.logging.Logger;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 *  Simple service using process coordination via synchronization.
 *
 *@author    Terracotta, Inc.
 */
public class CounterService
       implements DisposableBean, InitializingBean, Runnable {

   // By making this variables transient, Terracotta for Spring will *not* cluster it
   private transient boolean running;

   // These objects are distributed by Terracotta for Spring
   private int counter = 0;
   private final Object lock = new Object();
   private static final Logger logger = Logger.getLogger(CounterService.class.getName());

   private static final String LOG_PREFIX = System.getProperty("counter.log.prefix", "Counter service [INFO]:") + " ";
   private static final int MAX_COUNTER = 999;
   private static final String STATUS_FORMAT = "This node is currently <font color=\"green\"><b><i>{0}</i></b></font>.<br>The current distributed counter value is: {1}";

   public String getStatus() {
      synchronized (this) {
         return MessageFormat.format(STATUS_FORMAT, new Object[]{running ? "active" : "passive", new Integer(counter)});
      }
   }

   public void afterPropertiesSet()
          throws Exception {
      log("Creating background thread to try to increment counter");
      Thread t = new Thread(this, "MessageService");
      t.start();
   }

   public void destroy()
          throws Exception {
      running = false;
   }

   public void run() {
      log("[background thread] Waiting to synchronize on the counter lock...");
      synchronized (lock) {
         log("[background thread] Got the counter lock, I will start incrementing the counter");
         running = true;
         boolean logFirstIncrement = true;
         while (running) {
            synchronized (this) {
               counter++;
               if (counter > MAX_COUNTER) {
                  counter = 0;
               }
               if (logFirstIncrement) {
                  log("[background thread] Incremented the counter to " + counter + " -- will increment every second");
                  logFirstIncrement = false;
               }
            }
            try {
               Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {
               // Ignore and keep processing
            }
         }
      }
   }

   private static void log(String message) {
      logger.info(LOG_PREFIX + message);
   }

}
