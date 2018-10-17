package com.dwl.mindoc.database;

import com.dwl.mindoc.MinConfig;
import com.dwl.mindoc.database.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: mindoc
 * @description: factory
 * @author: daiwenlong
 * @create: 2018-10-13 11:53
 **/
@Component
public class BaseFactory {

    @Autowired
    private MinConfig config;

    private enum BaseType{

        MySQL,Oracle,PostgreSQL,SQLite,SQLServer

    }


    /**
     * 获取数据库信息
     * @return
     */
    public Database getDataBase() throws Exception {
        Database dataBase = null;
        String baseType = config.getValue("baseType");
        String baseName = config.getValue("baseName");
        BaseType type =BaseType.valueOf(baseType);
        switch (type) {
            case MySQL:
                dataBase = new MySQL(baseName);
                break;
            case Oracle:
                dataBase = new Oracle(baseName);
                break;
            case PostgreSQL:
                dataBase = new PostgreSQL();
                break;
            case SQLite:
                dataBase = new SQLite();
                break;
            case SQLServer:
                dataBase = new SQLServer();
                break;
            default:
                throw new Exception("Not Support BaseType: "+baseType);
        }
        return dataBase;
    }
}


