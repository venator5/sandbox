/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.City.City;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author The Venator
 */
public class Screen extends JPanel implements Runnable {

    Thread thrd = new Thread(this);
    JPanel bottom = new JPanel(new GridLayout(2, 0));

    /*Image arrays*/
    /*tile images*/
    public static Image[] mimg = new Image[15];

    public static Image[] crep = new Image[2];

    /**
     *
     */
    static boolean paused;
    /*mouse location initialisation*/
    public static Point mou = new Point(0, 0);
    /*first run  boolean*/
    public Boolean fir = true;
    /*window height and width integers*/
    public static int w, h, w2, h2;

boolean rbo = true;    
    public static Map mp;
    public static Navigation navi;
  
    /*statinf thread and adding inputs*/

    public Screen(Frame frame) {
        frame.addMouseListener(new Kyhndlr());
        frame.addMouseMotionListener(new Kyhndlr());
        frame.addKeyListener(new Kyhndlr());
        frame.addMouseWheelListener(new Kyhndlr());
        thrd.start();
    }
    /*Refresh*/

    public void run() {
        while (true) {
            repaint();
            try {
                thrd.sleep(15);
            } catch (Exception e) {
            }
        }
    }


    /*main graphic section*/
    public void paintComponent(Graphics g) {

        if (fir) {
            w = getWidth();
            h = getHeight();
            try {
                /*defines all graphics*/
                define();
            } catch (IOException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
            fir = false;
    paused = false;
        }
        /*sets background to black*/
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, getWidth(), getHeight());
        /*for live window size update*/
        w2 = getWidth();
        h2 = getHeight();
        /*calls clock*/
if(!paused){
            City.timer();
}
        /*draws tilemap*/
        mp.draw(g);
        navi.draw(g);
        /*draws main menu and stats*/
        /*draws build menu*/

        
   }
   
    public void define() throws IOException {
        mp = new Map();
        
        navi = new Navigation();
     
Load.Load(1);
        /*loads tile images into array*/
        for (int i = 0; i < mimg.length; i++) {
            mimg[i] = new ImageIcon("src/sause/ti2.png").getImage();
            mimg[i] = createImage(new FilteredImageSource(mimg[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));

        }
         for (int i = 0; i < crep.length; i++) {
            crep[i] = new ImageIcon("src/sause/creatures.png").getImage();
            crep[i] = createImage(new FilteredImageSource(crep[i].getSource(), new CropImageFilter(0, 16 * i, 16, 16)));

        }
        /*loads map from file*/
        /*writes each tile info to tile info array*/
         
        for (int i = 0; i < City.lnd.length; i++) {

            for (int x = 0; x < City.lnd[0].length; x++) {
                City.lnd[i][x] = new object();
                City.lnd[i][x] = City.sqr[Map.block[i][x].id];
             City.lnd[i][x].mx = i;
                     City.lnd[i][x].my =x; 
                /*City.file(i,x,temp);*/
                     
            }

        }
    }

}
