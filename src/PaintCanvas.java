
import java.awt.AWTException;
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
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JSlider;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PaintCanvas extends Canvas {

    private GeneralPath path;
    private Point lastPoint;
    private ArrayList<GeneralPath> paths;
    private ArrayList<Integer> sizes;
    private ArrayList<Color> colors;
    private int paintSize;
    private JSlider slider;
    private JLabel statusbar;
    private Color curentColor;
    private double scale;
    private AffineTransform at;
    private BufferedImage image;


    PaintCanvas() {
        paths = new ArrayList<GeneralPath>();
        sizes = new ArrayList<Integer>();
        colors = new ArrayList<Color>();
        curentColor = Color.black;
        lastPoint = null;
        paintSize = 3;
        scale=1;
        at = new AffineTransform();
        this.setSize(1024, 768);
        image=null;
    }

    public void bindEvents() {

        this.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                Rectangle bounds = getBounds();

                int x = e.getX();
                int y = e.getY();

                //System.out.println(bounds);
                if(x < bounds.width && y < bounds.height &&
                   x > 0     && y > 0) {
                    path.lineTo(x, y);
                    path.moveTo(x, y);

                    handleRepainting(lastPoint, new Point(x, y));
                    lastPoint = new Point(x, y);
                }
            }

            public void mouseMoved(MouseEvent e) {
                try {
                    Robot robotel = new Robot();
                    //statusbar.setText(robotel.getPixelColor(e.getX(), e.getY()).toString());
                    //System.out.println(robotel.getPixelColor(e.getX(), e.getY()).toString());
                } catch (AWTException ex) {
                    Logger.getLogger(PaintCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

        this.addMouseListener(new MouseListener() {

            public void mousePressed(MouseEvent e) {
                path = new GeneralPath();
                path.moveTo(e.getX(), e.getY());
                paths.add(path);


                sizes.add(paintSize);
                lastPoint = new Point(e.getX(), e.getY());
                if (e.getButton() == MouseEvent.BUTTON1) {
                    colors.add(curentColor);
                }
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    colors.add(Color.white);

                }
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
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
        

        if (image != null) {
            System.out.println(image.toString());
            g2d.drawImage(image, new AffineTransformOp(new AffineTransform(), AffineTransformOp.TYPE_BICUBIC),0, 0);
        }

        for (i = 0; i < paths.size(); i++) {
            g.setColor(colors.get(i));
            g2d.setStroke(new BasicStroke(sizes.get(i)));
            g2d.draw(paths.get(i));
        }
    }

    private void handleRepainting(Point start, Point end) {
        int dx = Math.abs(start.x - end.x) + paintSize;
        int dy = Math.abs(start.y - end.y) + paintSize;

        if (start.x < end.x) {
            if (start.y < end.y) {
                repaint(start.x, start.y, dx, dy);
            } else {
                repaint(start.x, end.y, dx, dy);
            }
        } else {
            if (start.y < end.y) {
                repaint(end.x, start.y, dx, dy);
            } else {
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

    public void attachStatusBar(JLabel label) {
        statusbar = label;
    }

    public void setNewColor(Color selectedColor) {
        curentColor = selectedColor;
    }

    public void setScale(int i) {

        switch(i) {
            case 1:
                scale = 1.25;
                break;

            case -1:
                scale = 0.75;
                break;
        }

        at.setToScale(scale, scale);
        
        for (GeneralPath gp : paths) {
            gp.transform(at);
        }

        this.repaint();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }
}
