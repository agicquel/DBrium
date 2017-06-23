package view.elements;

import javax.swing.*;
import java.awt.*;
import view.interfaces.*;
import controller.*;
import model.ConnectDB;


public class BarreCreationRequetes extends JPanel 
{
	
	private JButton nouveau, rechercher, executer, effacer, indenter, newUser, createTable;
	private ActionBarreCreationRequete a;
	private DBFrame f;
	private JToolBar jtb;
	private JComboBox<ConnectDB> currentConnexion;

	public BarreCreationRequetes (DBFrame f) 
	{

		// Initialisation du JPanel de la classe BarreCreationRequete

		super(new GridLayout(1,12));

		this.f = f;

		// Création de l'ActionListener affecté aux boutons de la Barre

		a = new ActionBarreCreationRequete(f);

		// Création des boutons, personnalisation et ajout de l'ActionListener

		nouveau = new JButton(new ImageIcon("Image/New.png"));
		nouveau.addActionListener(a);
		nouveau.setBorderPainted(false);
		nouveau.setToolTipText("New Script");

		executer = new JButton(new ImageIcon("Image/Execution.png"));
		executer.addActionListener(a);
		executer.setBorderPainted(false);
		executer.setToolTipText("Script Execution");

		indenter = new JButton(new ImageIcon("Image/indent.png"));
		indenter.addActionListener(a);
		indenter.setBorderPainted(false);
		indenter.setToolTipText("Indent Script");

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

		// Ajout des différents éléments

		JPanel space1, space2, space3, space4;
		
		jtb = new JToolBar();
		space1 = new JPanel();
		space2 = new JPanel();
		space3 = new JPanel();
		space4 = new JPanel();

		jtb.add(nouveau);
		jtb.add(executer);
		jtb.add(indenter);
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
    * @return the Indent Button
    */
	public JButton getIndenter () 
	{ 
		return this.indenter; 
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