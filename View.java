package gravity;

import java.awt.Graphics;

public abstract class View{    
        
    public abstract void paint(Model model, Graphics g, int screenWidth, int screenHeight);
    
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void zoomIn();
    public abstract void zoomOut();
}
