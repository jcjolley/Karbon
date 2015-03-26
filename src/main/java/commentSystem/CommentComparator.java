/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commentSystem;

import java.util.Comparator;

/**
 *
 * @author jolley
 */

public class CommentComparator implements Comparator<Comment> {
    @Override
    
    public int compare(Comment c1, Comment c2)
    {
        return c1.getTimePosted().compareTo(c2.getTimePosted());
    }

}