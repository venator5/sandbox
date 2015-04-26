/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.City.*;
import static java.lang.Math.sqrt;

import java.util.Random;

/**
 * 566
 *
 * @author The Venator
 */
public class Brain {

    public static Random rn = new Random();
    public static int tcheck = 35;
    public static int fcheck = 35;
  
    public static int hate = -35;
    public static int ogfrinc = -5, gfrinc = 2, frcomp = -3;
    
    
    public static int fleehp = 5;//unused atm
    
    public static int stratk = 1;
    static object lval;
    static int flx, fly;
    static int desp = 10;
    static double fld;
    static boolean lrem = false;
    public static int rdist = 7;
    static boolean iff;
    public static int rwy, rwx;
    public static Random rand = new Random();

    public static void brain(int n) {
 social(n, false);
        if (City.lherb.get(n).priority >= 1) {

            thirst(n);
        }
        if (City.lherb.get(n).priority >= 2) {

            hunger(n);
        }
        if (City.lherb.get(n).priority >= 3) {

            breed(n);
        }
        if (City.lherb.get(n).priority >= 4) {

            fight(n);
        }
        if (City.hmem.get(n).isEmpty()) {

            City.lherb.get(n).priority = 10000;

        }
        if (City.hmem.get(n).size() == 1) {
            Node poo = (Node) City.hmem.get(n).get(0);
            if (!City.pcap[poo.x][poo.y]) {
                City.hmem.get(n).clear();
            }
        }

       
        if (City.hmem.get(n).size() > 0) {

            Route.walk(n);

        }
    }

    public static void hunger(int n) {
        if (City.lherb.get(n).hun < fcheck) {
            if (City.foodq[City.lherb.get(n).cx][City.lherb.get(n).cy] < 6) {
                if (City.lherb.get(n).tt == 3 && City.hmem.get(n).size() > 0) {
                } else {
                    City.lherb.get(n).tt = 3;
                    City.lherb.get(n).priority = 2;
                    vision(City.lherb.get(n).tt, n, false, false);

                }
            }
        }
    }

    public static void breed(int n) {
        if (City.lherb.get(n).btime >= 100 && City.hmem.get(n).isEmpty()) {
            City.lherb.get(n).priority = 3;
            vision(City.lherb.get(n).tt, n, true, false);

        }
    }

    public static void thirst(int n) {
        if (City.lherb.get(n).thir < tcheck) {
            if (City.waterq[City.lherb.get(n).cx][City.lherb.get(n).cy] < 6) {

                if (City.lherb.get(n).tt == 0 && City.hmem.get(n).size() > 0) {
                } else {
                    City.lherb.get(n).tt = 0;
                    City.lherb.get(n).priority = 1;
                    vision(City.lherb.get(n).tt, n, false, false);

                }
            }
        }
    }

    public static void fight(int n) {
        if (City.lherb.get(n).hp >= (City.lherb.get(n).mhp / 2) && City.hmem.get(n).isEmpty()) {
            City.lherb.get(n).priority = 4;
            vision(City.lherb.get(n).tt, n, false, true);

        }
    }

