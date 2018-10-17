package com.dwl.mindoc.dao.impl;

import com.dwl.mindoc.dao.BaseDao;
import com.dwl.mindoc.database.Database;
import com.dwl.mindoc.domain.ColumnVo;
import com.dwl.mindoc.domain.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: mindoc
 * @description: dao
 * @author: daiwenlong
 * @create: 2018-10-13 11:48
 **/
@Repository
public class BaseDaoImpl implements BaseDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TableVo> getTables(Database base) {
        return jdbcTemplate.query(base.getTablesSql(base),new Object[]{},new BeanPropertyRowMapper(TableVo.class));
    }

    @Override
    public List<ColumnVo> getColumns(Database base ,String tableName) {
        return jdbcTemplate.query(base.getColumnSql(base ,tableName),new Object[]{},new BeanPropertyRowMapper(ColumnVo.class));
    }
}


