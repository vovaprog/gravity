[global]
[create]

var moon = new Packages.gravity.Body();
moon.setMass(7.3477E22);
moon.setRadius(1737100);
moon.setPosition(new Packages.gravity.Vector(0,384399000,0));
moon.setVelocity(new Packages.gravity.Vector(-1023,0,0));
moon.setColor(Packages.java.awt.Color.YELLOW);
moon.setName("moon");

model.addBody(moon);




