####################################
# elasticsearch 版本对照：
# https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#preface.versions
# Spring Data Elasticsearch:4.3.x
# Elasticsearch:7.15.2
# Spring Framework:5.3.x
# Spring Boot:2.5 .x
# @author renfei
# https://github.com/renfei/elasticsearch
####################################
FROM elasticsearch:7.17.0

ENV VERSION=7.17.0

# https://github.com/medcl/elasticsearch-analysis-ik/releases
ADD https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v${VERSION}/elasticsearch-analysis-ik-$VERSION.zip /tmp/
RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install -b file:///tmp/elasticsearch-analysis-ik-$VERSION.zip
RUN rm -rf /tmp/*