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
public class PostComparator implements Comparator<Post> {

    @Override
    public int compare(Post p1, Post p2)
    {
        return p2.getTimePosted().compareTo(p1.getTimePosted());
    }
}
   
