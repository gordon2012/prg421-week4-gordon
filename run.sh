#!/bin/bash
javac -d ./build/classes -sourcepath ./src ./src/Main.java
java -cp ./build/classes Main
