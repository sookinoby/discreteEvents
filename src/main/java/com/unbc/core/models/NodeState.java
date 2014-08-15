/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;

import com.unbc.utils.GeoMathFunctions;
import com.unbc.utils.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author sooki
 */
public class NodeState {
    
    /**
     *
     */
    public static enum StateType
    {
        ACTIVE,PASSIVE;
    }
    
    private Node nodeReference;
    private Point currentPosition;
    private Destinations destinationInfo;
    private Point destination; 
    private float velocity;
    private float angleInDegree;
    private StateType stateType;
    private float pauseTime;

     
    public NodeState(Node nodeReference, Point currentPosition, Point destination, float velocity,StateType stateType,float pauseTime) {
        this.nodeReference = nodeReference;
        this.currentPosition = currentPosition;
        this.destination = destination;
        this.velocity = velocity;
        this.stateType = stateType;
        this.pauseTime = pauseTime;
    }

    public NodeState(Node nodeReference, Point currentPosition, Point destination, float velocity) {
       this(nodeReference,currentPosition,destination,velocity,StateType.ACTIVE,0);
       angleInDegree = GeoMathFunctions.angleBetweenPointsInDegree(this.currentPosition,this.destination);
    }
     public NodeState(Node nodeReference, Point currentPosition,StateType stateType,float pauseTime ) throws Exception {
       this(nodeReference,currentPosition,new Point(),0.0f,stateType,pauseTime);
       if(stateType == StateType.PASSIVE)
        {
            this.destination.setLocation(currentPosition.x+0.01f, currentPosition.y+0.01f);
            this.velocity = GeoMathFunctions.distanceBetWeenTwoPoint(this.currentPosition, this.destination)/pauseTime;
            this.angleInDegree = 0.0f;
            this.pauseTime = pauseTime;
        }
       else if(stateType == StateType.ACTIVE)
        {
          throw new Exception("Cannot be invoked on active states");
        }
    }
    
     public StateType getStateType() {
        return stateType;
    }

    public void setStateType(StateType stateType) {
        this.stateType = stateType;
    }

    public float getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(float pauseTime) {
        this.pauseTime = pauseTime;
    }
  
    public Node getNodeReference() {
        return nodeReference;
    }

    public void setNodeReference(Node nodereference) {
        this.nodeReference = nodereference;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getAngleInDegree() {
        return angleInDegree;
    }

    public void setAngleInDegree(float angleInDegree) {
        this.angleInDegree = angleInDegree;
    }
  
    @Override
    public String toString ()
    {
        float equatedVelocity =  (velocity < .02) ? 0 : velocity;
        StringBuilder br = new StringBuilder();
        br.append(" current pos : ").append("(").append(Math.floor(currentPosition.x)).append(",").append(Math.floor(currentPosition.y)).append(") ").append(" destination pos : ").append("(").append(Math.floor(destination.x)).append(",").append(Math.floor(destination.y)).append(") ").append(" ,velocity : ").append(equatedVelocity).append(" ,Direction: ").append(angleInDegree);
        return br.toString();
    }
    
}
