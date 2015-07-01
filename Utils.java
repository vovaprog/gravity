package gravity;

import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;

public class Utils {

    public static String readFile(String path) throws IOException 
    {
        return readFile(path, StandardCharsets.UTF_8);
    }
    
    public static String readFile(String path, Charset encoding) throws IOException 
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    
    public static void getFolderContents(String folderName, List<String> folders, List<String> files)
    {
        folders.clear();
        files.clear();
        
        File directory = new File(folderName);
   
        File[] fList = directory.listFiles();   
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                folders.add(file.getAbsolutePath());
            }
        }        
    }       
    
    public static String timeToString(double time)
    {
        int numberOfDays = (int)(time / (60 * 60 * 24));
        double rem = time % (60 * 60 * 24);
        int numberOfHours = (int)(rem / (60 * 60));
        rem = rem % (60 * 60);
        int numberOfMinutes = (int)(rem / 60);
        int numberOfSeconds = (int)(rem % 60);
        
        return String.format("%02d days   %02d:%02d   %02d sec", numberOfDays, numberOfHours, numberOfMinutes,numberOfSeconds);
    }
}
