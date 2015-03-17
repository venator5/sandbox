/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.City.City;
import static City.City.lherb;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

/**
 *
 * @author The Venator
 */
public class Map {

    public static int Bsize = 48;
    public static int Bsize2 = 48;
    City ct = new City();
    /*creates array for displaying tiles*/
    public static Block[][] block;
    public static int cstat = 0;

    public Map() {
        define();
    }

    public void click(int mouseButton) throws IOException {

        if (mouseButton == 1) {
             if (Screen.navi.paus[0].contains(Screen.mou.x, Screen.mou.y)) {
              
                if (Screen.paused) {
                    Screen.paused = false;

                } else {
                    Screen.paused = true;

                } 
            }else{

            for (int i = 0; i < block.length; i++) {
                for (int x = 0; x < block[0].length; x++) {

                    if (block[i][x].contains(Screen.mou.x, Screen.mou.y)) {
                        for (int n = 0; n < lherb.size(); n++) {
                            if (lherb.get(n).cx == x && lherb.get(n).cy == i) {
                               
                                cstat = n;
                                
                            }

                        }

                        break;
                    }
                }
            }
             }
        }
        if (mouseButton == 3) {
            for (int i = 0; i < block.length; i++) {
                for (int x = 0; x < block[0].length; x++) {

                    if (block[i][x].contains(Screen.mou.x, Screen.mou.y)) {
                        City.newherb(x, i);
                        break;
                    }
                }
            }

        }
    }

    public void define() {

        block = new Block[City.mapsizex][City.mapsizey];
        /*defines each block*/
        for (int i = 0; i < block.length; i++) {
            for (int x = 0; x < block[0].length; x++) {

                block[i][x] = new Block(x * Bsize, i * Bsize, Bsize, (Bsize), 0);
            }
        }

    }

    public static boolean treset = true;
    Node cheese, cheesey, start,two;

    public void draw(Graphics g) {

        /*call draw function for each tile*/
        for (int i = 0; i < block.length; i++) {

            for (int x = 0; x < block[0].length; x++) {

                block[i][x].draw(g);

            }
        }

        for (int n = 0; n < lherb.size(); n++) {
            for (int x = 0; x < City.hmem.get(n).size() - 1; x++) {
                g.setColor(Color.black);
                cheese = (Node) City.hmem.get(n).get(x);
               
                                two = (Node) City.hmem.get(n).get(City.hmem.get(n).size() - 2);
                cheesey = (Node) City.hmem.get(n).get(x + 1);
                g.drawLine(block[cheese.y][cheese.x].x + Bsize / 2, block[cheese.y][cheese.x].y + Bsize / 2, block[cheesey.y][cheesey.x].x + Bsize / 2, block[cheesey.y][cheesey.x].y + Bsize / 2);

            }
            
            
            if(City.hmem.get(n).size()>0){
            if (lherb.get(n).treset) {
                             lherb.get(n).ax = (int)(block[lherb.get(n).oy][lherb.get(n).ox].x + Bsize / 4) ;
                lherb.get(n).ay =  (int)(block[lherb.get(n).oy][lherb.get(n).ox].y + Bsize / 4);
                               g.drawImage(Screen.crep[0],(block[lherb.get(n).cy][lherb.get(n).cx].x + Bsize / 4),(block[lherb.get(n).cy][lherb.get(n).cx].y + Bsize / 4),Map.Bsize / 2, Map.Bsize / 2, null);

   
                
         
              
                lherb.get(n).treset = false;
            } else {
                 start = (Node) City.hmem.get(n).get(City.hmem.get(n).size() - 1);
                g.drawImage(Screen.crep[0],
                        (int)(lherb.get(n).ax+((block[lherb.get(n).cy][lherb.get(n).cx].x + Bsize / 4)-lherb.get(n).ax)* ((float)City.t /(float) lherb.get(n).md)),
                        (int)(lherb.get(n).ay+((block[lherb.get(n).cy][lherb.get(n).cx].y + Bsize / 4)-lherb.get(n).ay)* ((float)City.t /(float) lherb.get(n).md)),
                        Map.Bsize / 2, Map.Bsize / 2, null);
               lherb.get(n).ax=(int)(lherb.get(n).ax+((block[lherb.get(n).cy][lherb.get(n).cx].x + Bsize / 4)-lherb.get(n).ax)* ((float)City.t /(float) lherb.get(n).md));
                       lherb.get(n).ay=(int)(lherb.get(n).ay+((block[lherb.get(n).cy][lherb.get(n).cx].y + Bsize / 4)-lherb.get(n).ay)* ((float)City.t /(float) lherb.get(n).md));
                       if(City.t<(City.tick-3)){
                                                   g.drawLine(block[lherb.get(n).ox][lherb.get(n).oy].y + Bsize / 2, block[lherb.get(n).ox][lherb.get(n).oy].x + Bsize / 2, 
                                                           block[start.y][start.x].x + Bsize / 2, block[start.y][start.x].y + Bsize / 2);
                       }
            }
            }else{
                g.drawImage(Screen.crep[0],(block[lherb.get(n).cy][lherb.get(n).cx].x + Bsize / 4),(block[lherb.get(n).cy][lherb.get(n).cx].y + Bsize / 4),Map.Bsize / 2, Map.Bsize / 2, null);
            }
            
            
        }
    }
}
