jarName="serviceSchedule.jar"
getProcessCount(){
    ps -ef | grep -c $jarName | xargs echo
}

count=$(getProcessCount)


if [ "$count"  == "1" ];then
	echo "dead"
	java -jar ~/serviceSchedule/$jarName &
else
	echo "alive"
fi

