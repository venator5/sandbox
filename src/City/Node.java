/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.City.lherb;
import static City.City.mapsizex;
import static City.City.mapsizey;
import static City.Route.nodes;
import static City.Route.steps;

/**
 *
 * @author The Venator
 */
public class Node  {
                      		 int x;
	  int y;
 int cost;
	Node parent;
       public  Node(int x, int y) {
			this.x = x;
			this.y = y;
		}         
      
        public void walk(int n){
         Node cheese = (Node) steps.get(0);
         lherb.get(n).cx =cheese.x;
                 lherb.get(n).cy = cheese.y;
        
        }
}
