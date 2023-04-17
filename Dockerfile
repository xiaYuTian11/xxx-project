FROM openjdk:8
WORKDIR /home/software/xxx
ADD  xxx-1.0-dev-package.zip .

EXPOSE 8080
# ENTRYPOINT：docker启动时，运行的命令，这里容器启动时直接运行jar服务。
ENTRYPOINT ["sh","/home/software/xxx/bin/startLine.sh"]
