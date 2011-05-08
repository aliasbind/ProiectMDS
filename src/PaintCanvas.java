
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import java.awt.geom.GeneralPath;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import javax.swing.JLabel;

import javax.swing.JSlider;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PaintCanvas extends Canvas {

    private GeneralPath path;
    private Point lastPoint;
    private ArrayList<GeneralPath> paths;
    private ArrayList<Integer> sizes;
    private ArrayList<Color> colors;
    private ArrayList<Rectangle> deletedSpots;
    
    private int paintSize;
    private JSlider slider;
    private JLabel statusbar;
    private Color curentColor;
    private double scale;
    private AffineTransform at;
    private BufferedImage image;
    private BufferedImage imageForPixelRGB;
    private boolean canvasChanged;
    private boolean selectionMode;
    private Rectangle selectionRectangle;

    PaintCanvas() {
        paths = new ArrayList<GeneralPath>();
        sizes = new ArrayList<Integer>();
        colors = new ArrayList<Color>();
        deletedSpots = new ArrayList<Rectangle>();
        curentColor = Color.black;
        lastPoint = null;
        paintSize = 3;
        scale = 1;
        at = new AffineTransform();
        this.setSize(1024, 768);
        image = null;
        imageForPixelRGB = null;
        canvasChanged = true;
        selectionMode = false;

        this.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setSelectionMode(false);
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public void bindEvents() {

        this.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                if (selectionMode == false) {
                    Rectangle bounds = getBounds();



                    //System.out.println(bounds);
                    if (x < bounds.width && y < bounds.height
                            && x > 0 && y > 0) {
                        path.lineTo(x, y);
                        path.moveTo(x, y);

                        handleRepainting(lastPoint, new Point(x, y));
                        lastPoint = new Point(x, y);
                    }
                } else {
                    Point src = selectionRectangle.getLocation();
                    selectionRectangle.setSize(x-src.x + 1, y-src.y + 1);
                    repaint();
                }
            }

            public void mouseMoved(MouseEvent e) {
                if (imageForPixelRGB == null) {
                    imageForPixelRGB = (BufferedImage) createImage(getWidth(), getHeight());
                }

                if (canvasChanged) {
                    Graphics g = imageForPixelRGB.getGraphics();
                    paint(g);
                    canvasChanged = false;
                }

                int color = imageForPixelRGB.getRGB(e.getX(), e.getY());
                int red = (color & 0x00ff0000) >> 16;
                int green = (color & 0x0000ff00) >> 8;
                int blue = color & 0x000000ff;
                statusbar.setText("R: " + red + " G: " + green + " B: " + blue);
            }
        });

        this.addMouseListener(new MouseListener() {

            public void mousePressed(MouseEvent e) {
                if (selectionMode == false) {
                    path = new GeneralPath();
                    path.moveTo(e.getX(), e.getY());
                    paths.add(path);

                    canvasChanged = true;
                    sizes.add(paintSize);
                    lastPoint = new Point(e.getX(), e.getY());
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        colors.add(curentColor);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        colors.add(Color.white);

                    }
                } else {
                    selectionRectangle.setLocation(new Point(e.getX(), e.getY()));
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

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setBackground(Color.white);
        g.setColor(Color.black);

        super.paint(g);

        if (image != null) {
            System.out.println(image.toString());
            g2d.drawImage(image, new AffineTransformOp(new AffineTransform(), AffineTransformOp.TYPE_BICUBIC), 0, 0);
        }

        int i;
        for (i = 0; i < paths.size(); i++) {
            g.setColor(colors.get(i));
            g2d.setStroke(new BasicStroke(sizes.get(i)));
            g2d.draw(paths.get(i));
        }

        if (selectionMode && selectionRectangle != null) {
            float[] dash1 = {10.0f};
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
            g2d.draw(selectionRectangle);
        }
        
        for(i = 0; i < deletedSpots.size(); i++) {
            g.setColor(Color.white);
            g2d.fill(deletedSpots.get(i));
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

        switch (i) {
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

    public void setSelectionMode(boolean b) {
        if (b) {
            System.out.println("Selection Mode Activated");
            selectionRectangle = new Rectangle();
        } else {
            System.out.println("Selection Mode Deactivated");
        }

        selectionMode = b;
    }

    public void deleteSelectedArea() {
        deletedSpots.add(selectionRectangle);
        repaint(selectionRectangle.x, selectionRectangle.y,
                selectionRectangle.width+10, selectionRectangle.height+10);
        selectionMode = false;
    }
}
