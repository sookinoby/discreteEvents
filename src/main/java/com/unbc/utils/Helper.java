/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.utils;

/**
 *
 * @author sooki
 */
public class Helper {
    
    public static boolean isNear(float x,float toCheck)
    {
        return Math.abs(x-toCheck) < 0.2;
    }
    
}
