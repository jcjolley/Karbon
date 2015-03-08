/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author jolley
 */
public class Post {
    private String content;
    private String author;
    List<Comment> comments; 
    private LocalDateTime timePosted;
    private int id;

    public Post(String content, String author, List<Comment> comments, LocalDateTime timePosted) {
        this.content = content;
        this.author = author;
        this.comments = comments;
        this.timePosted = timePosted;
    }

     public Post() {
        content = "";
        author = "";
        comments = new ArrayList<Comment>();
        timePosted = LocalDateTime.now();
        id = 0;
    }
        
    public Post(String content, String user) {
        this.content = content;
        this.author = user;
        timePosted = LocalDateTime.now();
        comments = new ArrayList<Comment>();
        id = 0;
    }

    public Post(String content, String author, List<Comment> comments) {
        this.content = content;
        this.author = author;
        this.comments = comments;
        timePosted = LocalDateTime.now();
        id = 0;
    }

    public Post(String content, String author, LocalDateTime timePosted) {
        this.content = content;
        this.author = author;
        this.timePosted = timePosted;
        comments = new ArrayList<Comment>();
        id = 0;
    }

    public Post(String content, String author, LocalDateTime timePosted, int id) {
        this.content = content;
        this.author = author;
        this.timePosted = timePosted;
        this.id = id;
        comments = new ArrayList<Comment>();
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public LocalDateTime getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(LocalDateTime timePosted) {
        this.timePosted = timePosted;
    }
    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void addComment(Comment comment) {
        if (comments != null) {
            comments.add(comment);
            Collections.sort(comments, new CommentComparator());
        } else {
            System.out.println("You're an idiot");
        }
    }

    @Override
    public String toString() {
        String post = "[" + timePosted + "] " + content + " ~" + author + "\n";
        for (Comment comment : comments) {
            post += "     " + comment + "\n";
        }
        return post;
    }
    
    
}

