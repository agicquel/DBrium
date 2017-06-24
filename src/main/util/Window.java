package util;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import controller.*;
import view.elements.*;
import view.interfaces.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Window extends JWindow{

  private Thread t;

  private JProgressBar bar;

  private Traitement tr;
   

  public Window(){     

    setPreferredSize(new Dimension(1000, 600)); 

    try {

      setIconImage(ImageIO.read(new File("Image/DBrium.png")));
    } 

    catch (IOException e) {

        
      e.printStackTrace();
      
    }

    tr = new Traitement(this);      

    t = new Thread(new Traitement(this));

    bar = new JProgressBar();

    bar.setMaximum(500);

    bar.setMinimum(0);

    bar.setStringPainted(true);

    this.addWindowListener(new ActionWindow(this));

    this.getContentPane().add(new JLabel(new ImageIcon("Image/DBrium.png")), BorderLayout.CENTER);
  
    this.getContentPane().setBackground(new Color(0, 33, 68));      

    this.getContentPane().add(bar, BorderLayout.SOUTH);      

    t.start();      

    this.pack();
    this.setVisible(true);  
    this.setLocationRelativeTo(null);    

  }


  public class Traitement implements Runnable{   

    Window w;

    public Traitement (Window w) {

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

          // TODO Auto-generated catch block

        e.printStackTrace();

        }

      }

    }   

  }


  public Thread getThread() { return this.t; }
  public JProgressBar getProgressBar() { return this.bar; }  
  public Traitement getTr () { return this.tr; }

}