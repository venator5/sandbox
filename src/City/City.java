/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.Brain.brain;

import static City.Frame.overcheck;
import static City.Frame.varib;

import static City.Map.block;
import java.awt.Image;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.lang.reflect.Array;

/**
 *
 * @author The Venator
 */

/**/
public class City {
    /*group shizzle*/

    public static int fn = 0, mn = 0;
    public static int gid = 0;
    public static int gvars = 5, gvard = 5, gvarhp = 5;
    public static int gfc;
    public static int gseed = 7, gfriend = 15, gfriend2 = 30, gmulti = 3, gbud;
    public static int bincm = 20, bincf = 5;
    public static int fregen = 4, wregen = 4;
    public static int hpregen = 2;
    public static int aod = 260;

    public static boolean gcheck;
    public static boolean ptoggle = false;

    public static int cn;
    /*time integers for time();*/

    public static int clock = 0;
    /*total turnss*/
    public static int time = 0;
    /*tick counter */
    public static int t = 0;
    /*total ticks/turn */
    public static int tick = 10;
    /* counts the amount of deaths*/
    static int dcounter = 0;

    public static double hdecay = 1;
    public static double tdecay = 1;

    public static City City = new City();
    public static Frame fra = new Frame();
    public static object get = new object();
    public static Brain b = new Brain();
    /*map sizes*/
    public static int mapsizex = 40;
    public static int mapsizey = 40;

    /*stores map tiles information*/
    public static object[][] lnd = new object[mapsizex][mapsizey];
    public object[] sqr = new object[4];

    /*values per tile*/
    public static int[][] foodq = new int[mapsizex][mapsizey];
    public static int[][] waterq = new int[mapsizex][mapsizey];
    public static boolean[][] pcap = new boolean[mapsizex][mapsizey];
    public static int[][] dgrid = new int[mapsizex][mapsizey];
    public static int[][] twot = new int[mapsizex][mapsizey];
    /*loading*/
    public static Object[] pa = new Object[8];
    public static Path path1 = Paths.get("src/Sav/1.txt");
    public static Path path2 = Paths.get("src/Sav/2.txt");
    public static Path path3 = Paths.get("src/Sav/3.txt");
    public static Path path4 = Paths.get("src/Sav/4.txt");
    public static Path path5 = Paths.get("src/Sav/5.txt");
    public static Path path6 = Paths.get("src/Sav/6.txt");
    public static Path path7 = Paths.get("src/Sav/7.txt");
    public static Path path8 = Paths.get("src/Sav/8.txt");
    public static Array[][] varibell = new Array[7][3];

    public static List<Image> icons;
    /*arraylists for creatures*/
    public static List<object> lherb;
    public static List<object> groupi;
    public object herb;
    /*path memory*/
    public static ArrayList<ArrayList> hmem = new ArrayList<>();
    public static ArrayList mem = new ArrayList();
    /*location memory*/
    public static ArrayList<List> hlmem = new ArrayList<>();
    public static List<object> lmem = new ArrayList<>();
    /*social memory*/
    public static ArrayList<List> hsmem = new ArrayList<>();
    public static List<object> smem = new ArrayList<>();

    public static ArrayList<ArrayList> groups = new ArrayList<>();
    public static ArrayList group = new ArrayList();

    public static object gi = new object();
    public static Random rn = new Random();

    /*THE START*/
    public static void main(String[] args) throws IOException {

        City.mapc();
        Load.Load(1);
        fra.Frame();

    }
    /*intialises map*/

    public void mapc() {
        for (int i = 0; i < sqr.length; ++i) {
            sqr[i] = new object();

            sqr[i].tident = get.tid[i];

            sqr[i].blok = get.block[i];
            sqr[i].mxfood = get.mfood[i];

            sqr[i].mxwater = get.mwater[i];
            sqr[i].pocap = get.popcap[i];

        }

        icons = new ArrayList<>();
        icons.add(new ImageIcon("src/sause/gov.png").getImage());
        lherb = new ArrayList<>();
        groupi = new ArrayList<>();
    }
    /*moves map x distance and y distance*/

