# Setup DB
```
Database Name: date_problem  
User: root  
Password: root
```

1. install MySQL <https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-community-8.0.15.0.msi> 
2. install HeidiSQL <https://www.heidisql.com/download.php>  
> open HeidiSQL then Error `Can't connect to MySQL server on '127.0.0.1' (10061)` > install MySQL
3. open date_problem.sql with _HeidiSQL_ then query sql-script

## Connect DB for IntelliJ
> run code then Error `java.lang.ClassNotFoundException: com.mysql.jdbc.Driver`
1. Open project on IntelliJ
2. File > Project Structure > Global Libraries > + (New Grobal Library) > Java > Find and Select `mysql-connector-java-8.0.15.jar` (C:\Program Files (x86)\MySQL\Connector J 8.0\mysql-connector-java-8.0.15.jar)

