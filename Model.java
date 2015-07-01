package gravity;

import java.util.List;
import java.util.ArrayList;

public abstract class Model{
        
    protected List<Body> bodies = new ArrayList<Body>();
            
    protected double timeFromStart = 0.0;        

    public List<Body> getBodies()
    {
        return bodies;
    }
    
    public Body getBody(String name) throws Exception
    {
        for(Body b : bodies)
        {
            if(b.getName().equals(name))
            {
                return b;    
            }
        }
        
        throw new Exception("no body "+name+" found!");
    }
    
    public void addBody(Body b)
    {
        bodies.add(b);
    }
        
    public void timeStep(double time)
    {
        positionStep(time);
    
        velocityStep(time);            
        
        timeFromStart += time;
    }
    
    public void savePositionToHistory()
    {
        for(Body b : bodies)
        {
            b.savePositionToHistory();
        }        
    }
 
    protected void positionStep(double time)
    {
        for(Body body : bodies)
        {
            body.positionStep(time);
        }        
    }
    
    protected void velocityStep(double time)
    {
        for(int i = 0; i < bodies.size() - 1; i++)
        {
            for(int j = i + 1;j < bodies.size(); j++)
            {
                applyGravity(bodies.get(i),bodies.get(j),time);
            }            
        }
        
        for(Body body : bodies)
        {
            if(body instanceof Spaceship)
            {
                ((Spaceship)body).applyThrust(time);
            }
        }
    }
    
    protected abstract double gravityPower(double mass0, double mass1, double radius);
        
    protected abstract void applyGravity(Body body0, Body body1, double time);	
    
    public double getTimeFromStart()
    {
        return timeFromStart;
    }
}

