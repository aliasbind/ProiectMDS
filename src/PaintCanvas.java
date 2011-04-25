import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import java.awt.Point;
import java.awt.RenderingHints;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSlider;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class PaintCanvas extends Canvas {

  private GeneralPath path;
  private Point lastPoint;

  private ArrayList<GeneralPath> paths;
  private ArrayList<Integer> sizes;
  
  private int paintSize;
  private JSlider slider;

  PaintCanvas() {
    paths = new ArrayList<GeneralPath>();
    sizes = new ArrayList<Integer>();
    lastPoint = null;
    paintSize = 3;
  }

  public void bindEvents() {

    this.addMouseMotionListener(new MouseMotionListener() {
      public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        path.lineTo(x, y);
        path.moveTo(x, y);

        handleRepainting(lastPoint, new Point(x, y));
        lastPoint = new Point(x, y);
      }

      public void mouseMoved(MouseEvent e) {}
    });

    this.addMouseListener(new MouseListener() {
      public void mousePressed(MouseEvent e) {
        path = new GeneralPath();
        path.moveTo(e.getX(), e.getY());
        paths.add(path);

        sizes.add(paintSize);
        lastPoint = new Point(e.getX(), e.getY());
      }

      public void mouseReleased(MouseEvent e) {
        System.out.println(paths.size());
      }

      public void mouseClicked(MouseEvent e) {}
      public void mouseEntered(MouseEvent e) {}
      public void mouseExited(MouseEvent e) {}
    });
  }

  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setBackground(Color.white);
    g.setColor(Color.black);

    super.paint(g);

    int i;
    for(i=0; i<paths.size(); i++) {
      g2d.setStroke(new BasicStroke(sizes.get(i)));
      g2d.draw(paths.get(i));
    }
  }

  private void handleRepainting(Point start, Point end) {
    int dx = Math.abs(start.x-end.x) + paintSize;
    int dy = Math.abs(start.y-end.y) + paintSize;

    if(start.x < end.x) {
      if(start.y < end.y) {
        repaint(start.x, start.y, dx, dy);
      }
      else {
        repaint(start.x, end.y, dx, dy);
      }
    }
    else {
      if(start.y < end.y) {
        repaint(end.x, start.y, dx, dy);
      }
      else {
        repaint(end.x, end.y, dx, dy);
      }
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
  
}
