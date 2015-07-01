package gravity;

import java.io.*;
import java.util.*;

public class ControllerSimple extends Controller{    
        
    protected ScriptManager scriptManager;
    
	protected double savePositionToHistoryInterval = 5 * getStepTimeBorder();
    protected double runStepScriptsInterval = 5 * getStepTimeBorder();    
    
    public ControllerSimple() throws IOException
    {    	
        model = new ModelSimple();
        
        double r = 440000000;
        
        view = new View2d(-r, r);
                
        scriptManager = new ScriptManagerFolder();
    }    
    
    protected Map<String,Object> createScriptParameters()
    {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("model",model);
        params.put("view",view);
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
            
            if(i==0 || saveTime>savePositionToHistoryInterval)
            {
            	model.savePositionToHistory();
            	
            	if(i>0)
            	{
            		saveTime -= savePositionToHistoryInterval;
            	}
            }        	
        	
            model.timeStep(time);
        }
    }    
}