    public void move(int cx, int cy) {
        for (int i = 0; i < block.length; i++) {

            for (int x = 0; x < block[0].length; x++) {

                block[i][x].x += cx;
                block[i][x].y += cy;
                Kyhndlr.cx = 0;
                Kyhndlr.cy = 0;
            }
        }
    }
    /**/

    public void timer() {
        t++;

        if (t >= tick) {
            clock++;
            t = 0;
            gbud = gmulti * gseed;
            time++;
            tileroom();
            for (int i = 0; i < dgrid.length; i++) {
                for (int x = 0; x < dgrid[i].length; x++) {
                    dgrid[i][x] = 0;
                    twot[i][x] = 0;
                }
            }
            /*resets attack*/
            for (int x = 0; x < lherb.size(); x++) {
                lherb.get(x).atkd = false;
                lherb.get(x).walk = false;
                lherb.get(x).age++;
            }
            /*tile regearation*/
            for (int i = 0; i < lnd.length; i++) {

                for (int n = 0; n < lnd[0].length; n++) {
                    if (City.lnd[i][n].tident == 0) {
                        waterq[i][n] += wregen;
                    }
                    if (City.lnd[i][n].tident == 3) {
                        foodq[i][n] += fregen;
                    }

                }
            }

            int x;
            for (x = 0; x < lherb.size(); x++) {
                /*creature time values*/
                lherb.get(x).treset = true;
                int z = lherb.get(x).cx;
                int y = lherb.get(x).cy;
                lherb.get(x).thir -= ((lherb.get(x).mhp/20)+(lherb.get(x).str/10)+(lherb.get(x).dex/10))*tdecay;
                lherb.get(x).hun -= ((lherb.get(x).mhp/20)+(lherb.get(x).str/10)+(lherb.get(x).dex/10))*hdecay;;
                lherb.get(x).hp += hpregen;
                if (lherb.get(x).gender) {
                    lherb.get(x).btime += bincm;
                } else {
                    lherb.get(x).btime += bincf;
                }
                eat(x, z, y);
                drink(x, z, y);
                /*death or operate brain*/
                if (lherb.get(x).hun < 0 || lherb.get(x).thir < 0 || lherb.get(x).hp <= 0) {
                    dgrid[lherb.get(x).cx][lherb.get(x).cy] -= 1;
                    death(x);
                } else if (lherb.get(x).age > aod) {
                    death(x);
                } else {
                    max(x);
                    brain(x);
                    tileroom();
                }
            }
            if (time >= 1) {
                time = 0;

                groupchk();
                groupdeathchk();
            }

        }

    }
    /*keeps vlues within limits*/

    public void max(int x) {
        for (int i = 0; i < City.lnd.length; i++) {

            for (int n = 0; n < City.lnd[0].length; n++) {
                if (waterq[i][n] > City.lnd[i][n].mxwater) {
                    waterq[i][n] = City.lnd[i][n].mxwater;
                }
                if (foodq[i][n] > City.lnd[i][n].mxfood) {
                    foodq[i][n] = City.lnd[i][n].mxfood;
                }
            }
        }

        if (lherb.size() > 0) {
            if (lherb.get(x).hun > lherb.get(x).mhun) {
                lherb.get(x).hun = lherb.get(x).mhun;
            }
            if (lherb.get(x).thir > lherb.get(x).mthir) {
                lherb.get(x).thir = lherb.get(x).mthir;
            }
            if (lherb.get(x).hp > lherb.get(x).mhp) {
                lherb.get(x).hp = lherb.get(x).mhp;
            }
        }
    }
    /*creating new creature*/

