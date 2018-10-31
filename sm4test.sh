#!/bin/bash
# This is a shell script for sm4 test
# The test result is in ../result/sm4test.txt
# It may takes 20 minutes to execute the program so please be patient

if [[ "$OSTYPE" == "darwin"* ]]
then
    sed  -i '' '48c\
                GuomiJava.Guomi_Interface_sm4_test
                ' pom.xml
elif [[ "$OSTYPE" == "linux-gnu" ]]
then
 #   echo "这是Linux的系统"
     sed  -i '48cGuomiJava.Guomi_Interface_sm2_test' pom.xml
fi

mvn clean install
rm result/sm4test.txt
cd target
java -jar test-1.0-SNAPSHOT.jar >> ../result/sm4test.txt
cd ..