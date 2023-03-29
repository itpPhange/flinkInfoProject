# 1.基础镜像使用jdk8
FROM openjdk:8
# 2.作者
MAINTAINER LUOPENG
# 3.挂载文件目录
VOLUME /tmp
# 4.添加文件
ADD demo-0.0.1-SNAPSHOT.jar /app.jar
# 5.指定build过程中执行的命令
RUN sh -c 'touch /app.jar'
# 6.定义环境变量
ENV JAVA_OPTS=""
ENV APP_OPTS=""
# 7.运行
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar $APP_OPTS"]