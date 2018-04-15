#!/bin/bash

#
#   This script requires inotify-tools on Linux and fswatch on MacOS
#   

MONITOR_PATH="$HOME/pictures/auhack"

# Linux support
if [[ "$OSTYPE" == "linux-gnu" ]]; then
    inotifywait -m -e create --format %f $MONITOR_PATH | while read file
do
    OLD_IMG_PATH="$file"
    NEW_IMG_PATH="$(echo $file | sed 's/ /_/g')"
    mv "${MONITOR_PATH}/${OLD_IMG_PATH}" "${MONITOR_PATH}/${NEW_IMG_PATH}"
    exec python recognize.py "${MONITOR_PATH}/${NEW_IMG_PATH}"
done

# Support MacOS
elif [[ "$OSTYPE" == "darwin"* ]]; then
    fswatch "${MONITOR_PATH}" | while read file
do
    OLD_IMG_PATH="$file"
    NEW_IMG_PATH="$(echo $file | sed 's/ /_/g')"
    mv "${MONITOR_PATH}/${OLD_IMG_PATH}" "${MONITOR_PATH}/${NEW_IMG_PATH}"
    exec python recognize.py "${MONITOR_PATH}/${NEW_IMG_PATH}"
done

else 
    echo "Your operating system is not supported!"
fi
