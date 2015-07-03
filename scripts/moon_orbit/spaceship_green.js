[create]//==========================================================================================================


var ship = new Packages.gravity.Spaceship();
ship.setMass(5000);
ship.setRadius(5);
ship.setPosition(new Packages.gravity.Vector(0,6371000+500*1000,0));
ship.setVelocity(new Packages.gravity.Vector(-7616.82454804,0,0));
ship.setColor(Packages.java.awt.Color.GREEN);
ship.setName("ship green");
ship.setEngineForce(5000.0);

model.addBody(ship);

view.setWorldSize(-50000000,50000000);

function getAngleToBody(ship, body)
{
    var bodyDirection = body.getPosition().sub(ship.getPosition());
    
    var velocityBodyAngle = ship.getVelocity().getAngleDegrees(bodyDirection);
    
    return velocityBodyAngle;        
}

function distance(body0, body1)
{    
    return body0.getPosition().sub(body1.getPosition()).getLength();    
}

function println(s)
{
    java.lang.System.out.println(s);
}   

[init]//==========================================================================================================


var action = 0;
var earth = model.getBody("earth");
var moon = model.getBody("moon");


[step]//==========================================================================================================


if(action==0) 
{
    var angleToMoon = getAngleToBody(ship,moon);
    
    if(angleToMoon > 40.0 && angleToMoon < 50.0)
    {        
        ship.setEngineForce(15300.0);
        ship.setThrust(ship.getVelocity(),20 * 60);       
        
        action = 1;
    }
}
else if(action==1)
{
    if(distance(ship,earth) > distance(earth,moon) - 500 * 1000)
    {
        ship.setEngineForce(5500.0);
        ship.setThrust(ship.getVelocity().mul(-1.0),5 * 60);
        action=2;
    }
}
else if(action==2)
{
    if(distance(ship,earth) > distance(earth,moon) + 500 * 1000)
    {
        action=3;          
    }
}
else if(action==3)
{
    if(distance(ship,earth) < distance(earth,moon))
    {
        ship.setEngineForce(7000.0);
        ship.setThrust(moon.getVelocity().rotateZDegrees(-30.0),10 * 60);
        action=4;        
    }
}
else if(action==4)
{
    println(ship.getName() + "     total thrust: "+ship.getTotalThrust()+"     time: "+Packages.gravity.Utils.timeToString(model.getTimeFromStart()));
    action=5;
}

