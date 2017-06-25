package view.interfaces;

import view.elements.*;
import javax.swing.*;
import java.awt.*;
import util.*;
import controller.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.UIManager.*;

import com.alee.laf.*;

//import de.javasoft.plaf.synthetica.*;

/**
 * This is the class which initialyse the principal Frame.
 * We add all the elements on this frame.
 * @author m.hervé, t.furno, a.gicquel
 * @version 1.0
 */

public class DBFrame extends JFrame 
{

	/**
	 * The BarreCreationRequete which is add to the Center of the Frame
	 */

	private static BarreCreationRequetes bcr;

	/**
	 * The FenetreCreationRequete which is add to the Center of the Frame
	 */

	private static FenetreCreationRequetes fcr;

	/**
	 * The BarreGauche which is add to The left of the Frame
	 */

	private static BarreGauche bg;

	/**
	 * The Menu which is add to the top of the Frame
	 */

	private static view.elements.Menu menu;

	/**
	 * The JPanel which is add at the Frame and which we add they elements of the Frame
	 */

	private static JPanel p;

	private static JSplitPane j1, j2, j3;

	private static Controller controller;

	/**
	 * This is the constructor of the class Frame
	 */

	public DBFrame () 
	{

		// initialisation de la fenêtre et ajout de ses caractéristiques

		super("DBrium - Gestionnaire de base de donnees");
		this.controller = new Controller(this);
		WebLookAndFeel.install(); // theme

		this.getContentPane().setLayout(new BorderLayout());

    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	this.setPreferredSize(new Dimension(800,800));

   		this.setResizable(true);


   		// Création des élements de la fenêtre

   		bcr = new BarreCreationRequetes(this);
      	fcr = new FenetreCreationRequetes(this);
     	bg = new BarreGauche(this);
   		menu = new view.elements.Menu(this);

   		// Création du Panel où l'on ajoute les élements

   		p = new JPanel(new BorderLayout(1,1));
   		p.setBackground(Color.black);

   		j1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bg, fcr);

   		// Ajout du menu et du Panel contenant les élements à la fenêtre

   		this.add(menu, BorderLayout.NORTH);
   		this.add(p, BorderLayout.CENTER);

   		// Ajout des élements au Panel

   		p.add(bcr, BorderLayout.NORTH);
   		p.add(j1, BorderLayout.CENTER);
   		//p.add(bg, BorderLayout.WEST);

   		try
   		{
   			this. setIconImage(ImageIO.read(getClass().getResourceAsStream(File.separator + "res" + File.separator + "img" + File.separator + "DBrium.png")));
   		}
   		catch(Exception err){}
   		
   		this.pack();
   		this.setVisible(true);
   		this.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(this.MAXIMIZED_BOTH);
	}

	// Les getters pour les différents éléments de la fenetre

	public Controller getController()
	{
		return this.controller;
	}

	public BarreGauche getBarreGauche() 
	{ 
		return this.bg; 
	}

	public FenetreCreationRequetes getFenetre() 
	{ 
		return this.fcr; 
	}

	public BarreCreationRequetes getBarreRequete() 
	{ 
		return this.bcr; 
	}

	public view.elements.Menu getMenu() 
	{ 
		return this.menu;
	}
}
