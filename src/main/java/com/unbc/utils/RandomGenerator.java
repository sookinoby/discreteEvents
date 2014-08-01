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
        
        int x = ran.nextInt(SimulationParameters.WIDTH_SIMULATION_AREA+1);
        ran = new Random();
        int y = ran.nextInt(SimulationParameters.HEIGHT_SIMULATION_AREA+1);
        return new Point2D.Float((float)x, (float)y);
    }
    public static float getVelocityFromNormalDistribution()
    {
       //Random ran = new Random();
        RandomEngine rn = new DRand();
        Normal normal = new Normal(SimulationParameters.VELOCITY_MEAN, SimulationParameters.VELOCITY_VARIANCE, rn);
        float velocity = (float)normal.nextDouble();
        return velocity;
    }
    
    public static Point2D.Float getRandomPositionAlongX()
    {
        Random ran = new Random();
        
        int x = ran.nextInt(SimulationParameters.WIDTH_SIMULATION_AREA+1);
        ran = new Random();
        int y = getRandomNumberBetweenZeroAndX(100);
        if(y % 2 == 0 )
            y = SimulationParameters.HEIGHT_SIMULATION_AREA+1;
        else
            y=0;
        return new Point2D.Float((float)x, (float)y);
    }
        public static Point2D.Float getRandomPositionAlongY()
    {
        Random ran = new Random();
        
        int y = ran.nextInt(SimulationParameters.HEIGHT_SIMULATION_AREA+1);
        ran = new Random();
        int x = getRandomNumberBetweenZeroAndX(100);
        if(x % 2 == 0 )
            y = SimulationParameters.WIDTH_SIMULATION_AREA+1;
        else
            x = 0;
        return new Point2D.Float((float)x, (float)y);
    }
    
     public static int getRandomNumberBetweenZeroAndX(int x)
    {
        Random ran = new Random();
        return ran.nextInt(x);
    }
    
}
