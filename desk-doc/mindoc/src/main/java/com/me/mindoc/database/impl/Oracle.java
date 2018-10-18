package com.me.mindoc.database.impl;

import com.me.mindoc.database.Database;

/**
 * @program: mindoc
 * @description: Oracle
 * @author: daiwenlong
 * @create: 2018-10-13 12:17
 **/
public class Oracle implements Database {

    private String baseName;

    public Oracle(String baseName){
        this.baseName = baseName;
    }

    public String getBaseName() {
        return this.baseName;
    }

    public String getType() {
        return "oracle.jdbc.OracleDriver";
    }

    public String getTablesSql(Database base) {
        return "select a.TABLE_NAME as table_name,b.COMMENTS as table_comment from user_tables a,user_tab_comments b WHERE a.TABLE_NAME=b.TABLE_NAME order by table_name";
    }

    public String getColumnSql(Database base ,String tableName) {
        return "select c.*,d.comments column_comment from (select a.column_name ,a.data_type||'('||data_length||')' column_type,a.nullable is_nullable,CASE when  b.column_name is not null then 'PK' else '' END column_key  from user_tab_columns a left join " +
                "                (select cu.* from user_cons_columns cu, user_constraints au where cu.constraint_name = au.constraint_name and au.constraint_type = 'P') b on " +
                "                b.table_name = a.Table_Name and a.column_name = b.column_name where a.Table_Name= '"+tableName+"' ) c left join user_col_comments d on c.column_name = d.column_name where d.table_name = '"+tableName+"' " +
                "";
    }
}


