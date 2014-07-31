/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;
import com.unbc.main.*;
import com.unbc.utils.GeoMathFunctions;
import com.unbc.utils.RandomGenerator;
import java.awt.geom.Point2D;
/**
 *
 * @author sooki
 */
public class HandlerCreateNewEvent implements HandlerMethodI{

    @Override
    public void handler(Event previous) {
        //create a new event if its doesnt exceed the simulation time
            float arrivalTime = previous.getFinishTime();
            Node a = previous.getNodeReference();
            Point2D.Float current = previous.getStateReference().getDestination();
            Point2D.Float destination = RandomGenerator.getRandomPosition();
            Float velocity = RandomGenerator.getVelocityFromNormalDistribution();
            NodeState state = new NodeState(a,current,destination,velocity);
            a.addState(state);
            float finishTime = GeoMathFunctions.distanceBetWeenTwoPoint(current, destination)/velocity;
            // add a new event to the EventQueue
            Event e = new Event(arrivalTime, arrivalTime+finishTime, a, state);
            EventQueue.addEvent(e);
        
     
    }
    
}
