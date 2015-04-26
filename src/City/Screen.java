/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package City;

import static City.City.City;
import static City.Frame.overcheck;
import static City.Frame.srn;
import static City.Frame.varib;
import static City.Frame.varii;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author The Venator
 */
public class Screen extends JPanel implements Runnable {

    Thread thrd = new Thread(this);


    /*Image arrays*/
    /*tile images*/
    public static Image[] bimg = new Image[6];
    public static Image[] mimg = new Image[15];
    public static Image[] bmimg = new Image[6];
    public static JTextField chp = new JTextField("20");
    public static JTextField cstr = new JTextField("10");
    public static JTextField cdex = new JTextField("10");
    ButtonGroup mf = new ButtonGroup();
    
   JRadioButton cm = new JRadioButton("Male");
     JRadioButton cf = new JRadioButton("Female");
     
    public static Image[] crep = new Image[2];
    public static JPanel cmenu;
    public static JPanel varip;
    public static JPanel overp;
    static boolean paused = true;
    /*mouse location initialisation*/
    public static Point mou = new Point(0, 0);
    /*first run  boolean*/
    public Boolean fir = true;
    /*window height and width integers*/
    public static int w, h, w2, h2;

    String p;

    boolean rbo = true;
    public static Map mp;
    public static Navigation navi;

    /*statinf thread and adding inputs*/
    public Screen(Frame frame) {
        frame.addMouseListener(new Kyhndlr());
        frame.addMouseMotionListener(new Kyhndlr());
        frame.addKeyListener(new Kyhndlr());
        frame.addMouseWheelListener(new Kyhndlr());

        thrd.start();
    }
    /*Refresh*/

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                thrd.sleep(40);
            } catch (Exception e) {
            }
        }
    }


    /*main graphic section*/
    @Override
    public void paintComponent(Graphics g) {

        if (fir) {
            w = getWidth();
            h = getHeight();
  cm.setSelected(true);

            cmenu = new JPanel();
            varip = new JPanel();
            overp = new JPanel();

     
            varip.setBackground(Color.white);
            overp.setBackground(Color.white);
            srn.add(cmenu);
            srn.add(varip);
            srn.add(overp);
            cmenu.setBounds(w - 120, h - 200, 110, 200);
            varip.setBounds(w / 2 - 200, h / 2 - 100, 400, 200);
            overp.setBounds(w / 2 - 150, h / 2 - 100, 300, 200);
            varip.setLayout(new GridLayout(8, 6));
            overp.setLayout(new GridLayout(5, 3));
            cmenu.setLayout(new GridLayout(6, 1));
mf.add(cm);
mf.add(cf);
            cmenu.add(cm);
            cmenu.add(cf);
            cmenu.add(chp);
            cmenu.add(cstr);
            cmenu.add(cdex);
            for (int i = 0; i < overcheck.length; i++) {

                for (int x = 0; x < overcheck[i].length; x++) {
                    overcheck[i][x] = new JCheckBox();
                    srn.overp.add(overcheck[i][x]);

                }
            }
            overcheck[0][0].setLabel("Gender");
            overcheck[0][1].setLabel("Resources");
            overcheck[0][2].setLabel("Friends");
            for (int i = 0; i < varib.length; i++) {

                for (int x = 0; x < varib[i].length; x++) {
                    varii[i][x] = new JLabel();
                    varib[i][x] = new JTextField(3);

                    srn.varip.add(varii[i][x]);
                    srn.varip.add(varib[i][x]);

                }
            }
    chp.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent ae) {

                      Map.ch= Integer.parseInt(chp.getText());;
                        }

                    });    
        cstr.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent ae) {

                      Map.cst= Integer.parseInt(cstr.getText());;
                        }

                    });   
                    cdex.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent ae) {

                      Map.cde= Integer.parseInt(cdex.getText());;
                        }

                    }); 
cm.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent ae) {

                      Map.cg= true;
                        }

                    });
cf.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent ae) {

                      Map.cg= false;
                        }

                    });
            for (int i = 0; i < overcheck.length; i++) {

                for (int x = 0; x < overcheck[i].length; x++) {
                    overcheck[i][x].addItemListener(new ItemListener() {
                        public void itemStateChanged(ItemEvent e) {
                            City.overedit();

                        }
                    });
                }
            }

            for (int i = 0; i < varib.length; i++) {

                for (int x = 0; x < varib[i].length; x++) {
                    int f = 0;
                    int h = x;
                    int j = i;
                    varib[i][x].addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent ae) {

                            int f = Integer.parseInt(varib[j][h].getText());
                            City.variedit(f, j, h);
                        }

                    });
                }
            }

            /*  add(Inputs.input(),Inputs.c); */
            try {
                /*defines all graphics*/
                define();
            } catch (IOException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }

            fir = false;

        }
        /*sets background to black*/
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, getWidth(), getHeight());
        /*for live window size update*/
        w2 = getWidth();
        h2 = getHeight();
        cmenu.setBounds(w2 - 130, h2 - 190, 115, 170);
        varip.setBounds(w2 / 2 - 230, h2 / 2 - 100, 460, 200);
        overp.setBounds(w2 / 2 - 150, h2 / 2 - 100, 300, 200);
        /*calls clock*/
        if (!paused) {
            City.timer();
        }
        /*draws tilemap*/

        mp.draw(g);
        navi.draw(g);

        /*draws main menu and stats*/
        /*draws build menu*/
        if (Map.overlay) {
            overp.setVisible(true);
        } else {
            overp.setVisible(false);
        }

        if (Map.vari) {

            varip.setVisible(true);

        } else {

            varip.setVisible(false);

        }
        if (Navigation.cc) {

            cmenu.setVisible(true);

        } else {

            cmenu.setVisible(false);

        }

    }

    public void define() throws IOException {
        mp = new Map();

        navi = new Navigation();

        Load.Load(1);
        srn.validate();
        srn.repaint();
        /*loads tile images into array*/
        for (int i = 0; i < bimg.length; i++) {
            bimg[i] = new ImageIcon("src/sause/barbut.jpg").getImage();
            bimg[i] = createImage(new FilteredImageSource(bimg[i].getSource(), new CropImageFilter(0, 64 * i, 64, 64)));

        }
        for (int i = 0; i < bmimg.length; i++) {
            bmimg[i] = new ImageIcon("src/sause/blobsym.png").getImage();
            bmimg[i] = createImage(new FilteredImageSource(bmimg[i].getSource(), new CropImageFilter(0, 64 * i, 64, 64)));

        }
        for (int i = 0; i < mimg.length; i++) {
            mimg[i] = new ImageIcon("src/sause/ti2.png").getImage();
            mimg[i] = createImage(new FilteredImageSource(mimg[i].getSource(), new CropImageFilter(0, 32 * i, 32, 32)));

        }
        for (int i = 0; i < crep.length; i++) {
            crep[i] = new ImageIcon("src/sause/creatures.png").getImage();
            crep[i] = createImage(new FilteredImageSource(crep[i].getSource(), new CropImageFilter(0, 16 * i, 16, 16)));

        }
        /*loads map from file*/
        /*writes each tile info to tile info array*/

        for (int i = 0; i < City.lnd.length; i++) {

            for (int x = 0; x < City.lnd[0].length; x++) {

                City.lnd[i][x] = City.sqr[Map.block[i][x].id];
                City.lnd[i][x].mx = i;
                City.lnd[i][x].my = x;
                /*City.file(i,x,temp);*/

            }

        }
    }

}
