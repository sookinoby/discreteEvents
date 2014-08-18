/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.main;

/**
 *
 * @author sooki
 */
public class SimulationParameters {
    
    public static final int NUMBER_OF_NODES = 1;
    public static final float TIME_OF_SIMULATION = 2000;
    public static final int HEIGHT_SIMULATION_AREA = 800;
    public static final int WIDTH_SIMULATION_AREA = 800;
    public static final float VELOCITY_MEAN = 7f;
    public static final int VELOCITY_VARIANCE = 0;
    public static final int WINDOW_SIZE_WIDTH = 600;
    public static final int WINDOW_SIZE_HEIGHT = 600;
    public static final float PIXEL_TO_MOVE_X = (float)WINDOW_SIZE_WIDTH /(float)(WIDTH_SIMULATION_AREA+100); // the amount of distance that the node should move in animation in X distance. 
    public static final float PIXEL_TO_MOVE_Y = (float)WINDOW_SIZE_HEIGHT/(float)(HEIGHT_SIMULATION_AREA+100); // I am mapping the animation panel to Simulation window
}
