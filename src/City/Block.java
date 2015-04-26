/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;


import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author The Venator
 */
public class Block extends Rectangle {

    City ct = new City();
    int id;

    /*defines size location and type opf each block*/
    public Block(int x, int y, int width, int height, int id) {
          setBounds(x, y, width, height);
        this.id = id;

    }
    /*draws tiles and grid*/

    public void draw(Graphics g) {

        g.drawImage(Screen.mimg[id], x, y, Map.Bsize, Map.Bsize, null);
        
    }

}

