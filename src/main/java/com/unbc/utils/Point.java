/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.utils;

/**
 *
 * @author chellads
 */
public class Point {

    public float x;
    public float y;
    public Point()
    {
      
    }
    public Point(float x,float y ) {
        this.x=x;
        this.y=y;
    }
    public void setLocation(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
        @Override
    public String toString() {
        return "( " + x + "," + y +" )";
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
  
    
}
