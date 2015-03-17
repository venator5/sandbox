/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;


import java.util.ArrayList;

/**
 *
 * @author The Venator
 */
public class object {

    /*System.out.print("Check please");*/
    
int mx;
 int tt=100000;
     int priority=10000000;
     int ox ,oy;
     int ax,ay;
     boolean treset=true;
        int my;
    String tile;
    String tnam;
    int tval;
    int tident;
    int cfood;
    int water;
    int blok;
    object parent;
    double md;
int num;
int hun;
int mhun;
int thir;
int mthir;
int cx;
int cy;
       
  int num1[]={1};
int hun1[]={40};
int mhun1[]={100};
int thir1[]={80};
int mthir1[]={100};
int cx1[]={2};
int cy1[]={2};
int popid=0;       

    String letter[] = {"W", "L", "R", "F"};
    String nam[] = {"Land", "Forest", "Rock", "Water"};
    int tid[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,12,13,14};
    int food[] = {0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0};
    int[] twater = {50, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0,0,0,0};
   int block[] = {1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,};
    /*max hunger, hunger, max thirst,thirst*/

    String[] valnam = {"Pop", "Food", "Wood", "Tools", "Workers", "Money"};
    int value[] = {70, 600, 400, 30, 0, 1000};
    int max[] = {60, 600, 400, 60, 70, 0};
    int wpool;

 
}
