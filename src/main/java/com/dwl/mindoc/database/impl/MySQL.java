package com.dwl.mindoc.database.impl;

import com.dwl.mindoc.database.Database;

/**
 * @program: mindoc
 * @description: MySQL数据库
 * @author: daiwenlong
 * @create: 2018-10-09 21:13
 **/
public class MySQL implements Database{

    @Override
    public String getType() {
        return "MySQL";
    }

    @Override
    public String getTablesSql() {
        return "select table_name,table_comment from information_schema.tables where table_schema = ? order by table_name asc";
    }

    @Override
    public String getColumnSql() {
        return "select column_name,column_type,column_key,is_nullable,column_comment from information_schema.columns where table_schema = ?  and table_name = ?";
    }
}


