package gravity;

import org.apache.commons.io.FilenameUtils;
import java.io.*;

public class ScriptInfo{
    
    protected String name;
    protected String codeCreate;
    protected String codeInit;
    protected String codeStep;
    protected String codeFinish;
    protected boolean globalContext = false;
    
    public ScriptInfo(String fileName) throws IOException
    {
        String fullText = Utils.readFile(fileName);
                                
        name = FilenameUtils.removeExtension(FilenameUtils.getName(fileName));
        
        String[] fullTextParts = fullText.split(
            "((?<=\\[step\\])|(?=\\[step\\])|(?<=\\[init\\])|(?=\\[init\\])|(?<=\\[create\\])|(?=\\[create\\])|(?<=\\[finish\\])|(?=\\[finish\\])|(?<=\\[global\\])|(?=\\[global\\]))");
        
        for(int i=0;i<fullTextParts.length;i++)
        {
            if(fullTextParts[i].equals("[create]"))
            {
                codeCreate = fullTextParts[i+1];
                i++;
            }
            else if(fullTextParts[i].equals("[init]"))
            {
                codeInit = fullTextParts[i+1];
                i++;                
            }
            else if(fullTextParts[i].equals("[step]"))
            {
                codeStep = fullTextParts[i+1];
                i++;                
            }
            else if(fullTextParts[i].equals("[finish]"))
            {
                codeFinish = fullTextParts[i+1];
                i++;                
            }
            else if(fullTextParts[i].equals("[global]"))
            {
                globalContext = true;
            }                        
        }
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getCodeCreate()
    {
        return codeCreate;
    }
    
    public String getCodeInit()
    {
        return codeInit;
    }
    
    public String getCodeStep()
    {
        return codeStep;
    }

    public boolean getGlobalContext()
    {
        return globalContext;    
    }
}

