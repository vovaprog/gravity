package gravity;

public abstract class Controller{    
    
    protected Model model;
    protected View view;    
    
    protected int numberOfSteps=1;
    protected double stepTime=getStepTimeBorder();
        
    protected Controller()
    {
    }
    
    public Controller(Model argModel, View argView)
    {
        model = argModel;
        view = argView;
    }
    
    public Model getModel()
    {
        return model;
    }
    
    public View getView()
    {
        return view;   
    }        
    
    public abstract void createModel() throws Exception;
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
}

