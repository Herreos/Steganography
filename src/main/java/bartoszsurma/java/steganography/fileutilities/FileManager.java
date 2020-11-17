package bartoszsurma.java.steganography.fileutilities;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileManager {
    public static File showFileDialog(JFrame frame, final boolean open) {
        JFileChooser fileChooser = new JFileChooser("Open an image");
        FileFilter fileFilter = new javax.swing.filechooser.FileFilter() {
            public boolean accept(File file) {
                String name = file.getName().toLowerCase();
                if(open)
                    return file.isDirectory() || name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                            name.endsWith(".png") || name.endsWith(".bmp");
                return file.isDirectory() || name.endsWith(".png") ||    name.endsWith(".bmp");
            }
            public String getDescription() {
                if(open)
                    return "Obraz (*.jpg, *.jpeg, *.png, *.bmp)";
                return "Obraz (*.png, *.bmp)";
            }
        };
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(fileFilter);

        File file = null;
        if(open && fileChooser.showOpenDialog(frame) == fileChooser.APPROVE_OPTION)
            file = fileChooser.getSelectedFile();
        else if(!open && fileChooser.showSaveDialog(frame) == fileChooser.APPROVE_OPTION)
            file = fileChooser.getSelectedFile();
        return file;
    }

    public static File showTextDialog(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser("Open a message");
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File f) {
                String name = f.getName().toLowerCase();
                return f.isDirectory() || name.endsWith(".txt");
            }
            public String getDescription() {
                return "Plik tekstowy (*.txt)";
            }
        };
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(fileFilter);

        File file = null;
        if(fileChooser.showOpenDialog(frame) == fileChooser.APPROVE_OPTION)
            file = fileChooser.getSelectedFile();
        return file;
    }
}
