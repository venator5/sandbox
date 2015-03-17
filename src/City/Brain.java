/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.City.City;
import static City.City.lval;
import static City.Map.Bsize;
import static City.Map.block;
import java.awt.Color;
import static java.lang.Math.sqrt;
import java.util.Collections;

import java.util.ArrayList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import java.nio.file.Path;
import java.util.Random;

/**
 *
 * @author The Venator
 */
public class Brain {

    int ty;
    int tx;
    static  int flx,fly;
   static double fld;
    static boolean lrem=false;

    public static Random rand = new Random();

    public static void Brain(int n) {

        if (City.lherb.get(n).priority >= 1) {

            thirst(n);
        }
        if (City.lherb.get(n).priority >= 2) {

            hunger(n);
        }
        if (City.hmem.get(n).isEmpty()) {

            City.lherb.get(n).priority = 10000;

        }
        if (City.hmem.get(n).size() > 0) {

            Route.walk(n);

        }
    }

    public static void hunger(int n) {
        if (City.lherb.get(n).hun < 36) {
            if (City.lnd[City.lherb.get(n).cy][City.lherb.get(n).cx].tident != 3) {
                if (City.lherb.get(n).tt == 3 && City.hmem.get(n).size() > 0) {
                } else {
                    City.lherb.get(n).tt = 3;
                    City.lherb.get(n).priority = 2;
                    vision(City.lherb.get(n).tt, n);

                }
            }
        }
    }

    public static void thirst(int n) {
        if (City.lherb.get(n).thir < 65) {
            if (City.lnd[City.lherb.get(n).cy][City.lherb.get(n).cx].tident != 0) {
                if (City.lherb.get(n).tt == 0 && City.hmem.get(n).size() > 0) {
                } else {
                    City.lherb.get(n).tt = 0;
                    City.lherb.get(n).priority = 1;
                    vision(City.lherb.get(n).tt, n);
                  
                }
            }
        }
    }

    public static void vision(int c, int n) {

        int xb = 1;
        int yb = 1;
        int i;
        int xa = -1;
        int ya = -1;

        int f = 0;
        for (int t = 0; t < 2; t++) {

            for (i = xa; i <= xb; i++) {

                if (City.mapsizex > (ya + City.lherb.get(n).cx) && (ya + City.lherb.get(n).cx) > -1 && City.mapsizey > (i + City.lherb.get(n).cy) && (i + City.lherb.get(n).cy) > -1) {

                    if (City.lnd[i + City.lherb.get(n).cy][ya + City.lherb.get(n).cx].tident == c) {

                        Route.loop(City.lherb.get(n).cx, City.lherb.get(n).cy, City.lherb.get(n).cx + ya, City.lherb.get(n).cy + i, n);
                            lval = new int[3];
                        lval[0] = c;
                        lval[1] = i + City.lherb.get(n).cy;
                        lval[2] = ya + City.lherb.get(n).cx;
                        City.hlmem.get(n).add(lval);

                    }
                }
            }

            ya++;
            for (i = ya; i <= yb; i++) {

                if (City.mapsizex > (i + City.lherb.get(n).cx) && (i + City.lherb.get(n).cx) > -1 && City.mapsizey > (xb + City.lherb.get(n).cy) && (xb + City.lherb.get(n).cy) > -1) {
                    if (City.lnd[xb + City.lherb.get(n).cy][i + City.lherb.get(n).cx].tident == c) {
                        Route.loop(City.lherb.get(n).cx, City.lherb.get(n).cy, City.lherb.get(n).cx + i, City.lherb.get(n).cy + xb, n);
                           lval = new int[3];
                        lval[0] = c;
                        lval[1] = i + City.lherb.get(n).cy;
                        lval[2] = ya + City.lherb.get(n).cx;
                        City.hlmem.get(n).add(lval);
                    }
                }
            }
            yb--;

            for (i = xb - 1; i >= xa; --i) {

                if (City.mapsizex > (xb + City.lherb.get(n).cx) && (xb + City.lherb.get(n).cx) > -1 && City.mapsizey > (i + City.lherb.get(n).cy) && (i + City.lherb.get(n).cy) > -1) {
                    if (City.lnd[i + City.lherb.get(n).cy][xb + City.lherb.get(n).cx].tident == c) {
                        Route.loop(City.lherb.get(n).cx, City.lherb.get(n).cy, City.lherb.get(n).cx + xb, City.lherb.get(n).cy + i, n);
                   lval = new int[3];
                        lval[0] = c;
                        lval[1] = i + City.lherb.get(n).cy;
                        lval[2] = ya + City.lherb.get(n).cx;
                        City.hlmem.get(n).add(lval);
                    }
                }
            }
            xb--;

            for (i = yb; i >= ya; --i) {
                f++;
                if (City.mapsizex > (i + City.lherb.get(n).cx) && (i + City.lherb.get(n).cx) > -1 && City.mapsizey > (City.lherb.get(n).cy + xa) && (City.lherb.get(n).cy + xa) > -1) {
                    if (City.lnd[City.lherb.get(n).cy + xa][i + City.lherb.get(n).cx].tident == c) {
                        Route.loop(City.lherb.get(n).cx, City.lherb.get(n).cy, City.lherb.get(n).cx + i, City.lherb.get(n).cy + xa, n);
                    lval = new int[3];
                        lval[0] = c;
                        lval[1] = i + City.lherb.get(n).cy;
                        lval[2] = ya + City.lherb.get(n).cx;
                        City.hlmem.get(n).add(lval);
                }
            }

            yb = 2;
            xb = 2;
            xa = -2;
            ya = -2;

        }
           if (City.hmem.get(n).isEmpty()) {
               fld=1000000000;
               lrem=false;
                        for (int k = 0; k < City.hlmem.get(n).size(); k++) {
                            int[] memo = (int[]) City.hlmem.get(n).get(k);
                            if (memo[0] == c) {
                                lrem =true;
                                if(fld> ((sqrt((memo[1] - City.lherb.get(n).cx) * (memo[1] - City.lherb.get(n).cx)) + ((memo[2] - City.lherb.get(n).cy) * (memo[2] - City.lherb.get(n).cy))))){
                           fld =((sqrt((memo[1] - City.lherb.get(n).cx) * (memo[1] - City.lherb.get(n).cx)) + ((memo[2] - City.lherb.get(n).cy) * (memo[2] - City.lherb.get(n).cy))));
                           flx = memo[1];
                           fly = memo[2];
                                }
                            }
                        }
                        if(lrem){
                                Route.loop(City.lherb.get(n).cx, City.lherb.get(n).cy, flx, fly, n);
                        }
                    }
        if (City.hmem.get(n).isEmpty()) {
            System.out.println("random");
            random(n);

        }
    }}

    public static void random(int n) {

        int rwy = rand.nextInt(((City.lherb.get(n).cx + 3) - (City.lherb.get(n).cx - 3))) + (City.lherb.get(n).cx - 3);
        int rwx = rand.nextInt(((City.lherb.get(n).cy + 3) - (City.lherb.get(n).cy - 3))) + (City.lherb.get(n).cy - 3);
        if (rwx < 0 || rwy < 0 || rwx > City.mapsizex - 1 || rwy > City.mapsizey - 1 || City.lnd[rwx][rwy].blok == 0 || rwy == City.lherb.get(n).cy || rwx == City.lherb.get(n).cx) {
            random(n);
        } else {
            Route.loop(City.lherb.get(n).cx, City.lherb.get(n).cy, rwy, rwx, n);
        }
    }

}
