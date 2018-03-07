Data Lake
============
### Spark2-Shell
$ spark2-shell --driver-memory 15G --executor-memory 15G --executor-cores 8

### Sqoop導入MySQL歷史資料到Hive
$ sqoop import --connect jdbc:mysql://120.109.150.175:3306/power --username hpc -P --table PowerMinute --hive-import --hive-table powerminute --target-dir /user/hive/warehouse/powerminute --split-by no -m 8

### Kafka創建TOPIC
$ kafka-topics --zookeeper master:2181 --create --partitions 1 --replication-factor 1 --topic test

### Kafka刪除TOPIC
$ kafka-topics --zookeeper master:2181 --delete --topic HPC


### Kafka查看已存在TOPIC
$ kafka-topics --zookeeper master:2181 --list

### 開啟Consumer 接收Producer產生的訊息(PowerData_Minute, PowerData_CC)

$ kafka-console-consumer --zookeeper master:2181 --topic PowerData_Minute

$ kafka-console-consumer --zookeeper master:2181 --topic PowerData_Minute_HBase

$ kafka-console-consumer --zookeeper master:2181 --topic PowerData_CC

$ kafka-console-consumer --zookeeper master:2181 --topic PowerData_CC_HBase

### Superset啟動
$ superset runserver -t 300

### HBase on Hue操作
$ LIB-4_2018-03-07 13:30*+29[location][time][kw]
