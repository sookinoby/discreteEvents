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
      public static float angleBetweenPointsInDegree(Point p, Point q)
    {
       float deltaY = q.y - p.y;
       float deltaX = q.x - p.x;
       return (float) Math.toDegrees(Math.atan2(deltaY,deltaX)) ;

    }
      
    public static float distanceBetWeenTwoPoint(Point currentPosition, Point destination)
    {
        return (float) Point2D.distance(currentPosition.x, currentPosition.y, destination.x,destination.y);

    }
    
        public static float findOppositetUsingAngle(float adjacent_side, float AngleInDegrees)
    {
        return (float) Math.tan(Math.toRadians(AngleInDegrees)) * adjacent_side;

    }
        
     public static float findHypotenuseUsingAdjacentAngel(float adjacent_side, float AngleInDegrees)
    {
        return (float) Math.cos(Math.toRadians(AngleInDegrees)) * adjacent_side;
        

    }
    public static float findOppositeGivenAdjacent(float adjacent_side, float AngleInDegrees)
    {
        return (float) Math.tan(Math.toRadians(AngleInDegrees)) * adjacent_side;
        

    }
      public static float findAdjacentUsingOpposite(float oppositeSide, float AngleInDegrees)
    {
        return (float) ((float)  oppositeSide / Math.tan(Math.toRadians(AngleInDegrees))) ;
        

    }
}
