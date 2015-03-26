/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classWork.scriptures.dataaccess;

import com.classWork.scriptures.Scripture;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jolley
 */
public class MySqlScriptureDao implements ScriptureDao {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/arctic";

//  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    @Override
    public Scripture getScripture(String book, int chapter, int verse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Scripture getScripture(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Scripture> findScripture(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addScripture(Scripture scripture) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteScripture(Scripture scripture) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateScripture(Scripture scripture) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Scripture> getAll() {
        List<Scripture> scriptures = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, book, chapter, verse, content FROM scriptures";
            
            ResultSet results = stmt.executeQuery(sql);
            
            while (results.next()) {
                int id = results.getInt("id");
                String book = results.getString("book");
                int chapter = results.getInt("chapter");
                int verse = results.getInt("verse");
                Blob contentBlob = results.getBlob("content");
                
                //convert blob to string 
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                InputStream in = contentBlob.getBinaryStream();
                
                int n = 0;
                while ((n=in.read(buf))>=0)
                {
                    baos.write(buf, 0, n);
                }
                in.close();
                byte[] bytes = baos.toByteArray();
                String content = new String(bytes);
                
                Scripture scripture = new Scripture(book, chapter, verse);
                scriptures.add(scripture);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return scriptures;
    }
    
}
