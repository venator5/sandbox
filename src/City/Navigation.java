/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.City.City;
import static City.City.lherb;
import static City.City.sqr;
import static City.Map.Bsize;

import static City.Map.block;
import static City.Route.steps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author The Venator
 */
public class Navigation {
    /*size of menu buttons*/

    /*wether destroying*/
    City ct = new City();
    public Rectangle[] paus = new Rectangle[1];

    public Navigation() {
        define();
    }

    public void click(int mouseButton) throws IOException {

    }

    public void define() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.gray);

        g.fillRect(Screen.w2 - 150, 0, 150, Screen.h2);
        g.setColor(Color.black);
if(Map.cstat>=0){
        g.drawString("Creature ID: " + lherb.get(Map.cstat).num, Screen.w2 - 100, (Screen.h2) / 12);
        g.drawString("Hunger: " + lherb.get(Map.cstat).hun + " / " + lherb.get(Map.cstat).mhun, Screen.w2 - 100, (Screen.h2 * 2) / 12);
        g.drawString("thirst: " + lherb.get(Map.cstat).thir + " / " + lherb.get(Map.cstat).mthir, Screen.w2 - 100, (Screen.h2 * 3) / 12);
        g.setColor(Color.red);
        g.drawRect(block[lherb.get(Map.cstat).cx][lherb.get(Map.cstat).cy].y, block[lherb.get(Map.cstat).cx][lherb.get(Map.cstat).cy].x, Bsize, Bsize);
 int i=0;
 if(City.hlmem.get(Map.cstat).size()>0){
        for(int  d=0;d<(City.hlmem.get(Map.cstat).size());d++){
                    g.setColor(new Color(0, 0, 255));
                    System.out.println(City.hlmem.get(Map.cstat).size());
              int[] memo = (int[]) City.hlmem.get(Map.cstat).get(d);
                                   g.drawRect(block[memo[1]][memo[2]].x, block[memo[1]][memo[2]].y, Bsize, Bsize);

        }
 }
        int xb = 1;
        int yb = 1;
       
        int xa = -1;
        int ya = -1;
          int f =0;
        for (int t = 0; t < 2; t++) {

            for (i = xa; i <= xb; i++) {
f++;
                   if (City.mapsizex > (ya + City.lherb.get(Map.cstat).cx) && (ya + City.lherb.get(Map.cstat).cx) >=0 && City.mapsizey > (i + City.lherb.get(Map.cstat).cy) && (i + City.lherb.get(Map.cstat).cy) >=0) {
                     g.setColor(new Color(0, 0, 0));
                    g.drawString(""+City.lnd[i+ City.lherb.get(Map.cstat).cy][ya + City.lherb.get(Map.cstat).cx].tident, block[(i + City.lherb.get(Map.cstat).cy)][ya + City.lherb.get(Map.cstat).cx].x+(Bsize/2), block[(i + City.lherb.get(Map.cstat).cy)][ya + City.lherb.get(Map.cstat).cx].y+(Bsize/2));
                    g.setColor(new Color(255, 0, 0, 90));
                    g.fillRect(block[(i + City.lherb.get(Map.cstat).cy)][ya + City.lherb.get(Map.cstat).cx].x, block[(i + City.lherb.get(Map.cstat).cy)][ya + City.lherb.get(Map.cstat).cx].y, Bsize, Bsize);
               
                }
            }
            ya++;
            for (i = ya; i <= yb; i++) {
               f++;
                if (City.mapsizex > (i + City.lherb.get(Map.cstat).cx) && (i + City.lherb.get(Map.cstat).cx) >= 0 && City.mapsizey > (xb + City.lherb.get(Map.cstat).cy) && (xb + City.lherb.get(Map.cstat).cy) >= 0) {
   g.setColor(new Color(0, 0, 0));
                    g.drawString(""+City.lnd[xb+ City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].tident, block[xb + City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].x+(Bsize/2), block[xb + City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].y+(Bsize/2));

                    
                    g.setColor(new Color(0, 255, 00, 90));
                    g.fillRect(block[xb + City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].x, block[xb + City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].y, Bsize, Bsize);
                }
            }
            yb--;

            for (i = xb - 1; i >= xa; --i) {
f++;
                if (City.mapsizex > (xb + City.lherb.get(Map.cstat).cx) && (xb + City.lherb.get(Map.cstat).cx) >=0&& City.mapsizey > (i + City.lherb.get(Map.cstat).cy) && (i + City.lherb.get(Map.cstat).cy) >=0) {
                    g.setColor(new Color(0, 0, 0));
                      g.drawString(""+City.lnd[i+ City.lherb.get(Map.cstat).cy][xb + City.lherb.get(Map.cstat).cx].tident, block[i + City.lherb.get(Map.cstat).cy][xb + City.lherb.get(Map.cstat).cx].x+(Bsize/2), block[i + City.lherb.get(Map.cstat).cy][xb + City.lherb.get(Map.cstat).cx].y+(Bsize/2));
                    g.setColor(new Color(0, 00, 255, 90));
                    g.fillRect(block[i + City.lherb.get(Map.cstat).cy][xb + City.lherb.get(Map.cstat).cx].x, block[i + City.lherb.get(Map.cstat).cy][xb + City.lherb.get(Map.cstat).cx].y, Bsize, Bsize);
                }
            }
            xb--;

            for (i = yb; i >= ya; --i) {
                f++;
                if (City.mapsizex > (i + City.lherb.get(Map.cstat).cx) && (i + City.lherb.get(Map.cstat).cx) >=0 && City.mapsizey > (xa + City.lherb.get(Map.cstat).cy) && (xa + City.lherb.get(Map.cstat).cy) >=0) {
                     g.setColor(new Color(0, 0, 0));
                      g.drawString(""+City.lnd[City.lherb.get(Map.cstat).cy+ xa][i + City.lherb.get(Map.cstat).cx].tident,xa + block[City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].x+(Bsize/2), block[xa + City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].y+(Bsize/2));
                    g.setColor(new Color(0, 0, 0, 90));
                    g.fillRect(xa + block[City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].x, block[xa + City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].y, Bsize, Bsize);
                }
            }

            yb = 2;
            xb = 2;
            xa = -2;
            ya = -2;

        }
        

        for (int x = 0; x < steps.size(); x++) {
            Node poo = (Node) steps.get(x);
            g.setColor(Color.black);
            g.drawString("X: " + poo.x + "  Y: " + poo.y, Screen.w2 - 100, (Screen.h2 * (4 + x)) / 12);
        }
}
        paus[0] = new Rectangle(Screen.w2 - 40, (Screen.h2) - 64, 30, 30);

        if (Screen.paused) {
            g.setColor(Color.red);

            g.fillRect(Screen.w2 - 40, (Screen.h2) - 64, 30, 30);

        } else {
            g.setColor(Color.green);
            g.fillRect(Screen.w2 - 40, (Screen.h2) - 64, 30, 30);

        }

    }

    

}