    public void newherb(int x, int y, int str, int mhp, int group, boolean gender, int dex) {
        herb = new object();
        lherb.add(herb);
        lherb.get(lherb.size() - 1).num = get.popid;
        lherb.get(lherb.size() - 1).hun = get.hun1[0];
        lherb.get(lherb.size() - 1).mhun = get.mhun1[0];
        lherb.get(lherb.size() - 1).thir = get.thir1[0];
        lherb.get(lherb.size() - 1).mthir = get.mthir1[0];
        lherb.get(lherb.size() - 1).ox = x;
        lherb.get(lherb.size() - 1).oy = y;
        lherb.get(lherb.size() - 1).cx = x;
        lherb.get(lherb.size() - 1).cy = y;
        lherb.get(lherb.size() - 1).mhp = mhp;
        lherb.get(lherb.size() - 1).hp = mhp;
        lherb.get(lherb.size() - 1).str = str;
        lherb.get(lherb.size() - 1).dex = dex;
        lherb.get(lherb.size() - 1).age = 0;
        lherb.get(lherb.size() - 1).btime = 0;
        lherb.get(lherb.size() - 1).tt = get.tt;
        lherb.get(lherb.size() - 1).priority = get.priority;
        lherb.get(lherb.size() - 1).treset = true;
        lherb.get(lherb.size() - 1).atkd = false;
        lherb.get(lherb.size() - 1).walk = false;
        lherb.get(lherb.size() - 1).alpha = false;
        lherb.get(lherb.size() - 1).gp = group;
        lherb.get(lherb.size() - 1).gender = gender;
        if (gender) {
            mn++;
        } else {
            fn++;
        }
        hmem.add(mem);
        lmem = new ArrayList<>();
        hlmem.add(lmem);
        smem = new ArrayList<>();
        hsmem.add(smem);
        if (group >= 0) {
            for (int a = 0; a < groupi.size(); a++) {
                if (lherb.get(lherb.size() - 1).gp == groupi.get(a).gnum) {
                    groups.get(a).add(lherb.get(lherb.size() - 1));
                    lherb.get(lherb.size() - 1).grpid = a;

                }
            }
        } else {
            lherb.get(lherb.size() - 1).gp = 0;

        }
        get.popid++;

    }
    /*kill greature*/
    public static int alphastr;
    public static int alphasuc;

    public static void death(int x) {
        dcounter++;
        alphastr = 0;
        alphasuc = 0;
        hlmem.remove(x);
        hsmem.remove(x);
        hmem.remove(x);
        if (City.lherb.get(x).gender) {
            mn--;
        } else {
            fn--;
        }

        if (lherb.get(x).gp > 0) {
            for (int a = 0; a < groups.size(); a++) {

                for (int bb = 0; bb < groups.get(a).size(); bb++) {
                    object cheese = (object) groups.get(a).get(bb);

                    if (lherb.get(x).num == cheese.num) {
                        groups.get(a).remove(bb);

                        break;
                    }

                }
            }
            if (lherb.get(x).alpha) {
                for (int d = 0; d < groupi.size(); d++) {
                    if (lherb.get(x).gp == groupi.get(d).gnum) {
                        for (int c = 0; c < groups.get(d).size(); c++) {
                            object ches = (object) groups.get(d).get(c);
                            if (ches.str > alphastr) {
                                alphastr = ches.str;
                                alphasuc = ches.num;
                            }
                        }
                    }
                }
                for (int t = 0; t < lherb.size(); t++) {
                    if (lherb.get(t).num == alphasuc) {
                        lherb.get(t).alpha = true;
                        break;
                    }
                }

            }
        }

        for (int i = 0; i < hsmem.size(); i++) {
            for (int p = 0; p < hsmem.get(i).size(); p++) {
                object memo = (object) hsmem.get(i).get(p);
                if (memo.iff == City.lherb.get(x).num) {
                    hsmem.get(i).remove(p);
                    break;
                }
            }

        }
        lherb.remove(x);
        if (Map.cstat == x) {
            Map.cstat = -1;
        }
    }

