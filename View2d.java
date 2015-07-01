package gravity;

import java.awt.Graphics;
import java.awt.Color;
import java.util.List;

public class View2d extends View{
        
    protected double xWorldLowBound, xWorldUpBound;
    protected double yWorldLowBound, yWorldUpBound;
    
    public View2d(double argXWorldLowBound, double argXWorldUpBound)
    {
        xWorldLowBound = argXWorldLowBound;
        xWorldUpBound = argXWorldUpBound;
        
        yWorldLowBound=xWorldLowBound;
        yWorldUpBound=xWorldUpBound;
    }
    
    protected Vector worldToScreenRadius(double r, int screenWidth, int screenHeight)
    {
        double xWorldRange = xWorldUpBound - xWorldLowBound;
        double xRate = screenWidth / xWorldRange;

        double yWorldRange = yWorldUpBound - yWorldLowBound;
        double yRate = screenHeight / yWorldRange;
        
        double xRadius = r * xRate;
        double yRadius = r * yRate;
        
        if(xRadius < 1.0) xRadius = 1.0;
        if(yRadius < 1.0) yRadius = 1.0;        
        
        return new Vector(xRadius, yRadius, 0);
    }
    
    protected Vector worldToScreenCoordinates(Vector worldPoint, int screenWidth, int screenHeight)
    {
        double xWorldRange = xWorldUpBound - xWorldLowBound;
        double xRate = screenWidth / xWorldRange;
                
        double screenX = (worldPoint.getX() - xWorldLowBound) * xRate;
        
        
        double yWorldRange = yWorldUpBound - yWorldLowBound;
        double yRate = screenHeight / yWorldRange;
        
        double screenY = (worldPoint.getY() - yWorldLowBound) * yRate;
        screenY = screenHeight - screenY;
        
        
        return new Vector(screenX, screenY, 0);
    }
        
    protected void paintBodyPositionHistory(Body body, Graphics graph, int screenWidth, int screenHeight)
    {
        List<Vector> positionHistory = body.getPositionHistory();
        
        double positionHistorySize = positionHistory.size();
                
        Color bodyColor = body.getColor();
        double r = bodyColor.getRed();
        double g = bodyColor.getGreen();        
        double b =  bodyColor.getBlue();        
                        
        Vector psPrev=null;
        
        int positionIndex=0;
        
        for(Vector pCur :  positionHistory)
        {
            final double funXMagic=0.8;
            final double funYMagic=0.4;
            
            double funX=(double)positionIndex/(double)positionHistorySize;            
            double funY;
            
            if(funX<funXMagic)
            {
                funY=funYMagic * (funX+(1.0-funXMagic));
            }
            else
            {
                funX = funX-funXMagic;
                funX = funX / (1.0-funXMagic);
                
                funY=funYMagic + (1.0-funYMagic)*(funX*funX);  
            }
                                    
            positionIndex++;
            
            Vector psCur = worldToScreenCoordinates(pCur,screenWidth,screenHeight);
            
            if(psPrev!=null)
            {
                graph.setColor(new Color((int)((double)r*funY),(int)((double)g*funY),(int)((double)b*funY)));
                graph.drawLine((int)psPrev.getX(),(int)psPrev.getY(),(int)psCur.getX(),(int)psCur.getY());                
            }
            
            psPrev=psCur;
        }
    }
    
    
    
    protected void paintBody(Body b, Graphics g, int screenWidth, int screenHeight)
    {
        Vector vPosition = worldToScreenCoordinates(b.getPosition(),screenWidth,screenHeight);
        Vector vRadius = worldToScreenRadius(b.getRadius(),screenWidth,screenHeight);

        int x = (int)vPosition.getX();
        int y = (int)vPosition.getY();
        int width = (int)vRadius.getX();
        int height = (int)vRadius.getY();
        
        if(width > 1) x-=width;
        if(height > 1) y-=height;
        
        width *= 2;
        height *= 2;

        g.setColor(b.getColor());
        g.fillOval(x, y, width, height);                
    }
    
