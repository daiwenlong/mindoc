package com.me.mindoc.database;

import com.me.mindoc.database.impl.MySQL;
import com.me.mindoc.database.impl.Oracle;

/**
 * @program: mindoc
 * @description: factory
 * @author: daiwenlong
 * @create: 2018-10-13 11:53
 **/
public class BaseFactory {

    private enum BaseType{

        MySQL,Oracle,PostgreSQL,SQLite,SQLServer

    }


    /**
     * 获取数据库信息
     * @return
     */
    public static Database getDataBase(String baseType,String name) throws Exception {
        Database dataBase = null;
        BaseType type =BaseType.valueOf(baseType);
        switch (type) {
            case MySQL:
                dataBase = new MySQL(name);
                break;
            case Oracle:
                dataBase = new Oracle(name);
                break;
            default:
                throw new Exception("Not Support BaseType: "+baseType);
        }
        return dataBase;
    }
}


