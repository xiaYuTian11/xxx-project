#!/bin/bash

java -Xms512m -Xmx1024m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/ykz_leader/server -cp /home/ykz_leader/server/*:/home/ykz_leader/server/lib/*:/home/ykz_leader/server/config/ com.zenith.leader.YkzLeaderApplication -Duser.timezone=Asia/Shanghai -Dfile.encoding=utf8
