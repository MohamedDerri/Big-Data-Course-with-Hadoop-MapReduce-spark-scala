JAVAC = javac
JAVA = java
JAVAC_FLAGS = -g -d bin -cp bin
JAVA_FLAGS = -cp bin
MAIN = Main

all: clean compile

compile:
	$(JAVAC) $(JAVAC_FLAGS) -sourcepath src *.java 

run:
	$(JAVA) $(JAVA_FLAGS) $(MAIN)

clean:
	rm -rf bin/*
	rm -rf bin