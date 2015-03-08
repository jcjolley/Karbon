/*Ï
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentSystem;

import java.sql.*;
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
    static final String DB_URL = "jdbc:mysql://localhost/commentProgram";

//  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Post testPost = new Post("You're really pessimistic, George.", "jolley", LocalDateTime.now());
        testPost.addComment(new Comment("Most of the time", "George", LocalDateTime.now()));

        insertPost(testPost);

        List<Post> posts = getPosts();
        for (Post post : posts) {
            System.out.println(post);
        }
    }//end main

    /**
     *
     * @return
     */
    public static List<Post> getPosts() {
        List<Post> posts = new ArrayList<Post>();

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT p.timePosted, u.username, p.content, p.id FROM user "
                    + "u JOIN post p ON (u.id = p.author)";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                String postAuthor = rs.getString("u.username");
                String postContent = rs.getString("p.content");
                LocalDateTime postTimePosted = rs.getTimestamp("p.timePosted").toLocalDateTime();
                int postId = rs.getInt("p.id");
                Post post = new Post(postContent, postAuthor, postTimePosted, postId);

                Statement stmt2 = conn.createStatement();
                String sql2;
                sql2 = "SELECT c.content, u.username, c.timePosted, c.id FROM comment"
                        + " c JOIN user u ON (u.id = c.author) WHERE c.post = " + postId;
                ResultSet rs2 = stmt2.executeQuery(sql2);

                while (rs2.next()) {
                    String commentAuthor = rs2.getString("u.username");
                    String commentContent = rs2.getString("c.content");
                    LocalDateTime commentTimePosted = rs2.getTimestamp("c.timePosted").toLocalDateTime();
                    int commentId = rs2.getInt("c.id");
                    post.addComment(new Comment(commentContent, commentAuthor, commentTimePosted, commentId));
                }

                posts.add(post);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
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

        return posts;
    }

    /**
     *
     * @param post
     */
    public static void insertPost(Post post) {
        Connection conn = null;
        ResultSet keys = null;
        String postAuthor = post.getAuthor();
        String postContent = post.getContent();
        Timestamp timePosted = Timestamp.valueOf(post.getTimePosted());

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String sql;
            sql = "INSERT INTO post (author, content, timePosted) values"
                    + "((SELECT id FROM user WHERE username = ?),?,?)";

            PreparedStatement preparedStmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, postAuthor);
            preparedStmt.setString(2, postContent);
            preparedStmt.setTimestamp(3, timePosted);

            preparedStmt.executeUpdate();
            keys = preparedStmt.getGeneratedKeys();
            int postId = -1;
            if (keys.next()) {
                postId = keys.getInt(1);
            }

            System.out.println("The ID of the last post is: " + postId);
            for (Comment comment : post.getComments()) {

                String commentAuthor = comment.getAuthor();
                String commentContent = comment.getContent();
                Timestamp commentPosted = Timestamp.valueOf(comment.getTimePosted());
                String sql2 = "INSERT INTO comment (author, content,"
                        + " timePosted, post) values((SELECT id FROM user WHERE"
                        + " username = ?),?,?,?)";

                PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
                prepStmt2.setString(1, commentAuthor);
                prepStmt2.setString(2, commentContent);
                prepStmt2.setTimestamp(3, commentPosted);
                prepStmt2.setInt(4, postId);

                prepStmt2.executeUpdate();
            }

            //STEP 6: Clean-up environment
            keys.close();
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
                if (conn != null) {
                    conn.close();
                }
                if (keys != null) {
                    keys.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    /**
     * 
     * @param post 
     */
    public static void deletePost(Post post) {
        Connection conn = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String sql;
            sql = "DELETE FROM post WHERE id = ?";

            PreparedStatement prest = conn.prepareStatement(sql);
            prest.setInt(1, post.getId());

            prest.executeUpdate();

            //STEP 6: Clean-up environment
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    /**
     * 
     * @param comment 
     */
    public static void deleteComment(Comment comment) {
        Connection conn = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String sql;
            sql = "DELETE FROM comment WHERE id = ?";

            PreparedStatement prest = conn.prepareStatement(sql);
            prest.setInt(1, comment.getId());

            prest.executeUpdate();

            //STEP 6: Clean-up environment
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    /**
     * 
     * @param username
     * @param password
     * @return 
     */
    public static boolean isValidLogin(String username, String password) {
        boolean isValid = false;
        Connection conn = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String sql;
            sql = "SELECT id FROM user WHERE username = \"" + username + 
                    "\" AND password = \"" + password + "\"";
            Statement prest = conn.createStatement();
            
            ResultSet rs = prest.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                if (rs.getInt("id") > 0) {
                    isValid = true;
                }
            }
            //STEP 6: Clean-up environment
            rs.close();
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return isValid;
    }
    
    public static boolean userExists(String username){
        boolean exists = false;
        boolean isValid = false;
        Connection conn = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String sql;
            sql = "SELECT id FROM user WHERE username = \"" + username + "\"";
            Statement prest = conn.createStatement();
            
            ResultSet rs = prest.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                if (rs.getInt("id") > 0) {
                    exists = true;
                }
            }
            //STEP 6: Clean-up environment
            rs.close();
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return exists;
    }
            
    public static void addUser(String username, String password) {
        Connection conn = null;
        try {
            
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String sql;
            sql = "INSERT INTO user (username, password) values "
                    + "(\"" + username + "\",\"" + password +"\")";
            Statement prest = conn.createStatement();
            
            prest.executeUpdate(sql);

            
            //STEP 6: Clean-up environment
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    
    static void insertComment(int postId, Comment comment) {
        Connection conn = null;
        ResultSet keys = null;
        String commentAuthor = comment.getAuthor();
        String commentContent = comment.getContent();
        Timestamp timePosted = Timestamp.valueOf(comment.getTimePosted());

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String sql;
            sql = "INSERT INTO comment (author, content,"
                        + " timePosted, post) values((SELECT id FROM user WHERE"
                        + " username = ?),?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, commentAuthor);
            preparedStmt.setString(2, commentContent);
            preparedStmt.setTimestamp(3, timePosted);
            preparedStmt.setInt(4, postId);

            preparedStmt.executeUpdate();
            
            
            //STEP 6: Clean-up environment
            keys.close();
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
                if (conn != null) {
                    conn.close();
                }
                if (keys != null) {
                    keys.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}
