package gravity;

public class ModelSimple extends Model{
    
    protected final double G_CONSTANT=6.67384 * 0.00000000001;
 
    protected final double APPLY_GRAVITY_MASS_LIMIT = 1000000; 
    
    protected double gravityPower(double mass0, double mass1, double radius)
    {
        return G_CONSTANT * (mass0 * mass1) / (radius * radius);   
    }
        
    protected void applyGravity(Body body0, Body body1, double time)
	{
	    if(body0.getMass()>APPLY_GRAVITY_MASS_LIMIT || body1.getMass()>APPLY_GRAVITY_MASS_LIMIT)
	    {
            Vector vr = body1.getPosition().sub(body0.getPosition());
    
            double r = vr.getLength();
    
            double f = gravityPower(body0.getMass(), body1.getMass(), r);
    
            double a0 = f / body0.getMass();
            double a1 = f / body1.getMass();
                    
            
            a0 *= time;
            a1 *= time;
            
            
            Vector va0 = vr.toLength(a0);
            
            body0.setVelocity(body0.getVelocity().add(va0));
    
                    
            Vector va1 = vr.toLength(a1).mul(-1.0);
    
            body1.setVelocity(body1.getVelocity().add(va1));	
        }
	}
}

