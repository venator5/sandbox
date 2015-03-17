/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author The Venator
 * 
 * int[] array = new int[s.nextInt()]; for(int i = 0; i < array.length; i++)
 * array[i] = s.nextInt();
 */
public class Load {

    public static void Load(int q) throws IOException {
        System.out.println("qwertyuiop");
        try {
            Scanner s = new Scanner(new File("src/Sav/" + q + ".txt"));

            for (int i = 0; i < Map.block.length; i++) {

                for (int x = 0; x < Map.block[0].length; x++) {
                    Map.block[i][x].id = s.nextInt();

                }
            }
           
        } catch (Exception e) {

        }
    }
}
