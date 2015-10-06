ifeq ($(OS),Windows_NT)
    PATHSEP=";"
    FILESEP=\\
    RM=rmdir /S /Q
else
    PATHSEP=":"
    FILESEP=/
    RM=rm -r
endif

OUTPUT = build

SRC = Vector.java Body.java Spaceship.java Model.java Utils.java ModelSimple.java \
   View.java View2d.java ScriptHost.java ScriptHostJavaxScript.java ScriptInfo.java \
   ScriptManager.java ScriptManagerFolder.java Controller.java ControllerSimple.java MainForm.java

CLASSES = $(patsubst %.java,$(OUTPUT)/gravity/%.class,$(SRC))    
    
.PHONY: all
all: $(OUTPUT) $(CLASSES) 

$(OUTPUT):
	mkdir $(OUTPUT)

$(OUTPUT)/gravity/%.class : %.java
	javac -cp "$(OUTPUT)$(PATHSEP)./lib/commons-io-2.4.jar" -d "$(OUTPUT)" $<

.PHONY: run	
run: all
	java -cp "$(OUTPUT)$(PATHSEP)./lib/commons-io-2.4.jar" gravity.MainForm

.PHONY: clean
clean:	
	$(RM) $(OUTPUT)

