[global]
[create]

view.setWorldSize(-500000000,500000000);

{
    var sun = new Packages.gravity.Body();
    sun.setMass(1.98855E30);
    sun.setRadius(696342E3);
    sun.setPosition(new Packages.gravity.Vector(0,0,0));
    sun.setVelocity(new Packages.gravity.Vector(0,0,0));
    sun.setColor(Packages.java.awt.Color.YELLOW);
    sun.setName("sun");    
    model.addBody(sun);    

    var mercury = new Packages.gravity.Body();
    mercury.setMass(0.33E24);
    mercury.setRadius(2439E3);
    mercury.setPosition(new Packages.gravity.Vector(0,57.9E9,0));
    mercury.setVelocity(new Packages.gravity.Vector(first_space_speed(sun.getMass(),57.9E9),0,0));
    mercury.setColor(Packages.java.awt.Color.RED);
    mercury.setName("mercury");    
    model.addBody(mercury);    
    
    var venus = new Packages.gravity.Body();
    venus.setMass(4.87E24);
    venus.setRadius(6052E3);
    venus.setPosition(new Packages.gravity.Vector(0,108.2E9,0));
    venus.setVelocity(new Packages.gravity.Vector(first_space_speed(sun.getMass(),108.2E9),0,0));
    venus.setColor(Packages.java.awt.Color.YELLOW);
    venus.setName("venus");    
    model.addBody(venus);        
    
    var earth = new Packages.gravity.Body();
    earth.setMass(5.97E24);
    earth.setRadius(6378E3);
    earth.setPosition(new Packages.gravity.Vector(0,149597870700,0));
    earth.setVelocity(new Packages.gravity.Vector(first_space_speed(sun.getMass(),149597870700),0,0));
    earth.setColor(Packages.java.awt.Color.CYAN);
    earth.setName("earth");    
    model.addBody(earth);    
    
    var mars = new Packages.gravity.Body();
    mars.setMass(0.642E24);
    mars.setRadius(3396E3);
    mars.setPosition(new Packages.gravity.Vector(0,227.9E9,0));
    mars.setVelocity(new Packages.gravity.Vector(first_space_speed(sun.getMass(),227.9E9),0,0));
    mars.setColor(Packages.java.awt.Color.RED);
    mars.setName("mars");    
    model.addBody(mars);    

    var jupiter = new Packages.gravity.Body();
    jupiter.setMass(1898E24);
    jupiter.setRadius(71492E3);
    jupiter.setPosition(new Packages.gravity.Vector(0,778.6E9,0));
    jupiter.setVelocity(new Packages.gravity.Vector(first_space_speed(sun.getMass(),778.6E9),0,0));
    jupiter.setColor(Packages.java.awt.Color.RED);
    jupiter.setName("jupiter");    
    model.addBody(jupiter);        
    
    var worldSize = distance(sun, jupiter) * 1.3;
    view.setWorldSize(-worldSize,worldSize);
}

[init]

