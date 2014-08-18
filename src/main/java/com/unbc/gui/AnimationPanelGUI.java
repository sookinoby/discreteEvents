package com.unbc.gui;

// WormPanel.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* The game's drawing surface. It shows:
     - the moving worm
     - the obstacles (blue boxes)
     - the current average FPS and UPS
*/



import com.sun.j3d.utils.timer.J3DTimer;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import com.unbc.core.models.*;
import com.unbc.core.animation.NodeAnimation;
import com.unbc.main.DiscreteEventsMain;
import com.unbc.main.SimulationParameters;


public class AnimationPanelGUI extends JPanel implements Runnable
{
  private static final int PWIDTH = SimulationParameters.WINDOW_SIZE_WIDTH;   // size of panel
  private static final int PHEIGHT = SimulationParameters.WINDOW_SIZE_HEIGHT; 

  private static long MAX_STATS_INTERVAL = 1000000000L;
  // private static long MAX_STATS_INTERVAL = 1000L;
    // record stats every 1 second (roughly)

  private static final int NO_DELAYS_PER_YIELD = 16;
  /* Number of frames with a delay of 0 ms before the animation thread yields
     to other running threads. */

  private static int MAX_FRAME_SKIPS = 5;   // was 2;
    // no. of frames that can be skipped in any one animation loop
    // i.e the games state is updated but not rendered

  private static int NUM_FPS = 10;
     // number of FPS values stored to get an average


  
  
  
  
  // used for gathering statistics
  private long statsInterval = 0L;    // in ns
  private long prevStatsTime;   
  private long totalElapsedTime = 0L;
  private long gameStartTime;
  private int timeSpentInGame = 0;    // in seconds

  private long frameCount = 0;
  private double fpsStore[];
  private long statsCount = 0;
  private double averageFPS = 0.0;

  private long framesSkipped = 0L;
  private long totalFramesSkipped = 0L;
  private double upsStore[];
  private double averageUPS = 0.0;


  private DecimalFormat df = new DecimalFormat("0.##");  // 2 dp
  private DecimalFormat timedf = new DecimalFormat("0.####");  // 4 dp


  private Thread animator;           // the thread that performs the animation
  private boolean running = false;   // used to stop the animation thread
  private boolean isPaused = false;

  private long period;                // period between drawing in _nanosecs_


  private MainJFrameGUI wcTop;
  private Node nodes[];
  private  ArrayList<NodeAnimation> all_node_animation;       // the node
  
 //arraylist of node
//  private Obstacles obs;   // the obstacles


  // used at game termination
  private boolean gameOver = false;
  private int score = 0;
  private Font font;
  private FontMetrics metrics;

  // off screen rendering
  private Graphics dbg; 
  private Image dbImage = null;
  private ContainerBox box;



  public AnimationPanelGUI(MainJFrameGUI wc, long period)
  {
    wcTop = wc;
    this.period = period;
    this.box = ContainerBox.getInstance();
    setBackground(Color.white);
    setPreferredSize( new Dimension(PWIDTH, PHEIGHT));

    setFocusable(true);
    requestFocus();    // the JPanel now has focus, so receives key events
    readyForTermination();
    nodes = DiscreteEventsMain.getAllNodes();
    all_node_animation = new ArrayList<>();
    if(nodes != null)
    {
        for(int i=0;i<nodes.length;i++)
        {
           all_node_animation.add(new NodeAnimation(nodes[i]));
        }
    }
    // create game components
   // obs = new Obstacles(wcTop);
//    nodes = new ArrayList<Node>();
//    single_node = new Node(PWIDTH-PWIDTH/2, PHEIGHT-PHEIGHT/2, 4,5,75,MobilityModelType.RANDOMWAYPOINT);
//    nodes.add(single_node);
//    nodes.add(new Node(PWIDTH-PWIDTH/2, PHEIGHT-PHEIGHT/2, 4,5,75,MobilityModelType.RANDOMWAYPOINT));
//    nodes.add(new Node(PWIDTH-PWIDTH/2, PHEIGHT-PHEIGHT/2, 4,5,75,MobilityModelType.RANDOMWAYPOINT));
//    nodes.add(new Node(PWIDTH-PWIDTH/2, PHEIGHT-PHEIGHT/2, 4,5,75,MobilityModelType.RANDOMWAYPOINT));
   /* addMouseListener( new MouseAdapter() {
      public void mousePressed(MouseEvent e)
      { 
          testPress(e.getX(), e.getY());
      }
    });*/

    // set up message font
    font = new Font("SansSerif", Font.BOLD, 24);
    metrics = this.getFontMetrics(font);

    // initialise timing elements
    fpsStore = new double[NUM_FPS];
    upsStore = new double[NUM_FPS];
    for (int i=0; i < NUM_FPS; i++) {
      fpsStore[i] = 0.0;
      upsStore[i] = 0.0;
    }
  }  // end of WormPanel()



