#!/bin/bash

mvn install

docker-compose build

sh Docker/clean.sh

docker-compose up 
#sh Docker/build.sh

#sh Docker/start.sh
