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
      if (nrWindows == 0)
          System.exit(0);
  }

  static public void createAndShowGUI() {
      nrWindows=1;
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
