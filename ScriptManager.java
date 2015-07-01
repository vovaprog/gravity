package gravity;

import java.util.*;

public class ScriptManager{
    
    protected ScriptHost scriptHost;
    protected List<ScriptInfo> scripts;
    
    public ScriptManager()
    {
        scriptHost=new ScriptHostJavaxScript();
    }

    protected String getScriptName(ScriptInfo script)
    {
        if(script.getGlobalContext())
        {
            return "global";    
        }
        else
        {
            return script.getName();   
        }
    }
    
    public void runCreateScripts(Map<String,Object> params) throws Exception
    {
        for(ScriptInfo script : scripts)
        {
            if(script.getCodeCreate()!=null)
            {
                scriptHost.runScript(getScriptName(script),script.getCodeCreate(),params);
            }
        }
    }    
        
    public void runInitScripts(Map<String,Object> params) throws Exception
    {
        for(ScriptInfo script : scripts)
        {
            if(script.getCodeInit()!=null)
            {
                scriptHost.runScript(getScriptName(script),script.getCodeInit(),params);
            }
        }
    }
    
    public void runStepScripts(Map<String,Object> params) throws Exception
    {
        for(ScriptInfo script : scripts)
        {
            if(script.getCodeStep()!=null)
            {
                scriptHost.runScript(getScriptName(script),script.getCodeStep(),params);
            }
        }
    }
}


