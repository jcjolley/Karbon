/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classWork.scriptures;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jolley
 */
public class HardCodedScriptureListHandler implements ScriptureListHandler{
    public List<Scripture> getFavoriteScriptures() {
        List<Scripture> scriptures = new ArrayList<Scripture>();
            
        //scriptures.add(new Scripture("Alma", 5,15));
        scriptures.add(new Scripture("D&C",45, 3));
        scriptures.add(new Scripture("Proverbs", 3,11));
        scriptures.add(new Scripture("Moroni", 8,25));
        
        return scriptures;
    }
}
