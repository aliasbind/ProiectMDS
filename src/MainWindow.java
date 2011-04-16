import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.KeyStroke;
import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import java.awt.Dimension;

public class MainWindow extends JFrame {
  public MainWindow() {
    layout = new GridBagLayout();
    constraints = new GridBagConstraints();
    setLayout(layout);

    InitComponents();
    
    setPreferredSize(new Dimension(900, 300));
    setSize(new Dimension(900, 300));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    canvas.repaint();
  }

  private void InitComponents() {
    InitMenuBar();
    InitLeftPanel();
    InitCanvas();
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
    constraints.weightx = 0.1;
    constraints.weighty = 0.1;
    layout.setConstraints(leftPanel, constraints);
    this.add(leftPanel);
  }

  private void InitCanvas() {
    canvas = new PaintCanvas();

    constraints.fill = GridBagConstraints.BOTH;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.weightx = 2.0;
    constraints.weighty = 2.0;
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    layout.setConstraints(canvas, constraints);
    this.add(canvas);
    canvas.bindEvents();
  }

  private PaintCanvas canvas;
  private LeftPanel leftPanel;
  private Menu menubar;

  private GridBagLayout layout;
  private GridBagConstraints constraints;
}
