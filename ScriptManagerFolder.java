package gravity;

import java.io.*;
import java.util.*;

public class ScriptManagerFolder extends ScriptManager{

    public ScriptManagerFolder() throws IOException
    {
    }
    
    protected void loadScriptsFromFolder(String folderName) throws IOException
    {
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