    public static void vision(int c, int n, boolean breed, boolean fight) {

        int xb = 2;
        int yb = 2;
        int i;
        int xa = -2;
        int ya = -2;

        if (breed) {

            City.breed(n);
        }
  

        for (int t = 0; t < 2; t++) {

            for (i = xa; i <= xb; i++) {

                if (City.mapsizex > (ya + City.lherb.get(n).cx) && (ya + City.lherb.get(n).cx) > -1 && City.mapsizey > (i + City.lherb.get(n).cy) && (i + City.lherb.get(n).cy) > -1
                        && City.pcap[ya + City.lherb.get(n).cx][City.lherb.get(n).cy + i]) {
                    if (!breed) {
                        fsearch(n, ya, i, c);
                    } else if (fight) {
                        fightsearch(n, ya, i);
                    } else {
                        bsearch(n, ya, i);
                    }

                }
            }

            ya++;
            for (i = ya; i <= yb; i++) {

                if (City.mapsizex > (i + City.lherb.get(n).cx) && (i + City.lherb.get(n).cx) > -1
                        && City.mapsizey > (xb + City.lherb.get(n).cy) && (xb + City.lherb.get(n).cy) > -1
                        && City.pcap[i + City.lherb.get(n).cx][City.lherb.get(n).cy + xb]) {
                    if (!breed) {
                        fsearch(n, i, xb, c);
                    } else if (fight) {
                        fightsearch(n, i, xb);
                    } else {
                        bsearch(n, i, xb);
                    }
                }
            }
            yb--;

            for (i = xb - 1; i >= xa; --i) {

                if (City.mapsizex > (xb + City.lherb.get(n).cx) && (xb + City.lherb.get(n).cx) > -1 && City.mapsizey > (i + City.lherb.get(n).cy) && (i + City.lherb.get(n).cy) > -1
                        && City.pcap[xb + City.lherb.get(n).cx][i + City.lherb.get(n).cy]) {
                    if (!breed) {
                        fsearch(n, xb, i, c);
                    } else if (fight) {
                        fightsearch(n, xb, i);
                    } else {
                        bsearch(n, xb, i);

                    }
                }
            }
            xb--;

            for (i = yb; i >= ya; --i) {
                if (City.mapsizex > (i + City.lherb.get(n).cx) && (i + City.lherb.get(n).cx) > -1
                        && City.mapsizey > (City.lherb.get(n).cy + xa) && (City.lherb.get(n).cy + xa) > -1
                        && City.pcap[i + City.lherb.get(n).cx][City.lherb.get(n).cy + xa]) {
                    if (!breed) {
                        fsearch(n, i, xa, c);
                    } else if (fight) {
                        fightsearch(n, i, xa);
                    } else {
                        bsearch(n, i, xa);

                    }

                }

                yb = 1;
                xb = 1;
                xa = -1;
                ya = -1;

            }
        }
        if (City.hmem.get(n).isEmpty()&&!breed&&!fight) {
            msearch(n, c);
        }
        if (City.hmem.get(n).isEmpty()&&!breed&&!fight) {

            random(n);

        }
    }

    public static void fsearch(int n, int a, int b, int c) {
        if (City.lnd[a + City.lherb.get(n).cx][b + City.lherb.get(n).cy].tident == c) {
            if (City.waterq[City.lherb.get(n).cx + a][b + City.lherb.get(n).cy] > 5 || City.foodq[City.lherb.get(n).cx + a][b + City.lherb.get(n).cy] > 5) {
                Route.loop(City.lherb.get(n).cx, City.lherb.get(n).cy, City.lherb.get(n).cx + a, City.lherb.get(n).cy + b, n);
                if (City.hmem.get(n).size() > 0) {
                    for (int d = 0; d < City.hlmem.get(n).size(); d++) {
                        object memo = (object) City.hlmem.get(n).get(d);
                        if (memo.lx == City.lherb.get(n).cx + a && memo.ly == City.lherb.get(n).cy + b) {
                            break;
                        }
                    }
                }
                lval = new object();
                City.hlmem.get(n).add(lval);
                object memo = (object) City.hlmem.get(n).get(City.hlmem.get(n).size() - 1);
                memo.lt = c;
                memo.lx = a + City.lherb.get(n).cx;
                memo.ly = b + City.lherb.get(n).cy;

            }

        }
    }

    public static void bsearch(int n, int a, int b) {
        for (int q = 0; q < lherb.size(); q++) {

            if (lherb.get(q).cx == a + City.lherb.get(n).cx && lherb.get(q).cy == b + City.lherb.get(n).cy) {
                if (lherb.get(q).btime >= 100 && lherb.get(q).gender != lherb.get(n).gender && lherb.get(q).gp == lherb.get(n).gp) {
                    Route.loop(City.lherb.get(n).cx, City.lherb.get(n).cy, City.lherb.get(n).cx + a, City.lherb.get(n).cy + b, n);
                }

            }
        }
    }

