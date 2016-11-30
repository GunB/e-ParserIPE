package com.caguaicorp.e.parser.utiility;

import com.caguaicorp.e.parser.bin.MetadataParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FilesUtility {

    public static String strRoot = System.getProperty("user.home");
    private static long unixTime = System.currentTimeMillis() / 1000L;
    private static String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime());
    public static String strFile2Change = "metadata.xml";

    public static Document XmlFormatBase() throws ParserConfigurationException, SAXException, IOException {
        String strBase = strRoot + File.separator + strFile2Change;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        //File f = new File(strBase);
        //if (!f.exists()) {
        try (PrintWriter out = new PrintWriter(strBase)) {

            String text = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
                    + "<cem:cem xmlns:cem=\"http://ltsc.ieee.org/xsd/CEM\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                    + "xsi:schemaLocation=\"http://ltsc.ieee.org/xsd/LOM lomCustom.xsd\" xsi:type=\" cem:cem\">"
                    + "\n<general>"
                    + "\n<identifier>"
                    + "\n<catalog catName=\"edistribution\" catSource=\"http://www.edistribution.co/\"/>"
                    + "\n</identifier>"
                    + "\n<title lang=\"\" subtitle=\"\"></title>"
                    + "\n<description lang=\"\"/>"
                    + "\n<keyword lang=\"\"/>"
                    + "\n<structure schema=\"CEM\"/>"
                    + "\n<aggregationLevel schema=\"CEM\"/>"
                    + "\n</general>"
                    + "\n<lifeCycle>"
                    + "<version date=\"30-04-2015\">1.0</version>\n"
                    + "<status>Publicado</status>"
                    + "\n</lifeCycle>"
                    + "\n<metaMetadata/>"
                    + "\n<technical/>"
                    + "\n<educational>"
                    + "\n<description>"
                    + "\n<recommendedUse lang=\"\"/>"
                    + "\n<triggerQuestion lang=\"\"/>"
                    + "\n<pedagogicalAspect lang=\"\"/>"
                    + "\n<learningGoal lang=\"\"/>"
                    + "\n</description>"
                    + "\n</educational>"
                    + "\n<rights/>"
                    + "\n<classification>"
                    + "\n</classification>"
                    + "\n</cem>";
            out.println(text);
        }
        //}
        //FileInputStream in = new FileInputStream(new File(strBase));
        //doc = dBuilder.parse(in, "UTF-8");
        return (Document) dBuilder.parse(strBase).cloneNode(true);
    }

    public static void copyFolder(File src, File dest)
            throws IOException {
        if (src.isDirectory()) {

            if (!dest.exists()) {
                dest.mkdir();
                System.out.println("Directory copied from " + src + "  to " + dest);
            }

            String[] files = src.list();

            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);

                copyFolder(srcFile, destFile);
            }

        } else {
            InputStream in = new FileInputStream(src);
            Object out = new FileOutputStream(dest);

            byte[] buffer = new byte['Ѐ'];

            int length;

            while ((length = in.read(buffer)) > 0) {
                ((OutputStream) out).write(buffer, 0, length);
            }

            in.close();
            ((OutputStream) out).close();
            System.out.println("File copied from " + src + " to " + dest);
        }
    }

    public static FileStore getPathFilesystem(String path) throws URISyntaxException, IOException {
        URI rootURI = new URI("file:///");
        Path rootPath = Paths.get(rootURI);
        Path dirPath = rootPath.resolve(path);
        FileStore dirFileStore = Files.getFileStore(dirPath);
        return dirFileStore;
    }

    public static void DrivesnDesc() {
        FileSystemView fsv = FileSystemView.getFileSystemView();

        File[] paths = File.listRoots();

        for (File path : paths) {
            System.out.println("Drive Name: " + path);
            System.out.println("Description: " + fsv.getSystemTypeDescription(path));
        }
    }

    public static String PathRootDesc(String strFilePath) {
        FileSystemView fsv = FileSystemView.getFileSystemView();

        File newFile = new File(strFilePath);
        newFile = GetFullParent(newFile);

        return fsv.getSystemTypeDescription(newFile);
    }

    public static File GetFullParent(File file) {
        try {
            file = GetFullParent(new File(file.getParent()));
        } catch (NullPointerException ex) {
            return file;
        }

        return file;
    }

    public static File CopyFolder(String strPathFolder) {

        File baseFileDirectory = new File(strPathFolder);

        File newFileDirectory
                = new File(baseFileDirectory.getParent() + File.separator
                        + "eFIXED_" + FilesUtility.unixTime + " " + FilesUtility.timeStamp);

        newFileDirectory.mkdirs();
        try {
            FilesUtility.copyFolder(baseFileDirectory, newFileDirectory);
        } catch (IOException ex) {
            Logger.getLogger(MetadataParser.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            System.exit(9);
        }

        return newFileDirectory;
    }

    public static void printFileStore(FileStore filestore, String path) throws IOException {
        System.out.println("Name: " + filestore.name());
        System.out.println("\tPath: " + path);
        System.out.println("\tSize: " + filestore.getTotalSpace());
        System.out.println("\tUnallocated: " + filestore.getUnallocatedSpace());
        System.out.println("\tUsable: " + filestore.getUsableSpace());
        System.out.println("\tType: " + filestore.type());
    }
}
