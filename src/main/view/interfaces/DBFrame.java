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

import de.javasoft.plaf.synthetica.*;

/**
 * This is the class which initialyse the principal Frame.
 * We add all the elements on this frame.
 * @author m.hervé, t.furno, a.gicquel
 * @version 1.0
 */

public class DBFrame extends JFrame {

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

	/**
	 * This is the Accueil, most precisely the first page where you enter your password and your account nam
	 */
	
	private static Accueil a;

	private static JSplitPane j1, j2, j3;

	private static Controller controller;

	/**
	 * This is the constructor of the class Frame
	 */

	public DBFrame () {

		// initialisation de la fenêtre et ajout de ses caractéristiques

		super("DBrium - Gestionnaire de base de donnees");
		this.controller = new Controller(this);

		try {

    		setIconImage(ImageIO.read(new File("Image/DBrium.png")));
  		}

  		catch (IOException e) {

    		e.printStackTrace();
  		}

  		LookAndFeelInfo[]  listLF;
		listLF = UIManager.getInstalledLookAndFeels();

		try {

			UIManager.setLookAndFeel(listLF[1].getClassName());
			//UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
			//UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}

		catch (ClassNotFoundException c) {

			c.printStackTrace();
		}

		catch (InstantiationException i) {

			i.printStackTrace();
		}

		catch (IllegalAccessException b) {

			b.printStackTrace();
		}

		catch (UnsupportedLookAndFeelException r) {

			r.printStackTrace();
		}

		/*for (int i = 0; i < listLF.length; i++) {

			System.out.println(listLF[i]. getName ()+" - " + listLF[i].getClassName());
		}*/

		this.getContentPane().setLayout(new BorderLayout());

    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	this.setPreferredSize(new Dimension(800,800));

   		this.setResizable(true);

   		// Création de l'accueil

   		//a = new Accueil(this);

   		// Création des élements de la fenêtre

   		bcr = new BarreCreationRequetes(this);
      	fcr = new FenetreCreationRequetes(this);
     	bg = new BarreGauche(this);
   		menu = new view.elements.Menu(this);

   		// Création du Panel où l'on ajoute les élements

   		p = new JPanel(new BorderLayout(1,1));
   		p.setBackground(Color.black);

   		j1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bg, fcr);
   		//j2 = new 

   		// Ajout du menu et du Panel contenant les élements à la fenêtre

   		this.add(menu, BorderLayout.NORTH);
   		this.add(p, BorderLayout.CENTER);

   		// Ajout des élements au Panel

   		p.add(bcr, BorderLayout.NORTH);
   		p.add(j1, BorderLayout.CENTER);
   		//p.add(bg, BorderLayout.WEST);

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

	public BarreGauche getBarreGauche() { return this.bg; }
	public Accueil getAccueil() { return this.a; }
	public FenetreCreationRequetes getFenetre() { return this.fcr; }
	public BarreCreationRequetes getBarreRequete() { return this.bcr; }
	public view.elements.Menu getMenu() { return this.menu;}

}
