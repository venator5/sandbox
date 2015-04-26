/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.Brain.rn;
import static City.City.City;
import static City.City.dgrid;
import static City.City.foodq;
import static City.City.groupi;
import static City.City.lherb;
import static City.City.lnd;
import static City.City.twot;
import static City.City.waterq;
import static City.Frame.varib;
import static City.Navigation.btile;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author The Venator
 */
public class Map {

    Font font = new Font("Arial", Bsize, Bsize);

    public static int Bsize = 12;
public static boolean cg = true;
public static int ch =20;
public static int cst = 10;
public static int cde=10;
    /*creates array for displaying tiles*/
    public static Block[][] block;
    public static int cstat = -1;
    public static String gend;
    int cstt;
    static int gdrawnum = 0;
    public static boolean vari = false;
public static boolean overlay = false;
public static boolean bmenut = false;
public static int bt = 1;
    public Map() {
        define();
    }

    

    public void click(int mouseButton) throws IOException {
        

        if (mouseButton == 1) {
            if(bmenut){
             for (int i = 0; i < btile.length; i++) {
                    if (Navigation.btile[i].contains(Screen.mou.x, Screen.mou.y)) {
                    
                    bt = i;
                    }}
             
            }
            for (int q = 0; q < Navigation.gcol.size(); q++) {
                if (Navigation.gcol.get(q).contains(Screen.mou.x, Screen.mou.y)) {
                    Navigation.ag = false;
                    gdrawnum = q;
                    Navigation.gd = true;
                }
            }

            for (int i = 0; i < block.length; i++) {
                for (int x = 0; x < block[0].length; x++) {
                    if (block[i][x].contains(Screen.mou.x, Screen.mou.y)) {
                        if (Navigation.cc) {
                           
                                City.newherb(i, x, cst, ch, 0, cg, cde);
                            

                        }
                        else if(bmenut){
                        block[i][x].id=bt;
                   
                           City.lnd[i][x] = City.sqr[Map.block[i][x].id];
                    City.foodq[i][x] = City.lnd[i][x].mxfood;
                    City.waterq[i][x] = City.lnd[i][x].mxwater;
                    City.pcap[i][x] = City.lnd[i][x].blok;
                        if(  City.pcap[i][x]){System.out.println("ture");}else{System.out.println("fle");}
                        }
                        
                        
                        else {

                            for (int n = 0; n < lherb.size(); n++) {
                                if (lherb.get(n).cx == i && lherb.get(n).cy == x) {
                                    cstt = lherb.get(n).num;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            for (int g = 0; g < Navigation.mnb.length; g++) {
                if (Navigation.mnb[g].contains(Screen.mou.x, Screen.mou.y)) {
                    switch (g) {
                        case 0: {
                            Navigation.cc = true;
                            bmenut=false;
                            break;
                        }
                        case 1: {
                            Navigation.ag = true;
                            break;
                        }
                        case 2: {
                            vari = true;
                             overlay = false;
                            break;
                        }
                            case 3: {
                            overlay = true;
                             vari = false;
                            break;
                        }
                            
                                  case 5: {
                            bmenut = true;
                       bt = 1;
                       Navigation.cc=false;
                            break;
                        }
                    }

                }
            }

        }
        if (mouseButton == 3) {
            if (vari) {
                vari = false;
            }else if(overlay){
            overlay=false;
            } else {
                          bmenut = false;
                       
                Navigation.cc = false;
                gdrawnum = 0;
                Navigation.gd = false;
                Navigation.ag = false;
            }
        }
    }

    public void define() {
        

        block = new Block[City.mapsizex][City.mapsizey];
        /*defines each block*/
        for (int i = 0; i < block.length; i++) {
            for (int x = 0; x < block[0].length; x++) {

                block[i][x] = new Block((x * Bsize) + 150, i * Bsize, Bsize, (Bsize), 0);
            }
        }

    }

    public void draw(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, Bsize / 2);
        g.setFont(font);
        for (int i = 0; i < dgrid.length; i++) {
            for (int x = 0; x < dgrid[i].length; x++) {
                twot[i][x] = 0;
            }
        }
        /*draws each tile*/
        for (int i = 0; i < block.length; i++) {

            for (int x = 0; x < block[0].length; x++) {

                block[i][x].draw(g);

                if (City.dgrid[i][x] == 2) {
                    g.setColor(new Color(255, 0, 0));
                    g.fillRect(block[i][x].x, block[i][x].y, Bsize, Bsize);
                } else if (City.dgrid[i][x] == 1) {
                    g.setColor(new Color(80, 80, 80));
                    g.fillRect(block[i][x].x, block[i][x].y, Bsize, Bsize);
                }
            }

        }
        /*draws pathing line*/
        if (City.groups.size() > 0 && Navigation.gd && !Navigation.ag) {
            groupdraw(g, gdrawnum);
        }
        if (City.groups.size() > 0 && Navigation.ag) {
            for (int y = 0; y < City.groups.size(); y++) {
                groupdraw(g, y);
            }
        }
        if (!City.hmem.isEmpty()) {
            for (int n = 0; n < lherb.size(); n++) {

                if (lherb.get(n).num == cstt) {
                    cstat = n;
                }

                if (lherb.get(n).gender) {
                    gend = "M";
                } else {
                    gend = "F";
                }
                if (City.hmem.get(n).size() > 0) {
                    if (lherb.get(n).treset) {
                        lherb.get(n).ax = (int) (block[lherb.get(n).ox][lherb.get(n).oy].x + Bsize / 4);
                        lherb.get(n).ay = (int) (block[lherb.get(n).ox][lherb.get(n).oy].y + Bsize / 4);
                        if (!City.pcap[lherb.get(n).cx][lherb.get(n).cy]) {

                            if (City.twot[lherb.get(n).cx][lherb.get(n).cy] == 0) {

                                cdraw(g, n, (block[lherb.get(n).cx][lherb.get(n).cy].x), (block[lherb.get(n).cx][lherb.get(n).cy].y), Bsize / 2, (block[lherb.get(n).cx][lherb.get(n).cy].y + 2 * Bsize / 4));

                                City.twot[lherb.get(n).cx][lherb.get(n).cy]++;
                            } else if (City.twot[lherb.get(n).cx][lherb.get(n).cy] > 0) {

                                cdraw(g, n, (block[lherb.get(n).cx][lherb.get(n).cy].x + Bsize / 2), (block[lherb.get(n).cx][lherb.get(n).cy].y + Bsize / 2), Bsize / 2, (block[lherb.get(n).cx][lherb.get(n).cy].y + Bsize));
                                City.twot[lherb.get(n).cx][lherb.get(n).cy] = 0;
                            }
                        } else {
                            cdraw(g, n, (block[lherb.get(n).cx][lherb.get(n).cy].x + Bsize / 4), (block[lherb.get(n).cx][lherb.get(n).cy].y + Bsize / 4), Bsize / 2, (block[lherb.get(n).cx][lherb.get(n).cy].y + 3 * Bsize / 4));
                        }
                        lherb.get(n).treset = false;
                    } else {
                        cdraw(g, n, (int) (lherb.get(n).ax + ((block[lherb.get(n).cx][lherb.get(n).cy].x + Bsize / 4) - lherb.get(n).ax) * ((float) City.t / (float) lherb.get(n).md)),
                                (int) (lherb.get(n).ay + ((block[lherb.get(n).cx][lherb.get(n).cy].y + Bsize / 4) - lherb.get(n).ay) * ((float) City.t / (float) lherb.get(n).md)), Map.Bsize / 2,
                                (int) (lherb.get(n).ay + ((block[lherb.get(n).cx][lherb.get(n).cy].y + 3 * Bsize / 4) - lherb.get(n).ay) * ((float) City.t / (float) lherb.get(n).md)));
                        lherb.get(n).ax = (int) (lherb.get(n).ax + ((block[lherb.get(n).cx][lherb.get(n).cy].x + Bsize / 4) - lherb.get(n).ax) * ((float) City.t / (float) lherb.get(n).md));
                        lherb.get(n).ay = (int) (lherb.get(n).ay + ((block[lherb.get(n).cx][lherb.get(n).cy].y + Bsize / 4) - lherb.get(n).ay) * ((float) City.t / (float) lherb.get(n).md));

                    }
                } else {

                    if (!City.pcap[lherb.get(n).cx][lherb.get(n).cy]) {
                        if (City.twot[lherb.get(n).cx][lherb.get(n).cy] == 0) {
                            cdraw(g, n, (block[lherb.get(n).cx][lherb.get(n).cy].x), (block[lherb.get(n).cx][lherb.get(n).cy].y), Bsize / 2, (block[lherb.get(n).cx][lherb.get(n).cy].y + 2 * Bsize / 4));

                            City.twot[lherb.get(n).cx][lherb.get(n).cy]++;
                        } else if (City.twot[lherb.get(n).cx][lherb.get(n).cy] > 0) {
                            cdraw(g, n, (block[lherb.get(n).cx][lherb.get(n).cy].x + Bsize / 2), (block[lherb.get(n).cx][lherb.get(n).cy].y + Bsize / 2), Bsize / 2, (block[lherb.get(n).cx][lherb.get(n).cy].y + Bsize));
                            City.twot[lherb.get(n).cx][lherb.get(n).cy] = 0;
                        }
                    } else {
                        cdraw(g, n, (block[lherb.get(n).cx][lherb.get(n).cy].x + Bsize / 4), (block[lherb.get(n).cx][lherb.get(n).cy].y + Bsize / 4), Bsize / 2, (block[lherb.get(n).cx][lherb.get(n).cy].y + 3 * Bsize / 4));
                    }
                }
            }

        }
        font = new Font("Arial", Font.BOLD, 15);
        g.setFont(font);
    }

    public void gpcolor(Graphics g, int n) {

        for (int a = 0; a < groupi.size(); a++) {
            if (groupi.get(a).gnum == lherb.get(n).gp) {
                g.setColor(new Color(City.groupi.get(a).gx, City.groupi.get(a).gy, City.groupi.get(a).gz));
                break;
            }
        }
    }

    public void groupdraw(Graphics g, int q) {
        if (q < City.groupi.size()) {
            g.setColor(new Color(City.groupi.get(q).gx, City.groupi.get(q).gy, City.groupi.get(q).gz));
            for (int x = 0; x < City.groups.get(q).size(); x++) {
                object cheese = (object) City.groups.get(q).get(x);
                g.setColor(new Color(City.groupi.get(q).gx, City.groupi.get(q).gy, City.groupi.get(q).gz));
                g.fillOval(block[cheese.cx][cheese.cy].x, block[cheese.cx][cheese.cy].y, Bsize, Bsize);
                g.setColor(Color.BLACK);
                g.drawOval(block[cheese.cx][cheese.cy].x, block[cheese.cx][cheese.cy].y, Bsize, Bsize);
            }
        }
    }

    public void cdraw(Graphics g, int n, int a, int b, int c, int d) {

        if (lherb.get(n).alpha) {
            g.setColor(Color.white);
            g.fillOval(a -Bsize/6, b-Bsize/10 , c + Bsize/3 , c +Bsize/5);

            
            if (lherb.get(n).gp > 0) {

                gpcolor(g, n);

                g.fillOval(a + 2, b + 2, c - 4, c - 4);
            }
            g.setColor(Color.black);
            
            g.drawString("" + gend, a, d);
        } else {
            g.setColor(Color.white);
            g.fillOval(a, b, c, c);

            if (lherb.get(n).gp > 0) {

                gpcolor(g, n);

                g.fillOval(a + 2, b + 2, c - 4, c - 4);
            }
            g.setColor(Color.black);
            //g.drawString("" + gend, a, d);
        }

    }
}
