package view.elements;

import view.interfaces.*;
import controller.*;
import model.ConnectDB;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.tree.*;
import util.*;

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

	private DBFrame f;
	private JPanel barreGauche, barreHaut, barreBas, boutonHaut;
	private JButton param, ajouter, refresh, delete;
	private JScrollPane jsp, jsp2;
	private JToolBar jtb;
	private JList<Object> list;

	private ActionBarreGauche abg;

	private DefaultMutableTreeNode racine;
	private JTree jtree;
	private DefaultTreeModel tree;
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

		ajouter = new JButton(new ImageIcon("Image/NewConnection2.png"));
		ajouter.setBorderPainted(false);
		ajouter.addActionListener(abg);
		ajouter.setToolTipText("Add New Connexion");

		param = new JButton(new ImageIcon("Image/Parametre.png"));
		param.setBorderPainted(false);
		param.addActionListener(abg);
		param.setToolTipText("Parameters");


		refresh = new JButton(new ImageIcon("Image/Refresh.png"));
		refresh.setBorderPainted(false);
		refresh.addActionListener(abg);
		refresh.setToolTipText("Refresh");

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

		tCellRenderer[0].setClosedIcon(new ImageIcon("Image/ConnexionLost.png"));
    	tCellRenderer[0].setOpenIcon(new ImageIcon("Image/ConnexionSucced.png"));
    	tCellRenderer[0].setLeafIcon(new ImageIcon("Image/Folder.png"));
    	jtree.setCellRenderer(tCellRenderer[0]);
      
      	// Création des différents JPanel

		barreGauche = new JPanel(new GridLayout(2,1));
		barreHaut = new JPanel(new BorderLayout());
		barreBas = new JPanel(new GridLayout(1,1));
		boutonHaut = new JPanel(new GridLayout(1,3));

		// Création des JScrollPane

		jsp2 = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp = new JScrollPane(jtree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Ajout des elements

		jtb.add(ajouter);
		jtb.add(refresh);
		jtb.add(param);

		boutonHaut.add(jtb);

		barreHaut.add(boutonHaut, BorderLayout.NORTH);
		barreHaut.add(jsp, BorderLayout.CENTER);

		barreBas.add(jsp2);

		barreGauche.add(barreHaut);
		barreGauche.add(barreBas);

		this.add(param, BorderLayout.NORTH);
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
    * @return the Parameters Button
    */
	public JButton getParam() 
	{ 
		return this.param; 
	}

   /**
    * @return the Refresh Button
    */
	public JButton getRefresh() 
	{ 
		return this.refresh; 
	}
   /**
    * @return the Delete Button
    */
	public JButton getDelete() 
	{ 
		return this.delete; 
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