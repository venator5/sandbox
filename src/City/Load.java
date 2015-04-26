/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.Brain.rn;
import static City.City.City;
import static City.Frame.varib;
import static City.Frame.varii;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JTextField;

/**
 *
 * @author The Venator
 *
 * 
 */
public class Load {

    public static void Load(int q) throws IOException {
        Random r = new Random();
        try {
            Scanner s = new Scanner(new File("src/Sav/" + q + ".txt"));

            for (Block[] block : Map.block) {
                for (int x = 0; x < Map.block[0].length; x++) {
                    r = new Random();
                    int l = r.nextInt(4);
                    if (rn.nextBoolean() && l == 0) {
                        l = 2;
                    }
                    if (rn.nextBoolean() && l == 3) {
                        l = 2;
                    }
                    if (l == 2) {
                        l = 1;
                    }
                    block[x].id = l;
                }
            }
            for (int i = 0; i < City.lnd.length; i++) {

                for (int x = 0; x < City.lnd[0].length; x++) {
                    City.lnd[i][x] = new object();
                    City.lnd[i][x] = City.sqr[Map.block[i][x].id];
                    City.foodq[i][x] = City.lnd[i][x].mxfood;
                    City.waterq[i][x] = City.lnd[i][x].mxwater;
                    City.pcap[i][x] = City.lnd[i][x].blok;
                    City.lnd[i][x].mx = i;
                    City.lnd[i][x].my = x;
                    /*City.file(i,x,temp);*/

                }

            }
            s = new Scanner(new File("src/Sav/vnams.txt"));
            for (int i = 0; i < varib.length; i++) {

                for (int x = 0; x < varib[i].length; x++) {
                    String p = s.nextLine();
                    varii[i][x].setText(p);

                }
            }
                              
            Scanner j = new Scanner(new File("src/Sav/varis.txt"));
            for (JTextField[] varib1 : varib) {
          
                for (JTextField varib11 : varib1) {
                    int p = j.nextInt();
                    varib11.setText("" + p);
                }
            }

        } catch (Exception e) {

        }
    }
}
