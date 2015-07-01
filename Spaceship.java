package gravity;

import java.awt.Color;

public class Spaceship extends Body
{
    protected Vector thrustDirection;
    protected double thrustTime;
    
    protected double engineForce;
    
    protected double totalThrust=0.0;
        
    public void setThrust(Vector direction, double time)
    {        
        thrustDirection = direction;
        
        thrustTime=time;
    }
    
    public void setEngineForce(double force)
    {
        engineForce = force;
    }
    
    public void applyThrust(double time)
    {
        if(thrustTime>0.0)
        {
            double curThrustTime = Math.min(thrustTime,time);
            
            double a = engineForce / mass;
            
            a *= curThrustTime;
            
            Vector curThrust = thrustDirection.toLength(a);
            
            setVelocity(getVelocity().add(curThrust));
            
            thrustTime -= curThrustTime;
            
            totalThrust += curThrustTime * engineForce;
        }
    }
    
    public boolean isEngineWorking()
    {
        return thrustTime > 0.0;
    }
    
    public Vector getThrustDirection()
    {
        return thrustDirection;
    }
    
    public double getTotalThrust()
    {
        return totalThrust;
    }
}

