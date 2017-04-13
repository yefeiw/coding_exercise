cd src
#compile
javac CourseSchedule.java ../../common/Input.java
cp ../../common/*.class ./
#execute
java CourseSchedule 
#cleanup
rm *.class
cd ..