package controleur;

import view.elements.*;
import view.interfaces.*;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.tree.*;
import java.io.*;
import util.*;

/**
 * This class lists all they actions on the BarreGauche so all the action on the JTree.
 * @author m.hervé;
 * @version 1.0;
 */

public class ActionBarreGauche implements ActionListener, MouseListener, TreeSelectionListener, ItemListener, TreeWillExpandListener/**, PopupMenuListener */{
	
	DBFrame f;
	int i;
	JPopupMenu jpm;
	DefaultMutableTreeNode lenoeud;
	JMenuItem co, deco, deleteCo, newTable, open, deleteTable; 

	/**
	 * This is the constructor which initialyse they elements add during they different
	 * action on the BarreGauche.
	 * @param f The Frame which is reference.
	 */
	public ActionBarreGauche(DBFrame f) {

		this.f = f;
		this.i = 0;
		jpm = new JPopupMenu();
		jpm.add(new JMenuItem("Ok"));
		co = new JMenuItem("Connection...");
		deco = new JMenuItem("Deconnection...");
		deleteCo = new JMenuItem("Supprimer",new ImageIcon("Image/deletemini.png"));
		co.addActionListener(this);
		deco.addActionListener(this);
		deleteCo.addItemListener(this);
		deleteCo.addActionListener(this);

		newTable = new JMenuItem("Nouvelle table...", new ImageIcon("Image/Table.png"));
		open = new JMenuItem("Afficher...");
		deleteTable = new JMenuItem("Supprimer table...", new ImageIcon("Image/deletemini.png"));
		deleteTable.addActionListener(this);


	}

	public void actionPerformed( ActionEvent e ) {

		Object source = e.getSource();

		// Action d'ajouter afin d'ajouter une Connexion

		if( source == f.getBarreGauche().getAjouter() ) {

			i++;

			//JOptionPane jop = new JOptionPane();
			//String connexionName = jop.showInputDialog(null, "Name of the connexion", "New connexion", JOptionPane.QUESTION_MESSAGE);

			ConnexionDialog cd = new ConnexionDialog(null, "New Connexion", true);

			String connexionName = cd.getNameCo();


			//DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Connexions");
						
			DefaultMutableTreeNode connexion = new DefaultMutableTreeNode(connexionName);
			//jpm.setInvoker(connexion);
			

			DefaultMutableTreeNode view = new DefaultMutableTreeNode("View");
			DefaultMutableTreeNode tables = new DefaultMutableTreeNode("Tables");
			DefaultMutableTreeNode trigger = new DefaultMutableTreeNode("trigger");

			f.getBarreGauche().getM().insertNodeInto(connexion, f.getBarreGauche().getRacine1(), i);


			connexion.add(tables);
			connexion.add(view);
			connexion.add(trigger);

			System.out.println("lol");

			f.getBarreGauche().getM().reload();

		// ACtion de deleteCo afin de supprimer une connection
			
		} else if(source == this.deleteCo ) {

			lenoeud = (DefaultMutableTreeNode)f.getBarreGauche().getJtree().getLastSelectedPathComponent();

			if(!lenoeud.isRoot()) {

				JOptionPane jop = new JOptionPane();

				int m = jop.showConfirmDialog(null,"Voulez-vous supprimer cette connexion ?","Supprimer Connection", JOptionPane.YES_NO_OPTION);
      	
      			if (m == JOptionPane.YES_OPTION ) {

      				i--;
					f.getBarreGauche().getRacine1().remove(lenoeud);
					f.getBarreGauche().getM().reload();
				}

			}
		} else if ( source == this.deleteTable ) {

			lenoeud = (DefaultMutableTreeNode)f.getBarreGauche().getJtree().getLastSelectedPathComponent();

			if(!lenoeud.isRoot()) {

				JOptionPane jop = new JOptionPane();

				int m = jop.showConfirmDialog(null,"Voulez-vous supprimer cette Table ?","Supprimer Table", JOptionPane.YES_NO_OPTION);
      	
      			if (m == JOptionPane.YES_OPTION ) {

      				//i--;
					lenoeud.removeFromParent();
					f.getBarreGauche().getM().reload();
				}

			}

		}
		else if ( source == this.co )
		{
			try
			{
				this.treeWillExpand(null);
				// MAX FAUT OUVRIR LE JTREE PUTAAIIIN
			}
			catch(Exception err){}

		}

		else if ( source == this.deco )
		{
			try
			{
				this.treeWillCollapse(null);
				// MAX FAUT FERMER LE JTREE PUTAAIIIN
			}
			catch(Exception err){}

		}

		f.getBarreGauche().getM().reload();

	}

	public void mouseClicked(MouseEvent e){

		/**if(e.getSource() == f.getBarreGauche().getJtree() && e.getClickCount() == 2)
		{
			lenoeud = (DefaultMutableTreeNode)f.getBarreGauche().getJtree().getLastSelectedPathComponent();
			int index = lenoeud.getParent().getIndex(lenoeud);

			try
			{
				if(f.getController().getConnexions().get(index).isConnected())
				{
					f.getController().getConnexions().get(index).disconnect();
					f.getBarreRequete().getCurrentConnexion().removeItem(f.getController().getConnexions().get(index));
				}
				else
				{
					f.getController().getConnexions().get(index).connect();
					f.getBarreRequete().getCurrentConnexion().addItem(f.getController().getConnexions().get(index));
					f.getBarreGauche().getJtree().expandPath(new TreePath(lenoeud));
				}
			}
			catch(Exception err)
			{//lenoeud.setEditable(false);
				JOptionPane.showMessageDialog(new JFrame(), err.getMessage(), "Probleme de connection", JOptionPane.ERROR_MESSAGE);
			}
		}*/

					
	}

