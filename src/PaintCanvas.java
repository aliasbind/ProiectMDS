import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.Point;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import java.util.ArrayList;

public class PaintCanvas extends Canvas {

  private ArrayList<Point> points;
  private boolean pressed = false;

  PaintCanvas() {
    points = new ArrayList<Point>();
  }

  public void bindEvents() {
    this.addMouseListener(new MouseListener() {

      public void mousePressed(MouseEvent e) {
        System.out.println("PRESSED");
        pressed = true;
      }
      public void mouseReleased(MouseEvent e) {
        System.out.println("RELEASED");
      }

      public void mouseClicked(MouseEvent e) {
        System.out.println("CLICKED");
      }

      public void mouseEntered(MouseEvent e) {
        System.out.println("ENTERED");
      }

      public void mouseExited(MouseEvent e) {
        System.out.println("EXITED");
      }
    });

    this.addMouseMotionListener(new MouseMotionListener() {
      public void mouseDragged(MouseEvent e) {
        points.add(new Point(e.getX(), e.getY()));
        repaint();
      }

      public void mouseMoved(MouseEvent e) {
      }
    });
  }

  public void paint(Graphics g) {

    Graphics2D g2d = (Graphics2D) g;
    super.paint(g);
    for(Point pt : points) {
      g2d.draw(new Ellipse2D.Float(pt.x, pt.y, 3, 3));
    }
  }
  
}
