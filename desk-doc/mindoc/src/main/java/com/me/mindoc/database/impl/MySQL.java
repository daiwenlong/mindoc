package com.me.mindoc.database.impl;

import com.me.mindoc.database.Database;

/**
 * @program: mindoc
 * @description: MySQL数据库
 * @author: daiwenlong
 * @create: 2018-10-09 21:13
 **/
public class MySQL implements Database{

    private String baseName;

    public MySQL(String baseName){
        this.baseName = baseName;
    }

    public String getBaseName() {
        return this.baseName;
    }

    public String getType() {
        return "com.mysql.jdbc.Driver";
    }

    public String getTablesSql(Database base) {
        return "select table_name,table_comment from information_schema.tables where table_schema = '"+base.getBaseName()+"' order by table_name asc";
    }

    public String getColumnSql(Database base ,String tableName) {
        return "select column_name,column_type,column_key,is_nullable,column_comment from information_schema.columns where table_schema = '"+base.getBaseName()+"'  and table_name = '"+tableName+"'";
    }

}


