/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;

import java.beans.EventHandler;
import java.util.Comparator;

/**
 *
 * @author sooki
 * 
 */
public class Event implements Comparable<Event>{
    // @arrivalTime. This time determines when the event is scheduled. The lowest arrival time will be scheduled first
     private float arrivalTime;
     private Node nodeReference;
     private float finishTime;
     private NodeState stateReference;
     private HandlerMethodI methodToInovke;
   
    public Event(float arrivalTime,float finishTime, Node nodeReference, NodeState stateReference) {
        this.arrivalTime = arrivalTime;
        this.finishTime = finishTime;
        this.nodeReference = nodeReference;
        this.stateReference = stateReference;
        this.methodToInovke = new HandlerCreateNewEvent();
    }
    
    public HandlerMethodI getMethodToInovke() {
        return methodToInovke;
    }

    public void setMethodToInovke(HandlerMethodI methodToInovke) {
        this.methodToInovke = methodToInovke;
    }

    public float getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(float arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public float getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(float finishTime) {
        this.finishTime = finishTime;
    }

    public NodeState getStateReference() {
        return stateReference;
    }

    public void setStateReference(NodeState stateReference) {
        this.stateReference = stateReference;
    }
    
    public Node getNodeReference() {
        return nodeReference;
    }

    public void setNodeReference(Node nodeReference) {
        this.nodeReference = nodeReference;
    }
    @Override
    public String toString()
    {
        StringBuilder br = new StringBuilder();
        br.append("Node id : ").append(this.getNodeReference().getId()).append(" , Node position").append(this.stateReference.toString()).append("\n");
        return br.toString();
    }
    

    @Override
    public int compareTo(Event o) {
       if( this.getFinishTime()< this.getFinishTime())
           return -1;
       else 
           return 1;
    }
    
}
