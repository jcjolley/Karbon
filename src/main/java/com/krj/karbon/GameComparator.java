/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krj.karbon;

import java.util.Comparator;

/**
 *
 * @author jolley
 */
public class GameComparator implements Comparator<Game> {

    @Override
    public int compare(Game g1, Game g2) {
        if (g1.getInstances() > g2.getInstances()) {
            return -1;
        } else if (g1.getInstances() == g2.getInstances()) {
            return 0;
        }
        return 1;
    }
}
