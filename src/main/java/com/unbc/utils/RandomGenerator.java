/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.utils;

import cern.jet.random.Normal;
import cern.jet.random.engine.DRand;
import cern.jet.random.engine.RandomEngine;
import com.unbc.main.SimulationParameters;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 *
 * @author sooki
 */
public class RandomGenerator {
    
    
    public static Point2D.Float getRandomPosition()
    {
        Random ran = new Random();
        
        int x = ran.nextInt(SimulationParameters.WIDTH_SIMULATION_AREA);
        ran = new Random();
        int y = ran.nextInt(SimulationParameters.WIDTH_SIMULATION_AREA);
        return new Point2D.Float((float)x, (float)y);
    }
    public static float getVelocityFromNormalDistribution()
    {
        Random ran = new Random();
        RandomEngine rn = new DRand(ran.nextInt());
        Normal normal = new Normal(SimulationParameters.VELOCITY_MEAN, SimulationParameters.VELOCITY_VARIANCE, rn);
        float velocity = (float)normal.nextDouble();
        return velocity;
    }
    
}
