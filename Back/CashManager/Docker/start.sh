#!/bin/bash

dockercontainer=tomcatcontainer
docker container stop $dockercontainer
docker container rm $dockercontainer
docker container run -it -d --name $dockercontainer -p 8080:8080 cashmanager/tomcat8
#docker exec -it $dockercontainer bash
docker container logs $dockercontainer