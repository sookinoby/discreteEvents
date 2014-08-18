/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unbc.main.SimulationParameters;
import com.unbc.utils.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chellads
 */
public class Destinations {
    
    private int id;
   
    private String destinationName;
    private int waitTime;
    private boolean isHome;
    private int probability[];
    private Point position;
    private static List<Destinations> ListAllDestination;
    public static void initialiseDestinationFromJson(){
		Gson gson = new Gson();
                try(Reader reader = new InputStreamReader(Destinations.class.getResourceAsStream("/Destinations.json"), "UTF-8")){
//            Gson gson = new GsonBuilder().create();
//            Person p = gson.fromJson(reader, Person.class);
//            System.out.println(p);
               
               Type type = new TypeToken<List<Destinations>>(){}.getType();
               ListAllDestination = gson.fromJson(reader, type); 	
	                
     }
     catch (Exception ex) {
       Logger.getLogger(Destinations.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }	
	

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }



    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public boolean isIsHome() {
        return isHome;
    }

    public void setIsHome(boolean isHome) {
        this.isHome = isHome;
    }

    public int[] getProbablity() {
        return probability;
    }

    public void setProbablity(int[] probablity) {
        this.probability = probablity;
    }

    public int[] getProbability() {
        return probability;
    }

    public void setProbability(int[] probability) {
        this.probability = probability;
    }

    public static List<Destinations> getListAllDestination() {
        return ListAllDestination;
    }
    
    public static void draw(Graphics g) {
        Font  font = new Font("SansSerif", Font.BOLD,10);
        for(Destinations d : ListAllDestination)
        {
            float x = (int)d.getPosition().getX() * SimulationParameters.PIXEL_TO_MOVE_X;
            float y = (int)d.getPosition().getY() * SimulationParameters.PIXEL_TO_MOVE_Y;
            g.setColor(Color.YELLOW);
            g.fillOval((int)x,(int)y,10,10);
            g.setFont(font);
            g.drawString(d.getDestinationName(),(int)x ,(int)y-5);
        }
   }

}
