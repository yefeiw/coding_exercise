####Only chance this line for the relevant HW code name
HW=ContainsDuplicate

#compile
javac ../src/$HW.java ../../common/Input.java
mv ../../common/*.class ./
mv ../src/*.class ./
#execute
java $HW
#cleanup
rm *.class

