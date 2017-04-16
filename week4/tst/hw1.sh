cd ../src
#compile
javac nQueens.java ../../common/Input.java
cp ../../common/*.class ./
java nQueens
#execute
#cleanup
rm *.class
cd ../tst