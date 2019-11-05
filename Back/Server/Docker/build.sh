#!/bin/bash

mvn test test/services/AllTests.java
mvn clean install
docker build -t cashmanager/tomcat8 .
