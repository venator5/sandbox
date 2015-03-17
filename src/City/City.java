/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;


import static City.City.lnd;
import static City.Map.block;
import static City.Route.nodes;
import static City.Route.steps;

import java.awt.Image;
import java.io.IOException;
import static java.lang.System.load;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.Node;
import javax.swing.ImageIcon;

/**
 *
 * @author The Venator
 */

/**/
public class City {

   

    double wpop = 0.8;

    public static int wout;
    public static int res = 0;
    /*time integers for time();*/
    public static int t = 0;
    public static int mon = 0;
    public static int yr = 1;
    public static String[] month = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov,", "Dec"};
  
    static City City = new City();
    static Frame fra = new Frame();

    Scanner input = new Scanner(System.in);
    public static int mapsizex = 20;
    public static int mapsizey = 20;
    /*stores map tiles information*/
    static object[][] lnd = new object[mapsizex][mapsizey];
    
    /*stores tile type information*/
    public static object[] sqr = new object[4];
    public object herb;
   
    public static object get = new object();
       public static Brain b = new Brain();

    public static Object[] pa = new Object[8];
    public static Path path1 = Paths.get("src/Sav/1.txt");
    public static Path path2 = Paths.get("src/Sav/2.txt");
    public static Path path3 = Paths.get("src/Sav/3.txt");
    public static Path path4 = Paths.get("src/Sav/4.txt");
    public static Path path5 = Paths.get("src/Sav/5.txt");
    public static Path path6 = Paths.get("src/Sav/6.txt");
    public static Path path7 = Paths.get("src/Sav/7.txt");
    public static Path path8 = Paths.get("src/Sav/8.txt");

    public static List<Image> icons;
       
    public static List<object> lherb;
   public static     ArrayList<ArrayList> hmem = new ArrayList<>();
 public static ArrayList mem = new ArrayList();
    public static     ArrayList<ArrayList> hlmem = new ArrayList<>();
 public static ArrayList<ArrayList> lmem = new ArrayList<>();
    public static int[] lval = new int[3];
    /*building ints( x,y,buiding type)*/

    

    Random rn = new Random();

    /*THE START*/
    public static void main(String[] args) throws IOException {

        City.mapc();
        Load.Load(1);
        fra.Frame();
     
    }
    /*loads info*/

    public void mapc() {
        /* System.out.println("Set width of map");
         mapsizex = input.nextInt();
         System.out.println("Set height of map");
         mapsizey = input.nextInt(); 
         
        
         */
        for (int i = 0; i < sqr.length; ++i) {
            sqr[i] = new object();
            sqr[i].tile = get.letter[i];
            sqr[i].tident = get.tid[i];
            sqr[i].tnam = get.nam[i];
            sqr[i].cfood = get.food[i];
         sqr[i].blok = get.block[i];
            sqr[i].water = get.twater[i];
            sqr[i].parent = null;
          
          
        }
 
        
        
        icons = new ArrayList<>();
        icons.add(new ImageIcon("src/sause/gov.png").getImage());
lherb = new ArrayList<>();
newherb(15,2);



System.out.println("zero:   " +lherb.get(0).num);
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


   public static int tick = 80;
   int k = 0;
    /*game clock*/
    public void timer() {
        t++;
     

        if (t > tick) {
            t = 0;
            for(int x=0;x<lherb.size();x++)
            { if(City.hlmem.get(x).isEmpty()){
                 lval = new int[3];
                        lval[0] = 3;
                        lval[1] = 2;
                        lval[2] = 2;
                        City.hlmem.get(x).add(lval);
                  
            }
                lherb.get(x).treset=true;
                   lherb.get(x).thir -=2;
            lherb.get(x).hun -=2;
          
        lherb.get(x).hun += lnd[lherb.get(x).cy][lherb.get(x).cx].cfood;
        lherb.get(x).thir += lnd[lherb.get(x).cy][lherb.get(x).cx].water;
          if(lherb.get(x).hun<0||lherb.get(x).thir<0){
            lherb.remove(x);
            if(Map.cstat ==x){
            Map.cstat=-1;
            }
            }else{
         max(x);
        b.Brain(x);
          }
        }
         
          
        }
        

    }
public void max(int x){

if(lherb.get(x).hun>lherb.get(x).mhun){
lherb.get(x).hun=lherb.get(x).mhun;
}
if(lherb.get(x).thir>lherb.get(x).mthir){
lherb.get(x).thir=lherb.get(x).mthir;
}
}
   public void newherb(int x, int y){
       herb = new object();
   lherb.add(herb);
lherb.get(lherb.size()-1).num =get.popid;
lherb.get(lherb.size()-1).hun=get.hun1[0];
lherb.get(lherb.size()-1).mhun=get.mhun1[0];
lherb.get(lherb.size()-1).thir=get.thir1[0];
lherb.get(lherb.size()-1).mthir=get.mthir1[0];
lherb.get(lherb.size()-1).ox=x;
lherb.get(lherb.size()-1).oy=y;
lherb.get(lherb.size()-1).cx=x;
lherb.get(lherb.size()-1).cy=y;

lherb.get(lherb.size()-1).tt=  get.tt;
lherb.get(lherb.size()-1).priority=   get.priority;
lherb.get(lherb.size()-1).treset=true;
hmem.add(mem);
hlmem.add(lmem);


get.popid++; 
  for(int d=0;d<lherb.size();d++)
            { 
              System.out.println(lherb.get(d).num);
        }
   }
}
