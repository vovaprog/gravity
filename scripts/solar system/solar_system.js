[global]
[create]

[init]

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
    mercury.setVelocity(new Packages.gravity.Vector(-first_space_speed(sun.getMass(),57.9E9),0,0));
    mercury.setColor(Packages.java.awt.Color.RED);
    mercury.setName("mercury");    
    model.addBody(mercury);    
    
    var venus = new Packages.gravity.Body();
    venus.setMass(4.87E24);
    venus.setRadius(6052E3);
    venus.setPosition(new Packages.gravity.Vector(0,108.2E9,0));
    venus.setVelocity(new Packages.gravity.Vector(-first_space_speed(sun.getMass(),108.2E9),0,0));
    venus.setColor(Packages.java.awt.Color.YELLOW);
    venus.setName("venus");    
    model.addBody(venus);        
    
    var earth = new Packages.gravity.Body();
    earth.setMass(5.97E24);
    earth.setRadius(6378E3);
    earth.setPosition(new Packages.gravity.Vector(0,149597870700,0));
    earth.setVelocity(new Packages.gravity.Vector(-first_space_speed(sun.getMass(),149597870700),0,0));
    earth.setColor(Packages.java.awt.Color.CYAN);
    earth.setName("earth");    
    model.addBody(earth);    

    var ceres = new Packages.gravity.Body();
    ceres.setMass(9.39E20);
    ceres.setRadius(469E3);
    ceres.setPosition(new Packages.gravity.Vector(0,413774500E3,0));
    ceres.setVelocity(new Packages.gravity.Vector(-first_space_speed(sun.getMass(),413774500E3),0,0));
    ceres.setColor(Packages.java.awt.Color.WHITE);
    ceres.setName("ceres");    
    model.addBody(ceres);    

    var mars = new Packages.gravity.Body();
    mars.setMass(0.642E24);
    mars.setRadius(3396E3);
    mars.setPosition(new Packages.gravity.Vector(0,227.9E9,0));
    mars.setVelocity(new Packages.gravity.Vector(-first_space_speed(sun.getMass(),227.9E9),0,0));
    mars.setColor(Packages.java.awt.Color.RED);
    mars.setName("mars");    
    model.addBody(mars);    

    var jupiter = new Packages.gravity.Body();
    jupiter.setMass(1898E24);
    jupiter.setRadius(71492E3);
    jupiter.setPosition(new Packages.gravity.Vector(0,778.6E9,0));
    jupiter.setVelocity(new Packages.gravity.Vector(-first_space_speed(sun.getMass(),778.6E9),0,0));
    jupiter.setColor(Packages.java.awt.Color.RED);
    jupiter.setName("jupiter");    
    model.addBody(jupiter);        

    var saturn = new Packages.gravity.Body();
    saturn.setMass(568E24);
    saturn.setRadius(60268E3);
    saturn.setPosition(new Packages.gravity.Vector(0,1433.5E9,0));
    saturn.setVelocity(new Packages.gravity.Vector(-first_space_speed(sun.getMass(),1433.5E9),0,0));
    saturn.setColor(Packages.java.awt.Color.YELLOW);
    saturn.setName("saturn");    
    model.addBody(saturn);        
    
    var uranus = new Packages.gravity.Body();
    uranus.setMass(86.8E24);
    uranus.setRadius(25559E3);
    uranus.setPosition(new Packages.gravity.Vector(0,2872.5E9,0));
    uranus.setVelocity(new Packages.gravity.Vector(-first_space_speed(sun.getMass(),2872.5E9),0,0));
    uranus.setColor(Packages.java.awt.Color.GREEN);
    uranus.setName("uranus");    
    model.addBody(uranus);        
    
    var neptune = new Packages.gravity.Body();
    neptune.setMass(102E24);
    neptune.setRadius(24764E3);
    neptune.setPosition(new Packages.gravity.Vector(0,4495.1E9,0));
    neptune.setVelocity(new Packages.gravity.Vector(-first_space_speed(sun.getMass(),4495.1E9),0,0));
    neptune.setColor(Packages.java.awt.Color.BLUE);
    neptune.setName("neptune");    
    model.addBody(neptune);        
    
    var worldSize = distance(sun, neptune) * 1.2;
    view.setWorldSize(-worldSize,worldSize);
    
    controller.setSavePositionToHistoryInterval(60 * 60 * 24 * 2);
}