    protected void updateScreenRate(int screenWidth, int screenHeight)
    {
        double screenRate = (double)screenHeight/(double)screenWidth;
        
        double xWorldRange = xWorldUpBound - xWorldLowBound;
        double yWorldRange = yWorldUpBound - yWorldLowBound;
        
        double newYWorldRange = xWorldRange * screenRate;
        
        double yRangeChange = (yWorldRange - newYWorldRange) / 2;
        
        yWorldUpBound -= yRangeChange;
        yWorldLowBound += yRangeChange;        
    }
    
    protected void paintBackground(Graphics g, int screenWidth, int screenHeight)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,screenWidth,screenHeight);
    }
    
    protected void paintThrust(Spaceship b, Graphics g,int screenWidth, int screenHeight)
    {
        Vector direction = b.getThrustDirection();
        
        double length = (xWorldUpBound-xWorldLowBound)/20.0;
        
        direction = direction.toLength(length);
        
        Vector p0 = b.getPosition();
        Vector p1 = p0.add(direction);

        p0 = worldToScreenCoordinates(p0,screenWidth,screenHeight);
        p1 = worldToScreenCoordinates(p1,screenWidth,screenHeight);
        
        g.setColor(Color.RED);
        g.drawLine((int)p0.getX(),(int)p0.getY(),(int)p1.getX(),(int)p1.getY());
    }
    
    public void paint(Model model, Graphics g, int screenWidth, int screenHeight)
    {
        updateScreenRate(screenWidth, screenHeight);
        
        paintBackground(g, screenWidth, screenHeight);

        for(Body b : model.getBodies())
        {
            paintBodyPositionHistory(b, g, screenWidth, screenHeight);
        }
        
        for(Body b : model.getBodies())
        {
            paintBody(b,g,screenWidth,screenHeight);
        }
        
        for(Body b : model.getBodies())
        {
            if(b instanceof Spaceship)
            {
                if(((Spaceship)b).isEngineWorking())
                {
                    paintThrust((Spaceship)b,g,screenWidth,screenHeight);
                }
            }
        }        
    }
    
    protected double getMoveWorldDistance()
    {
        final double moveWorldCoef = 0.05;        
        
        double xWorldRange = xWorldUpBound - xWorldLowBound;
        
        return xWorldRange * moveWorldCoef;
    }
    
    public void moveUp()
    {
        double moveWorldDistance = getMoveWorldDistance();
        
        yWorldLowBound += moveWorldDistance;
        yWorldUpBound += moveWorldDistance;
    }
    
    public void moveDown()
    {
        double moveWorldDistance = getMoveWorldDistance();        
        
        yWorldLowBound -= moveWorldDistance;
        yWorldUpBound -= moveWorldDistance;        
    }
    
    public void moveLeft()
    {
        double moveWorldDistance = getMoveWorldDistance();        
        
        xWorldLowBound -= moveWorldDistance;
        xWorldUpBound -= moveWorldDistance;        
    }
    
    public void moveRight()
    {
        double moveWorldDistance = getMoveWorldDistance();        
        
        xWorldLowBound += moveWorldDistance;
        xWorldUpBound += moveWorldDistance;                
    }
    
    public void zoomIn()
    {
        double moveWorldDistance = getMoveWorldDistance();        
        
        xWorldLowBound += moveWorldDistance;
        xWorldUpBound -= moveWorldDistance;                
        yWorldLowBound += moveWorldDistance;
        yWorldUpBound -= moveWorldDistance;                                
    }
    
    public void zoomOut()
    {
        double moveWorldDistance = getMoveWorldDistance();        
        
        xWorldLowBound -= moveWorldDistance;
        xWorldUpBound += moveWorldDistance;                
        yWorldLowBound -= moveWorldDistance;
        yWorldUpBound += moveWorldDistance;                                        
    }
    
    public void setWorldSize(double xWorldLowBound, double xWorldUpBound)
    {
        this.xWorldLowBound=xWorldLowBound;
        this.xWorldUpBound=xWorldUpBound;
    }
}
