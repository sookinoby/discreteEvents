/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;
import com.unbc.main.*;
import com.unbc.utils.GeoMathFunctions;
import com.unbc.utils.Point;
import com.unbc.utils.RandomGenerator;
import com.unbc.utils.RandomSelector;
import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *This is the handler for random way point model. Hey the handler will create new wayout when a previous event is processed
 * @author sooki
 * 
 * 
 */
public class HandlerRandomWayPoint implements HandlerMethodI{

    /**
 * This  method is invoked when a node reaches its destination. It is assumed
 * that when a event is almost completely proccessed when a node reaches its destination.
 * When a event is processed it invokes the handler of that event(here previous event)
 * The previous event causes a new event to be added to the event queue.
 * Does not return anything, but may add a event to event queue depending on
 * the finish time
 * @param  previous  The previous event which almost completed processing 
 *  
 */
    @Override
    public void handler(Event previous) {
        //create a new event if its doesnt exceed the simulation time
        // Write code for passive event where the node remains stationary
           float arrivalTime = previous.getFinishTime();
            Node a = previous.getNodeReference();
            Point current = previous.getStateReference().getDestination();
            NodeState state;
        if(previous.getStateReference().getStateType() == NodeState.StateType.ACTIVE)
        {
         
            try {
                state = new NodeState(a, current, NodeState.StateType.PASSIVE,1);
                a.addState(state);
                // Not generating new velocity, since in passive state the velocity is almost zero and it is generated as part of 
                // node state constructs
                // Query back the velocity that was set by the NodeState construct;
                Float velocity = state.getVelocity();
                float finishTime = GeoMathFunctions.distanceBetWeenTwoPoint(current, state.getDestination())/velocity;
                Event e = new Event(arrivalTime,arrivalTime + finishTime, a, state,previous.getMobilityType());
                EventQueue.addEvent(e);
            } catch (Exception ex) {
                Logger.getLogger(HandlerRandomWayPoint.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
        else{
        // this is for active events
            Point destination = RandomSelector
            Float velocity = RandomGenerator.getVelocityFromNormalDistribution();
            state = new NodeState(a,current,destination,velocity);
            a.addState(state);
            float finishTime = GeoMathFunctions.distanceBetWeenTwoPoint(current, destination)/velocity;
            // add a new event to the EventQueue
            Event e = new Event(arrivalTime, arrivalTime + finishTime, a, state,previous.getMobilityType());
            EventQueue.addEvent(e);
        }
     
    }
    
}
