# mindoc
数据库文档生成器

## 介绍
通过配置数据库连接等基本信息，自动生成数据库设计文档。<br> 
可选择Word和PDF格式。

## 使用方法

### application.properties配置信息

#数据库连接信息<br> 
spring.datasource.url=jdbc:mysql://localhost:3306/reports<br> 
spring.datasource.username=root<br> 
spring.datasource.password=111111<br> 
spring.datasource.driver-class-name=com.mysql.jdbc.Driver<br> 

#数据库类型<br> 
baseType=MySQL<br> 
#baseType=Oracle<br> 
#baseType=SQLite<br> 
#baseType=SQLServer<br> 
#baseType=PostgreSQL<br> 

#文档类型<br>
fileType=pdf<br> 
#fileType=word<br> 

#数据库实例名<br> 
baseName=reports

### 运行MindocApplication

#### 运行日志
![image](https://github.com/daiwenlong/mindoc/blob/master/images/log.png)

#### 生成文档
![image](https://github.com/daiwenlong/mindoc/blob/master/images/show.png)

#### 文档预览
![image](https://github.com/daiwenlong/mindoc/blob/master/images/table.png)


### 桌面程序
#### 下载mindoc-1.1.jar双击运行
mindoc.jar只有Word格式，mindoc-1.1.jar有Word和PDF两种格式
![image](https://github.com/daiwenlong/mindoc/blob/master/images/desk.png)

#### 填写数据库信息
![image](https://github.com/daiwenlong/mindoc/blob/master/images/data.png)

#### 点击生成按钮
![image](https://github.com/daiwenlong/mindoc/blob/master/images/view.png)

#### 文档预览
![image](https://github.com/daiwenlong/mindoc/blob/master/images/show1.png)

暂只支持MySQL，Oracle数据库







