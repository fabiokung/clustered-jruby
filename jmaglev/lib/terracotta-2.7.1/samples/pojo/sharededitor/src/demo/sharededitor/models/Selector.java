/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.sharededitor.models;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
final class Selector extends BaseObject {
   private Rectangle2D.Double shape;

   private int x1, y1, x2, y2;

   public Selector() {
      x1 = y1 = x2 = y2 = 0;
      shape = new Rectangle2D.Double();
      shape.setFrameFromDiagonal(x1, y1, x2, y2);
   }

   public boolean isTransient() {
      return true;
   }

   public synchronized void move(int dx, int dy) {
      x1 += dx;
      y1 += dy;
      x2 += dx;
      y2 += dy;
      shape.setFrameFromDiagonal(x1, y1, x2, y2);
      this.notifyListeners(this);
   }

   public synchronized void resize(int x, int y) {
      switch (grabbedAnchor()) {
       case 0:
          x1 = x;
          y2 = y;
          break;
       case 1:
          x2 = x;
          y2 = y;
          break;
       case 2:
          x2 = x;
          y1 = y;
          break;
       case 3:
          x1 = x;
          y1 = y;
          break;
      }
      shape.setFrameFromDiagonal(x1, y1, x2, y2);
      this.notifyListeners(this);
   }

   protected Shape getShape() {
      return shape;
   }

   protected Shape[] getAnchors() {
      return new Shape[]{new Ellipse2D.Double(x1 - 5, y2 - 5, 10, 10),
            new Ellipse2D.Double(x2 - 5, y2 - 5, 10, 10),
            new Ellipse2D.Double(x2 - 5, y1 - 5, 10, 10),
            new Ellipse2D.Double(x1 - 5, y1 - 5, 10, 10)};
   }
}
