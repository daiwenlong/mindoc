package com.dwl.mindoc.database;

public interface Database {

    String getBaseName();

    String getType();

    String getTablesSql(Database base);

    String getColumnSql(Database base ,String tableName);


}
