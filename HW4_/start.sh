#!bin/bash

#name равен второму параметру при старте скрипта!
name=$1

#Память должна быть равной
MEMORY= "-Xms512m -Xmx512m"

#Настройка GC
#Однопоточный, для небольших java приложений (до 100 Mb)!
CG="-XX:+UseSerialGC"
#Многопоточный, Нацелен на макс производительность(если нужна пиковая производительность), use in default, длинные Stop the World паузы
#CG="-XX:+UseParallelGC" # дружит с CG="-XX:+UseParallelOldGC"
#Потребить больше ресурсов, как можно больше сделать в фоне, уменьшить StopTheWorld  паузы, предпочтительней на серверах!!
#CG="-XX:+UseParNewGC" # дружит с CG="-XX:+UseConcMarkSweepGC"

#Параметр
OOM="kill -3 % p"

#Настройки для Log
GC_LOG=" -verbose:gc -Xloggc:/var/log/gc_uteev_hw4_pid_%p.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

java $MEMORY $CG $GC_LOG -XX:OnOutOfMemoryError="kill -3 %p" -jar ./target/$name