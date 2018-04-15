#!/bin/bash
MONITOR_PATH="$HOME/pictures/auhack"

inotifywait -m -e create --format %f $MONITOR_PATH | while read file
do
    echo $MONITOR_PATH/$file
    exec python recognize.py $MONITOR_PATH/$file
done
