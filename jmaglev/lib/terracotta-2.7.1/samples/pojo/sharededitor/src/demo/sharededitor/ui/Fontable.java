/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.sharededitor.ui;

/**
 *  Description of the Interface
 *
 *@author    Terracotta, Inc.
 */
public interface Fontable {
   void setFontInfo(String name, int size, String text);

   void setFontName(String name);

   void setFontSize(int size);

   void setText(String text);

   String getText();

   void appendToText(char c);
}
