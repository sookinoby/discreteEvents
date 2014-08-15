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

    private float x;
    private float y;
    public Point()
    {
      
    }
    public Point(float x,float y ) {
        this.x=x;
        this.y=y;
    }
        @Override
    public String toString() {
        return "( " + x + "," + y +" )";
    }
  
    
}
