/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.utils;

import com.unbc.main.SimulationInit;
import com.unbc.main.SimulationParameters;
import java.awt.geom.Point2D;

/**
 *
 * @author sooki
 */
public class Helper {
    
    public static boolean isNear(float x,float toCheck)
    {
        return Math.abs(x-toCheck) < 0.2;
    }
    
    public static Point normalisePoint(Point point)
    {
        return new Point((float)point.getX() * SimulationParameters.PIXEL_TO_MOVE_X, (float)point.getY() * SimulationParameters.PIXEL_TO_MOVE_Y);
    }
    
}
