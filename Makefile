
ifeq ($(OS),Windows_NT)
    PATHSEP=";"
    FILESEP=\\
else
    PATHSEP=":"
    FILESEP=/
endif

Output=./build
Outpackage=./build/gravity

Body=$(Outpackage)/Body.class
SimpleProfiler=$(Outpackage)/SimpleProfiler.class
Controller=$(Outpackage)/Controller.class
ScriptHostRhino=$(Outpackage)/ScriptHostRhino.class
ControllerSimple=$(Outpackage)/ControllerSimple.class
Spaceship=$(Outpackage)/Spaceship.class
Vector=$(Outpackage)/Vector.class
View=$(Outpackage)/View.class
View2d=$(Outpackage)/View2d.class
ModelSimple=$(Outpackage)/ModelSimple.class
ScriptHost=$(Outpackage)/ScriptHost.class
Model=$(Outpackage)/Model.class
ScriptManagerFolder=$(Outpackage)/ScriptManagerFolder.class
ScriptInfo=$(Outpackage)/ScriptInfo.class
ScriptManager=$(Outpackage)/ScriptManager.class
Utils=$(Outpackage)/Utils.class
ScriptHostJavaxScript=$(Outpackage)/ScriptHostJavaxScript.class
MainForm=$(Outpackage)/MainForm.class

all: ./build $(Vector) $(Body) $(Spaceship) $(Model) $(Utils)  $(ModelSimple)  $(View) $(View2d) $(ScriptHost) $(ScriptHostJavaxScript) $(ScriptInfo) $(ScriptManager) $(ScriptManagerFolder) \
    $(Controller) $(ControllerSimple) $(ModelEarthMoon) $(MainForm) 
    

./build:
	mkdir .${FILESEP}build

$(Body): Body.java
	javac -cp "$(Output)" -d "$(Output)" Body.java
	
$(Controller): Controller.java
	javac -cp "$(Output)" -d "$(Output)" Controller.java

$(ScriptHostRhino): ScriptHostRhino.java
	javac -cp "$(Output)" -d "$(Output)" ScriptHostRhino.java

$(ControllerSimple): ControllerSimple.java
	javac -cp "$(Output)${PATHSEP}./lib/commons-io-2.4.jar" -d "$(Output)" ControllerSimple.java

$(Spaceship): Spaceship.java
	javac -cp "$(Output)" -d "$(Output)" Spaceship.java

$(Vector): Vector.java
	javac -cp "$(Output)" -d "$(Output)" Vector.java

$(View): View.java
	javac -cp "$(Output)" -d "$(Output)" View.java

$(View2d): View2d.java
	javac -cp "$(Output)" -d "$(Output)" View2d.java

$(ModelSimple): ModelSimple.java
	javac -cp "$(Output)" -d "$(Output)" ModelSimple.java

$(ScriptHost): ScriptHost.java
	javac -cp "$(Output)" -d "$(Output)" ScriptHost.java

$(Model): Model.java
	javac -cp "$(Output)" -d "$(Output)" Model.java

$(ScriptManagerFolder): ScriptManagerFolder.java
	javac -cp "$(Output)" -d "$(Output)" ScriptManagerFolder.java

$(ScriptInfo): ScriptInfo.java
	javac -cp "$(Output)${PATHSEP}./lib/commons-io-2.4.jar" -d "$(Output)" ScriptInfo.java

$(ScriptManager): ScriptManager.java
	javac -cp "$(Output)" -d "$(Output)" ScriptManager.java

$(Utils): Utils.java
	javac -cp "$(Output)" -d "$(Output)" Utils.java

$(ScriptHostJavaxScript): ScriptHostJavaxScript.java
	javac -cp "$(Output)" -d "$(Output)" ScriptHostJavaxScript.java

$(MainForm): MainForm.java
	javac -cp "$(Output)" -d "$(Output)" MainForm.java


run: all
	java -cp "./build${PATHSEP}./lib/commons-io-2.4.jar" gravity.MainForm

clean:	
	rm -r ./build/gravity
	
	