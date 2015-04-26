/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.Brain.hate;
import static City.City.City;
import static City.City.foodq;
import static City.City.gfriend;
import static City.City.groupi;
import static City.City.groups;
import static City.City.hsmem;
import static City.City.lherb;
import static City.City.lnd;
import static City.City.waterq;
import static City.Frame.overcheck;

import static City.Map.Bsize;

import static City.Map.block;
import static City.Map.bmenut;
import static City.Map.overlay;
import static City.Map.vari;
import static City.Route.steps;
import static City.Screen.bimg;
import static City.Screen.bmimg;
import static City.Screen.mimg;
import java.awt.BasicStroke;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author The Venator
 */
public class Navigation {
    /*size of menu buttons*/

    /*wether destroying*/
    City ct = new City();
    public Rectangle[] paus = new Rectangle[1];
    public static ArrayList<Rectangle> gcol = new ArrayList<Rectangle>();
    public static boolean overgend = false;
    public static boolean overres = false;
    public static boolean ag = false;
    public static boolean cc = true;
    public static boolean gd = false;
    public static Rectangle[] mnb = new Rectangle[6];
    public static Rectangle[] btile = new Rectangle[4];

    public Navigation() {
        define();
    }

    public void click(int mouseButton) throws IOException {

    }

    public void define() {

    }

