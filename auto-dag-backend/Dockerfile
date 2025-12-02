# 使用官方OpenJDK运行时作为基础镜像
FROM openjdk:17-jdk-slim

# 设置维护者信息
LABEL maintainer="autodag-team@example.com"

# 设置工作目录
WORKDIR /app

# 复制jar文件到容器中
COPY target/*.jar app.jar

# 设置环境变量
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=prod

# 暴露端口
EXPOSE 8080

# 运行应用程序
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar app.jar"]