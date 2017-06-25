package view.elements;

import view.interfaces.*;
import controller.*;
import model.ConnectDB;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.tree.*;
import util.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Here is the class that creates the left part of the DBFrame.
 * This class initially creates the JTree which allows the management of the connections 
 * and their contents.
 * This same Jtree is managed by multiple buttons like the addButton button that creates 
 * a new connection and adds it to the JTree.
 * @author m.hervé;
 * @version 1.0;
 */

public class BarreGauche extends JPanel {

	/**
	 * The principal Frame.
	 */
	private DBFrame f;

	/**
	 * They differents parts of the leftBar
	 */
	private JPanel barreGauche, barreHaut, barreBas;

	/**
	 * The button of the leftBar to add new connexion
	 */
	private JButton ajouter;

	/**
	 * The JscrollPane of the JTree and the JList
	 */
	private JScrollPane jsp, jsp2;

	/**
	 * The JToolBar where the button is added
	 */
	private JToolBar jtb;

	/**
	 * The List which show they Tables, View or Trigger of a connexion
	 */
	private JList<Object> list;

	/**
	 * They Actions of the leftBar
	 */
	private ActionBarreGauche abg;

	/**
	 * The root of the JTree
	 */
	private DefaultMutableTreeNode racine;

	/**
	 * The JTree which contains they connexions
	 */
	private JTree jtree;

	/**
	 * The Model of the JTree
	 */
	private DefaultTreeModel tree;

	/**
	 * The TreeCellRenderer which contains they pictures of the JTree
	 */
	private DefaultTreeCellRenderer[] tCellRenderer;
	
	/**
	 * This is the constructor of the class leftBar which initialize the leftBar.
	 * @param f The principal Frame where this component will be add.
	 */

	public BarreGauche( DBFrame f) {


		super(new BorderLayout());

		this.f = f;
		abg = new ActionBarreGauche(f);

		// Creation des JButton

		try
		{
			ajouter = new JButton(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(File.separator + "res" + File.separator + "img" + File.separator + "NewConnection2.png"))));
			ajouter.setBorderPainted(false);
			ajouter.addActionListener(abg);
			ajouter.setToolTipText("Ajouter une nouvelle connexion");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		// Initialisation de la JList et affectation d'un MonRenderer

		list = new JList<Object>();
		list.addMouseListener(abg);

		// Création de la JToolBar

		jtb = new JToolBar();
		
		// Création de la racine du JTree

		racine = new DefaultMutableTreeNode("Connection");
		tree = new DefaultTreeModel(racine);

		// On ajoute les différents élements du JTree

		if(f.getController().getConnexions() != null && f.getController().getConnexions().size() != 0)
		{
			for(ConnectDB c : f.getController().getConnexions())
			{
				DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(c.getName());
				dmtn.add(new DefaultMutableTreeNode("Tables"));
				dmtn.add(new DefaultMutableTreeNode("View"));
				dmtn.add(new DefaultMutableTreeNode("Trigger"));
				racine.add(dmtn);
			}
		}

		jtree = new JTree(tree);
		jtree.addTreeSelectionListener(abg);
		jtree.addMouseListener(abg);
		jtree.addTreeWillExpandListener(abg);

		tCellRenderer = new  DefaultTreeCellRenderer[3];
		tCellRenderer[0] = new  DefaultTreeCellRenderer();

		try
		{
			tCellRenderer[0].setClosedIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(File.separator + "res" + File.separator + "img" + File.separator + "ConnexionLost.png"))));
    		tCellRenderer[0].setOpenIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(File.separator + "res" + File.separator + "img" + File.separator + "ConnexionSucced.png"))));
    		tCellRenderer[0].setLeafIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(File.separator + "res" + File.separator + "img" + File.separator + "Folder.png"))));
    		jtree.setCellRenderer(tCellRenderer[0]);
    	}
      	catch (IOException e)
		{
			e.printStackTrace();
		}
      	// Création des différents JPanel

		barreGauche = new JPanel(new GridLayout(1,1));
		barreHaut = new JPanel(new BorderLayout());
		barreBas = new JPanel(new GridLayout(1,1));

		// Création des JScrollPane

		jsp2 = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp = new JScrollPane(jtree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JSplitPane j1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, barreHaut, barreBas);

		// Ajout des elements

		jtb.add(ajouter);

		barreHaut.add(jtb, BorderLayout.NORTH);
		barreHaut.add(jsp, BorderLayout.CENTER);

		barreBas.add(jsp2);

		barreGauche.add(j1);

		this.add(barreGauche, BorderLayout.CENTER);

	}

   /**
    * @return the Add Button
    */
	public JButton getAjouter() 
	{ 
		return this.ajouter; 
	}

   /**
    * @return the JTree of this class
    */
	public JTree getJtree() 
	{ 
		return this.jtree; 
	}
	
   /**
    * @return the JList of this class
    */
	public JList<Object> getList() 
	{ 
		return this.list; 
	}

   /**
    * @return the Root of the JTree
    */
	public DefaultMutableTreeNode getRacine1() 
	{ 
		return this.racine; 
	}

   /**
    * @return the Model of the JTree
    */
	public DefaultTreeModel getM() 
	{ 
		return this.tree; 
	}

   /**
    * @return the CellRenderer of the JTree
    */
	public DefaultTreeCellRenderer[] getTCellRenderer() 
	{ 
		return this.tCellRenderer; 
	}

	public ActionBarreGauche getActionBarreGauche()
	{
		return this.abg;
	}

}