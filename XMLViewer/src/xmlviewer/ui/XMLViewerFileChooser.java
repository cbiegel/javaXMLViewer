package xmlviewer.ui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * This class is a custom modification of a JFileChooser fit to XML files.
 * 
 * @author cbiegel
 */
public class XMLViewerFileChooser {

    private final JFileChooser _fileChooser;
    private static String _lastFilePath = "";
    private static boolean _filePathChanged = false;

    public XMLViewerFileChooser() {
        _fileChooser = new JFileChooser("Open an XML file");
        setupFileChooser();
    }

    private void setupFileChooser()
    {
        _fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        _fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        final File startDir = new File(System.getProperty("user.home"));

        _fileChooser.setCurrentDirectory(startDir);

        FileFilter xmlFilter = new FileNameExtensionFilter("XML file", "xml");
        _fileChooser.setFileFilter(xmlFilter);
        _fileChooser.addChoosableFileFilter(xmlFilter);

        final int result = _fileChooser.showOpenDialog(null);

        _fileChooser.setVisible(true);
        if (result == JFileChooser.APPROVE_OPTION) {
            File chosenFile = _fileChooser.getSelectedFile();

            // if a new file was selected, set to true
            _filePathChanged = _lastFilePath.equals(chosenFile.getPath()) ? false : true;

            _lastFilePath = chosenFile.getPath();

        }
        _fileChooser.setVisible(false);
    }

    public JFileChooser getCustomFileChooser()
    {
        return _fileChooser;
    }

    public String getLastFilePath()
    {
        return _lastFilePath;
    }

    public boolean hasPathChanged()
    {
        return _filePathChanged;
    }
}
