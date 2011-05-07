
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {

    static public int nrWindows;

    static public void setWindowsNumber(int state) {
        switch (state) {
            case 1:
                nrWindows++;
                break;
            case -1:
                nrWindows--;
                break;
        }
        if (nrWindows == 0) {
            System.exit(0);
        }
    }

    static public void createAndShowGUI() {
        nrWindows = 1;
        new MainWindow();
    }

    static public void main(String args[]) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                createAndShowGUI();
            }
        });
    }
}
