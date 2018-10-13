package com.dwl.mindoc.database;

public interface Database {

    String getBaseName();

    String getType();

    String getTablesSql();

    String getColumnSql();


}
