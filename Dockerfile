FROM openjdk:8
#WORKDIR /home
ADD  xxx-1.0-dev.jar /xxx/xxx.jar
ADD config/ /xxx/config/
ADD lib/ /xxx/lib/
COPY bin/startLine.sh /xxx/startLine.sh

CMD chmod +x /xxx/startLine.sh

EXPOSE 8080
# ENTRYPOINT：docker启动时，运行的命令，这里容器启动时直接运行jar服务。
#ENTRYPOINT ["java","-jar","/xxx/xxx.jar"]
ENTRYPOINT ["sh","/xxx/startLine.sh"]