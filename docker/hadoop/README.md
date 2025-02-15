
1. hadoop hdfs 클러스터 실행
```
docker-compose up -d
(종료) docker-compose down
```

2. 네임 노드 접속
```
docker exec -it namenode bash
```
3. hdfs 디렉토리 생 및 파일 업로드
```
hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/test
echo "Hello Hadoop" > test.txt
hdfs dfs -put test.txt /user/test/
hdfs dfs -ls /user/test/
```
> root@772261e88a65:/# hdfs dfs -ls /user/test/
Found 1 items
-rw-r--r--   3 root supergroup         13 2025-02-15 07:45 /user/test/test.txt



---

