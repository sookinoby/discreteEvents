/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.main;

import com.unbc.core.models.Scheduler;

/**
 *
 * @author sooki
 */
public class SimulationInit {
    //Start the process method of scheduler.
    
    public static void startSimulation()
    {
      new Thread(new Scheduler()).start();
    }
    
}
