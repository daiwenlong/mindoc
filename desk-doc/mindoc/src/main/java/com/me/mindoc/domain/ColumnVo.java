package com.me.mindoc.domain;

import org.nutz.dao.entity.annotation.Column;

/**
 * @program: mindoc
 * @description: 列信息
 * @author: daiwenlong
 * @create: 2018-10-09 21:58
 **/
public class ColumnVo {
    /*列名*/
	@Column("column_name")
    private String columnName;
    /*数据类型*/
	@Column("column_type")
    private String columnType;
    /*键类型*/
	@Column("column_key")
    private String columnKey;
    /*可否为空*/
	@Column("is_nullable")
    private String isNullable;
    /*注释*/
	@Column("column_comment")
    private String columnComment;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}


