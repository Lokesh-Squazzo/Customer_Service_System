package com.customerservice.dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {


    private static final String dbName = "data_base_name";
    private static final String port = "PORT";
    private static final String url = "jdbc:mysql://localhost:"+port+"/"+dbName;
    private static final String user = "userName";
    private static final String password = "password";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
