cd src
#compile
javac zigZag.java ../../common/Input.java
cp ../../common/*.class ./
#execute
java zigZag 
#cleanup
rm *.class
cd ..