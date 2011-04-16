public class Main {
  static public void createAndShowGUI() {
    new MainWindow();
  }

  static public void main(String args[]) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}
