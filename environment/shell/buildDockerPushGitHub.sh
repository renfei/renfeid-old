#!/bin/bash
#########################################
## Maven构建、Docker镜像制作、Docker仓库推送
## Author RenFei(i@renfei.net)
## 极狐地址：registry.jihulab.com/renfei/renfeid
## GitLab地址：registry.gitlab.com/renfei/renfeid
## GitHub地址：ghcr.io/renfei/renfeid
## 公网地址：registry.cn-hangzhou.aliyuncs.com/privately/renfei
## 专有网络：registry-vpc.cn-hangzhou.aliyuncs.com/privately/renfei
## 经典网络：registry-internal.cn-hangzhou.aliyuncs.com/privately/renfei
#########################################
PASSWORD=$1
PASSWORD_GITLAB=$2
PROJECT_VERSION=$(mvn -Dexec.executable='echo' -Dexec.args='${project.version}' --non-recursive exec:exec -q)
REGISTRY=ghcr.io
NAMESPACES=renfei
REPOSITORIES=renfeid
#########################################
echo "#########################################"
echo "# RENFEI.NET 编译构建生产环境Docker镜像文件"
echo "# Build Version: $REPOSITORIES:$PROJECT_VERSION"
echo "#########################################"
if [ "$PASSWORD" == "" ]; then
  read -p "请输入 Docker 仓库密码：" PASSWORD
fi
mvn clean package -Dmaven.test.skip=true -P prod
echo "#########################################"
echo "# Docker 构建开始 >>>>"
echo "#########################################"
#docker build -t $REPOSITORIES:"$PROJECT_VERSION" .
echo "docker build -t $REGISTRY/$NAMESPACES/$REPOSITORIES:$PROJECT_VERSION ."
docker build -t $REGISTRY/$NAMESPACES/$REPOSITORIES:"$PROJECT_VERSION" .
IMAGEID=$(docker images -q --filter reference=$REPOSITORIES:"$PROJECT_VERSION")
echo "构建完成 >>>> IMAGE ID:$IMAGEID"
echo "#########################################"
echo "# 登陆 Docker 仓库 >>>>"
echo "#########################################"
docker login --username=i@renfei.net --password="$PASSWORD" $REGISTRY
#docker tag "$IMAGEID" $REGISTRY/$NAMESPACES/$REPOSITORIES:"$PROJECT_VERSION"
echo "#########################################"
echo "# 开始推送 Docker 镜像到仓库 >>>>"
echo "#########################################"
docker push $REGISTRY/$NAMESPACES/$REPOSITORIES:"$PROJECT_VERSION"
echo "#########################################"
echo "# 登陆 Docker GitLab 仓库 >>>>"
echo "#########################################"
docker login --username=renfei --password="$PASSWORD_GITLAB" registry.gitlab.com
echo "#########################################"
echo "# 开始推送 Docker 镜像到GitLab仓库 >>>>"
echo "#########################################"
docker tag $REGISTRY/$NAMESPACES/$REPOSITORIES:"$PROJECT_VERSION" registry.gitlab.com/$NAMESPACES/$REPOSITORIES:"$PROJECT_VERSION"
docker push registry.gitlab.com/$NAMESPACES/$REPOSITORIES:"$PROJECT_VERSION"
echo "#########################################"
echo "# 删除本地 Docker 镜像到仓库 >>>>"
echo "#########################################"
#docker rmi "$IMAGEID"
docker rmi "$REGISTRY/$NAMESPACES/$REPOSITORIES:$PROJECT_VERSION"
echo "#########################################"
echo "# 全部构建完成！ Version: $REPOSITORIES:$PROJECT_VERSION"
echo "# 镜像地址: $REGISTRY/$NAMESPACES/$REPOSITORIES:$PROJECT_VERSION"
echo "#########################################"