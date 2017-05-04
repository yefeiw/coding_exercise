####Only chance this line for the relevant HW code name
HW=MaxConsecutiveOnes


cd ../src
#compile
javac $HW.java ../../common/Input.java
cp ../../common/*.class ./
java $HW
#execute
#cleanup
rm *.class
cd ../tst