  private void readyForTermination()
  {
	addKeyListener( new KeyAdapter() {
	// listen for esc, q, end, ctrl-c on the canvas to
	// allow a convenient exit from the full screen configuration
       public void keyPressed(KeyEvent e)
       { int keyCode = e.getKeyCode();
         if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q) ||
             (keyCode == KeyEvent.VK_END) ||
             ((keyCode == KeyEvent.VK_C) && e.isControlDown()) ) {
           running = false;
         }
       }
     });
  }  // end of readyForTermination()


  public void addNotify()
  // wait for the JPanel to be added to the JFrame before starting
  { super.addNotify();   // creates the peer
    startGame();         // start the thread
  }


  private void startGame()
  // initialise and start the thread 
  { 
    if (animator == null || !running) {
      animator = new Thread(this);
	  animator.start();
    }
  } // end of startGame()
    

  // ------------- game life cycle methods ------------
  // called by the JFrame's window listener methods


  public void resumeGame()
  // called when the JFrame is activated / deiconified
  {  isPaused = false;  } 


  public void pauseGame()
  // called when the JFrame is deactivated / iconified
  { isPaused = true;   } 


  public void stopGame() 
  // called when the JFrame is closing
  {  running = false;   }

  // ----------------------------------------------


//  private void testPress(int x, int y)
//  // is (x,y) near the head or should an obstacle be added?
//  {
//    if (!isPaused && !gameOver) {
//      if (fred.nearHead(x,y)) {   // was mouse press near the head?
//        gameOver = true;
//        score =  (40 - timeSpentInGame) + (40 - obs.getNumObstacles());    
//            // hack together a score
//      }
//      else {   // add an obstacle if possible
//        if (!fred.touchedAt(x,y))   // was the worm's body untouched?
//          obs.add(x,y);
//      }
//    }
//  }  // end of testPress()


  public void run()
  /* The frames of the animation are drawn inside the while loop. */
  {
    long beforeTime, afterTime, timeDiff, sleepTime;
    long overSleepTime = 0L;
    int noDelays = 0;
    long excess = 0L;

    gameStartTime = J3DTimer.getValue();
    prevStatsTime = gameStartTime;
    beforeTime = gameStartTime;

	running = true;

	while(running) {
	  gameUpdate();
      gameRender();
      paintScreen();

      afterTime = J3DTimer.getValue();
      timeDiff = afterTime - beforeTime;
      sleepTime = (period - timeDiff) - overSleepTime;  

      if (sleepTime > 0) {   // some time left in this cycle
        try {
          Thread.sleep(sleepTime/1000000L);  // nano -> ms
        }
        catch(InterruptedException ex){}
        overSleepTime = (J3DTimer.getValue() - afterTime) - sleepTime;
      }
      else {    // sleepTime <= 0; the frame took longer than the period
        excess -= sleepTime;  // store excess time value
        overSleepTime = 0L;

        if (++noDelays >= NO_DELAYS_PER_YIELD) {
          Thread.yield();   // give another thread a chance to run
          noDelays = 0;
        }
      }

      beforeTime = J3DTimer.getValue();

      /* If frame animation is taking too long, update the game state
         without rendering it, to get the updates/sec nearer to
         the required FPS. */
      int skips = 0;
      while((excess > period) && (skips < MAX_FRAME_SKIPS)) {
        excess -= period;
	    gameUpdate();    // update state but don't render
        skips++;
      }
      framesSkipped += skips;

      }
    System.exit(0);   // so window disappears
  } // end of run()


  private void gameUpdate() 
  { 
     if (!isPaused && !gameOver)
     for(NodeAnimation anim : all_node_animation)
     {
        anim.move();
     }
 
  }  // end of gameUpdate()


  private void gameRender()
  {
    if (dbImage == null){
      dbImage = createImage(PWIDTH, PHEIGHT);
      if (dbImage == null) {
        System.out.println("dbImage is null");
        return;
      }
      else
        dbg = dbImage.getGraphics();
    }
   // draw game elements: the obstacles and the worm
  
    box.draw(dbg);
  
    for(NodeAnimation anim : all_node_animation)
     {
        anim.draw(dbg);
     }
      Destinations.draw(dbg);
    if (gameOver)
      gameOverMessage(dbg);
  }  // end of gameRender()


  private void gameOverMessage(Graphics g)
  // center the game-over message in the panel
  {
    String msg = "Game Over. Your Score: " + score;
	int x = (PWIDTH - metrics.stringWidth(msg))/2; 
	int y = (PHEIGHT - metrics.getHeight())/2;
	g.setColor(Color.red);
        g.setFont(font);
	g.drawString(msg, x, y);
  }  // end of gameOverMessage()


  private void paintScreen()
  // use active rendering to put the buffered image on-screen
  { 
    Graphics g;
    try {
      g = this.getGraphics();
      if ((g != null) && (dbImage != null))
        g.drawImage(dbImage, 0, 0, null);
      g.dispose();
    }
    catch (Exception e)
    { System.out.println("Graphics context error: " + e);  }
  } // end of paintScreen()
}  // end of AnimationPanelGui class
