FROM openjdk:8-jdk-alpine
WORKDIR /app

RUN addgroup -S renfeid && adduser -S renfeid -G renfeid
USER renfeid:renfeid

COPY --chown=renfeid:renfeid renfeid-service/target ./

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
             "/app/renfeid.jar" \
]