    /*checks how full tile is if full sets as unpassable*/
    public static void tileroom() {
        for (int i = 0; i < pcap.length; i++) {

            for (int n = 0; n < pcap[0].length; n++) {
                int p = 0;
                if (lnd[i][n].tident == 2) {
                    pcap[i][n] = false;
                } else {
                    for (object lherb1 : lherb) {

                        if (lherb1.cy == i && lherb1.cx == n) {
                            p++;
                        }
                        if (p >= lnd[i][n].pocap) {
                            pcap[i][n] = false;
                            break;
                        } else {
                            pcap[i][n] = true;
                        }
                    }
                }

            }

        }
    }
    /*creature eating from tile*/

    public static void eat(int x, int z, int y) {
        if (lnd[z][y].mxfood > 0) {
            if (lherb.get(x).hun <= (lherb.get(x).mhun * 0.9)) {
                foodq[z][y] -= 20;

                if (foodq[z][y] <= 0) {
                    lherb.get(x).hun += (20 + foodq[z][y]);
                    foodq[z][y] = 0;

                } else {
                    lherb.get(x).hun += 20;
                }
            }
        }
    }
    /*creature drinking from tile*/

    public static void drink(int x, int z, int y) {
        /*creature drinking from tile*/
        if (lnd[z][y].mxwater > 0) {
            if (lherb.get(x).thir <= (lherb.get(x).mthir * 0.9)) {
                waterq[z][y] -= 20;

                if (waterq[z][y] < 0) {
                    lherb.get(x).thir += (20 + waterq[z][y]);
                    waterq[z][y] = 0;
                } else {
                    lherb.get(x).thir += 20;
                }
            }
        }
    }
    /*gathering and combing parents values*/

    public void breed(int n) {
        for (int q = 0; q < lherb.size(); q++) {

            if (lherb.get(q).cx == City.lherb.get(n).cx && lherb.get(q).cy == City.lherb.get(n).cy) {

                if (lherb.get(q).btime >= 100 && lherb.get(q).gender != lherb.get(n).gender && lherb.get(q).gp == lherb.get(n).gp) {
                    rn = new Random();
                    int s = (lherb.get(q).str + lherb.get(n).str) / 2;

                    if (rn.nextBoolean()) {

                        s -= rn.nextInt(gvars);
                        if (s < 5) {
                            s = 5;
                        }
                    } else {
                        s += rn.nextInt(gvars);
                    }

                    rn = new Random();
                    int dx = (lherb.get(q).dex + lherb.get(n).dex) / 2;
                    if (rn.nextBoolean()) {

                        dx -= rn.nextInt(gvard);
                        if (dx < 5) {
                            dx = 5;
                        }
                    } else {
                        dx += rn.nextInt(gvard);
                    }

                    int hp = (lherb.get(q).mhp + lherb.get(n).mhp) / 2;
                    rn = new Random();

                    if (rn.nextBoolean()) {
                        hp -= rn.nextInt(gvarhp);
                        if (hp < 5) {
                            hp = 5;
                        }
                    } else {
                        hp += rn.nextInt(gvarhp);
                    }
                    lherb.get(q).btime = 0;
                    lherb.get(n).btime = 0;
                    if (City.lherb.get(n).gender) {
                        cn = City.lherb.get(n).gp;
                    } else {
                        cn = City.lherb.get(q).gp;
                    }
                    rn = new Random();
                    if (rn.nextBoolean()) {
                        City.newherb(City.lherb.get(n).cx, City.lherb.get(n).cy, s + 4, hp + 5, cn, true, dx);
                    } else {
                        City.newherb(City.lherb.get(n).cx, City.lherb.get(n).cy, s, hp, cn, false, dx);
                    }

                    brain(n);
                }

            }
        }
    }
    /*pause toggle*/

    public static void pause() {
        if (Screen.paused) {
            Screen.paused = false;

        } else {
            Screen.paused = true;

        }
    }
    /*checks wether forming group is possible*/

