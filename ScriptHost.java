package gravity;

import java.util.Map;

public abstract class ScriptHost{

    public abstract void runScript(String name, String script, Map<String,Object> params) throws Exception;

}

