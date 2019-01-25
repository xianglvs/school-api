#!/bin/sh
rm -f tpid
APP_JAR=`ls -l $pwd | grep .*\.jar | awk '{print $9}'`
APP_NAME=`echo ${APP_JAR%.jar}`
nohup java -jar $APP_JAR > $APP_NAME".log" 2>&1 &
echo $! > $APP_NAME".tpid"
echo $APP_NAME Start Success!
