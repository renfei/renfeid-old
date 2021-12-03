package net.renfei.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库 Meta 信息工具
 */
public class DBMSMetaUtil {
    /**
     * 数据库类型,枚举
     */
    public enum DATABASETYPE {
        ORACLE, MYSQL, SQLSERVER, SQLSERVER2005, DB2, INFORMIX, SYBASE, OTHER, EMPTY
    }

    /**
     * 根据字符串,判断数据库类型
     *
     * @param databaseType
     * @return
     */
    public static DATABASETYPE parseDATABASETYPE(String databaseType) {
        // 空类型
        if (null == databaseType || databaseType.trim().length() < 1) {
            return DATABASETYPE.EMPTY;
        }
        // 截断首尾空格,转换为大写
        databaseType = databaseType.trim().toUpperCase();
        // Oracle数据库
        if (databaseType.contains("ORACLE")) {
            //
            return DATABASETYPE.ORACLE;
        }
        // MYSQL 数据库
        if (databaseType.contains("MYSQL")) {
            //
            return DATABASETYPE.MYSQL;
        }
        // SQL SERVER 数据库
        if (databaseType.contains("SQL") && databaseType.contains("SERVER")) {
            //
            if (databaseType.contains("2005") || databaseType.contains("2008") || databaseType.contains("2012")) {

                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                return DATABASETYPE.SQLSERVER2005;
            } else {
                try {
                    // 注册 JTDS
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return DATABASETYPE.SQLSERVER;
            }
        }
        // 下面的这几个没有经过实践测试, 判断可能不准确
        // DB2 数据库
        if (databaseType.contains("DB2")) {
            //
            return DATABASETYPE.DB2;
        }
        // INFORMIX 数据库
        if (databaseType.contains("INFORMIX")) {
            //
            return DATABASETYPE.INFORMIX;
        }
        // SYBASE 数据库
        if (databaseType.contains("SYBASE")) {
            //
            return DATABASETYPE.SYBASE;
        }

        // 默认,返回其他
        return DATABASETYPE.OTHER;
    }

    /**
     * 列出数据库的所有表
     */
    // 可以参考: http://www.cnblogs.com/chinafine/articles/1847205.html
    public static List<Map<String, Object>> listTables(String databasetype, String ip, String port, String dbname,
                                                       String username, String password) {
        // 去除首尾空格
        databasetype = trim(databasetype);
        ip = trim(ip);
        port = trim(port);
        dbname = trim(dbname);
        username = trim(username);
        password = trim(password);
        //
        DATABASETYPE dbtype = parseDATABASETYPE(databasetype);
        //
        List<Map<String, Object>> result = null;
        String url = concatDBURL(dbtype, ip, port, dbname);
        Connection conn = getConnection(url, username, password);
        // Statement stmt = null;
        ResultSet rs = null;
        //
        try {
            // 这句话我也不懂是什么意思... 好像没什么用
            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // 获取Meta信息对象
            DatabaseMetaData meta = conn.getMetaData();
            // 数据库
            String catalog = null;
            // 数据库的用户
            String schemaPattern = null;// meta.getUserName();
            // 表名
            String tableNamePattern = null;//
            // types指的是table、view
            String[] types = {"TABLE"};
            // Oracle
            if (DATABASETYPE.ORACLE.equals(dbtype)) {
                schemaPattern = username;
                if (null != schemaPattern) {
                    schemaPattern = schemaPattern.toUpperCase();
                }
                // 查询
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);
            } else if (DATABASETYPE.MYSQL.equals(dbtype)) {
                // Mysql查询
                // MySQL 的 table 这一级别查询不到备注信息
                schemaPattern = dbname;
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);
            } else if (DATABASETYPE.SQLSERVER.equals(dbtype) || DATABASETYPE.SQLSERVER2005.equals(dbtype)) {
                // SqlServer
                tableNamePattern = "%";
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);
            } else if (DATABASETYPE.DB2.equals(dbtype)) {
                // DB2查询
                schemaPattern = "jence_user";
                tableNamePattern = "%";
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);
            } else if (DATABASETYPE.INFORMIX.equals(dbtype)) {
                // SqlServer
                tableNamePattern = "%";
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);
            } else if (DATABASETYPE.SYBASE.equals(dbtype)) {
                // SqlServer
                tableNamePattern = "%";
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);
            } else {
                throw new RuntimeException("不认识的数据库类型!");
            }
            //
            result = parseResultSetToMapList(rs);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(conn);
        }
        //
        return result;
    }

    /**
     * 列出表的所有字段
     */
    public static List<Map<String, Object>> listColumns(String databasetype, String ip, String port, String dbname,
                                                        String username, String password, String tableName) {
        // 去除首尾空格
        databasetype = trim(databasetype);
        ip = trim(ip);
        port = trim(port);
        dbname = trim(dbname);
        username = trim(username);
        password = trim(password);
        tableName = trim(tableName);
        //
        DATABASETYPE dbtype = parseDATABASETYPE(databasetype);
        //
        List<Map<String, Object>> result = null;
        String url = concatDBURL(dbtype, ip, port, dbname);
        Connection conn = getConnection(url, username, password);
        // Statement stmt = null;
        ResultSet rs = null;
        //
        try {
            // 获取Meta信息对象
            DatabaseMetaData meta = conn.getMetaData();
            // 数据库
            String catalog = null;
            // 数据库的用户
            String schemaPattern = null;// meta.getUserName();
            // 表名
            String tableNamePattern = tableName;//
            // 转换为大写
            if (null != tableNamePattern) {
                tableNamePattern = tableNamePattern.toUpperCase();
            }
            //
            String columnNamePattern = null;
            // Oracle
            if (DATABASETYPE.ORACLE.equals(dbtype)) {
                // 查询
                schemaPattern = username;
                if (null != schemaPattern) {
                    schemaPattern = schemaPattern.toUpperCase();
                }
            } else {
                //
            }

            rs = meta.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
            // TODO 获取主键列,但还没使用
            meta.getPrimaryKeys(catalog, schemaPattern, tableNamePattern);
            //
            result = parseResultSetToMapList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            close(rs);
            close(conn);
        }
        //
        return result;
    }

    /**
     * 根据IP,端口,以及数据库名字,拼接Oracle连接字符串
     *
     * @param ip
     * @param port
     * @param dbname
     * @return
     */
    public static String concatDBURL(DATABASETYPE dbtype, String ip, String port, String dbname) {
        //
        String url = "";
        // Oracle数据库
        if (DATABASETYPE.ORACLE.equals(dbtype)) {
            //
            url += "jdbc:oracle:thin:@";
            url += ip.trim();
            url += ":" + port.trim();
            url += ":" + dbname;

            // 如果需要采用 hotbackup
            String url2 = "";
            url2 = url2 + "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = "
                    + ip.trim() + ")(PORT =" + port.trim() + ")))(CONNECT_DATA = (SERVICE_NAME =" + dbname +
                    ")(FAILOVER_MODE = (TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))";
            //
            // url = url2;
        } else if (DATABASETYPE.MYSQL.equals(dbtype)) {
            //
            url += "jdbc:mysql://";
            url += ip.trim();
            url += ":" + port.trim();
            url += "/" + dbname;
        } else if (DATABASETYPE.SQLSERVER.equals(dbtype)) {
            //
            url += "jdbc:jtds:sqlserver://";
            url += ip.trim();
            url += ":" + port.trim();
            url += "/" + dbname;
            url += ";tds=8.0;lastupdatecount=true";
        } else if (DATABASETYPE.SQLSERVER2005.equals(dbtype)) {
            //
            url += "jdbc:sqlserver://";
            url += ip.trim();
            url += ":" + port.trim();
            url += "; DatabaseName=" + dbname;
        } else if (DATABASETYPE.DB2.equals(dbtype)) {
            url += "jdbc:db2://";
            url += ip.trim();
            url += ":" + port.trim();
            url += "/" + dbname;
        } else if (DATABASETYPE.INFORMIX.equals(dbtype)) {
            // Infox mix 可能有BUG
            url += "jdbc:informix-sqli://";
            url += ip.trim();
            url += ":" + port.trim();
            url += "/" + dbname;
            // +":INFORMIXSERVER=myserver;user="+bean.getDatabaseuser()+";password="+bean.getDatabasepassword()
        } else if (DATABASETYPE.SYBASE.equals(dbtype)) {
            url += "jdbc:sybase:Tds:";
            url += ip.trim();
            url += ":" + port.trim();
            url += "/" + dbname;
        } else {
            throw new RuntimeException("不认识的数据库类型!");
        }
        //
        return url;
    }

    /**
     * 获取JDBC连接
     *
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static Connection getConnection(String url, String username, String password) {
        Connection conn = null;
        try {
            // 不需要加载Driver. Servlet 2.4规范开始容器会自动载入
            // conn = DriverManager.getConnection(url, username, password);
            //
            Properties info = new Properties();
            //
            info.put("user", username);
            info.put("password", password);
            // !!! Oracle 如果想要获取元数据 REMARKS 信息,需要加此参数
            info.put("remarksReporting", "true");
            // !!! MySQL 标志位, 获取TABLE元数据 REMARKS 信息
            info.put("useInformationSchema", "true");
            // 不知道SQLServer需不需要设置...
            //
            conn = DriverManager.getConnection(url, info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 将一个未处理的ResultSet解析为Map列表.
     *
     * @param rs
     * @return
     */
    public static List<Map<String, Object>> parseResultSetToMapList(ResultSet rs) {
        //
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        //
        if (null == rs) {
            return null;
        }
        //
        try {
            while (rs.next()) {
                //
                Map<String, Object> map = parseResultSetToMap(rs);
                //
                if (null != map) {
                    result.add(map);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //
        return result;
    }

    /**
     * 解析ResultSet的单条记录,不进行 ResultSet 的next移动处理
     *
     * @param rs
     * @return
     */
    private static Map<String, Object> parseResultSetToMap(ResultSet rs) {
        //
        if (null == rs) {
            return null;
        }
        //
        Map<String, Object> map = new HashMap<String, Object>();
        //
        try {
            ResultSetMetaData meta = rs.getMetaData();
            //
            int colNum = meta.getColumnCount();
            //
            for (int i = 1; i <= colNum; i++) {
                // 列名
                String name = meta.getColumnLabel(i); // i+1
                Object value = rs.getObject(i);
                // 加入属性
                map.put(name, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //
        return map;
    }

    //
    public static boolean TryLink(String databasetype, String ip, String port, String dbname, String username, String password) {
        //
        DATABASETYPE dbtype = parseDATABASETYPE(databasetype);
        String url = concatDBURL(dbtype, ip, port, dbname);
        Connection conn = null;
        //
        try {
            conn = getConnection(url, username, password);
            if (null == conn) {
                return false;
            }
            DatabaseMetaData meta = conn.getMetaData();
            //
            if (null == meta) {
                return false;
            } else {
                // 只有这里返回true
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }
        //
        return false;
    }

    //
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //
    public static String trim(String str) {
        if (null != str) {
            str = str.trim();
        }
        return str;
    }
}
