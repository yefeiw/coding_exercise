cd src
#compile
javac SortChar.java ../../common/Input.java
cp ../../common/*.class ./
java SortChar
#execute
#cleanup
rm *.class
cd ..