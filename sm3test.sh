#!/bin/bash
# This is a shell script for sm3 test
# The test result is in ../result/sm3test.txt
# It may takes 10 minutes to execute the program so please be patient

if [[ "$OSTYPE" == "darwin"* ]]
then
    sed  -i '' '48c\
                GuomiJava.Guomi_Interface_sm3_test
                ' pom.xml
elif [[ "$OSTYPE" == "linux-gnu" ]]
then
 #   echo "这是Linux的系统"
    sed  -i '48cGuomiJava.Guomi_Interface_sm3_test' pom.xml
fi

mvn clean install
cd target
rm ../result/sm3test.txt
java -jar test-1.0-SNAPSHOT.jar >> ../result/sm3test.txt
cd ..
