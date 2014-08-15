/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;


import com.unbc.main.SimulationParameters;
import com.unbc.utils.GeoMathFunctions;
import com.unbc.utils.Helper;
import com.unbc.utils.Point;
import com.unbc.utils.RandomGenerator;


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
            Point current = previous.getStateReference().getDestination();
            NodeState state;
            Point destination = null;
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
            destination = new Point(20,100);
            Float velocity = RandomGenerator.getVelocityFromNormalDistribution();
            state = new NodeState(a,current,destination,velocity);
            a.addState(state);
            float finishTime = GeoMathFunctions.distanceBetWeenTwoPoint(current, destination)/velocity;
            // add a new event to the EventQueue
            Event e = new Event(arrivalTime, arrivalTime + finishTime, a, state,previous.getMobilityType());
            EventQueue.addEvent(e);
            }
            else {
                  float current_x = (float)previous.getStateReference().getDestination().getX();
                  float current_y = (float)previous.getStateReference().getDestination().getY();
                // this code is for part where y bound is reached
                if( Helper.isNear(current_y, 0))
                {
                     float x = previous.getStateReference().getDestination().x;
                     float remainingDistance = SimulationParameters.WIDTH_SIMULATION_AREA - x;
                     float previous_angle_in_degree = previous.getStateReference().getAngleInDegree();
                     // check if mobile node is going up and moving toward first qudarant. Please remember the quadrant is inverted
                     float adjacentX;
                     if(previous_angle_in_degree > -90 && previous_angle_in_degree < 0)
                     {
                          float abs_previous_angle = Math.abs(previous_angle_in_degree);
                          float opposite = GeoMathFunctions.findOppositetUsingAngle(remainingDistance, abs_previous_angle);
                          // now check if opposite is too big.
                          if(opposite > SimulationParameters.HEIGHT_SIMULATION_AREA)
                          {   
                            adjacentX = GeoMathFunctions.findAdjacentUsingOpposite(SimulationParameters.HEIGHT_SIMULATION_AREA, abs_previous_angle);
                            destination = new Point(x+adjacentX,SimulationParameters.HEIGHT_SIMULATION_AREA);
                          }
                          else{
                                destination = new Point(SimulationParameters.WIDTH_SIMULATION_AREA,opposite);
                          }
                     }
                        // check if mobile node is going up and moving toward second qudarant. Please remember the quadrant is inverted
                     else if  (previous_angle_in_degree <= -90 && previous_angle_in_degree >= -180)
                     {
                           float abs_previous_angle = Math.abs(previous_angle_in_degree);
                           float calculated = 180 - abs_previous_angle;
                          float opposite = GeoMathFunctions.findOppositetUsingAngle(remainingDistance, calculated);
                          // now check if opposite is too big.
                          if(opposite > SimulationParameters.HEIGHT_SIMULATION_AREA)
                          {   
                            adjacentX = GeoMathFunctions.findAdjacentUsingOpposite(SimulationParameters.HEIGHT_SIMULATION_AREA, calculated);
                            destination = new Point(x-adjacentX,SimulationParameters.HEIGHT_SIMULATION_AREA);
                          }
                          else{
                                destination = new Point(0,opposite);
                          }
                     }
                          
                }// this code is for part where y bound height is reached
                else if( Helper.isNear(current_y, SimulationParameters.HEIGHT_SIMULATION_AREA))
                {
                     float x = previous.getStateReference().getDestination().x;
                     float remainingDistance = SimulationParameters.WIDTH_SIMULATION_AREA - x;
                     float previous_angle_in_degree = previous.getStateReference().getAngleInDegree();
                     // check if mobile node is going up and moving toward first qudarant. Please remember the quadrant is inverted
                     float adjacentX;
                     if(previous_angle_in_degree > 0 && previous_angle_in_degree < 90)
                     {
                          float abs_previous_angle = Math.abs(previous_angle_in_degree);
                         
                          float opposite = GeoMathFunctions.findOppositetUsingAngle(remainingDistance, abs_previous_angle);
                          // now check if opposite is too big.
                          if(opposite > SimulationParameters.HEIGHT_SIMULATION_AREA)
                          {   
                            adjacentX = GeoMathFunctions.findAdjacentUsingOpposite(SimulationParameters.HEIGHT_SIMULATION_AREA, abs_previous_angle);
                            destination = new Point(x+adjacentX,0);
                          }
                          else{
                                destination = new Point(SimulationParameters.WIDTH_SIMULATION_AREA,SimulationParameters.HEIGHT_SIMULATION_AREA-opposite);
                          }
                     }
                        // check if mobile node is going up and moving toward second qudarant. Please remember the quadrant is inverted
                     else if  (previous_angle_in_degree <= 90 && previous_angle_in_degree >= 180)
                     {
                          float abs_previous_angle = Math.abs(previous_angle_in_degree);
                          float calculated = 180 - abs_previous_angle;
                          float opposite = GeoMathFunctions.findOppositetUsingAngle(remainingDistance, calculated);
                          // now check if opposite is too big.
                          if(opposite > SimulationParameters.HEIGHT_SIMULATION_AREA)
                          {   
                            adjacentX = GeoMathFunctions.findAdjacentUsingOpposite(SimulationParameters.HEIGHT_SIMULATION_AREA, calculated);
                            destination = new Point(x-adjacentX,0);
                          }
                          else{
                                destination = new Point(SimulationParameters.HEIGHT_SIMULATION_AREA-opposite,0);
                          }
                     }
                          
                }
                 if( Helper.isNear(current_x, 0))
                {
                     float x = previous.getStateReference().getDestination().x;
                     float y = previous.getStateReference().getDestination().y;
                     float remainingDistance = SimulationParameters.HEIGHT_SIMULATION_AREA - y;
                     float previous_angle_in_degree = previous.getStateReference().getAngleInDegree();
                     // check if mobile node is going up and moving toward first qudarant. Please remember the quadrant is inverted
                     float adjacentY;
                     if(previous_angle_in_degree > 90 && previous_angle_in_degree < 180)
                     {
                          float abs_previous_angle = Math.abs(previous_angle_in_degree);
                          float calculated = 180 - abs_previous_angle;
                          float opposite = GeoMathFunctions.findOppositetUsingAngle(remainingDistance, calculated);
                          // now check if opposite is too big.
                          if(opposite > SimulationParameters.WIDTH_SIMULATION_AREA)
                          {   
                            adjacentY = GeoMathFunctions.findAdjacentUsingOpposite(SimulationParameters.WIDTH_SIMULATION_AREA, calculated);
                            destination = new Point(SimulationParameters.WIDTH_SIMULATION_AREA,y+adjacentY);
                          }
                          else{
                                destination = new Point(opposite,SimulationParameters.HEIGHT_SIMULATION_AREA);
                          }
                     }
                        // check if mobile node is going up and moving toward second qudarant. Please remember the quadrant is inverted
                     else if  (previous_angle_in_degree <= -90 && previous_angle_in_degree >= -180)
                     {
                           float abs_previous_angle = Math.abs(previous_angle_in_degree);
                           float calculated = 180 - abs_previous_angle;
                          float opposite = GeoMathFunctions.findOppositetUsingAngle(remainingDistance, calculated);
                          // now check if opposite is too big.
                          if(opposite > SimulationParameters.WIDTH_SIMULATION_AREA)
                          {   
                            adjacentY = GeoMathFunctions.findAdjacentUsingOpposite(SimulationParameters.WIDTH_SIMULATION_AREA, calculated);
                            destination = new Point(SimulationParameters.HEIGHT_SIMULATION_AREA,y-adjacentY);
                          }
                          else{
                                destination = new Point(opposite,SimulationParameters.HEIGHT_SIMULATION_AREA);
                          }
                     }
                          
                }
                else if( Helper.isNear(current_x, SimulationParameters.WIDTH_SIMULATION_AREA))
                {
                     float y = previous.getStateReference().getDestination().x;
                     float remainingDistance = SimulationParameters.HEIGHT_SIMULATION_AREA - y;
                     float previous_angle_in_degree = previous.getStateReference().getAngleInDegree();
                     // check if mobile node is going up and moving toward first qudarant. Please remember the quadrant is inverted
                     float adjacentY;
                     if(previous_angle_in_degree > 0 && previous_angle_in_degree < 90)
                     {
                          float abs_previous_angle = Math.abs(previous_angle_in_degree);
                         
                          float opposite = GeoMathFunctions.findOppositetUsingAngle(remainingDistance, abs_previous_angle);
                          // now check if opposite is too big.
                          if(opposite > SimulationParameters.HEIGHT_SIMULATION_AREA)
                          {   
                            adjacentY = GeoMathFunctions.findAdjacentUsingOpposite(SimulationParameters.HEIGHT_SIMULATION_AREA, abs_previous_angle);
                            destination = new Point(0,y + adjacentY);
                          }
                          else{
                                destination = new Point(opposite,SimulationParameters.HEIGHT_SIMULATION_AREA);
                          }
                     }
                        // check if mobile node is going up and moving toward second qudarant. Please remember the quadrant is inverted
                     else if  (previous_angle_in_degree > -90 && previous_angle_in_degree < 0)
                     {
                          float abs_previous_angle = Math.abs(previous_angle_in_degree);
                          float calculated = 180 - abs_previous_angle;
                          float opposite = GeoMathFunctions.findOppositetUsingAngle(remainingDistance, calculated);
                                                  // now check if opposite is too big.
                          if(opposite > SimulationParameters.HEIGHT_SIMULATION_AREA)
                          {   
                            adjacentY = GeoMathFunctions.findAdjacentUsingOpposite(SimulationParameters.HEIGHT_SIMULATION_AREA, calculated);
                            destination = new Point(0,y-adjacentY);
                          }
                          else{
                                destination = new Point(0,SimulationParameters.WIDTH_SIMULATION_AREA-opposite);
                          }
                     }
                          
                }
                
            if(null != destination)
             {
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
    
}

// The code here is not working ,  I am planning to write something thats easy and better
/*

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
                     float adjacent_side;
                     float y;
                    
                     opposite = Math.abs(opposite);
                     if(opposite > SimulationParameters.HEIGHT_SIMULATION_AREA)
                     {
                         // this means the angle was too steep and incerpt is not at the x axis (right side). 
                         float adjacent_angle = 90.0f - Math.abs(angle_of_reflection);
                         adjacent_side = GeoMathFunctions.findOppositeGivenAdjacent(SimulationParameters.HEIGHT_SIMULATION_AREA, adjacent_angle);
                         
                     }
                     y = SimulationParameters.HEIGHT_SIMULATION_AREA - opposite;
                     
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
*/