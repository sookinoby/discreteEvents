/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.animation;

import com.unbc.core.models.*;
import com.unbc.main.SimulationParameters;
import com.unbc.utils.GeoMathFunctions;
import com.unbc.utils.Helper;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author sooki
 */
public class NodeAnimation {
   
   private  int counter_of_states = 0; 
   private  int total_number_states = 0;
   private Node reference;
   private ArrayList<NodeState> allStates;
   private float x, y;           // Node's state (associated with 'a' state)
   private float speedX, speedY; // Node's speedX,SpeedY (associated with 'a' state)
   private float radius;         // Node's Radius (associated with 'a' state)
   private float angleInDegree;  // Node's angle in degree (associated with 'a' state)
   private NodeState.StateType stateType;
   private float speed;
   protected Color color;  // Ball's color
   protected static Color DEFAULT_COLOR = Color.RED;
   private Point2D.Float destination;
   private float pauseTime;
   boolean record = false;
   long start = 0 ;
   /**
    * Constructor: For user friendliness, user specifies velocity in speed and
    * moveAngle in usual Cartesian coordinates. Need to convert to speedX and
    * speedY in Java graphics coordinates for ease of operation.
     * @param reference
     * @param  color
    */
   public NodeAnimation(Node reference,Color color) {
      this.reference = reference;
      this.allStates = reference.getAllStates();
      this.color = color;
      this.radius = 4;
      total_number_states = this.allStates.size();
      getNewPositonAndSpeed();
     
    // System.out.println("mobility set" + moveType);
   }
   /** Constructor with the default color
     * @param reference */
   public NodeAnimation(Node reference) {
      this(reference,DEFAULT_COLOR);
   }
  
   /** Draw itself using the given graphics context. */
   public void draw(Graphics g) {
      g.setColor(color);
      g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
   
      g.setColor(Color.white);
      g.fillOval((int)destination.getX(),(int)destination.getY(),10,10);
   
   }
   
   /** 
    * Make one move, check for collision and react accordingly if collision occurs.
    * 
    * @param box: the container (obstacle) for this ball. 
    */
   
//   public void move() {
//      // Get the ball's bounds, offset by the radius of the ball
//       if(moveType == MobilityModelType.BOUNDARY)
//       {
//          movement.move();
//       }
//       else if(moveType == MobilityModelType.RANDOMWAYPOINT)
//       {
//           movement.move();
//       }
//   }
    
   /** Return the magnitude of speed. */
   public float getSpeed() {
      return (float)Math.sqrt(speedX * speedX + speedY * speedY);
   }
   
   /** Return the direction of movement in degrees (counter-clockwise). */
   public float getMoveAngle() {
      return (float)Math.toDegrees(Math.atan2(-speedY, speedX));
   }
   
   /** Return mass */
   public float getMass() {
      return radius * radius * radius / 1000f;  // Normalize by a factor
   }
   
   /** Return the kinetic energy (0.5mv^2) */
   public float getKineticEnergy() {
      return 0.5f * getMass() * (speedX * speedX + speedY * speedY);
   }
  
   /** Describe itself. */
   public String toString() {
      sb.delete(0, sb.length());
      formatter.format("@(%3.0f,%3.0f) r=%3.0f V=(%2.0f,%2.0f) " +
            "S=%4.1f \u0398=%4.0f KE=%3.0f", 
            x, y, radius, speedX, speedY, getSpeed(), getMoveAngle(),
            getKineticEnergy());  // \u0398 is theta
      return sb.toString();
   }
   // Re-use to build the formatted string for toString()
   private StringBuilder sb = new StringBuilder();
   private Formatter formatter = new Formatter(sb);
   
  
    public void move() {
        //System.out.println("reached here");
        float distance_left = (float) Point2D.distance(x,y, destination.getX(), destination.getY());
        //System.out.println("angle" + node.angleInDegree);
       if(NodeState.StateType.PASSIVE == this.stateType)
       {
         
        if(record == false)
        {
            record = true;
            start = System.nanoTime();
        }
        else {
             long current = System.nanoTime();
             long difference = current-start;
               x += 0;
               y += 0;
              long diff = TimeUnit.SECONDS.convert(difference, TimeUnit.NANOSECONDS);
             if( diff > this.pauseTime)
             {
                 getNewPositonAndSpeed();
                 record = false;
             }
        }
       }
       else{
        x += speedX;
        y += speedY;
        
        
        float distance_left_after = (float) Point2D.distance(x,y, destination.getX(), destination.getY());
        if(distance_left < distance_left_after)
        {
            getNewPositonAndSpeed();
        }
           
//       if(distance_left < distance_left_after)
//       {
//          // System.out.println("distance_left"+ distance_left);
//         //  System.out.println("distance_left_after" + distance_left_after);
//          // getNewPositonAndSpeed();
//       }
       }
    }
 
    private void getNewPositonAndSpeed()
    {
     //   System.out.println("counter of states " + counter_of_states);
     //   System.out.println("total number of states "  + total_number_states);
     if(counter_of_states < total_number_states)
     {
       
      this.x = this.allStates.get(counter_of_states).getCurrentPosition().x * SimulationParameters.PIXEL_TO_MOVE_X ;
      this.y = this.allStates.get(counter_of_states).getCurrentPosition().y * SimulationParameters.PIXEL_TO_MOVE_Y;
      
      this.destination = this.allStates.get(counter_of_states).getDestination();
      this.destination = Helper.normalisePoint(destination);
      this.speed = this.allStates.get(counter_of_states).getVelocity();
      this.angleInDegree = GeoMathFunctions.angleBetweenPointsInDegree(new Point2D.Float(x, y), destination);
      this.speedX = (float)(this.speed * Math.cos(Math.toRadians(this.angleInDegree ))) * SimulationParameters.PIXEL_TO_MOVE_X ;
         System.out.println("speed" + speedX);
      this.speedY = (float)(this.speed * (float)Math.sin(Math.toRadians(this.angleInDegree ))) * SimulationParameters.PIXEL_TO_MOVE_Y;
      this.stateType = this.allStates.get(counter_of_states).getStateType();
      if(this.stateType == NodeState.StateType.PASSIVE)
      {
          this.pauseTime = this.allStates.get(counter_of_states).getPauseTime();
      }
        counter_of_states++;     
     }
     else {
         this.x = x;
         this.y = y;
         this.speed  = 0;
         this.speedX = 0;
         this.speedY =  0;
     }
    
    }
        
        
    }
    
  
    

