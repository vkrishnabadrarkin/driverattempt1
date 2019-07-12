package org.attempt1.doit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class simplequery {
    public static void main(String[] args) throws Exception{
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
        Connection connection = DriverManager.getConnection("jdbc:clickhouse://localhost:8123/default", "badri", "12345");

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT (number % 3 + 1) as n, sum(number) FROM numbers(10000000) GROUP BY n");

        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t" + rs.getLong(2));
        }
        stmt.close();
        connection.close();

    }
}
