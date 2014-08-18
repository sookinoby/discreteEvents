/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.utils;

import com.unbc.core.models.Destinations;
import com.unbc.core.models.NodeType;
import java.util.List;



/**
 *
 * @author chellads
 */
public class RandomSelectorStatic {
    
    public static RandomSelector listOfDestination[];
    
    public static void initialiseDestination()
    {
       Destinations.initialiseDestinationFromJson();
       List<Destinations> data = Destinations.getListAllDestination();
       listOfDestination = new RandomSelector[NodeType.values().length];
       for (NodeType qq : NodeType.values())
          listOfDestination[qq.ordinal()] = new RandomSelector(data,qq);
    }
    
    public static Destinations  getRandomDestinationOfType(NodeType type)
    {
       return listOfDestination[type.ordinal()].getRandom();
         
    }
    
}
