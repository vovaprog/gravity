package gravity;

public class Vector
{
    protected double x, y, z;
    
    public Vector(double vx, double vy, double vz)
    {
        x = vx;
        y = vy;
        z = vz;
    }
    
    public Vector add(Vector v)
    {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }
    
    public Vector sub(Vector v)
    {
        return new Vector(x - v.x, y - v.y, z - v.z);
    }
    
    public Vector mul(double d)
    {
        return new Vector(x * d, y * d, z * d);
    }
    
    public double getLength()
    {
        return Math.sqrt(x*x + y*y + z*z); 
    }
    
    public Vector normalize()
    {
        double length = getLength();
        
        return new Vector(x / length, y/ length, z / length);        
    }
    
    public Vector toLength(double length)
    {
        Vector v = normalize();
        
        v.x = v.x * length;        
        v.y = v.y * length;
        v.z = v.z * length;
        
        return v;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }    
    
    @Override
    public String toString()
    {
    	return String.format("%f   %f   %f",x,y,z);
    }
    
    public double getAngleDegrees(Vector v)
    {
        double cosOfAngle = (x*v.x + y*v.y + z*v.z) / (getLength() * v.getLength());
        
        double angleRadians = Math.acos(cosOfAngle);
        
        return Math.toDegrees(angleRadians);
    }
    
    public Vector rotateZDegrees(double a)
    {
        a = Math.toRadians(a);
        
        Vector v=new Vector(
            x * Math.cos(a) - y * Math.sin(a),
            x * Math.sin(a) + y * Math.cos(a),
            z);
                
        return v;
    }
}
