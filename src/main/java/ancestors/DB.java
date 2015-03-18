/*Ï
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ancestors;

import commentSystem.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jolley
 */
public class DB {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/ancestors";

//  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
    }//end main

    /**
     *
     * @return
     */
    public static List<Person> getPeople() {
        List<Person> people = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM person";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int id = rs.getInt("id");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();

                stmt2 = conn.createStatement();
                String sql2;
                sql2 = "SELECT f.child, parent.firstname as parentFirstName, "
                        + "child.firstname as childFirstName, p.firstname, "
                        + "p.lastname, p.birthday, p.id \n"
                        + "FROM person as p "
                        + "JOIN family as f ON (f.parent = p.id)"
                        + "JOIN family as fam ON (fam.child = p.id)"
                        + "JOIN person as child ON (child.id = f.child)"
                        + "JOIN person as parent ON (parent.id = fam.parent)";
                
                ResultSet rs2 = stmt2.executeQuery(sql);
                 
                Person person = new Person(firstname, lastname, birthday, id);
                people.add(person);
            }

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            stmt2.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return people;
    }

//    public static Person getPerson() {
//        
//        Connection conn = null;
//        Statement stmt = null;
//        try {
//            //STEP 2: Register JDBC driver
//            Class.forName(JDBC_DRIVER);
//            //STEP 3: Open a connection
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            //STEP 4: Execute a query
//            String sql;
//            sql = "INSERT INTO post (author, content, timePosted) values"
//                    + "((SELECT id FROM user WHERE username = ?),?,?)";
//
//            PreparedStatement preparedStmt = conn.prepareStatement(sql,
//                    Statement.RETURN_GENERATED_KEYS);
//            preparedStmt.setString(1, postAuthor);
//            preparedStmt.setString(2, postContent);
//            preparedStmt.setTimestamp(3, timePosted);
//
//            ResultSet rs = preparedStmt.executeQuery();
//            
//
//            //STEP 5: Extract data from result set
//            while (rs.next()) {
//                String firstname = rs.getString("firstname");
//                String lastname = rs.getString("lastname");
//                LocalDate birthday = rs.getDate("birthday").toLocalDate();
//                int id = rs.getInt("id");
//                
//                Person person = new Person(firstname, lastname, birthday, id);
//            }
//
//            //STEP 6: Clean-up environment
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (SQLException se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        } catch (Exception e) {
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        } finally {
//            //finally block used to close resources
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//            } catch (SQLException se2) {
//            }// nothing we can do
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }//end finally try
//        }//end try
//        return person;
//    }
}
