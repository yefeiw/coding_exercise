cd src
#compile
javac maxGap.java ../../common/Input.java
cp ../../common/*.class ./
#execute
java maxGap 
#cleanup
rm *.class
cd ..