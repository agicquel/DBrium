package view.elements;

import javax.swing.*;
import java.awt.*;
import view.interfaces.*;
import controller.*;
import model.ConnectDB;

/**
 * This class initializes the top part of the first part containing all the buttons 
 * that have an action directly on the WindowCreateDefinition.
 * This translation is a machine translation of the character string intended for the 
 * user in the JOptionPane.
 * Note that all buttons are added to a JToolBar.
 * @author M.Hervé;
 * @version 3.0;
 */
public class BarreCreationRequetes extends JPanel 
{

	/**
	 * They differents button of the JToolBar
	 */
	private JButton nouveau, rechercher, executer, effacer, newUser, createTable;

	/**
	 * The ActionListener of they buttons
	 */
	private ActionBarreCreationRequete a;

	/**
	 * The principal Frame.
	 */
	private DBFrame f;

	/**
	 * The space where all buttons are added
	 */
	private JToolBar jtb;

	/**
	 * A JComboBox where we can see the currentConnexion
	 */
	private JComboBox<ConnectDB> currentConnexion;

	/**
	 * This is the constructor of the class BarreCreationRequetes which initialize the button bar.
	 * @param f The principal Frame where this component will be add.
	 */

	public BarreCreationRequetes (DBFrame f) 
	{

		// Initialisation du JPanel de la classe BarreCreationRequete

		super(new GridLayout(1,11));

		this.f = f;

		// Création de l'ActionListener affecté aux boutons de la Barre

		a = new ActionBarreCreationRequete(f);

		// Création des boutons, personnalisation et ajout de l'ActionListener

		nouveau = new JButton(new ImageIcon("Image/New.png"));
		nouveau.addActionListener(a);
		nouveau.setBorderPainted(false);
		nouveau.setToolTipText("Nouveau Script");

		executer = new JButton(new ImageIcon("Image/Execution.png"));
		executer.addActionListener(a);
		executer.setBorderPainted(false);
		executer.setToolTipText("Execution du script");

		effacer = new JButton(new ImageIcon("Image/8.png"));
		effacer.addActionListener(a);
		effacer.setBorderPainted(false);
		effacer.setToolTipText("Effacer Script");

		rechercher = new JButton(new ImageIcon("Image/Search.png"));
		rechercher.addActionListener(a);
		rechercher.setBorderPainted(false);
		rechercher.setToolTipText("Search");

		createTable = new JButton(new ImageIcon("Image/ajouterTableBig.png"));
		createTable.addActionListener(a);
		createTable.setBorderPainted(false);
		createTable.setToolTipText("Créer une table");

		newUser = new JButton(new ImageIcon("Image/users.png"));
		newUser.addActionListener(a);
		newUser.setBorderPainted(false);
		newUser.setToolTipText("Nouvel utilisateur");

		currentConnexion = new JComboBox<ConnectDB>();
		for(ConnectDB c : f.getController().getConnexions())
			if(c.isConnected())
				currentConnexion.addItem(c);

		// Création des espaces entres le boutons et de la JToolBar

		JPanel space1, space2, space3, space4;
		
		jtb = new JToolBar();
		space1 = new JPanel();
		space2 = new JPanel();
		space3 = new JPanel();
		space4 = new JPanel();

		// Ajout des différents éléments

		jtb.add(nouveau);
		jtb.add(executer);
		jtb.add(rechercher);
		jtb.add(effacer);
		jtb.add(space1);
		jtb.add(space2);
		jtb.add(space3);
		jtb.add(space4);
		jtb.add(newUser);
		jtb.add(createTable);
		jtb.add(currentConnexion);

		this.add(jtb);

	}

   /**
    * @return the New Button
    */
	public JButton getNouveau () 
	{ 
		return this.nouveau; 
	}

	/**
    * @return the Execution Button
    */
	public JButton getExecuter () 
	{ 
		return this.executer; 
	}

   /**
    * @return the Search Button
    */
	public JButton getRechercher () 
	{ 
		return this.rechercher; 
	}

   /**
    * @return the Clean Button
    */
	public JButton getEffacer () 
	{
		return this.effacer; 
	}

   /**
    * @return the New User Button
    */
	public JButton getNewUser () 
	{ 
		return this. newUser; 
	}

   /**
    * @return the Create Table Button
    */
	public JButton getCreateTable () 
	{ 
		return this.createTable; 
	}

   /**
    * @return the ActionListener of the QueryCreationBar
    */
	public ActionBarreCreationRequete getActionBarre() 
	{ 
		return this.a; 
	}

   /**
    * @return the principal Frame
    */
	public DBFrame getFrame() 
	{ 
		return this.f; 
	}

   /**
    * @return the Combox which show the current connexion
    */
	public JComboBox<ConnectDB> getCurrentConnexion()
	{ 
		return this.currentConnexion;
	}
}