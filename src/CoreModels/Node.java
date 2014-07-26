/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CoreModels;

/**
 * This class contains attribute of nodes.
 * @author sooki
 */
public class Node {
    // size and number of dots in a worm
  private static final int DOTSIZE = 12;
  private static final int RADIUS = DOTSIZE/2;
  private static final int MAXPOINTS = 40;

  // compass direction/bearing constants
  private static final int NUM_DIRS = 8;
  private static final int N = 0;  // north, etc going clockwise
  private static final int NE = 1;
  private static final int E = 2;
  private static final int SE = 3;
  private static final int S = 4;
  private static final int SW = 5;
  private static final int W = 6;
  private static final int NW = 7;

  private int currCompass;  // stores the current compass dir/bearing
  int id;
  int groupid;
  float transmission_range;
  float currentPositionX, currentPositionY;
  float nextPositionX, nextPositionY;
  float direction; // This is in degree. 
  int velocity; // meters per second
    
}
