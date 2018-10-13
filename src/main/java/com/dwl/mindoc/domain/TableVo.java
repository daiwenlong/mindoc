package com.dwl.mindoc.domain;

import java.util.List;

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

    private List<ColumnVo> Columns;

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

    public List<ColumnVo> getColumns() {
        return Columns;
    }

    public void setColumns(List<ColumnVo> columns) {
        Columns = columns;
    }
}


