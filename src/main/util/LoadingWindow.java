package util;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import controller.*;
import view.elements.*;
import view.interfaces.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class LoadingWindow extends JWindow{

  private Thread t;

  private JProgressBar bar;

  private Loading tr;
   

  public LoadingWindow(){     

    setPreferredSize(new Dimension(1000, 600)); 
    ClassLoader classLoader = getClass().getClassLoader();

    try {
      setIconImage(ImageIO.read(getClass().getResourceAsStream(File.separator + "res" + File.separator + "img" + File.separator + "DBrium.png")));
    } 
    catch (IOException e) {
      e.printStackTrace();
    }

    tr = new Loading(this);      

    t = new Thread(new Loading(this));

    bar = new JProgressBar();

    bar.setMaximum(500);

    bar.setMinimum(0);

    bar.setStringPainted(true);

    this.addWindowListener(new LoadingWindowListener(this));

    try
    {
      this.getContentPane().add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(File.separator + "res" + File.separator + "img" + File.separator + "DBrium.png")))), BorderLayout.CENTER);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  
    this.getContentPane().setBackground(new Color(0, 33, 68));      

    this.getContentPane().add(bar, BorderLayout.SOUTH);      

    t.start();      

    this.pack();
    this.setVisible(true);  
    this.setLocationRelativeTo(null);    

  }


  public class Loading implements Runnable{   

    LoadingWindow w;

    public Loading (LoadingWindow w) {

      this.w = w;

    }
    public void run(){


      for(int val = 0; val <= 500; val++){

        bar.setValue(val);

        if(val == 500) {

          w.dispose();
          DBFrame f = new DBFrame();
          f.setVisible(true);
          f.setLocationRelativeTo(null);
        }

        try {

          t.sleep(5);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }

    }   

  }


  public Thread getThread() { return this.t; }
  public JProgressBar getProgressBar() { return this.bar; }  
  public Loading getTr () { return this.tr; }

}