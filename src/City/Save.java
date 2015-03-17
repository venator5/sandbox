/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author The Venator
 */
public class Save {

    BufferedWriter writer = null;

    public void Save1(int s) throws IOException {
        try {
            File file = new File("src/Sav/" + s + ".txt");
            writer = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < Map.block.length; i++) {

                for (int x = 0; x < Map.block[0].length; x++) {
                    int c = Map.block[i][x].id;
                    writer.write(c + " ");
                    ;
                }
                writer.newLine();
            }

        

        } catch (Exception e) {
        } finally {
            if (writer != null) {
                writer.close();
            }

        }

    }
}