    public static void groupchk() {
        for (int i = 0; i < lherb.size(); i++) {
            int l = 0;
            for (int a = 0; a < groupi.size(); a++) {
                if (groupi.get(a).gnum == lherb.get(i).gp) {
                    l = a;
                }
            }
            if (lherb.get(i).gp > 0 && groups.get(l).size() >= gbud) {
                groupcount2(i);
            } else if (hsmem.get(i).size() >= gseed) {
                groupcount(i);
            }
        }
    }

    /*forms group*/
    public static void groupcount(int i) {
        gfc = 0;
        boolean m = false;
        boolean f = false;
        for (int x = 0; x < hsmem.get(i).size(); x++) {
            object cheese = (object) hsmem.get(i).get(x);
            if (cheese.fl >= gfriend) {
                for (int n = 0; n < lherb.size(); n++) {
                    if (City.lherb.get(n).num == cheese.iff) {
                        if (lherb.get(n).gp == 0 && 0 == lherb.get(i).gp) {
                            gfc++;

                            if (gfc >= gseed) {

                                groupform(i);
                                break;
                            }

                        }
                    }
                }

            }
        }
    }

    public static void groupcount2(int i) {
        gfc = 0;
        boolean m = false;
        boolean f = false;
        for (int x = 0; x < hsmem.get(i).size(); x++) {
            object cheese = (object) hsmem.get(i).get(x);
            if (cheese.fl >= gfriend2) {
                for (int n = 0; n < lherb.size(); n++) {
                    if (City.lherb.get(n).num == cheese.iff) {
                        if (lherb.get(n).gp > 0 && lherb.get(n).gp == lherb.get(i).gp) {
                            gfc++;

                            if (gfc >= gseed) {

                                groupform(i);
                                break;
                            }

                        }
                    }

                }

            }
        }
    }

    public static void groupform(int i) {
        gformi();

        for (int x = 0; x < hsmem.get(i).size(); x++) {
            object cheese = (object) hsmem.get(i).get(x);
            if (cheese.fl >= gfriend && lherb.get(i).gp == 0) {
                for (int n = 0; n < lherb.size(); n++) {
                    if (City.lherb.get(n).num == cheese.iff) {

                        if (lherb.get(n).gp == 0) {
                            addgmem(n);

                        }

                    }
                }
            }

            if (cheese.fl >= gfriend2 && lherb.get(i).gp > 0) {
                for (int n = 0; n < lherb.size(); n++) {
                    if (City.lherb.get(n).num == cheese.iff) {

                        if (lherb.get(n).gp > 0 && lherb.get(n).gp == lherb.get(i).gp) {
                            for (int y = 0; y < groups.size(); y++) {
                                for (int p = 0; p < groups.get(y).size(); p++) {
                                    object chee = (object) groups.get(y).get(p);
                                    if (groupi.get(y).gnum == lherb.get(n).gp && chee.num == lherb.get(n).num) {
                                        groups.get(y).remove(p);
                                    }

                                }
                            }
                            addgmem(n);

                        }

                    }
                }
            }

        }
        if (lherb.get(i).gp > 0) {
            for (int u = 0; u < groups.size(); u++) {
                for (int o = 0; o < groups.get(u).size(); o++) {
                    object cheey = (object) groups.get(u).get(o);
                    if (groupi.get(u).gnum == lherb.get(i).gp && cheey.num == lherb.get(i).num) {
                        groups.get(u).remove(o);
                    }
                }

            }

        }
        lherb.get(i).alpha = true;
        lherb.get(i).gp = gid;
        groups.get(groups.size() - 1).add(lherb.get(i));

    }

    public static void addgmem(int n) {

        System.out.println("grpid :  " + (groups.get(groups.size() - 1).size() - 1) + "grpid :  " + lherb.get(n).num);

        lherb.get(n).gp = groupi.get(groupi.size() - 1).gnum;

        groups.get(groups.size() - 1).add(lherb.get(n));

        for (int a = 0; a < hsmem.size(); a++) {
            for (int b = 0; b < hsmem.get(a).size(); b++) {
                object ches = (object) hsmem.get(a).get(b);
                if (ches.iff == City.lherb.get(n).num) {
                    ches.gn = lherb.get(n).gp;
                }

            }
        }
    }

