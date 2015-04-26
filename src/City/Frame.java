package City;

import static City.Screen.h;
import static City.Screen.w;

import java.awt.Dimension;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;

public class Frame extends JFrame {

    public static String gnam = "Sim Shitty";
    public static Dimension res = new Dimension(1024, 768);
  static GraphicsDevice device = GraphicsEnvironment
        .getLocalGraphicsEnvironment().getScreenDevices()[0];
    
        public static JTextField[][] varib = new JTextField[7][3];
         public static JLabel[][] varii = new JLabel[7][3];
         public static JCheckBox[][] overcheck = new JCheckBox[4][3];
         
          public static Screen srn;
    
    /*General Frame details*/
    public void Frame() {

        setTitle(gnam);
        setSize(res);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImages(City.icons);
 
        init();
     setVisible(true);

    }

    /*screen initaliser*/
    public void init() {

        srn = new Screen(this);
          srn.setLayout(null);
    w = getWidth();
            h = getHeight();


           
        add(srn);
    

    }

}
