package gravity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.awt.Color;

public class Body{

	protected double mass;
	protected double radius;
	protected Vector velocity;
	protected Vector position;
	protected LinkedList<Vector> positionHistory = new LinkedList<Vector>();
	protected Color color;
	protected String name;
	
	protected int positionHistoryLength=5000;
	
	public double getMass()
	{
		return mass;
	}

	public void setMass(double mass)
	{
	    this.mass = mass;
	}
	
	public double getRadius()
	{
		return radius;
	}

	public void setRadius(double radius)
	{
	    this.radius = radius;
	}
	
	public Vector getVelocity()
	{
		return velocity;
	}
	
	public void setVelocity(Vector v)
	{
	    velocity = v;	   
	}

	public Vector getPosition()
	{
	    return position;
	}
	
	public void setPosition(Vector position)
	{
	    this.position = position;
	}
	
	public void savePositionToHistory()
	{
	    if(positionHistory.size() > getPositionHistoryLenght())
	    {
	        positionHistory.removeFirst();
	    }
	    
	    positionHistory.addLast(position);
	}
	
	public void positionStep(double time)
	{	    
	    Vector v = velocity.mul(time); 
	    
		position=position.add(v);
	}
	
	public Color getColor()
	{
	    return color;
	}
	
	public void setColor(Color color)
	{
	    this.color = color;
	}
		
	protected int getPositionHistoryLenght()
	{
		return positionHistoryLength;
	}

	protected void setPositionHistoryLenght(int positionHistoryLength)
	{
	    this.positionHistoryLength = positionHistoryLength;
	}
		
	public List<Vector> getPositionHistory()
	{
	    return positionHistory;
	}
	
	public String getName()
	{
	     return name;   
	}
	
	public void setName(String name)
	{
	    this.name = name;
	}
}