    public static void gformi() {
        gid++;
        gi = new object();
        groupi.add(gi);
        groupi.get(groupi.size() - 1).gnum = gid;
        groupi.get(groupi.size() - 1).gx = rn.nextInt(255);
        groupi.get(groupi.size() - 1).gy = rn.nextInt(255);
        groupi.get(groupi.size() - 1).gz = rn.nextInt(255);
        group = new ArrayList();
        Navigation.gcol.add(new Rectangle(100, 76 + ((groupi.size() - 1) * 14), 45, 14));
        groups.add(group);
    }

    public static void groupdeathchk() {
        for (int i = 0; i < groups.size(); i++) {
            boolean m = false;
            boolean f = false;
            if (groups.get(i).size() < gseed) {
                groupdeath(i);
            } else {
                for (int k = 0; k < groups.get(i).size(); k++) {
                    object cheese = (object) groups.get(i).get(k);
                    if (cheese.gender) {
                        m = true;
                    } else if (!cheese.gender) {
                        f = true;
                    }
                }
                if (!f || !m) {
                    groupdeath(i);
                }

            }

        }
    }

    public static void groupdeath(int i) {
        for (int w = 0; w < lherb.size(); w++) {
            if (i == lherb.get(w).grpid) {

            }
            for (int q = 0; q < hsmem.size(); q++) {
                for (int p = 0; p < hsmem.get(q).size(); p++) {
                    object memo = (object) hsmem.get(q).get(p);
                    if (memo.iff == lherb.get(w).num) {
                        memo.gn = 0;
                    }
                }

            }
        }
        for (object lherb1 : lherb) {
            if (lherb1.grpid > i) {
                lherb1.grpid--;
            }
        }
        groups.remove(i);
        groupi.remove(i);
   
         Navigation.gcol.remove(i);
 
         
        

    }

    public static void variedit(int f, int i, int x) {
        varib[i][x].setText("" + f);
        aod = Integer.parseInt(varib[0][0].getText());
        bincm = Integer.parseInt(varib[1][0].getText());
        bincf = Integer.parseInt(varib[2][0].getText());
        gvard = Integer.parseInt(varib[3][0].getText());
        gvars = Integer.parseInt(varib[4][0].getText());
        gvarhp = Integer.parseInt(varib[5][0].getText());
        Brain.rdist = Integer.parseInt(varib[6][0].getText());

        hdecay = Integer.parseInt(varib[0][1].getText());
        tdecay = Integer.parseInt(varib[1][1].getText());
        Brain.fcheck = Integer.parseInt(varib[2][1].getText());
        Brain.tcheck = Integer.parseInt(varib[3][1].getText());
        Brain.desp = Integer.parseInt(varib[4][1].getText());
        hpregen = Integer.parseInt(varib[5][1].getText());
        Brain.fleehp = Integer.parseInt(varib[6][1].getText());

        gseed = Integer.parseInt(varib[0][2].getText());
        gfriend = Integer.parseInt(varib[1][2].getText());
        fregen = Integer.parseInt(varib[2][2].getText());
        wregen = Integer.parseInt(varib[3][2].getText());
        Brain.ogfrinc = Integer.parseInt(varib[4][2].getText());
        Brain.gfrinc = Integer.parseInt(varib[5][2].getText());
        Brain.frcomp
                = Integer.parseInt(varib[6][2].getText());

    }

    public static void overedit() {
        if (overcheck[0][0].isSelected()) {
            Navigation.overgend = true;
        } else {
            Navigation.overgend = false;
        }
        if (overcheck[0][1].isSelected()) {
            Navigation.overres = true;
        } else {
            Navigation.overres = false;
        }

    }
}
