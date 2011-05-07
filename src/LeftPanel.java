
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class LeftPanel extends JPanel {

    LeftPanel() {
        this.setLayout(new GridLayout(3, 1));
        ((GridLayout) this.getLayout()).setVgap(10);
        this.add(new JLabel("Brush Size: "));

        brushSlider = new JSlider(1, 24);
        statusbar = new StatusBar();
        this.add(brushSlider);
        this.add(statusbar);

    }

    public JSlider getSlider() {
        return brushSlider;
    }

    public StatusBar getStatusBar() {
        return statusbar;
    }

    private JSlider brushSlider;
    private StatusBar statusbar;
}
