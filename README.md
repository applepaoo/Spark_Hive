Data Lake
============
### Spark2-Shell
$ spark2-shell --driver-memory 25G --executor-memory 25G --executor-cores 20

$ spark-shell --master local[*] --driver-memory 15G --executor-memory 15G --executor-cores 20

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

### Hue On HBase操作
$ LIB-4_2018-03-07 13:30*+29[location][time][kw]

$ THUC-M0001-AC-main_2018-03-07 18:17+16

### Apache Phoenix
$ phoenix-sqlline.py 140.128.101.177:2181

$ select "time", "location", "kw" from "powerdata_minute_hbase" where "location" = 'LIB-4';

$ select "time", "location", "kw" from "powerdata_minute_hbase" where "location" = 'LIB-4' order by "time" DESC limit 30;

$ select "time", "meter_id", "p" from "powerdata_cc_hbase" where "meter_id" = 'THUC-M0005-server-main' order by "time" desc limit 60;

### 產生Java class
$ javac -cp "/opt/cloudera/parcels/KAFKA/lib/kafka/libs/*" producer.java

### 執行Java file
$ java -cp "/opt/cloudera/parcels/KAFKA/lib/kafka/libs/*":. producer

###測試用
$ spark-shell --jars target/spark-ts-examples-0.0.1-SNAPSHOT-jar-with-dependencies.jar \ --driver-class-path target/spark-ts-examples-0.0.1-SNAPSHOT-jar-with-dependencies.jar
