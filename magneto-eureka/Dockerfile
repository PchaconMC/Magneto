# CentOS 7
FROM centos:7
LABEL maintainer="Magneto Eureka server"

ENV JAVA_VER  11
ENV JAVA_HOME /opt/jdk-$JAVA_VER/
ARG BUILD_VERSION
ENV BUILD_VERSION=0.0.1
ENV GROUP_ID=com.magneto
ENV ARTIFACT_ID=magneto-eureka
ENV MAGNETO_PATH=/opt/magneto/

# Install Packages
RUN yum update -y
RUN yum install -y wget unzip curl vim python-setuptools sudo
RUN easy_install supervisor
RUN wget https://download.java.net/openjdk/jdk${JAVA_VER}/ri/openjdk-${JAVA_VER}+28_linux-x64_bin.tar.gz -O /opt/jdk.tar.gz

RUN cd /opt; \
    tar -xvf jdk.tar.gz; \
    rm jdk.tar.gz

RUN cd /opt/jdk-$JAVA_VER; \
    alternatives --install /usr/bin/java java /opt/jdk-$JAVA_VER/bin/java 2
RUN yum clean all

COPY target/${ARTIFACT_ID}-${BUILD_VERSION}.jar ${ARTIFACT_ID}.jar
RUN mkdir ${MAGNETO_PATH}
RUN mv ${ARTIFACT_ID}.jar ${MAGNETO_PATH}${ARTIFACT_ID}.jar;
	
EXPOSE 8761

CMD java -jar ${MAGNETO_PATH}${ARTIFACT_ID}.jar