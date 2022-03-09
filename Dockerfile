FROM ubuntu:20.04

RUN adduser --system --group --home /opt/renfeid renfei

RUN set -ex; \
	apt-get update; \
	if ! which openjdk-8-jdk; then \
		apt-get install -y --no-install-recommends openjdk-8-jdk; \
	fi; \
	if ! which dnsutils; then \
    	apt-get install -y --no-install-recommends dnsutils; \
    fi; \
    if ! which ntp; then \
            apt-get install -y --no-install-recommends ntp; \
    fi; \
    if ! which whois; then \
        apt-get install -y --no-install-recommends whois; \
    fi; \
    if ! which curl; then \
    	apt-get install -y --no-install-recommends curl; \
    fi; \
    if ! which tzdata; then \
        apt-get install -y --no-install-recommends tzdata; \
    fi; \
    if ! which jq; then \
        apt-get install -y --no-install-recommends jq; \
    fi; \
    rm -rf /var/lib/apt/lists/* \
    && ln -fs /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && dpkg-reconfigure -f noninteractive tzdata

ENV TZ="Asia/Shanghai"

ARG JAR_FILE=renfeid-server/target/renfeid.jar
# git clone https://github.com/renfei/ip2location.git
#ARG IP2LocationBinFile=src/main/resources/ip2location/*.BIN

RUN mkdir /opt/renfeid/ip2location

COPY --chown=renfei:renfei ${JAR_FILE} /opt/
# IP2Location 数据过大，不再随 Docker 打包
#COPY --chown=renfei:renfei ${IP2LocationBinFile} /opt/renfeid/ip2location/

USER renfei

WORKDIR /opt/renfeid

ENTRYPOINT [ "java", \
             "-Xms128M", \
             "-Xmx1024M", \
             "-XX:+PrintGCDetails", \
             "-XX:GCLogFileSize=100M", \
             "-XX:+UseGCLogFileRotation", \
             "-Xloggc:/opt/renfeid/log/gc.log", \
             "-XX:+HeapDumpOnOutOfMemoryError", \
             "-XX:HeapDumpPath=/opt/renfeid/log/java_heapdump.hprof", \
             "-XX:+UseCompressedOops", \
             "-XX:+UseG1GC", \
             "-XX:+UseStringDeduplication", \
             "-XX:+PrintTenuringDistribution", \
             "-Dfile.encoding=UTF-8", \
             "-Xverify:none", \
             "-jar", \
             "/opt/renfeid.jar" \
]

HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 \
            CMD curl --silent --fail --request GET http://localhost:9595/actuator/health \
                | jq --exit-status '.status == "UP"' || exit 1
#                | jq --exit-status -n 'inputs | if has("status") then .status=="UP" else false end' > /dev/null || exit 1

VOLUME ["/opt/renfeid"]

EXPOSE 9595
EXPOSE 53