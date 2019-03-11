package com.dwl.mindoc.database.impl;

import com.dwl.mindoc.database.Database;

/**
 * @program: mindoc
 * @description: SQLServer
 * @author: daiwenlong
 * @create: 2018-10-13 12:20
 **/
public class SQLServer implements Database {

	private String baseName;

	public SQLServer(String baseName) {
		this.baseName = baseName;
	}

	@Override
	public String getBaseName() {
		return this.baseName;
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public String getTablesSql(Database base) {
		 return "SELECT\r\n" + 
		 		"	obj.name AS table_name\r\n" + 
		 		"   ,CONVERT(NVARCHAR, cmt.value) AS table_comment\r\n" + 
		 		"   from dbo.sysobjects obj LEFT JOIN sys.extended_properties cmt\r\n" + 
		 		"	ON obj.id = cmt.major_id\r\n" + 
		 		"		AND cmt.minor_id = 0\r\n" + 
		 		"		AND cmt.name = 'MS_Description'\r\n" + 
		 		"		where 	 obj.xtype = 'U'\r\n" + 
		 		"		AND obj.status >= 0\r\n" + 
		 		"		order by 	obj.name asc";
	}

	@Override
	public String getColumnSql(Database base, String tableName) {
		return "SELECT obj.NAME \r\n" + 
				"       AS \r\n" + 
				"       表名, \r\n" + 
				"       CONVERT(NVARCHAR, epTwo.value) \r\n" + 
				"       AS 表说明, \r\n" + 
				"       col.colorder \r\n" + 
				"       AS colorder, \r\n" + 
				"       col.NAME \r\n" + 
				"       AS column_name, \r\n" + 
				"       t.NAME + CASE WHEN t.NAME LIKE '%char%' THEN CASE CONVERT(NVARCHAR, \r\n" + 
				"       Isnull( \r\n" + 
				"       Columnproperty(col.id, col.NAME, 'PRECISION'), '')) WHEN '-1' THEN \r\n" + 
				"       '(max)' ELSE \r\n" + 
				"       '('+CONVERT(NVARCHAR, \r\n" + 
				"       Isnull(Columnproperty(col.id, col.NAME, 'PRECISION'), '')) \r\n" + 
				"       +')' END ELSE CASE WHEN t.NAME = 'decimal' THEN '('+CONVERT(NVARCHAR, \r\n" + 
				"       Isnull( \r\n" + 
				"       Columnproperty(col.id, col.NAME, 'PRECISION'), '')) + ',' + CONVERT( \r\n" + 
				"       NVARCHAR, \r\n" + 
				"       Isnull(Columnproperty(col.id, col.NAME, 'Scale'), ''))+')' ELSE '' END \r\n" + 
				"       END AS \r\n" + 
				"       column_type, \r\n" + 
				"       Isnull(ep.[value], '') \r\n" + 
				"       AS column_comment \r\n" + 
				"       --    ,CASE \r\n" + 
				"       --  WHEN COLUMNPROPERTY(col.id, col.name, 'IsIdentity') = 1 THEN '1' \r\n" + 
				"       --  ELSE '' \r\n" + 
				"       --END AS [IDENTITY] \r\n" + 
				"       , \r\n" + 
				"       CASE \r\n" + 
				"         WHEN EXISTS (SELECT 1 \r\n" + 
				"                      FROM   dbo.sysindexes si \r\n" + 
				"                             INNER JOIN dbo.sysindexkeys sik \r\n" + 
				"                                     ON si.id = sik.id \r\n" + 
				"                                        AND si.indid = sik.indid \r\n" + 
				"                             INNER JOIN dbo.syscolumns sc \r\n" + 
				"                                     ON sc.id = sik.id \r\n" + 
				"                                        AND sc.colid = sik.colid \r\n" + 
				"                             INNER JOIN dbo.sysobjects so \r\n" + 
				"                                     ON so.NAME = si.NAME \r\n" + 
				"                                        AND so.xtype = 'PK' \r\n" + 
				"                      WHERE  sc.id = col.id \r\n" + 
				"                             AND sc.colid = col.colid) THEN 'Y' \r\n" + 
				"         ELSE '' \r\n" + 
				"       END \r\n" + 
				"       AS column_key, \r\n" + 
				"       CASE \r\n" + 
				"         WHEN col.isnullable = 1 THEN 'Y' \r\n" + 
				"         ELSE '' \r\n" + 
				"       END \r\n" + 
				"       AS is_nullable, \r\n" + 
				"       Isnull(comm.text, '') \r\n" + 
				"       AS default_value \r\n" + 
				"--,CONVERT(NVARCHAR, ep.value) remark \r\n" + 
				"FROM   dbo.syscolumns col \r\n" + 
				"       LEFT JOIN dbo.systypes t \r\n" + 
				"              ON col.xtype = t.xusertype \r\n" + 
				"       INNER JOIN dbo.sysobjects obj \r\n" + 
				"               ON col.id = obj.id \r\n" + 
				"                  AND obj.xtype = 'U' \r\n" + 
				"                  AND obj.status >= 0 \r\n" + 
				"       LEFT JOIN dbo.syscomments comm \r\n" + 
				"              ON col.cdefault = comm.id \r\n" + 
				"       LEFT JOIN sys.extended_properties ep \r\n" + 
				"              ON col.id = ep.major_id \r\n" + 
				"                 AND col.colid = ep.minor_id \r\n" + 
				"                 AND ep.NAME = 'MS_Description' \r\n" + 
				"       LEFT JOIN sys.extended_properties epTwo \r\n" + 
				"              ON obj.id = epTwo.major_id \r\n" + 
				"                 AND epTwo.minor_id = 0 \r\n" + 
				"                 AND epTwo.NAME = 'MS_Description' \r\n" + 
				"WHERE  1 = 1 \r\n" + 
				"       AND obj.NAME = '" + tableName + "' \r\n" + 
				"ORDER  BY obj.NAME, \r\n" + 
				"          col.colorder";
		//return "select column_name,column_type,column_key,is_nullable,column_comment from information_schema.columns where table_schema = '"+base.getBaseName()+"'  and table_name = '"+tableName+"'";
	}
}
