#
# Build stage
#
FROM maven:3.8.6-openjdk-8 AS builder
RUN mkdir /app
COPY mybatis-generator ./app/mybatis-generator
COPY renfeid-bpm ./app/renfeid-bpm
COPY renfeid-cms ./app/renfeid-cms
COPY renfeid-common ./app/renfeid-common
COPY renfeid-proprietary ./app/renfeid-proprietary
COPY renfeid-server ./app/renfeid-server
COPY renfeid-uaa ./app/renfeid-uaa
COPY pom.xml ./app/pom.xml

WORKDIR /app

RUN mvn clean package -Dmaven.test.skip=true -P prod

#
# Package stage
#
FROM openjdk:8-jdk-alpine AS runner
WORKDIR /app

RUN addgroup -S renfeid && adduser -S renfeid -G renfeid
USER renfeid:renfeid

COPY --from=builder renfeid-service/renfeid-server/target ./app/target

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 9595

ENTRYPOINT [ "java", \
             "-Xms128M", \
             "-Xmx1024M", \
             "-XX:+PrintGCDetails", \
             "-XX:GCLogFileSize=100M", \
             "-XX:+UseGCLogFileRotation", \
             "-Xloggc:/app/log/gc.log", \
             "-XX:+HeapDumpOnOutOfMemoryError", \
             "-XX:HeapDumpPath=/app/log/java_heapdump.hprof", \
             "-XX:+UseCompressedOops", \
             "-XX:+UseG1GC", \
             "-XX:+UseStringDeduplication", \
             "-XX:+PrintTenuringDistribution", \
             "-Dfile.encoding=UTF-8", \
             "-Xverify:none", \
             "-jar", \
             "/app/app.jar" \
]