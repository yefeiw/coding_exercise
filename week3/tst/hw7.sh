cd src
#compile
javac sortList.java ../../common/Input.java
cp ../../common/*.class ./
#execute
java sortList 
#cleanup
rm *.class
cd ..