    public static void msearch(int n, int c) {
        fld = 1000000000;
        lrem = false;
        for (int k = 0; k < City.hlmem.get(n).size(); k++) {
            object memo = (object) City.hlmem.get(n).get(k);
            if (memo.lt == c) {
                lrem = true;
                if (fld > ((sqrt(memo.lx - City.lherb.get(n).cx) * (memo.lx - City.lherb.get(n).cx)) + ((memo.ly - City.lherb.get(n).cy) * (memo.ly - City.lherb.get(n).cy)))) {
                    fld = ((sqrt((memo.lx - City.lherb.get(n).cx) * (memo.lx - City.lherb.get(n).cx)) + ((memo.ly - City.lherb.get(n).cy) * (memo.ly - City.lherb.get(n).cy))));
                    flx = memo.lx;
                    fly = memo.ly;
                }
            }
        }
        if (lrem) {
            if (flx < mapsizex && fly < mapsizey && flx >= 0 && fly >= 0) {
                Route.loop(City.lherb.get(n).cx, City.lherb.get(n).cy, flx, fly, n);
            }
        }
    }

    public static void fightsearch(int n, int a, int b) {
        for (int q = 0; q < lherb.size(); q++) {

            if (lherb.get(q).cx == a + City.lherb.get(n).cx && lherb.get(q).cy == b + City.lherb.get(n).cy) {
                if (lherb.get(q).hp <= lherb.get(n).hp+5 && lherb.get(q).str <= lherb.get(n).str+5) {
                 combat(n,q);
                }

            }
        }
    }

    public static void random(int n) {

        rwx = rand.nextInt(((City.lherb.get(n).cx + rdist) - (City.lherb.get(n).cx - rdist))) + (City.lherb.get(n).cx - rdist);
        rwy = rand.nextInt(((City.lherb.get(n).cy + rdist) - (City.lherb.get(n).cy - rdist))) + (City.lherb.get(n).cy - rdist);

        if (rwx < 0 || rwy < 0 || rwy >= City.mapsizey || rwx >= City.mapsizex) {
            random(n);

        } else 
            if (rwy == City.lherb.get(n).cy && rwx == City.lherb.get(n).cx) {
                random(n);
            } 
           else {

                    Route.loop(City.lherb.get(n).cx, City.lherb.get(n).cy, rwx, rwy, n);
                }
            }
        
    

    public static void social(int n, boolean flee) {

        object sval;
        int xb = 1;
        int yb = 1;
        int i;
        int xa = -1;
        int ya = -1;
        if(!flee){
          for (int q = 0; q < lherb.size(); q++) {
            iff = false;
            if (lherb.get(q).cx == City.lherb.get(n).cx && lherb.get(q).cy == City.lherb.get(n).cy) {
                if (!City.hsmem.get(n).isEmpty()) {
                    for (int k = 0; k < City.hsmem.get(n).size(); k++) {
                        if (k < City.hsmem.get(n).size()) {
                            object memo = (object) City.hsmem.get(n).get(k);

                            if (memo.iff
                                    == City.lherb.get(q).num) {
                                if (City.lnd[City.lherb.get(n).cx][City.lherb.get(n).cy].tident == 0 || City.lnd[City.lherb.get(n).cx][City.lherb.get(n).cy].tident == 3) {
                                    if (City.lherb.get(n).gp != City.lherb.get(q).gp && City.lherb.get(n).gp > 0) {
                                        memo.fl -= 10;
                                    } else if (City.lherb.get(n).gp != City.lherb.get(q).gp) {
                                         memo.fl -= 10;
                                    } else {
                                        memo.fl -= frcomp;
                                    }
                                    if (memo.fl <= hate) {
                                        if (!lherb.get(n).atkd) {
                                            combat(n, q);
                                            break;
                                        }
                                    }

                                } else {

                                    memo.fl++;
                                }

                                iff = true;
                            }
                        }
                    }
                }
                if (!iff&&City.lherb.get(q).num != 
                        City.lherb.get(n).num) {
                    sval = new object();
                    City.hsmem.get(n).add(sval);
                    object memo = (object) City.hsmem.get(n).get(City.hsmem.get(n).size() - 1);
                    memo.iff = City.lherb.get(q).num;
                    memo.fl = 0;
                    memo.gn = City.lherb.get(q).gp;
                }
            }
        }
        }
        for (i = xa; i <= xb; i++) {
            if (flee) {
                flee(n, ya, i);
            } else {
                talking(ya, i, n);
            }
        }

        ya++;
        for (i = ya; i <= yb; i++) {
            if (flee) {
                flee(n, i, xb);
            } else {
                talking(i, xb, n);
            }
        }
        yb--;

        for (i = xb - 1; i >= xa; --i) {
            if (flee) {
                flee(n, i, xb);
            } else {
                talking(i, xb, n);
            }
        }
        

        for (i = yb; i >= ya; --i) {
            if (flee) {
                flee(n, i, xa);
            } else {
                talking(i, xa, n);
            }
        }

      

    }

