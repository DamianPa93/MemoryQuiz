/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.memoryquiz;

import java.sql.*;

public class CrunchifyDataBase {
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static final String CONN_STRING="jdbc:mysql://localhost:3306/MemoryQuizStarterDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    public static void run(){
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Connected.");
            Statement stmt = (Statement) conn.createStatement();
            String wordA = "fioletowy"; //polish
            String wordB = "purple"; //english
            String insert = "INSERT INTO memoryquizstarterdb.vocabulary(wordA,wordB) VALUES ('" + wordA + "','" + wordB + "');";
            stmt.execute(insert);
        }catch(SQLException e){
            System.err.println(e);
        }
    }
}
