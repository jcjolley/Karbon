/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classWork.scriptures.dataaccess;

import com.classWork.scriptures.Scripture;
import java.util.List;

/**
 *
 * @author jolley
 */
public interface ScriptureDao {
    public Scripture getScripture(String book, int chapter, int verse);
    public Scripture getScripture(int id);
    public List<Scripture> findScripture(String query);   
    
    public void addScripture(Scripture scripture);
    public void deleteScripture(Scripture scripture);
    
    public void updateScripture(Scripture scripture);
    
    public List<Scripture> getAll();
}

