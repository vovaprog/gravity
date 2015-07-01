package gravity;

import javax.script.*;
import java.util.*;

public class ScriptHostJavaxScript extends ScriptHost{
    
    protected ScriptEngineManager factory = new ScriptEngineManager();        
    
    protected Map<String,ScriptEngine> engines=new HashMap<String,ScriptEngine>();
    protected Map<String,CompiledScript> compiledScripts=new HashMap<String,CompiledScript>();
    protected Map<String,Bindings> bindings=new HashMap<String,Bindings>();    
        
    protected void runScriptSimple(String name, String script, Map<String,Object> params) throws Exception
    {        
        ScriptEngine engine = engines.get(name);
        
        if(engine == null)
        {
            engine = factory.getEngineByName("JavaScript");
            engines.put(name, engine);            
        }

        for(String key : params.keySet())
        {
            engine.put(key,params.get(key));
        }
        
        engine.eval(script);
    }
   
    protected void runScriptCompiled(String name, String script, Map<String,Object> params) throws Exception
    {
        ScriptEngine engine = engines.get(name);
    
        if(engine == null)
        {
            engine = factory.getEngineByName("JavaScript");
            engines.put(name, engine);   
        }
                
        CompiledScript cscript = compiledScripts.get(name+script);
                
        if(cscript == null)
        {                        
            cscript = ((Compilable)engine).compile(script);                        
            compiledScripts.put(name+script,cscript);
        }
        
        Bindings bs = bindings.get(name);
        
        if(bs==null)
        {
            bs = engine.createBindings();
            bindings.put(name,bs);
        }        
            
        for(String key : params.keySet())
        {
            bs.put(key,params.get(key));
        }
              
        cscript.eval(bs);
    }    
    
    public void runScript(String name, String script, Map<String,Object> params) throws Exception
    {
        runScriptCompiled(name,script,params);
        //runScriptSimple(name,script,params);
    }
}

