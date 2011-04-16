import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Menu extends JMenuBar {
  Menu() {
    File = new JMenu("File");

    New = new JMenuItem("New");
    New.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
    File.add(New);
    
    Open = new JMenuItem("Open");
    Open.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
    File.add(Open);
    
    Save = new JMenuItem("Save");
    Save.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
    File.add(Save);
    
    SaveAs = new JMenuItem("Save as");
    File.add(SaveAs);
    
    File.add(new JSeparator());

    Quit = new JMenuItem("Quit");
    Quit.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
    Quit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        OnQuit(e);
      }
    });
    File.add(Quit);
    
    Edit = new JMenu("Edit");
    
    Undo = new JMenuItem("Undo");
    Undo.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
    Edit.add(Undo);

    Redo = new JMenuItem("Redo");
    Redo.setAccelerator(KeyStroke.getKeyStroke("shift ctrl Z"));
    Edit.add(Redo);

    Edit.add(new JSeparator());

    Cut = new JMenuItem("Cut");
    Cut.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
    Edit.add(Cut);

    Copy = new JMenuItem("Copy");
    Copy.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
    Edit.add(Copy);

    Paste = new JMenuItem("Paste");
    Paste.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
    Edit.add(Paste);

    Delete = new JMenuItem("Delete");
    Delete.setAccelerator(KeyStroke.getKeyStroke("DELETE"));
    Edit.add(Delete);

    this.add(File);
    this.add(Edit);

  }

  public void findParent() {
    parent = (MainWindow) getParent().getParent().getParent();
  }

  private JMenu File;
  private JMenuItem New;
  private JMenuItem Open;
  private JMenuItem Save;
  private JMenuItem SaveAs;
  private JMenuItem Quit;

  private JMenu Edit;
  private JMenuItem Undo;
  private JMenuItem Redo;
  private JMenuItem Cut;
  private JMenuItem Copy;
  private JMenuItem Paste;
  private JMenuItem Delete;

  private MainWindow parent;

  public void OnQuit(ActionEvent e) {
    System.exit(0);
  }
}
