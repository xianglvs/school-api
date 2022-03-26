#!/bin/sh
source /etc/profile
APP_NAME=`ls -l $pwd | grep '.\+\.jar$' | awk '{print $9}'`
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo $APP_NAME 'is not running!'
fi
