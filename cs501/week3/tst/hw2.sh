cd src
#compile
javac minHeap.java ../../common/Input.java
cp ../../common/*.class ./
java minHeap
#execute
#cleanup
rm *.class
cd ..