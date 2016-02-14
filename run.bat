javac -d ./build/classes -cp ./lib/derby.jar -sourcepath ./src ./src/Main.java
java -cp ./lib/derby.jar;./build/classes Main %1