    public void draw(Graphics g) {
        if (overgend) {
            for (int i = 0; i < lherb.size(); i++) {
                if (lherb.get(i).gender) {
                    g.setColor(Color.blue);
                } else {
                    g.setColor(Color.pink);
                }
                g.fillRect(block[lherb.get(i).cx][lherb.get(i).cy].x, block[lherb.get(i).cx][lherb.get(i).cy].y, Bsize / 2, Bsize / 2);
            }
        }
        if (overres) {
            for (int i = 0; i < lnd.length; i++) {

                for (int x = 0; x < lnd[0].length; x++) {

                    if (lnd[i][x].tident == 3) {
                        if (foodq[i][x] < 6) {
                            g.setColor(Color.red);
                        } else {
                            g.setColor(Color.green);
                        }

                        g.fillRect(block[i][x].x + Bsize / 2, block[i][x].y, Bsize / 2, Bsize / 2);
                        //                 g.setColor(Color.black);
//g.drawString(""+foodq[i][x],block[i][x].x+Bsize/2, block[i][x].y+12);
                    }

                    if (lnd[i][x].tident == 0) {
                        if (waterq[i][x] < 6) {
                            g.setColor(Color.red);
                        } else {
                            g.setColor(Color.green);
                        }
                        g.fillRect(block[i][x].x + Bsize / 2, block[i][x].y, Bsize / 2, Bsize / 2);
                    //        g.setColor(Color.black);
//g.drawString(""+waterq[i][x],block[i][x].x+Bsize/2, block[i][x].y+12);

                    }
                }
            }

        }
        g.setColor(Color.WHITE);
        g.fillRect(0, Screen.h2 - 50, Screen.w2, 50);
        g.fillRect(0, 0, Screen.w2, 50);
        g.fillRect(0, 0, 150, Screen.h2);
        g.fillRect(Screen.w2 - 150, 0, 150, Screen.h2);
        if (overlay) {
            //  g.fillRect(Screen.w2/2 - 400, Screen.h2/2-120, 800,300);

        }
        if (vari) {
            g.fillRect(Screen.w2 / 2 - 240, Screen.h2 / 2 - 160, 480, 260);

        }
        if( bmenut){
             g.setColor(Color.black);
        g.drawString("Tiles", Screen.w2-85, Screen.h2-175);
        
        }
if(cc){
            g.setColor(Color.black);
        g.drawString("Blob Stats", Screen.w2-105, Screen.h2-215);
        }
        for (int i = 0; i < mnb.length; i++) {
            mnb[i] = new Rectangle(((Screen.w2 / 2) - ((46 * mnb.length - 1) / 2)) + (i * 46), Screen.h2 - 45, 40, 40);
            g.drawImage(bimg[i], ((Screen.w2 / 2) - ((46 * mnb.length - 1) / 2)) + (i * 46), Screen.h2 - 45, 40, 40, null);

        }
        if (Map.bmenut) {
            for (int i = 0; i < btile.length; i++) {
                btile[i] = new Rectangle(Screen.w2 - 140, Screen.h2 - 160 + (i * 40), 35, 35);
                g.drawImage(mimg[i], Screen.w2 - 140, Screen.h2 - 160 + (i * 40), 35, 35, null);

            }

        }
        if (cc) {
            g.setColor(new Color(0, 255, 0, 90));
            g.fillRect(((Screen.w2 / 2) - ((46 * mnb.length - 1) / 2)), Screen.h2 - 45, 40, 40);

        }
        if (ag) {
            g.setColor(new Color(0, 255, 0, 90));
            g.fillRect(((Screen.w2 / 2) - ((46 * mnb.length - 1) / 2)) + 46, Screen.h2 - 45, 40, 40);

        }
        g.setColor(Color.black);
        g.drawString("POP: " + lherb.size(), Screen.w2 - 210, 25);
        g.drawString("TURN: " + City.clock, Screen.w2 - 115, 25);
        g.drawString("DEATH: " + City.dcounter, Screen.w2 - 320, 25);

        g.drawString("grp no Pop Color", 10, 64);
        for (int i = 0; i < groupi.size(); i++) {
            g.setColor(Color.black);
            g.drawString("" + groupi.get(i).gnum, 20, 88 + (i * 14));
            g.drawString("" + groups.get(i).size(), 60, 88 + (i * 14));
            g.setColor(new Color(groupi.get(i).gx, groupi.get(i).gy, groupi.get(i).gz));
            g.fillRect(100, 76 + (i * 14), 45, 14);
            Navigation.gcol.set(i, new Rectangle(100, 76 + (i * 14), 45, 14));
        }

        if (Map.cstat >= 0) {
            if (overcheck[0][2].isSelected()) {
                for (int i = 0; i < hsmem.get(Map.cstat).size(); i++) {
                    object cheese = (object) hsmem.get(Map.cstat).get(i);
                    for (int t = 0; t < lherb.size(); t++) {
                        if (cheese.iff == lherb.get(t).num) {
                            if (cheese.fl <= hate) {
                                g.setColor(Color.red);
                            } else if (cheese.fl >= gfriend) {
                                g.setColor(Color.green);
                            } else {
                                g.setColor(Color.gray);
                            }
                            g.fillRect(block[lherb.get(t).cx][lherb.get(t).cy].x, block[lherb.get(t).cx][lherb.get(t).cy].y + Bsize / 2, Bsize / 2, Bsize / 2);
                            break;
                        }
                    }

                }

            }

            g.setColor(Color.black);
            if (lherb.get(Map.cstat).alpha) {
                g.setColor(new Color(155, 155, 0));

            }
            g.drawString("" + lherb.get(Map.cstat).num, Screen.w2 - 115, 64);
            if (lherb.get(Map.cstat).gender) {
                g.drawString("AGE: " + lherb.get(Map.cstat).age, Screen.w2 - 115, 64 + 22);

            } else {
                g.drawString("AGE: " + lherb.get(Map.cstat).age, Screen.w2 - 115, 64 + 22);

            }

            g.drawString("" + lherb.get(Map.cstat).hun + " / " + lherb.get(Map.cstat).mhun, Screen.w2 - 115, 108);
            g.drawString("" + lherb.get(Map.cstat).thir + " / " + lherb.get(Map.cstat).mthir, Screen.w2 - 115, 130);
            g.drawString("" + lherb.get(Map.cstat).hp + " / " + lherb.get(Map.cstat).mhp, Screen.w2 - 115, 152);
            g.drawString("STR: " + lherb.get(Map.cstat).str, Screen.w2 - 140, 152 + 22);
            g.drawString("DEX:" + lherb.get(Map.cstat).dex, Screen.w2 - 140, 152 + 44);
            int ff = 0;
            for (int d = 0; d < bmimg.length; d++) {
                ff++;
                if (d == 2 || d == 1) {
                    if (lherb.get(Map.cstat).gender) {
                        if (d == 1) {
                            g.drawImage(bmimg[d], Screen.w2 - 140, 25 + (ff * 22), 20, 20, null);

                        } else {
                            ff--;
                        }
                    } else {
                        if (d == 2) {
                            g.drawImage(bmimg[d], Screen.w2 - 140, 25 + (ff * 22), 20, 20, null);

                        } else {
                            ff--;
                        }
                    }
                } else {
                    g.drawImage(bmimg[d], Screen.w2 - 140, 25 + (ff * 22), 20, 20, null);
                }
            }
            g.setColor(Color.red);
            Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(4));
            g.drawRect(block[lherb.get(Map.cstat).cx][lherb.get(Map.cstat).cy].x, block[lherb.get(Map.cstat).cx][lherb.get(Map.cstat).cy].y, Bsize, Bsize);
        g2.setStroke(oldStroke);
            int i = 0;
            int xb = 1;
            int yb = 1;

            int xa = -1;
            int ya = -1;
            int f = 0;
            for (int t = 0; t < 2; t++) {

                for (i = xa; i <= xb; i++) {
                    f++;
                    if (City.mapsizex > (ya + City.lherb.get(Map.cstat).cx) && (ya + City.lherb.get(Map.cstat).cx) >= 0 && City.mapsizey > (i + City.lherb.get(Map.cstat).cy) && (i + City.lherb.get(Map.cstat).cy) >= 0) {
                        g.setColor(new Color(160, 160, 160, 60));

                        g.fillRect(block[(i + City.lherb.get(Map.cstat).cy)][ya + City.lherb.get(Map.cstat).cx].x, block[(i + City.lherb.get(Map.cstat).cy)][ya + City.lherb.get(Map.cstat).cx].y, Bsize, Bsize);

                    }
                }
                ya++;
                for (i = ya; i <= yb; i++) {
                    f++;
                    if (City.mapsizex > (i + City.lherb.get(Map.cstat).cx) && (i + City.lherb.get(Map.cstat).cx) >= 0 && City.mapsizey > (xb + City.lherb.get(Map.cstat).cy) && (xb + City.lherb.get(Map.cstat).cy) >= 0) {
                        g.setColor(new Color(160, 160, 160, 60));
                        g.fillRect(block[xb + City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].x, block[xb + City.lherb.get(Map.cstat).cy][i + City.lherb.get(Map.cstat).cx].y, Bsize, Bsize);
                    }
                }
                yb--;

                for (i = xb - 1; i >= xa; --i) {
                    f++;
                    if (City.mapsizex > (xb + City.lherb.get(Map.cstat).cx) && (xb + City.lherb.get(Map.cstat).cx) >= 0 && City.mapsizey > (i + City.lherb.get(Map.cstat).cy) && (i + City.lherb.get(Map.cstat).cy) >= 0) {
                        g.setColor(new Color(160, 160, 160, 60));
                        g.fillRect(block[i + City.lherb.get(Map.cstat).cy][xb + City.lherb.get(Map.cstat).cx].x, block[i + City.lherb.get(Map.cstat).cy][xb + City.lherb.get(Map.cstat).cx].y, Bsize, Bsize);
                    }
                }
                xb--;

                for (i = yb; i >= ya; --i) {
                    f++;
                    if (City.mapsizex > (i + City.lherb.get(Map.cstat).cx) && (i + City.lherb.get(Map.cstat).cx) >= 0 && City.mapsizey > (xa + City.lherb.get(Map.cstat).cy) && (xa + City.lherb.get(Map.cstat).cy) >= 0) {
                        g.setColor(new Color(160, 160, 160, 60));
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

        if (Screen.paused) {
            g.setColor(Color.red);

        } else {
            g.setColor(Color.green);

        }
        Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(150, 50, Screen.w2 - 300, Screen.h2 - 100);
        g2.setStroke(oldStroke);
    }

}
