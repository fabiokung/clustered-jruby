/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.continuations;

import com.uwyn.rife.engine.Site;
import com.uwyn.rife.engine.SiteBuilder;
import com.uwyn.rife.rep.SingleObjectParticipant;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class ParticipantSite extends SingleObjectParticipant {
   private Site site;

   public ParticipantSite() {
      SiteBuilder builder = new SiteBuilder("main");

      builder.setArrival("Order")
            .enterElement()
            .setImplementation(Order.class)
            .leaveElement();

      site = builder.getSite();
   }

   public Object getObject() {
      return site;
   }
}
