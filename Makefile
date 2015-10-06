ifeq ($(OS),Windows_NT)
    PATHSEP=";"
    FILESEP=\\
else
    PATHSEP=":"
    FILESEP=/
endif

OUTPUT = build

SRC = Vector.java Body.java Spaceship.java Model.java Utils.java ModelSimple.java \
   View.java View2d.java ScriptHost.java ScriptHostJavaxScript.java ScriptInfo.java \
   ScriptManager.java ScriptManagerFolder.java Controller.java ControllerSimple.java MainForm.java

CLASSES = $(patsubst %.java,$(OUTPUT)/gravity/%.class,$(SRC))    
    
all: $(CLASSES) 

$(OUTPUT)/gravity/%.class : %.java
	javac -cp "$(OUTPUT)$(PATHSEP)./lib/commons-io-2.4.jar" -d "$(OUTPUT)" $<

run: all
	java -cp "$(OUTPUT)$(PATHSEP)./lib/commons-io-2.4.jar" gravity.MainForm

clean:	
	rm -r $(OUTPUT)/gravity

