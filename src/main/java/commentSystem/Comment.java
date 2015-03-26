/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentSystem;

import java.time.LocalDateTime;

/**
 *
 * @author jolley
 */
public class Comment {
    
    private String content;
    private String author;
    private LocalDateTime timePosted;
    private int id;
    
    public Comment()
    {
        content = "";
        author = "";
        timePosted = LocalDateTime.now();
        id = 0;
    }

    public Comment(String content, String author, LocalDateTime timePosted) {
        this.content = content;
        this.author = author;
        this.timePosted = timePosted;
        id = 0;
    }
    
    
    public Comment(String content, String author) {
        this.content = content;
        this.author = author;
        timePosted = LocalDateTime.now();
        id = 0;
    }

    public Comment(String content, String author, LocalDateTime timePosted, int id) {
        this.content = content;
        this.author = author;
        this.timePosted = timePosted;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(LocalDateTime timePosted) {
        this.timePosted = timePosted;
    }

    
    @Override
    public String toString() {
        return "[" + timePosted + "] " + content + " ~" + author;
    }
    
    
    
}
