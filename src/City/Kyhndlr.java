/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.City.mapsizex;
import static City.Map.Bsize;
import static City.Map.block;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author The Venator
 */
public class Kyhndlr implements MouseMotionListener, MouseListener, KeyListener, MouseWheelListener {

    public static int cx = 0, cy = 0;
int sftx=0;
int sfty=0;
    public static City ct = new City();

    @Override
    public void mouseMoved(MouseEvent me) {
        /*updates mouse location*/
        Screen.mou = new Point((me.getX() - ((Screen.w2 - Screen.w2) / 2) - 8), (me.getY() - ((Screen.h2 - Screen.h2) / 2) - 32));

    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseClicked(MouseEvent me) {

    }

    public void mouseExited(MouseEvent me) {
    }

    public void mousePressed(MouseEvent e) {
        /*Deals with mouse click and tranfers it*/

        try {
            Screen.mp.click(e.getButton());
        } catch (IOException ex) {
            Logger.getLogger(Kyhndlr.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*moves map in oppesite direction of arrow by one tile*/
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                cy += Map.Bsize;
                sfty += Map.Bsize;
                break;
            case KeyEvent.VK_DOWN:
                cy -= Map.Bsize;
                sfty -= Map.Bsize;
                break;
            case KeyEvent.VK_LEFT:
                cx += Map.Bsize;
                sftx += Map.Bsize;
                break;
            case KeyEvent.VK_RIGHT:
                cx -= Map.Bsize;
sftx -= Map.Bsize;
                break;

            case KeyEvent.VK_SPACE:
                City.pause();
                break;
                case KeyEvent.VK_1:
        {
            try {
                Load.Load(1);
            } catch (IOException ex) {
                Logger.getLogger(Kyhndlr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                    break;
                    
                              case KeyEvent.VK_2:
        {
            try {
                Load.Load(2);
            } catch (IOException ex) {
                Logger.getLogger(Kyhndlr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                    break;
                                            case KeyEvent.VK_3:
        {
            try {
                Load.Load(3);
            } catch (IOException ex) {
                Logger.getLogger(Kyhndlr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                    break;
        }
        
        ct.move(cx, cy);

    }

    ;

    @Override
    /*zoom function*/
    public void mouseWheelMoved(MouseWheelEvent e) {
        int steps = e.getWheelRotation();
   
        Map.Bsize += 4 * -steps;
        /*zoom limits*/
       
        
        if (Bsize > 128) {
            Bsize = 128;
        } else if (Bsize < 8) {
            Bsize = 8;
        }
        /*redraws tile map*/
        for (int i = 0; i < block.length; i++) {
            for (int x = 0; x < block[0].length; x++) {
int q =((x * Bsize)+((Screen.w2/2)-(mapsizex*Bsize)/2))+sftx;
int w =((i * Bsize)+((Screen.h2/2)-(mapsizex*Bsize)/2))+sfty;
                block[i][x] = new Block(q, w, Bsize, Bsize, block[i][x].id);
            }
        }

    }

}
