package City;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Frame extends JFrame {

    public static String gnam = "Sim Shitty";
    public static Dimension res = new Dimension(1024, 768);

    /*General Frame details*/
    public void Frame() {

        setTitle(gnam);
        setSize(res);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImages(City.icons);
        setVisible(true);

        init();

    }

    /*screen initaliser*/
    public void init() {

        Screen srn = new Screen(this);
        add(srn);

    }

}
