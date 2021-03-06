/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.sharededitor.models;

import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.awt.Image;
import java.awt.Shape;

import demo.sharededitor.ui.Texturable;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
class Square extends BaseObject implements Texturable {
   private RoundRectangle2D.Double shape;

   private transient Shape[] anchors = null;

   private int x1, y1, x2, y2;

   public Square() {
      x1 = y1 = x2 = y2 = 0;
      shape = new RoundRectangle2D.Double();
      shape.arcwidth = shape.archeight = 12;
      shape.setFrameFromDiagonal(x1, y1, x2, y2);
   }

   public void setTexture(Image image) {
      synchronized (this) {
         super.setTexture(image);
         notifyListeners(this);
      }
   }

   public boolean isTransient() {
      RectangularShape bounds = (RectangularShape) shape.getBounds();
      return (bounds.getHeight() * bounds.getWidth()) < 4;
   }

   public void move(int dx, int dy) {
      synchronized (this) {
         x1 += dx;
         y1 += dy;
         x2 += dx;
         y2 += dy;
         shape.setFrameFromDiagonal(x1, y1, x2, y2);
         updateAnchors();
      }
      this.notifyListeners(this);
   }

   public void resize(int x, int y) {
      synchronized (this) {
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
         updateAnchors();
      }
      this.notifyListeners(this);
   }

   public void clearTexture() {
      synchronized (this) {
         super.clearTexture();
      }
   }

   protected Shape getShape() {
      return shape;
   }

   protected Shape[] getAnchors() {
      return updateAnchors();
   }

   private Shape[] updateAnchors() {
      if (anchors == null) {
         anchors = new Shape[]{
               new Ellipse2D.Double(x1 - 5, y2 - 5, 10, 10),
               new Ellipse2D.Double(x2 - 5, y2 - 5, 10, 10),
               new Ellipse2D.Double(x2 - 5, y1 - 5, 10, 10),
               new Ellipse2D.Double(x1 - 5, y1 - 5, 10, 10)};
         return anchors;
      }

      ((Ellipse2D.Double) anchors[0]).x = x1 - 5;
      ((Ellipse2D.Double) anchors[0]).y = y2 - 5;
      ((Ellipse2D.Double) anchors[1]).x = x2 - 5;
      ((Ellipse2D.Double) anchors[1]).y = y2 - 5;
      ((Ellipse2D.Double) anchors[2]).x = x2 - 5;
      ((Ellipse2D.Double) anchors[2]).y = y1 - 5;
      ((Ellipse2D.Double) anchors[3]).x = x1 - 5;
      ((Ellipse2D.Double) anchors[3]).y = y1 - 5;
      return anchors;
   }
}
