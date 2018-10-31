#!/bin/bash
# This is a shell script for sm2 test
# The test result is in ../result/sm2test.txt
# It may takes 30 minutes to execute the program so please be patient

if [[ "$OSTYPE" == "darwin"* ]]
then
    sed  -i '' '48c\
                GuomiJava.Guomi_Interface_sm2_test
                ' pom.xml
elif [[ "$OSTYPE" == "linux-gnu" ]]
    sed  -i '48cGuomiJava.Guomi_Interface_sm2_test' pom.xml
then
 #   echo "这是Linux的系统"
fi

mvn clean install
rm result/sm2test.txt
cd target
java -jar test-1.0-SNAPSHOT.jar >> ../result/sm2test.txt
cd ..