package net.renfei.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static net.renfei.utils.DBMSMetaUtil.listColumns;
import static net.renfei.utils.DBMSMetaUtil.listTables;

public class DBMSMetaUtilTests extends ApplicationTests {

    @Test
    public void test() throws JsonProcessingException {
        testMySQL();
    }

    private void testMySQL() throws JsonProcessingException {
        //
        String ip = "127.0.0.1";
        String port = "3306";
        String dbname = "renfeid";
        String username = "root";
        String password = "root";
        //
        String databasetype = "mysql";
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
