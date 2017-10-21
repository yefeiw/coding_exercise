####Only chance this line for the relevant HW code name
HW=GreaterTree


cd ../src
#compile
javac $HW.java ../../common/Input.java
cp ../../common/*.class ./
java $HW
#execute
#cleanup
rm *.class
cd ../tst
