/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;

import com.unbc.main.SimulationParameters;
import java.util.concurrent.PriorityBlockingQueue;

/**
 *
 * @author sooki
 */
public class EventQueue {
    
    private static final PriorityBlockingQueue<Event> EVENT_LIST= new PriorityBlockingQueue<>();
    
    public static boolean addEvent(Event e)
    {
        // allow adding of event when the arrival time is less the length of simulation
        if(e.getFinishTime() < SimulationParameters.TIME_OF_SIMULATION)
        {
            EVENT_LIST.add(e);
            return true;
        }
        else {
            return false;
        }
    }
    
    public static Event removeNextEvent()
    {
        try{
        return EVENT_LIST.poll();
       
        }
        catch(Exception E)
        {
            System.out.println("The Event Queue is empty");
            return null;
        }
        
    }
    
    
    
}
