
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.AdjustmentEvent;

import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;

import javax.swing.KeyStroke;
import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import java.awt.Dimension;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowListener;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {

    public MainWindow() {
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        setLayout(layout);

        InitComponents();

        setPreferredSize(new Dimension(900, 700));
        setSize(new Dimension(900, 700));
        this.addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {

            }

            public void windowClosing(WindowEvent e) {
                Main.setWindowsNumber(-1);
            }

            public void windowClosed(WindowEvent e) {
              //dispose();
              
            }

            public void windowIconified(WindowEvent e) {

            }

            public void windowDeiconified(WindowEvent e) {

            }

            public void windowActivated(WindowEvent e) {

            }

            public void windowDeactivated(WindowEvent e) {

            }
        });
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        canvas.repaint();
    }

    private void InitComponents() {
        InitMenuBar();
        InitLeftPanel();
        InitCanvas();

        leftPanel.getStatusBar().getChooser().setCanvas(canvas);
        leftPanel.getStatusBar().setCanvas(canvas);
        this.pack();
    }

    private void InitMenuBar() {
        menubar = new Menu();
        setJMenuBar(menubar);
        menubar.findParent();
    }

    private void InitLeftPanel() {
        leftPanel = new LeftPanel();
        constraints.anchor = GridBagConstraints.NORTHWEST;
//        constraints.weightx = 1;
//        constraints.weighty = 1;
        layout.setConstraints(leftPanel, constraints);
        this.add(leftPanel);
        
        JLabel test = new JLabel("TEST");
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.fill = GridBagConstraints.REMAINDER;
        layout.setConstraints(test, constraints);
        this.add(test);
    }

    private void InitCanvas() {
        canvas = new PaintCanvas();
        canvas.attachSlider(leftPanel.getSlider());
        canvas.attachStatusBar(leftPanel.getStatusBar().getLabel());


        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 0.2;
        constraints.weighty = 0.2;
        constraints.gridwidth = GridBagConstraints.REMAINDER;


        scrollPane = new JScrollPane(canvas);
        

        layout.setConstraints(scrollPane, constraints);
        this.add(scrollPane);

        canvas.bindEvents();
        canvas.addComponentListener(new ComponentListener() {

            public void componentHidden(ComponentEvent e) {
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentShown(ComponentEvent e) {
            }

            public void componentResized(ComponentEvent e) {
                canvas.repaint();
            }
        });

        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            public void adjustmentValueChanged(AdjustmentEvent e) {
                canvas.repaint();
            }
        });

        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            public void adjustmentValueChanged(AdjustmentEvent e) {
                canvas.repaint();
            }
        });
    }

    public PaintCanvas getCanvas() {
        return canvas;
    }
    private PaintCanvas canvas;
    private LeftPanel leftPanel;
    private Menu menubar;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private JScrollPane scrollPane;
}
