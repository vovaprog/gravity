package gravity;

import java.io.*;
import java.util.*;
import org.apache.commons.io.FilenameUtils;

public class ControllerSimple extends Controller{    
        
    protected ScriptManagerFolder scriptManager;  
    protected double runStepScriptsInterval;    
    
    public ControllerSimple() throws Exception
    {    	        
        reset();
    }    
    
    protected void reset() throws Exception
    {
        super.reset();
        
        setSavePositionToHistoryInterval(5 * getStepTimeBorder());
        runStepScriptsInterval = 5 * getStepTimeBorder();
        
        double r = 440000000;
        
        view = new View2d(-r, r);
                
        scriptManager = new ScriptManagerFolder();
    }
    
    public String[] getModelNames()
    {
        String scriptFolderName="./scripts";
        
        ArrayList<String> folderNames=new ArrayList<String>();
        ArrayList<String> fileNames=new ArrayList<String>();
        
        Utils.getFolderContents(scriptFolderName,folderNames,fileNames);
        
        String[] modelNames = (String[])folderNames.toArray(new String[folderNames.size()]);
        
        for(int i=0;i<modelNames.length;i++)
        {
            modelNames[i]=FilenameUtils.getName(modelNames[i]);   
        }
        
        Arrays.sort(modelNames);
        
        return modelNames;
    }
    
    public void createModel(String ModelName) throws Exception
    {
        reset();
        
        model = new ModelSimple();
        
        scriptManager.loadScriptsFromFolder("./scripts/"+ModelName);
        
        scriptManager.runCreateScripts(createScriptParameters());
    }
    
    protected Map<String,Object> createScriptParameters()
    {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("model",model);
        params.put("view",view);
        params.put("controller",this);
        return params;
    }
    
    public void createModel() throws Exception
    {
        scriptManager.runCreateScripts(createScriptParameters());
    }
    
    public void initModel() throws Exception
    {
        scriptManager.runInitScripts(createScriptParameters());
    }
    
    protected void timeStep(double time, int numberOfSteps) throws Exception
    {        
        double scriptTime = 0.0;
        double saveTime = 0.0;

        for(int i=0;i<numberOfSteps;i++)
        {   
        	scriptTime += time;            
            
            if(i==0 || scriptTime>runStepScriptsInterval)
            {
            	scriptManager.runStepScripts(createScriptParameters());
            	
            	if(i>0)
            	{
            		scriptTime -= runStepScriptsInterval;
            	}
            }
            
            saveTime += time;
            
            if(i==0 || saveTime>getSavePositionToHistoryInterval())
            {
            	model.savePositionToHistory();
            	
            	if(i>0)
            	{
            		saveTime -= getSavePositionToHistoryInterval();
            	}
            }        	
        	
            model.timeStep(time);
        }
    }    
}

