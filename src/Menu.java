
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javax.swing.KeyStroke;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import javax.swing.*;

public class Menu extends JMenuBar {

    private boolean alreadysave;
    private File savelocation;

    Menu() {
        alreadysave=false;

        File = new JMenu("File");

        New = new JMenuItem("New");
        New.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        New.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                OnNew(e);
            }
        });
        File.add(New);

        Open = new JMenuItem("Open");
        Open.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        Open.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    OnOpen(e);
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        File.add(Open);


        Save = new JMenuItem("Save");
        Save.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        Save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if(alreadysave)
                    {
                         Saved(e);
                    }
                    else
                        OnSave(e);
                }catch(IOException err){}
            }
        });
        File.add(Save);

        SaveAs = new JMenuItem("Save as");
        File.add(SaveAs);
        SaveAs.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                        OnSave(e);
                }catch(IOException err){}
            }
        });

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
        Delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                OnDelete(e);
            }
        });
        
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

    public void OnNew(ActionEvent e) {
        new MainWindow().setVisible(true);
        Main.setWindowsNumber(1);
    }

    public void OnOpen(ActionEvent e) throws IOException {

        JFileChooser fc;

            fc = new JFileChooser();
            fc.addChoosableFileFilter(new ImageFilter("bmp"));
            fc.addChoosableFileFilter(new ImageFilter("gif"));
            fc.addChoosableFileFilter(new ImageFilter("jpg"));
            fc.addChoosableFileFilter(new ImageFilter("jpeg"));
            fc.addChoosableFileFilter(new ImageFilter("png"));
            fc.addChoosableFileFilter(new ImageFilter("Toate pozele"));
            fc.setAcceptAllFileFilterUsed(false);
            fc.setAccessory(new ImagePreview(fc));
            
        File f ;
        JPanel aux=new JPanel();
        int returnVal = fc.showDialog(aux,"Select");

        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            f = fc.getSelectedFile();
            BufferedImage image = ImageIO.read(f);
            parent.getCanvas().setImage(image);
        }
        
        parent.getCanvas().clearArrays();
    }

    public void Saved(ActionEvent e) throws IOException {
        int imgWidth;
        int imgHeight;

        PaintCanvas mainCanvas = parent.getCanvas();
        Dimension canvasDimension = mainCanvas.getSize();

        imgWidth = canvasDimension.width;
        imgHeight = canvasDimension.height;

        BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        mainCanvas.paint(image.getGraphics());
        ImageIO.write(image, "jpeg",savelocation);

    }

    public void OnSave(ActionEvent e) throws IOException {
        int imgWidth;
        int imgHeight;

        JFileChooser fc2=new JFileChooser("Save");

        PaintCanvas mainCanvas = parent.getCanvas();
        Dimension canvasDimension = mainCanvas.getSize();

        imgWidth = canvasDimension.width;
        imgHeight = canvasDimension.height;

        fc2 = new JFileChooser();
        File initial = new File("Untitled");
        fc2.setSelectedFile(initial);
        fc2.addChoosableFileFilter(new ImageFilter("bmp"));
        fc2.addChoosableFileFilter(new ImageFilter("gif"));
        fc2.addChoosableFileFilter(new ImageFilter("jpeg"));
        fc2.addChoosableFileFilter(new ImageFilter("png"));
        fc2.addChoosableFileFilter(new ImageFilter("jpg"));
        fc2.setAcceptAllFileFilterUsed(false);
        JPanel aux=new JPanel();
        int returnVal = fc2.showSaveDialog(aux);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fc2.getSelectedFile();
                if(Utils.getExtension(file)==null)
                {

                    String path=file.toString();
                    path=path.concat("."+fc2.getFileFilter().getDescription());
                    file=new File(path);
                }
                BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
                mainCanvas.paint(image.getGraphics());
                ImageIO.write(image, "jpeg",file);
                alreadysave=true;
                savelocation=file;

            } 
    }

    public void OnQuit(ActionEvent e) {
        parent.dispose();
        Main.setWindowsNumber(-1);
    }
    
    public void OnDelete(ActionEvent e) {
        parent.getCanvas().deleteSelectedArea();
    }
}
