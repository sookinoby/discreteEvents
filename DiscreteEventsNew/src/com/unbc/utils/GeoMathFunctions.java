/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.utils;

import java.awt.geom.Point2D;

/**
 *
 * @author sooki
 */
public class GeoMathFunctions {
      public static float angleBetweenPointsInDegree(Point2D.Float p, Point2D.Float q)
    {
       float deltaY = q.y - p.y;
       float deltaX = q.x - p.x;
       return (float) Math.toDegrees(Math.atan2(deltaY,deltaX)) ;

    }
      
     public static float distanceBetWeenTwoPoint(Point2D.Float currentPosition, Point2D.Float destination)
    {
        return (float) Point2D.distance(currentPosition.x, currentPosition.y, destination.x,destination.y);

    }
    
}
