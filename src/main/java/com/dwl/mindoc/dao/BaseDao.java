package com.dwl.mindoc.dao;

import com.dwl.mindoc.database.Database;
import com.dwl.mindoc.domain.ColumnVo;
import com.dwl.mindoc.domain.TableVo;

import java.util.List;

public interface BaseDao {

    List<TableVo> getTables(Database base);

    List<ColumnVo> getColumns(Database base ,String tableName);

}
