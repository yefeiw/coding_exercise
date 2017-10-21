cd src
#compile
javac LargestNumber.java ../../common/Input.java
cp ../../common/*.class ./
#execute
java LargestNumber 
#cleanup
rm *.class
cd ..