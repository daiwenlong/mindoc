# mindoc
数据库文档生成器

## 介绍
通过配置数据库连接等基本信息，自动生成数据库设计Word文档。

## 使用方法

### application.properties配置信息

#数据库连接信息<br> 
spring.datasource.url=jdbc:mysql://localhost:3306/reports<br> 
spring.datasource.username: root<br> 
spring.datasource.password: 111111<br> 
spring.datasource.driver-class-name: com.mysql.jdbc.Driver<br> 

#数据库类型<br> 
baseType=MySQL<br> 
#baseType=Oracle<br> 
#baseType=SQLite<br> 
#baseType=SQLServer<br> 
#baseType=PostgreSQL<br> 

#数据库实例名<br> 
baseName=reports

### 运行MindocApplication

#### 运行日志
![image](https://github.com/daiwenlong/mindoc/blob/master/images/log.png)

#### 生成文档
![image](https://github.com/daiwenlong/mindoc/blob/master/images/show.png)

#### 文档预览
![image](https://github.com/daiwenlong/mindoc/blob/master/images/table.png)

暂只支持MySQL数据库







