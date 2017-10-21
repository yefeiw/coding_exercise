cd src
#compile
javac NumIslands.java ../../common/Input.java
cp ../../common/*.class ./
java NumIslands
#execute
#cleanup
rm *.class
cd ..