FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

ENV TZ="Asia/Shanghai"

RUN addgroup -g 1001 -S renfeid
RUN adduser -S renfeid -u 1001

COPY --chown=renfeid:renfeid renfeid-server/target ./

EXPOSE 9595

HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 \
            CMD curl --silent --fail --request GET http://localhost:9595/actuator/health \
                | jq --exit-status '.status == "UP"' || exit 1

USER renfeid

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