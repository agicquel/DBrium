package view.elements;

import view.interfaces.*;
import controleur.*;
import model.ConnectDB;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.tree.*;

public class BarreGauche extends JPanel {

	private DBFrame f;
	private JPanel barreGauche, barreHaut, barreBas, boutonHaut;
	private JButton param, ajouter, refresh, delete;
	private JScrollPane jsp, jsp2;
	private JTree jtree;
	private JList<Object> list;
	private JToolBar jtb;
	private DefaultMutableTreeNode racine, racine2;
	
	private DefaultTreeModel tree;
	private DefaultTreeCellRenderer[] tCellRenderer, tCellRenderer2;
	private ActionBarreGauche abg;
	private JPopupMenu jpm;

	public BarreGauche( DBFrame f) {


		super(new BorderLayout());

		this.f = f;
		abg = new ActionBarreGauche(f);

		// Creation des JButton

		ajouter = new JButton(new ImageIcon("Image/NewConnection.png"));
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


		list = new JList<Object>();
		


		// Création de la JToolBar

		jtb = new JToolBar();
		jpm = new JPopupMenu("Marche");

		// Création des racines des JTree

		racine = new DefaultMutableTreeNode("Connection");
		racine2 = new DefaultMutableTreeNode("Scripts");

		tree = new DefaultTreeModel(racine);
		//tree.addMouseListener(abg);

		// On ajoute les différents élements de jtree
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

		//jpm.setInvoker(jtree);
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

    	// Ajout des elements
      
      		// Création des différents JPanel

		barreGauche = new JPanel(new GridLayout(2,1));
		barreHaut = new JPanel(new BorderLayout());
		barreBas = new JPanel(new GridLayout(1,1));
		boutonHaut = new JPanel(new GridLayout(1,3));

		// Création des JScrollPane

		jsp2 = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp = new JScrollPane(jtree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		this.add(param, BorderLayout.NORTH);
		this.add(barreGauche, BorderLayout.CENTER);

		barreGauche.add(barreHaut);
		barreGauche.add(barreBas);

		barreBas.add(jsp2);

		barreHaut.add(boutonHaut, BorderLayout.NORTH);
		barreHaut.add(jsp, BorderLayout.CENTER);

		boutonHaut.add(jtb);

		jtb.add(ajouter);
		jtb.add(refresh);
		jtb.add(param);

	}

	public JButton getAjouter() { return this.ajouter; }
	public JButton getParam() { return this.param; }
	public JButton getRefresh() { return this.refresh; }
	public JButton getDelete() { return this.delete; }

	public JTree getJtree() { return this.jtree; }
	
	public JList<Object> getList() { return this.list; }

	public DefaultMutableTreeNode getRacine1() { return this.racine; }
	public DefaultMutableTreeNode getRacine2() { return this.racine2; }
	public DefaultTreeModel getM() { return this.tree; }

	public DefaultTreeCellRenderer[] getTCellRenderer1() { return this.tCellRenderer; }
	public DefaultTreeCellRenderer[] getTCellRenderer2() { return this.tCellRenderer2; }

	public JPopupMenu getPopUp() { return this.jpm; }


}