[global]
[create]

var GRAVITY_CONSTANT = 6.67428E-11;

function first_space_speed(M,R)
{
    return java.lang.Math.sqrt(GRAVITY_CONSTANT * M / R);    
}

function distance(body0, body1)
{    
    return body0.getPosition().sub(body1.getPosition()).getLength();    
}

function println(s)
{
    java.lang.System.out.println(s);
}   

function getAngleToBody(ship, body)
{
    var bodyDirection = body.getPosition().sub(ship.getPosition());
    
    var velocityBodyAngle = ship.getVelocity().getAngleDegrees(bodyDirection);
    
    return velocityBodyAngle;        
}

