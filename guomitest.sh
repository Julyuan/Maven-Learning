#!/bin/bash

if [[ "$OSTYPE" == "darwin"* ]]
then
    sed  -i '' '48c\
                GuomiJava.example
                ' pom.xml
elif [[ "$OSTYPE" == "linux-gnu" ]]
then
 #   echo "这是Linux的系统"
    sed -i '48cGuomiJava.example' pom.xml
fi

rm result/example.txt

mvn clean install

cd target
java -jar test-1.0-SNAPSHOT.jar >> ../result/example.txt
cd ..

# sed  -i '' '48c\
#             GuomiJava.Guomi_Interface_sm2_test
#             ' pom.xml
# mvn clean install
# cd target
# java -jar test-1.0-SNAPSHOT.jar >> ../result/sm2test.txt
# cd ..

# sed  -i '' '48c\
#             GuomiJava.Guomi_Interface_sm3_test
#             ' pom.xml
# mvn clean install
# cd target
# java -jar test-1.0-SNAPSHOT.jar >> ../result/sm3test.txt
# cd ..

# sed  -i '' '48c\
#             GuomiJava.Guomi_Interface_sm4_test
#             ' pom.xml
# mvn clean install
# cd target
# java -jar test-1.0-SNAPSHOT.jar >> sm4test.txt
# cd ..
