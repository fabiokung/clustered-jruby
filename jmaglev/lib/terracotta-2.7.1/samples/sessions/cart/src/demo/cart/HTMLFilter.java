/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.cart;

/**
 *  HTML filter utility.
 *
 *@author     Craig R. McClanahan
 *@author     Tim Tye
 *@version    $Revision: 1 $ $Date: 2006-11-27 16:46:47 -0800 (Mon, 27 Nov
 *      2006) $
 */

public final class HTMLFilter {

   /**
    *  Filter the specified message string for characters that are sensitive
    *  in HTML. This avoids potential attacks caused by including JavaScript
    *  codes in the request URL that is often reported in error messages.
    *
    *@param  message  The message string to be filtered
    *@return          Description of the Returned Value
    */
   public static String filter(String message) {

      if (message == null) {
         return (null);
      }

      char content[] = new char[message.length()];
      message.getChars(0, message.length(), content, 0);
      StringBuffer result = new StringBuffer(content.length + 50);
      for (int i = 0; i < content.length; i++) {
         switch (content[i]) {
          case '<':
             result.append("&lt;");
             break;
          case '>':
             result.append("&gt;");
             break;
          case '&':
             result.append("&amp;");
             break;
          case '"':
             result.append("&quot;");
             break;
          default:
             result.append(content[i]);
         }
      }
      return (result.toString());
   }

}

