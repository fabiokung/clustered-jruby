/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.events;

import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 *  Simple message event, this will be distributed by Terracotta for Spring
 *  and sent to each of the nodes in the cluster whenever this event is sent
 *  via the {@link ApplicationContext#publishEvent(ApplicationEvent)} method.
 *
 *@author    Terracotta, Inc.
 */
public class MessageEvent
       extends ApplicationEvent {
   private final String message;

   public MessageEvent(Object source, String message) {
      super(source);
      this.message = message;
   }

   public String getMessage() {
      return message;
   }

   public Date getDate() {
      return new Date(getTimestamp());
   }

   public String toString() {
      return message;
   }
}
