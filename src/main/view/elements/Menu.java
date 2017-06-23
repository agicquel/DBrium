/**
* Button for the file popupButton of the menu
* extends JPopUpMenu
* @author Tristan Furno
* @version 1.0
*/

package view.elements;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.*;
import java.awt.*;

import view.interfaces.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;

import controller.*;


public class Menu extends JPanel{

  /**
  * Menu bar contain all of Menu
  */
  private JMenuBar menuBar;

  /**
  * File menu
  */
  private JMenu fileMenu;

  /**
  * Edit Menu
  */
  private JMenu editMenu;

  /**
  * Find menu
  */
  private JMenu findMenu;

  /**
  * Help menu
  */
  private JMenu helpMenu;

  /**
  * View controller menu
  */
  private JMenu view;

  /**
  * All of items for all of menus in order for all menus
  */
  private JMenuItem newFile, newWindow, open, save, saveAs, settings, quit, undo, redo, copy, paste, cut, selectA, fullScreen, find, manual, about;

  /**
  * Action for the menu item
  */
  private ActionMenu a;

  /**
  * The frame of the menu
  */
  private DBFrame frame;


  /**
  * Constructor of the class PanelMenu. Use in the top of the file. No param to use de menuBar
  * @param f the frame use
  */
  public Menu(DBFrame f){
    super(new GridLayout(1,1));
    this.frame = f;
    a = new ActionMenu(f);




    //Creation of the BarMenu
    this.menuBar = new JMenuBar();
    this.add(menuBar);

    //Creation of the menu file
    this.fileMenu = new JMenu("Fichier");
    //this.fileMenu.setMnemonic(KeyEvent.VK_A);
    //this.fileMenu.getAccessibleContext().setAccessibleDescription("Menu Fichier");
    this.menuBar.add(fileMenu);

    //Add of the items in the file menu
    //newWindow
    this.newFile = new JMenuItem("Nouveau fichier", KeyEvent.VK_T);
    this.newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
    this.newFile.addActionListener(a);
    this.fileMenu.add(this.newFile);

    //NewFile
    this.newWindow = new JMenuItem("Nouvelle fenetre", KeyEvent.VK_N);
    this.newWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    this.newWindow.addActionListener(a);
    this.fileMenu.add(this.newWindow);

    //open file
    this.open = new JMenuItem("Ouvrir", KeyEvent.VK_O);
    this.open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
    this.open.addActionListener(a);
    this.fileMenu.add(this.open);

    //Add a separator
    this.fileMenu.addSeparator();

    //save
    this.save = new JMenuItem("Enregistrer", KeyEvent.VK_S);
    this.save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    this.save.addActionListener(a);
    this.fileMenu.add(this.save);

    //save as
    this.saveAs = new JMenuItem("Enregistrer sous");
    this.saveAs.addActionListener(a);
    this.fileMenu.add(this.saveAs);



    //Add a separator
    //this.fileMenu.addSeparator();

    //settings
    this.settings = new JMenuItem("Parametres", KeyEvent.VK_P);
    this.settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
    this.settings.addActionListener(a);
    //this.fileMenu.add(this.settings);

    //Add a separator
    this.fileMenu.addSeparator();

    //quit
    this.quit = new JMenuItem("Quitter", KeyEvent.VK_W);
    this.quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
    this.fileMenu.add(this.quit);
    this.quit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
                System.exit(0);
        }
    });

    //Creation of the menu Edit
    this.editMenu = new JMenu("Editer");
    //this.fileMenu.setMnemonic(KeyEvent.VK_A);
    //this.fileMenu.getAccessibleContext().setAccessibleDescription("Menu Fichier");
    this.menuBar.add(this.editMenu);

    this.undo = new JMenuItem("Annuler", KeyEvent.VK_Z);
    this.undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
    this.undo.addActionListener(a);
    this.editMenu.add(this.undo);

    this.redo = new JMenuItem("Retablir", KeyEvent.VK_Y);
    this.redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
    this.redo.addActionListener(a);
    this.editMenu.add(this.redo);

    //Add of a separator
    this.editMenu.addSeparator();

    this.copy = new JMenuItem("Copier", KeyEvent.VK_C);
    this.copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
    this.copy.addActionListener(a);
    this.editMenu.add(this.copy);

    this.paste = new JMenuItem("Coller", KeyEvent.VK_V);
    this.paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
    this.paste.addActionListener(a);
    this.editMenu.add(this.paste);

    this.cut = new JMenuItem("Couper", KeyEvent.VK_X);
    this.cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
    this.cut.addActionListener(a);
    this.editMenu.add(this.cut);

    this.selectA = new JMenuItem("Tout selectionner", KeyEvent.VK_A);
    this.selectA.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    this.selectA.addActionListener(a);
    this.editMenu.add(this.selectA);

    //Add of a separator
    this.editMenu.addSeparator();

    //full screen
    this.fullScreen = new JMenuItem("Plein Ecran");
    this.fullScreen.addActionListener(a);
    this.editMenu.add(this.fullScreen);

    //Creation of the menu find
    this.findMenu = new JMenu("Rechercher");
    this.menuBar.add(this.findMenu);

    //Add the menu items

    //find
    this.find = new JMenuItem("Recherche", KeyEvent.VK_F);
    this.find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
    this.find.addActionListener(a);
    this.findMenu.add(this.find);

    //Creation of the help menu
    this.helpMenu = new JMenu("Aide");
    this.menuBar.add(this.helpMenu);

    //Add the menu items

    //manual
    this.manual = new JMenuItem("Manuel");
    this.manual.addActionListener(a);
    this.helpMenu.add(this.manual);

    //about
    this.about = new JMenuItem("A propos");
    this.about.addActionListener(a);
    this.helpMenu.add(this.about);


  }


  //Getters of the class Menu for the menu items
   /**
   * @return the menu item save as
   */
   public JMenuItem getSaveAs(){
    return this.saveAs;
  }

   /**
   * @return the menu item new file
   */
   public JMenuItem getNewFile(){
     return this.newFile;
   }

   /**
   * @return the menu item newWindow
   */
   public JMenuItem getNewWindow(){
    return this.newWindow;
   }

   /**
   * @return the menu item open
   */
   public JMenuItem getOpen(){
    return this.open;
   }

   /**
   * @return the menu item save
   */
   public JMenuItem getSave(){
    return this.save;
   }

   /**
   * @return the menu item settings
   */
   public JMenuItem getSettings(){
    return this.settings;
   }

   /**
   * @return the menu item quit
   */
   public JMenuItem getQuit(){
    return this.quit;
   }

   /**
   * @return the menu item undo
   */
   public JMenuItem getUndo(){
    return this.undo;
   }

   /**
   * @return the menu item redo
   */
   public JMenuItem getRedo(){
    return this.redo;
   }

   /**
   * @return the menu item copy
   */
   public JMenuItem getCopy(){
     return this.copy;
   }

   /**
   * @return the menu item paste
   */
   public JMenuItem getPaste(){
     return this.paste;
   }

   /**
   * @return the menu item cut
   */
   public JMenuItem getCut(){
     return this.cut;
   }

   /**
   * @return the menu item selectA
   */
   public JMenuItem getSelectA(){
     return this.selectA;
   }

   /**
   * @return the menu item fullScreen
   */
   public JMenuItem getFullScreen(){
    return this.fullScreen;
   }

   /**
   * @return the menu item find
   */
   public JMenuItem getFind(){
    return this.find;
   }

   /**
   * @return the menu item manual
   */
   public JMenuItem getManual(){
    return this.manual;
   }

   /**
   * @return the menu item about
   */
   public JMenuItem getAbout(){
    return this.about;
   }

 }