	public void mouseEntered(MouseEvent e) {


	}

	public void mouseExited(MouseEvent e) {


	}

	public void mousePressed(MouseEvent e) {

		if ( SwingUtilities.isRightMouseButton ( e ) )
            {
            	//TreePath path1 = f.getBarreGauche().getJtree().getSelectionPath();
                TreePath path = f.getBarreGauche().getJtree().getPathForLocation ( e.getX (), e.getY () );
                Rectangle pathBounds = f.getBarreGauche().getJtree().getUI ().getPathBounds ( f.getBarreGauche().getJtree(), path );
                //DefaultMutableTreeNode t = (DefaultMutableTreeNode)((DefaultMutableTreeNode)path1.getLastPathComponent()).getUserObject();

                if ( pathBounds != null && pathBounds.contains ( e.getX (), e.getY () ) ) 

                {	if(!lenoeud.isLeaf()) {

                    	JPopupMenu menu = new JPopupMenu ("Connections");
                    	menu.setPopupSize(new Dimension(300,60));
                    	menu.add(co);
                    	menu.add(deco);
                    	menu.add(deleteCo);
                    	menu.show ( f.getBarreGauche().getJtree(), pathBounds.x, pathBounds.y + pathBounds.height );

                    } else {

                    	JPopupMenu menu = new JPopupMenu ("Connections");
                    	menu.setPopupSize(new Dimension(300,60));
                    	menu.add(newTable);
                    	menu.add(open);
                    	menu.add(deleteTable);
                    	menu.show ( f.getBarreGauche().getJtree(), pathBounds.x, pathBounds.y + pathBounds.height );

                    }
                }
            }

	}

	public void mouseReleased(MouseEvent e) {


	}

    public void valueChanged(TreeSelectionEvent e) {

    	lenoeud = (DefaultMutableTreeNode)f.getBarreGauche().getJtree().getLastSelectedPathComponent();
		/*if(!lenoeud.isRoot()) {

			JOptionPane jop = new JOptionPane();

			int m = jop.showConfirmDialog(null,"Voulez-vous supprimer cette connexion ?","Supprimer Connection", JOptionPane.YES_NO_OPTION);
      	
      		if (m == JOptionPane.YES_OPTION ) {

      			i--;
      			System.out.println("yo");
				f.getBarreGauche().getRacine1().remove(lenoeud);
				System.out.println("yoyo");
				f.getBarreGauche().getM().reload();
				System.out.println("yoyoyo");
			}

		}*/

	}

	public void itemStateChanged(ItemEvent e) {

		System.out.println("quequette");
		if (e.getItem() == deleteCo ) {

			System.out.println("quequette");

			if(!lenoeud.isRoot()) {

				JOptionPane jop = new JOptionPane();

				int m = jop.showConfirmDialog(null,"Voulez-vous supprimer cette connexion ?","Supprimer Connection", JOptionPane.YES_NO_OPTION);
      	
      			if (m == JOptionPane.YES_OPTION ) {

      				i--;
      				System.out.println("yo");
					f.getBarreGauche().getRacine1().remove(lenoeud);
					System.out.println("yoyo");
					f.getBarreGauche().getM().reload();
					System.out.println("yoyoyo");
				}

			}
		}

		f.getBarreGauche().getM().reload();

	}

	@Override
	public void treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException
	{
		if(lenoeud.getFirstChild().isLeaf()) 
		{
			int index = lenoeud.getParent().getIndex(lenoeud);

			try
			{
				if(f.getController().getConnexions().get(index).isConnected() == false)
				{
					f.getController().getConnexions().get(index).connect();
					f.getBarreRequete().getCurrentConnexion().addItem(f.getController().getConnexions().get(index));
					f.getBarreGauche().getJtree().expandPath(new TreePath(lenoeud));
				}
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(new JFrame(), err.getMessage(), "Probleme de connection", JOptionPane.ERROR_MESSAGE);
				throw new ExpandVetoException(e);
			}
			
		}
	}

	@Override
	public void treeWillCollapse(TreeExpansionEvent e) throws ExpandVetoException
	{
		int index = lenoeud.getParent().getIndex(lenoeud);
		if(lenoeud.getFirstChild().isLeaf()) 
		{
			try
			{
				if(f.getController().getConnexions().get(index).isConnected() == true)
				{
					f.getController().getConnexions().get(index).disconnect();
					f.getBarreRequete().getCurrentConnexion().removeItem(f.getController().getConnexions().get(index));
				}
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(new JFrame(), err.getMessage(), "Probleme de deconnection", JOptionPane.ERROR_MESSAGE);
				throw new ExpandVetoException(e);
			}

		}
	}

}