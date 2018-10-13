# mindoc
数据库文档生成器

##介绍
通过配置数据库连接等基本信息，自动生成数据库设计Word文档。

##使用方法

###application.properties配置信息

*数据库连接信息
spring.datasource.url=jdbc:mysql://localhost:3306/reports
spring.datasource.username: root
spring.datasource.password: 111111
spring.datasource.driver-class-name: com.mysql.jdbc.Driver

*数据库类型
baseType=MySQL

*数据库实例名
baseName=reports

###运行MindocApplication

####运行日志
![image](https://github.com/daiwenlong/mindoc/blob/master/images/log.png)

####生成文档
![image](https://github.com/daiwenlong/mindoc/blob/master/images/show.png)

####文档预览
![image](https://github.com/daiwenlong/mindoc/blob/master/images/table1.png)

![image](https://github.com/daiwenlong/mindoc/blob/master/images/table.png)

暂只支持MySQL数据库







