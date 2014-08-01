/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;

/**
 *
 * @author sooki
 */
public class Scheduler implements Runnable{
    
    volatile Boolean shouldRun = true;
    @Override
    public void run() {
        while(shouldRun)
    // pick up a event from event queue;
        {
        Event E = EventQueue.removeNextEvent();
     //process the event by call its handler method
        if(null != E)
        {
         System.out.println(" "+ E.toString());
        E.getMethodToInovke().handler(E);
        }
        else {
            shouldRun = false;
            System.out.println("The simulation has ended, no more Events to Process");
        }
       
        }
    }
   
    
    
}
