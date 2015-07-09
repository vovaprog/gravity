package gravity;

public abstract class Controller{    
    
    protected Model model;
    protected View view;    
    
    protected int numberOfSteps;
    protected double stepTime;

    protected double savePositionToHistoryInterval;
    
    protected Controller() throws Exception
    {
        reset();
    }
        
    protected void reset() throws Exception
    {
        numberOfSteps=1;
        stepTime=getStepTimeBorder();
    }
    
    public abstract String[] getModelNames(); 
    public abstract void createModel(String ModelName) throws Exception;    
    
    public Model getModel()
    {
        return model;
    }
    
    public View getView()
    {
        return view;   
    }        
    
    
    public abstract void initModel() throws Exception;
    protected abstract void timeStep(double time, int numberOfSteps) throws Exception;
    
    public void timeFaster()
    {        
        if(stepTime < getStepTimeBorder())
        {
            stepTime *= getTimeChangeRate();
            
            if(stepTime > getStepTimeBorder())
            {
                stepTime = getStepTimeBorder();
            }
        }
        else
        {
            numberOfSteps = (int)(numberOfSteps * getTimeChangeRate());
        }
    }
    
    public void timeSlower()
    {        
        if(numberOfSteps>1)
        {
            numberOfSteps = (int)((double)numberOfSteps / getTimeChangeRate());
            if(numberOfSteps<1)
            {
                numberOfSteps=1;		           
            }
        }
        else
        {
            stepTime = stepTime / getTimeChangeRate();
        }        
    }    
    
    protected double getTimeChangeRate()
    {
        return 2.0;
    }
    
    protected double getStepTimeBorder()
    {
    	return 60.0;
    }
    
    public void timeStep() throws Exception
    {
        timeStep(stepTime, numberOfSteps);
    }
    
    public double getSavePositionToHistoryInterval()
    {
        return savePositionToHistoryInterval;
    }
    
    public void setSavePositionToHistoryInterval(double interval)
    {
        savePositionToHistoryInterval = interval;    
    }
}

