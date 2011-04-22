import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class LeftPanel extends JPanel {
  LeftPanel() {
    this.setLayout(new GridLayout(2, 1));
    ((GridLayout) this.getLayout()).setVgap(10);
    this.add(new JLabel("Brush Size: "));

    brushSlider = new JSlider(1, 24);
    this.add(brushSlider);
  }

  public JSlider getSlider() {
    return brushSlider;
  }

  private JSlider brushSlider;
}
