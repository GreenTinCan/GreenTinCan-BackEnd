#!/bin/bash
LOG_FILE="/home/ubuntu/work/application.log"

echo "start Build!"
./gradlew build
PID="ProTalkTime"
echo $PID kill
# shellcheck disable=SC2046
kill -9 $(ps -ef | grep $PID | grep -v grep | awk '{print $2 }')
rm -f "$LOG_FILE"
echo "start Application!"
nohup java -jar -Duser.timezone=Asia/Seoul ./build/libs/ProTalkTime.jar >> "$LOG_FILE" 2>&1 &

echo "Running Application"
