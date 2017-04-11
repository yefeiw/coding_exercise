cd src
#compile
javac rightNode.java ../../common/Input.java
cp ../../common/*.class ./
java rightNode
#execute
#cleanup
rm *.class
cd ..