[global]
[create]

var earth = new Packages.gravity.Body();
earth.setMass(5.9726E24);
earth.setRadius(6371000);
earth.setPosition(new Packages.gravity.Vector(0,0,0));
earth.setVelocity(new Packages.gravity.Vector(12.838312,0,0));
earth.setColor(Packages.java.awt.Color.CYAN);
earth.setName("earth");

model.addBody(earth);

