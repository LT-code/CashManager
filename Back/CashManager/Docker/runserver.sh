#!/bin/sh

echo $PORT
#echo "${value//8080/$PORT}" > /opt/tomcat/conf/server.xml

sed -i "s/8080/${PORT:-8080}/g" /opt/tomcat/conf/server.xml
/opt/tomcat/bin/catalina.sh run