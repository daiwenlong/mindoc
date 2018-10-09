package com.dwl.mindoc.domain;

/**
 * @program: mindoc
 * @description: 表信息
 * @author: daiwenlong
 * @create: 2018-10-09 21:50
 **/
public class TableVo {
    /*表名*/
    private String tableName;
    /*表注释*/
    private String tableComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}


