/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.main;

import com.unbc.core.animation.AnimationGlobals;
import com.unbc.core.models.Event;
import com.unbc.core.models.EventQueue;
import com.unbc.core.models.MobilityType;
import com.unbc.core.models.Node;
import com.unbc.core.models.NodeState;
import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.unbc.gui.MainJFrameGUI;

/**
 *
 * @author sooki
 */
public class DiscreteEventsMain {
    public static Node nodes[];
    /**
     * @param args the command line arguments
     */
    public static Node[] getAllNodes()
    {
        return nodes;
    }
    public static void main(String[] args) {
     
        // TODO code application logic her
       nodes = new Node[SimulationParameters.NUMBER_OF_NODES];
        for(int i=0; i < SimulationParameters.NUMBER_OF_NODES; i++)
        {
            Node a = new Node("one", i);
            Point2D.Float current = new Point2D.Float(0.0f,0.0f);
            NodeState state;
             try {
                 state = new NodeState(a, current, NodeState.StateType.PASSIVE,1);
                 a.addState(state);
                 nodes[i] = a;
                 Event e = new Event(0,0, a, state,MobilityType.RANDOMWAYPOINT);
                 EventQueue.addEvent(e);
             } catch (Exception ex) {
                 Logger.getLogger(DiscreteEventsMain.class.getName()).log(Level.SEVERE, null, ex);
             }
            
        }
        
    
      SimulationInit.startSimulation();
      while(true)
      {
      if(AnimationGlobals.animationCanRun == true)
      {
          System.out.println("Animation is starting");
          MainJFrameGUI.startAnimation();
          break;
      }
      }
      
      
    }
    
    // this is a testcase for event queue
//    public static void TestPriorityQueueInsertAndRetrival()
//    {
//          Node nodes[] = new Node[SimulationParameters.NUMBER_OF_NODES+1];
//        for(int i=1; i < SimulationParameters.NUMBER_OF_NODES + 1; i++)
//        {
//            Node a = new Node("one", i);
//            Point2D.Float current = RandomGenerator.getRandomPosition();
//            Point2D.Float destination = RandomGenerator.getRandomPosition();
//            Float velocity = RandomGenerator.getVelocityFromNormalDistribution();
//            NodeState state = new NodeState(a,current,destination,velocity,NodeState.StateType.PASSIVE);
//            a.addState(state);
//            nodes[i] = a;
//            Event e = new Event(0,0, a, state);
//            EventQueue.addEvent(e);
//        }
//        Point2D.Float current = RandomGenerator.getRandomPosition();
//            Point2D.Float destination = RandomGenerator.getRandomPosition();
//            Float velocity = RandomGenerator.getVelocityFromNormalDistribution();
//            NodeState state = new NodeState(nodes[1],current,destination,velocity,NodeState.StateType.PASSIVE);
//            nodes[1].addState(state);
//        Event e = new Event(0,250,nodes[1],state);
//        EventQueue.addEvent(e);
//        System.out.println("Testing");
//        System.out.println("The first event has arrival time" + EventQueue.removeNextEvent().getNodeReference().getId() + " ");
//        System.out.println("The second event has arrival time" + EventQueue.removeNextEvent().getFinishTime());
//        System.out.println("The third event has arrival time" + EventQueue.removeNextEvent().getNodeReference().getId());
//    }
//    
    
    /*
    InputStream in = DiscreteEventsMain.class.getResourceAsStream("/newjson.json");
        Reader      reader      = new InputStreamReader(in);

        int data;
        try {
            data = reader.read();
            while(data != -1){
            char theChar = (char) data;
                System.out.println("" + theChar);
             data = reader.read();
             }
        } catch (IOException ex) {
            Logger.getLogger(DiscreteEventsMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
}
