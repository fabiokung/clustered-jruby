/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.continuations;

import com.uwyn.rife.site.ConstrainedBean;
import com.uwyn.rife.site.ConstrainedProperty;
import com.uwyn.rife.site.MetaData;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class OrderDataMetaData extends MetaData<ConstrainedBean, ConstrainedProperty> {
   /**
    *  Description of the Field
    */
   public static final String GROUP_SHIPPING = "shipping";
   /**
    *  Description of the Field
    */
   public static final String GROUP_CREDITCARD = "creditcard";

   public void activateMetaData() {
      addGroup(GROUP_SHIPPING)
            .addConstraint(new ConstrainedProperty("shippingMethod")
            .notNull(true));

      addGroup(GROUP_CREDITCARD)
            .addConstraint(new ConstrainedProperty("creditCardType")
            .notNull(true))
            .addConstraint(new ConstrainedProperty("creditCardNumber")
            .notNull(true)
            .minLength(16)
            .maxLength(16)
            .regexp("\\d+"))
            .addConstraint(new ConstrainedProperty("creditCardExpiration")
            .notNull(true)
            .maxLength(5)
            .regexp("\\d{2}/\\d{2}"));
   }
}
