/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;

import com.unbc.utils.GeoMathFunctions;
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
    private Point2D.Float currentPosition;
    private Point2D.Float destination;
    private float velocity;
    private float angleInDegree;
    private StateType stateType;

    
    private float pauseTime;

    public NodeState(Node nodeReference, Point2D.Float currentPosition, Point2D.Float destination, float velocity,StateType stateType) {
        this.nodeReference = nodeReference;
        this.currentPosition = currentPosition;
        this.destination = destination;
        this.velocity = velocity;
        this.stateType = stateType;
        
    }

    public NodeState(Node nodeReference, Point2D.Float currentPosition, Point2D.Float destination, float velocity) {
       this(nodeReference,currentPosition,destination,velocity,StateType.ACTIVE);
       angleInDegree = GeoMathFunctions.angleBetweenPointsInDegree(this.destination, this.currentPosition);
    }
     public NodeState(Node nodeReference, Point2D.Float currentPosition,StateType stateType,float pauseTime ) throws Exception {
       this(nodeReference,currentPosition,new Point2D.Float(),0.0f,stateType);
       if(stateType == StateType.PASSIVE)
        {
            this.destination.setLocation(currentPosition.x+0.01, currentPosition.y+0.01);
            this.velocity =GeoMathFunctions.distanceBetWeenTwoPoint(this.currentPosition, this.destination)/pauseTime;
            this.angleInDegree = 0.0f;
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

    public Point2D.Float getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point2D.Float currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Point2D.Float getDestination() {
        return destination;
    }

    public void setDestination(Point2D.Float destination) {
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
        br.append(" current postion : ").append(currentPosition.toString()).append(" ,velocity : ").append(equatedVelocity).append(" ,Direction: ").append(angleInDegree);
        return br.toString();
    }
    
}
