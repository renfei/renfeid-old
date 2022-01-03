package net.renfei.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.renfei.ApplicationTests;
import net.renfei.config.SystemConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

import static net.renfei.utils.DBMSMetaUtil.*;

public class DBMSMetaUtilTests extends ApplicationTests {
    @Autowired
    private SystemConfig systemConfig;

    @Test
    public void test() throws JsonProcessingException {
        testMySQL();
//        testDaMengSQL();
    }

    private void testMySQL() throws JsonProcessingException {
        //
        String ip = "127.0.0.1";
        if (systemConfig.getActive().equals("gitlab")) {
            ip = "mariadb";
        }
        String port = "3306";
        String dbname = "renfeid";
        String username = "root";
        String password = "root";
        //
        String databasetype = "mysql";
        //
        String tableName = "sys_region";

        Assert.isTrue(TryLink(databasetype, ip, port, dbname, username, password), "");

        List<Map<String, Object>> tables = listTables(databasetype, ip, port, dbname, username, password);
        List<Map<String, Object>> columns = listColumns(databasetype, ip, port, dbname, username, password, tableName);
        //
        tables = MapUtil.convertKeyList2LowerCase(tables);
        columns = MapUtil.convertKeyList2LowerCase(columns);
        //
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonT = objectMapper.writeValueAsString(tables);
        System.out.println(jsonT);
        System.out.println("tables.size()=" + tables.size());
        //
        System.out.println("-----------------------------------------" + "-----------------------------------------");
        System.out.println("-----------------------------------------" + "-----------------------------------------");
        //
        String jsonC = objectMapper.writeValueAsString(columns);
        System.out.println(jsonC);
        System.out.println("columns.size()=" + columns.size());
    }

    private void testDaMengSQL() throws JsonProcessingException {
        String ip = "127.0.0.1";
        String port = "5236";
        String dbname = "PROD";
        String username = "SYSDBA";
        String password = "SYSDBA";
        //
        String databasetype = "DAMENG";
        //
        String tableName = "sys_region";

        List<Map<String, Object>> tables = listTables(databasetype, ip, port, dbname, username, password);
        List<Map<String, Object>> columns = listColumns(databasetype, ip, port, dbname, username, password, tableName);
        //
        tables = MapUtil.convertKeyList2LowerCase(tables);
        columns = MapUtil.convertKeyList2LowerCase(columns);
        //
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonT = objectMapper.writeValueAsString(tables);
        System.out.println(jsonT);
        System.out.println("tables.size()=" + tables.size());
        //
        System.out.println("-----------------------------------------" + "-----------------------------------------");
        System.out.println("-----------------------------------------" + "-----------------------------------------");
        //
        String jsonC = objectMapper.writeValueAsString(columns);
        System.out.println(jsonC);
        System.out.println("columns.size()=" + columns.size());
    }
}
