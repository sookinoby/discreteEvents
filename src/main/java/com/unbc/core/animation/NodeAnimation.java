/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Formatter;
import com.unbc.core.models.*;
import com.unbc.utils.GeoMathFunctions;
import java.util.ArrayList;

/**
 *
 * @author sooki
 */
public class NodeAnimation {
   
   private static int counter_of_states = 0; 
   private static int total_number_states = 0;
   private Node reference;
   private ArrayList<NodeState> allStates;
   private float x, y;           // Ball's center x and y (package access)
   private float speedX, speedY; // Ball's speed per step in x and y (package access)
   private float radius;         // Ball's radius (package access)
   private float angleInDegree;
   private float speed;
   protected Color color;  // Ball's color
   protected static Color DEFAULT_COLOR = Color.RED;
   private Point2D.Float destination;
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
       System.out.println("total number of states" + total_number_states);
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
        System.out.println("distanc left" + distance_left);
        x += speedX;
        y += speedY;
        if (distance_left < 5)
        {
           getNewPositonAndSpeed();
         
         }
        
    }
 
    private void getNewPositonAndSpeed()
    {
        System.out.println("counter of states " + counter_of_states);
        System.out.println("total number of states "  + total_number_states);
     if(counter_of_states < total_number_states)
     {
      this.x = this.allStates.get(counter_of_states).getCurrentPosition().x;
      this.y = this.allStates.get(counter_of_states).getCurrentPosition().y;
      this.destination = this.allStates.get(counter_of_states).getDestination();
      this.speed = this.allStates.get(counter_of_states).getVelocity();
      this.angleInDegree = GeoMathFunctions.angleBetweenPointsInDegree(new Point2D.Float(x, y), destination);
      this.speedX = (float)(this.speed * Math.cos(Math.toRadians(this.angleInDegree )));
      this.speedY = (float)(this.speed * (float)Math.sin(Math.toRadians(this.angleInDegree )));
         System.out.println("current position is (" + x + "," + y + ") destination (" + destination.x + "," + destination.y +")" );
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
    
  
    

