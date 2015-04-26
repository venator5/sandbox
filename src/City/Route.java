/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.Brain.brain;
import static City.City.lherb;
import static City.City.lnd;
import static City.City.mapsizey;
import static City.City.pcap;
import static City.Map.Bsize;
import static java.lang.Math.*;
import java.util.ArrayList;

public class Route {

    public static Node[][] nodes = new Node[City.mapsizex][City.mapsizey];

    public static ArrayList open = new ArrayList();
    public static ArrayList closed = new ArrayList();
    public static ArrayList steps = new ArrayList();
    public static boolean f = false;
    public static Node current;
    public static int na;
public static  boolean nod= false;
    public static void loop(int sx, int sy, int tx, int ty, int n) {

        Node();
        nod=true;
if(pcap[tx][ty]=false){
Brain.brain(n);
}
        steps.clear();
        closed.clear();
        open.clear();

        nodes[sx][sy].cost = 0;
        open.add(nodes[sx][sy]);


        while (!open.isEmpty()) {
            current = (Node) open.get(0);

         
            open.remove(current);
            closed.add(current);

            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {

                    if ((x == 0) && (y == 0)) {
                        continue;
                    }
                    int px = x + current.x;
                    int py = y + current.y;

                    if ((px > -1 && py > -1 && px < City.mapsizex && py < City.mapsizey)) {
                        if(City.pcap[px][py]=true){
                        int dis = (int) ((sqrt(((x - current.x) * (x - current.x)) + ((y - current.y) * (y - current.y)))) * 10);
                        int tar = (int) ((sqrt(((tx - current.x) * (tx - current.x)) + ((ty - current.y) * (ty - current.y)))) * 10);
                        float ccost = dis + tar + current.cost;
                        Node neighbour = nodes[px][py];
                        if (ccost < neighbour.cost) {
                            if (open.contains(neighbour)) {
                                open.remove(neighbour);
                            }
                            if (closed.contains(neighbour)) {
                                closed.remove(neighbour);
                            }

                        }
                        if ((!open.contains(neighbour)) && !(closed.contains(neighbour))) {
                            neighbour.cost = (int) ccost;
                            neighbour.parent = current;
                            open.add(neighbour);

                        }
                    }
                    }
                }

            }
        }
    

        Node target = nodes[tx][ty];
     ferror(target,sx,sy);
     
        steps.add(nodes[sx][sy]);
            City.mem = new ArrayList();
            for(int x=0;x<steps.size();x++){
    City.mem.add(steps.get(x));
    Node poo =(Node)steps.get(x);
   /*  System.out.println("X: " + poo.x+ "  Y: " + poo.y);*/
            }
        City.hmem.set(n, City.mem);
      
    
        for (int b = 0; b < steps.size(); b++) {
            Node cheese = (Node) steps.get(b);

            

        }
       brain(n);

    }

    
    public static void ferror(Node target,int sx,int sy){
     if (target != nodes[sx][sy]) {
            steps.add(target);
            target = target.parent;
             ferror(target,sx,sy);
        }
    }
    
    public static void Node() {
        for (int q = 0; q < City.mapsizex; q++) {
            for (int w = 0; w < City.mapsizey; w++) {
                nodes[q][w] = new Node(q, w);
            }
        }
    }
     public static int vx,vy,ox,oy;
              static double mdistance;
              
    public static void walk(int n) {
        if (City.hmem.get(n).size() > 0) {         
            Node cheese = (Node) City.hmem.get(n).get(City.hmem.get(n).size() - 1);
 lherb.get(n).oy=lherb.get(n).cy;
         lherb.get(n).ox=lherb.get(n).cx;
            lherb.get(n).cx = cheese.x;
            lherb.get(n).cy = cheese.y;
           lherb.get(n).walk=true;
             int p = 0;
            lherb.get(n).md=(float)((sqrt((lherb.get(n).cx - lherb.get(n).ox) * (lherb.get(n).cx - lherb.get(n).ox)) + ((lherb.get(n).cy - lherb.get(n).oy) * (lherb.get(n).cy - lherb.get(n).oy))) * Bsize );;
           City.hmem.get(n).remove(City.hmem.get(n).size() - 1);
        }

    }
}
