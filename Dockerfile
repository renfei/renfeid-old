FROM ubuntu:18.04
MAINTAINER RenFei <i@renfei.net>

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
    rm -rf /var/lib/apt/lists/*

RUN mkdir -p /opt/renfeid/log;

COPY target/renfeid.jar /app/

HEALTHCHECK --interval=5s --timeout=5s CMD curl -f http://localhost:8099/HealthCheck || exit 1

ENTRYPOINT ["java","-Xms128M","-Xmx1024M","-XX:+UseCompressedOops","-XX:+UseConcMarkSweepGC","-XX:SoftRefLRUPolicyMSPerMB=50","-Dfile.encoding=UTF-8","-Xverify:none","-Ddruid.mysql.usePingMethod=false","-jar","/app/renfeid.jar"]

EXPOSE 8099