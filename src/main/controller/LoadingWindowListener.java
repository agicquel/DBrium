package controller;

import view.elements.*;
import view.interfaces.*;

import javax.swing.*;
import java.awt.event.*;
import util.*;

/**
* Listener of the loading frame used on the start of the app
*/
public class LoadingWindowListener implements WindowListener 
{
    private LoadingWindow w;

    public LoadingWindowListener (LoadingWindow w) 
    {
        this.w = w;
    }

    public void windowOpened (WindowEvent e) 
    {
        Thread t = new Thread(w.getTr());
    }

    public void windowClosing(WindowEvent e){}

    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e){}

    public void windowDeiconified(WindowEvent e){}

    public void windowActivated(WindowEvent e){}

    public void windowDeactivated(WindowEvent e){}

    public void windowGainedFocus(WindowEvent e){}

    public void windowLostFocus(WindowEvent e){}

    public void windowStateChanged(WindowEvent e){}
}