package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }
    public Connection getConnection(){
        try{
            return (Connection) DriverManager.getConnection(url);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}

