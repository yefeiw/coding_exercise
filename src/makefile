JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	PreviousPermutation.java \
	..\tst\Input.java \
	Main.java 

default: classes

classes: $(CLASSES:.java=.class)

clean: $(RM) *.class