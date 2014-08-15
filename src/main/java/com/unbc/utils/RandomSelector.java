/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.utils;

import com.unbc.core.models.Destinations;
import com.unbc.core.models.NodeType;
import java.util.List;
import java.util.Random;

/**
 *
 * @author chellads
 */
public class RandomSelector {
    List<Destinations> listOfDestinations;
    Random rand = new Random();
    int totalSum = 0;
    NodeType type;

    RandomSelector(List<Destinations> listOfDestinations,NodeType type ) {
        this.listOfDestinations = listOfDestinations;
        this.type = type;
        for(Destinations des : listOfDestinations) {
            totalSum = totalSum + des.getProbablity()[type.ordinal()];
        }
    }

    public Destinations getRandom() {

        int index = rand.nextInt(totalSum);
        int sum = 0;
        int i=0;
        while(sum < index ) {
             sum = sum + listOfDestinations.get(i++).getProbablity()[type.ordinal()];
        }
        return listOfDestinations.get(i-1);
    }
    
}
