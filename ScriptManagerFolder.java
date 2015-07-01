package gravity;

import java.io.*;
import java.util.*;

public class ScriptManagerFolder extends ScriptManager{

    public ScriptManagerFolder() throws IOException
    {
        scripts=new ArrayList<ScriptInfo>();
        
        loadScriptsFromDefaultFolder();
    }
    
    protected void loadScriptsFromDefaultFolder() throws IOException
    {
        String scriptFolderName="./scripts";
        
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
        }
                
        Utils.getFolderContents(runFolderName,folderNames,fileNames);
        
        for(String fileName : fileNames)
        {           
            scripts.add(new ScriptInfo(fileName));
        }
    }
}
