package gravity;

import java.io.*;
import java.util.*;

public class ScriptManagerFolder extends ScriptManager{

    public ScriptManagerFolder() throws IOException
    {
        
        
        //loadScriptsFromDefaultFolder();
    }
    
    protected void loadScriptsFromFolder(String folderName) throws IOException
    {
        /*String scriptFolderName="./scripts";
        
        ArrayList<String> folderNames=new ArrayList<String>();
        ArrayList<String> fileNames=new ArrayList<String>();
        
        Utils.getFolderContents(scriptFolderName,folderNames,fileNames);
        
        String runFolderName = null;
        
        for(String folderName : folderNames)
        {
            if(folderName.endsWith("_run"))
            {
                runFolderName = folderName;
                break;
            }
        }
        
        if(runFolderName == null)
        {
            throw new IOException("script folder not found!");    
        }*/
        
        
        scripts=new ArrayList<ScriptInfo>();

        ArrayList<String> folderNames=new ArrayList<String>();
        ArrayList<String> fileNames=new ArrayList<String>();        
        
        Utils.getFolderContents(folderName,folderNames,fileNames);
        
        for(String fileName : fileNames)
        {           
            scripts.add(new ScriptInfo(fileName));
        }
    }
}
