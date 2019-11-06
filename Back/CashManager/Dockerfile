FROM centos

ENV CASHMANAGER_DOCKER_PRODUCTION 1
RUN mkdir /opt/tomcat/

WORKDIR /opt/tomcat
RUN curl -O http://mirror.ibcp.fr/pub/apache/tomcat/tomcat-8/v8.5.47/bin/apache-tomcat-8.5.47.tar.gz
RUN tar xzvf apache*.tar.gz
RUN mv apache-tomcat-8.5.47/* /opt/tomcat/.
RUN rm apache-tomcat-8.5.47* -r
RUN yum -y install java
RUN java -version
RUN ls

WORKDIR /opt/tomcat/webapps
RUN rm * -r
COPY ./target/cashmanager-1.0.war ./cashmanager.war

EXPOSE 8080

CMD ["/opt/tomcat/bin/catalina.sh", "run"]