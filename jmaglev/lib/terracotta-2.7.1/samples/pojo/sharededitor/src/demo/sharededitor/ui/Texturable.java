/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.sharededitor.ui;

import java.awt.Image;

/**
 *  Description of the Interface
 *
 *@author    Terracotta, Inc.
 */
public interface Texturable {
   void setTexture(Image image);

   void clearTexture();
}
