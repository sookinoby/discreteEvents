/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;


import com.unbc.main.SimulationParameters;
import com.unbc.utils.GeoMathFunctions;
import com.unbc.utils.Helper;
import com.unbc.utils.RandomGenerator;
import java.awt.geom.Point2D;

/**
 *
 * @author sooki
 */
public class RandomWalkHandler implements HandlerMethodI{
    
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
        
        // Here the Passive state is checked to initialise simulaton else
        // random walks dont not have passive states
            Node a = previous.getNodeReference();
            float arrivalTime = previous.getFinishTime();
            Point2D.Float current = previous.getStateReference().getDestination();
            NodeState state;
            Point2D.Float destination;
            if(previous.getStateReference().getStateType() == NodeState.StateType.PASSIVE)
            {
        // this is for active events
           /* int ran= RandomGenerator.getRandomNumberBetweenZeroAndX(100);
            if(ran % 2 == 0)
            {
             destination = RandomGenerator.getRandomPositionAlongX();
            }
            else
            {
             destination = RandomGenerator.getRandomPositionAlongY();
            } */
                // these edit for made for checking. by sooki
            destination = new Point2D.Float(20,100);
            Float velocity = RandomGenerator.getVelocityFromNormalDistribution();
            state = new NodeState(a,current,destination,velocity);
            a.addState(state);
            float finishTime = GeoMathFunctions.distanceBetWeenTwoPoint(current, destination)/velocity;
            // add a new event to the EventQueue
            Event e = new Event(arrivalTime, arrivalTime + finishTime, a, state,previous.getMobilityType());
            EventQueue.addEvent(e);
            }
            else{
                // check if reaching the height bounds, then reflect by changing the angle by -(angle). Then calculate the destination from angle.
                // The parameter we know. the displacement along x axis(the intercept) which can now be used to calculate remaining distance.
                float current_y = (float)previous.getStateReference().getCurrentPosition().getY();
                if( Helper.isNear(current_y, 0) || Helper.isNear(current_y, SimulationParameters.HEIGHT_SIMULATION_AREA))
                 {
                 // adjust the position
                     float x = previous.getStateReference().getDestination().x;
                     float remainingDistance = SimulationParameters.WIDTH_SIMULATION_AREA - x;
                     float previous_angle_in_degree = previous.getStateReference().getAngleInDegree();
                     float angle_of_reflection = -previous_angle_in_degree;
                     float opposite = GeoMathFunctions.findOppositetUsingAngle(remainingDistance, angle_of_reflection);
                     float y;
                    
                     opposite = Math.abs(opposite);
                     if(opposite > SimulationParameters.HEIGHT_SIMULATION_AREA)
                     {
                         // this means the angle was too steep and incerpt is not at the x axis (right side). But at the y = 
                         
                     }
                     y = SimulationParameters.HEIGHT_SIMULATION_AREA - opposite;
                     if(y < 0)
                         y = 0;
                     if( y > 100 )
                         y = SimulationParameters.HEIGHT_SIMULATION_AREA;
                     if(previous_angle_in_degree < 90 && previous_angle_in_degree > -90 )
                         x = SimulationParameters.WIDTH_SIMULATION_AREA;
                     else
                         x = 0;
                     destination = new Point2D.Float(x,y);
                     Float velocity = previous.getStateReference().getVelocity();
                     state = new NodeState(a,current,destination,velocity);
                     a.addState(state);
                     float finishTime = GeoMathFunctions.distanceBetWeenTwoPoint(current, destination)/velocity;
                     // add a new event to the EventQueue
                     Event e = new Event(arrivalTime, arrivalTime + finishTime, a, state,previous.getMobilityType());
                     EventQueue.addEvent(e);
                 }
                // check if reaching the width bounds, then reflect by changing the angle by -(angle). Then calculate the destination from angle.
                // The parameter we know. the displacement along y axis(the intercept) which can now be used to calculate remaining distance.
                else {
                     float y = previous.getStateReference().getDestination().y;
                     float remainingDistance = SimulationParameters.HEIGHT_SIMULATION_AREA - y;
                     float previous_angle_in_degree = previous.getStateReference().getAngleInDegree();
                     float angle_of_reflection = -previous_angle_in_degree;
                     float opposite = GeoMathFunctions.findOppositetUsingAngle(remainingDistance, angle_of_reflection);
                     float x;
                    
                     System.out.println("height" + opposite);
                     x = SimulationParameters.WIDTH_SIMULATION_AREA - Math.abs(opposite);
                       if(x < 0)
                         x = 0;
                     if( x > 100 )
                         x = SimulationParameters.HEIGHT_SIMULATION_AREA;
                     if(previous_angle_in_degree < 90 && previous_angle_in_degree > -90 )
                         y = SimulationParameters.WIDTH_SIMULATION_AREA;
                     else
                         y = 0;
                     destination = new Point2D.Float(x,y);
                     Float velocity = previous.getStateReference().getVelocity();
                     state = new NodeState(a,current,destination,velocity);
                     a.addState(state);
                     float finishTime = GeoMathFunctions.distanceBetWeenTwoPoint(current, destination)/velocity;
                     // add a new event to the EventQueue
                     Event e = new Event(arrivalTime, arrivalTime + finishTime, a, state,previous.getMobilityType());
                     EventQueue.addEvent(e);
                    
                }
            }
     
    }
    
}

// The code here is not working ,  I am planning to write something thats easy and better
