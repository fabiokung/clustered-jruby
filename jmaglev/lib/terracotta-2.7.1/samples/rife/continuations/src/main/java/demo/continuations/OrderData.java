/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.continuations;

import java.util.Date;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class OrderData {

   private ShippingMethod shippingMethod;
   private CreditCardType creditCardType;
   private String creditCardNumber;
   private String creditCardExpiration;

   public void setShippingMethod(ShippingMethod shippingMethod) {
      this.shippingMethod = shippingMethod;
   }

   public void setCreditCardType(CreditCardType creditCardType) {
      this.creditCardType = creditCardType;
   }

   public void setCreditCardNumber(String creditCardNumber) {
      this.creditCardNumber = creditCardNumber;
   }

   public void setCreditCardExpiration(String creditCardExpiration) {
      this.creditCardExpiration = creditCardExpiration;
   }

   public ShippingMethod getShippingMethod() {
      return shippingMethod;
   }

   public CreditCardType getCreditCardType() {
      return creditCardType;
   }

   public String getCreditCardNumber() {
      return creditCardNumber;
   }

   public String getCreditCardExpiration() {
      return creditCardExpiration;
   }

   enum ShippingMethod { ground, express, air }
   enum CreditCardType { amex, visa, mastercard }
}