    static public void combat(int a, int b) {
        boolean c = true;
        if (!City.lherb.get(a).atkd && City.lherb.get(b).hp > 0) {
double q = rn.nextDouble();
double t= (lherb.get(b).dex/(double)lherb.get(a).dex)*0.5;
if(t>q){


            City.lherb.get(b).hp -= City.lherb.get(a).str*stratk;
            City.lherb.get(a).atkd = true;
            if (City.lherb.get(b).hp <= 0) {
                dgrid[lherb.get(b).cx][lherb.get(b).cy] = 2;
                City.death(b);
                c = false;
                System.out.println("DEATH");
            }
            
        }}
        if (City.lherb.get(b).hp <= City.lherb.get(b).hp /5) {
            if (City.lherb.get(b).thir > desp && City.lherb.get(b).hun > desp) {
                rn = new Random();

                int f = (City.lherb.get(b).dex / City.lherb.get(a).dex) * 60;
                if (f > rn.nextInt(100)) {
                      System.out.println("RUN");
                    social(b, true);
                }
            }
        } else {

            Float q = rn.nextFloat();
Double t= (lherb.get(a).dex/lherb.get(b).dex)*0.5;
if(t>q){
            if (!City.lherb.get(b).atkd && c) {
                City.lherb.get(a).hp -= City.lherb.get(b).str*stratk;
                City.lherb.get(b).atkd = true;
                if (City.lherb.get(a).hp <= 0) {
                    dgrid[lherb.get(a).cx][lherb.get(a).cy] = 2;
                    City.death(a);
                    System.out.println("DEATH");
                }

            }
        }}
    }

    public static void talking(int a, int b, int n) {
        if (City.mapsizex > (a + City.lherb.get(n).cx) && (a + City.lherb.get(n).cx) > -1 && City.mapsizey > (b + City.lherb.get(n).cy) && (b + City.lherb.get(n).cy) > -1) {

            for (int q = 0; q < lherb.size(); q++) {
                iff = false;
                if (lherb.get(q).cx == a + City.lherb.get(n).cx && lherb.get(q).cy == b + City.lherb.get(n).cy) {

                    for (int k = 0; k < City.hsmem.get(n).size(); k++) {
                        object memo = (object) City.hsmem.get(n).get(k);

                        if (memo.iff == City.lherb.get(q).num) {
                            if (City.lherb.get(n).gp == City.lherb.get(n).gp) {
                                memo.fl += gfrinc;
                            } else if (City.lherb.get(n).gp != City.lherb.get(n).gp && City.lherb.get(n).gp > 0) {
                                memo.fl -= ogfrinc;
                            } else {
                                memo.fl++;
                            }
                            iff = true;
                        }

                    }
                    if (!iff && q != City.lherb.get(n).num) {
                        object sval = new object();
                        City.hsmem.get(n).add(sval);
                        object memo = (object) City.hsmem.get(n).get(City.hsmem.get(n).size() - 1);
                        memo.iff = City.lherb.get(q).num;
                        memo.fl = 0;
                        memo.gn = 0;

                    }
                }
            }

        }

    }

    public static void flee(int n, int a, int b) {
        if (City.mapsizex > (a + City.lherb.get(n).cx) && (a + City.lherb.get(n).cx) > -1 && City.mapsizey > (b + City.lherb.get(n).cy) && (b + City.lherb.get(n).cy) > -1) {

            if (pcap[a + City.lherb.get(n).cx][b + City.lherb.get(n).cy]) {
                City.lherb.get(n).cx = a + City.lherb.get(n).cx;
                City.lherb.get(n).cy = b + City.lherb.get(n).cy;
                City.lherb.get(n).atkd = true;
            }
        }
    }
}
