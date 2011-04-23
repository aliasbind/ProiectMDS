import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.Point;
import java.awt.RenderingHints;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JSlider;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.util.ArrayList;

public class PaintCanvas extends Canvas {

  private ArrayList<Point> points;
  private ArrayList<Integer> sizes;

  PaintCanvas() {
    points = new ArrayList<Point>();
    sizes = new ArrayList<Integer>();
    paintSize = 3;
  }

  public void bindEvents() {

    this.addMouseMotionListener(new MouseMotionListener() {
      public void mouseDragged(MouseEvent e) {
        points.add(new Point(e.getX(), e.getY()));
        sizes.add(new Integer(paintSize));
        repaint(e.getX()-paintSize, e.getY()-paintSize, 
                paintSize*2, paintSize*2);
      }

      public void mouseMoved(MouseEvent e) {}
    });
  }

  public void paint(Graphics g) {

    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
    super.paint(g);
    int i;
    for(i=0; i<points.size(); i++) {
      Point pt = points.get(i);
      Integer x = sizes.get(i);
      g2d.fill(new Ellipse2D.Float(pt.x, pt.y, x, x));
    }
  }

  public void attachSlider(JSlider slider) {
    this.slider = slider;
    this.slider.setValue(paintSize);
    this.slider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        paintSize = ((JSlider) e.getSource()).getValue();
      }
    });
  }
  
  private JSlider slider;
  private int paintSize;
}
