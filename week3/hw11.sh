cd src
#compile
javac sortTest.java ../../common/Input.java
cp ../../common/*.class ./
#execute
java sortTest 
#cleanup
rm *.class
cd ..