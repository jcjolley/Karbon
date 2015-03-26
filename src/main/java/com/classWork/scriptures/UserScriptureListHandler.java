/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classWork.scriptures;

import com.classWork.scriptures.dataaccess.MySqlScriptureDao;
import com.classWork.scriptures.dataaccess.ScriptureDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jolley
 */
public class UserScriptureListHandler implements ScriptureListHandler {

    @Override
    public List<Scripture> getFavoriteScriptures() {
        List<Scripture> scriptures = new ArrayList<>();
        ScriptureDao dao = new MySqlScriptureDao();
        scriptures = dao.getAll();
        return scriptures;
    }
    
}
