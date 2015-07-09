[global]
[create]

[init]

{
    var earth_moon_distance = distance(earth,moon); 
    println("earth-moon distance = "+parseInt(distance(earth,moon)/1000));
    
    
    var centerOfMass = earth_moon_distance * (moon.getMass() / earth.getMass());    
    println("earth-moon center of mass = "+parseInt(centerOfMass/1000));
    
    
    view.setWorldSize(-earth_moon_distance * 2.0 * 1.2,earth_moon_distance * 2.0 * 1.2);
    
    
    var alfa = moon.getMass() / (earth.getMass() + moon.getMass());
    
    var L1_radius = earth_moon_distance * (1.0 - java.lang.Math.pow(alfa / 3.0, 1.0/3.0));
    var L2_radius = earth_moon_distance * (1.0 + java.lang.Math.pow(alfa / 3.0, 1.0/3.0));
    var L3_radius = earth_moon_distance * (1.0 + (5.0/12.0) * alfa);
    
    L1_radius += centerOfMass;
    L2_radius += centerOfMass;
    L3_radius -= centerOfMass;
    
     
    println("earth-L1 distance = "+parseInt(L1_radius/1000));
    println("earth-L2 distance = "+parseInt(L2_radius/1000));
    println("earth-L3 distance = "+parseInt(L3_radius/1000));
    
    var L1_velocity = moon.getVelocity().mul(L1_radius / earth_moon_distance);
    var L2_velocity = moon.getVelocity().mul(L2_radius / earth_moon_distance);
    var L3_velocity = moon.getVelocity().mul(-L3_radius / earth_moon_distance);
    
        
    var L1 = new Packages.gravity.Body();
    L1.setMass(5000);
    L1.setRadius(5);
    L1.setPosition(new Packages.gravity.Vector(0,L1_radius,0));
    L1.setVelocity(L1_velocity);
    L1.setColor(Packages.java.awt.Color.GREEN);
    L1.setName("L1 body");

    
    
    var L2 = new Packages.gravity.Body();
    L2.setMass(5000);
    L2.setRadius(5);
    L2.setPosition(new Packages.gravity.Vector(0,L2_radius,0));
    L2.setVelocity(L2_velocity);
    L2.setColor(Packages.java.awt.Color.ORANGE);
    L2.setName("L2 body");


    
    var L3 = new Packages.gravity.Body();
    L3.setMass(5000);
    L3.setRadius(5);
    L3.setPosition(new Packages.gravity.Vector(0,-L3_radius,0));
    L3.setVelocity(L3_velocity);
    L3.setColor(Packages.java.awt.Color.PINK);
    L3.setName("L3 body");

    
    
    
    
    var beta = (earth.getMass() - moon.getMass()) / (earth.getMass() + moon.getMass());
        
    var L45_y = beta * ( earth_moon_distance / 2.0 );
    var L45_x = (java.lang.Math.sqrt(3.0) * earth_moon_distance) / 2.0;
    
    L45_y = L45_y + centerOfMass;
    
    var L4 = new Packages.gravity.Body();
    L4.setMass(5000);
    L4.setRadius(5);
    L4.setPosition(new Packages.gravity.Vector(L45_x,L45_y,0));
    L4.setColor(Packages.java.awt.Color.WHITE);
    L4.setName("L4 body");
    
    var L4_velocity_length = moon.getVelocity().getLength() * (distance(earth,L4) / distance(earth,moon));
    
    var L4_velocity = L4.getPosition().sub(earth.getPosition()).toLength(L4_velocity_length).rotateZDegrees(90.0);
    
    L4.setVelocity(L4_velocity);

    println("earth-L4 distance = "+parseInt(distance(earth,L4)/1000));
    
    
    var L5 = new Packages.gravity.Body();
    L5.setMass(5000);
    L5.setRadius(5);
    L5.setPosition(new Packages.gravity.Vector(-L45_x,L45_y,0));
    L5.setColor(Packages.java.awt.Color.WHITE);
    L5.setName("L5 body");

    var L5_velocity_length = moon.getVelocity().getLength() * (distance(earth,L5) / distance(earth,moon));
    
    var L5_velocity = L5.getPosition().sub(earth.getPosition()).toLength(L5_velocity_length).rotateZDegrees(90.0);
    
    L5.setVelocity(L5_velocity);
    
    println("earth-L5 distance = "+parseInt(distance(earth,L5)/1000));

          
    model.addBody(L1);
    model.addBody(L2);
    model.addBody(L3);
    model.addBody(L4);
    model.addBody(L5);

    
    /*var testBody0 = new Packages.gravity.Body();
    testBody0.setMass(5000);
    testBody0.setRadius(5);
    testBody0.setPosition(new Packages.gravity.Vector(-distance(earth,L5),0,0));
    testBody0.setColor(Packages.java.awt.Color.CYAN);
    testBody0.setName("testBody0");
    testBody0.setVelocity(new Packages.gravity.Vector(0,L5.getVelocity().getLength(),0));
    
    model.addBody(testBody0);*/
}

