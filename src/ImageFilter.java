import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;


public class ImageFilter extends FileFilter {

    String choice;
    ImageFilter(String ch)
    {
        choice=ch;
    }
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if(choice.matches("Toate pozele"))
            {
                if (extension.equals(Utils.bmp) ||
                    extension.equals(Utils.gif) ||
                    extension.equals(Utils.jpeg) ||
                    extension.equals(Utils.jpg) ||
                    extension.equals(Utils.png)) {
                        return true;
                }
                else
                {
                    return false;
                }
            }
            else if(choice.matches(extension))
                return true;
        }

        return false;
    }


    public String getDescription() {
        return choice;
    }
}