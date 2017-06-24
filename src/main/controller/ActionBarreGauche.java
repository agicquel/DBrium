package controller;

import view.elements.*;
import view.interfaces.*;
import model.*;

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

public class ActionBarreGauche implements ActionListener, MouseListener, TreeSelectionListener, TreeWillExpandListener{
	
	DBFrame f;
	int indexTree;
	JPopupMenu jpm, listMenu;
	DefaultMutableTreeNode lenoeud;
	JMenuItem openTable, openCode, createTable, delete, deleteTable, deleteCo, co, deco;
	boolean bTable, bView, bTrigger;

	/**
	 * This is the constructor which initialyse they elements add during they different
	 * action on the BarreGauche.
	 * @param f The Frame which is reference.
	 */
	public ActionBarreGauche(DBFrame f) {

		this.f = f;
		this.indexTree = f.getController().getConnexions().size();

		bView = false;
		bTrigger = false;
		bTable = false;

		delete = new JMenuItem("Supprimer",new ImageIcon("Image/deletemini.png"));

		delete.addActionListener(this);

		co = new JMenuItem("Connection...");
		deco = new JMenuItem("Deconnection...");
		openTable = new JMenuItem("Ouvrir donn\u00e9es...");
		openCode = new JMenuItem("Afficher code...");
		createTable = new JMenuItem("Cr\u00e9er une nouvelle table...", new ImageIcon("Image/table.png"));
		deleteTable = new JMenuItem("Supprimer...", new ImageIcon("Image/deleteTable.png"));
		deleteCo = new JMenuItem("Supprimer...", new ImageIcon("Image/deleteTable.png"));

		co.addActionListener(this);
		deco.addActionListener(this);
		openTable.addActionListener(this);
		openCode.addActionListener(this);
		createTable.addActionListener(this);
		deleteTable.addActionListener(this);
		deleteCo.addActionListener(this);


	}

//==================================== ActionListener ====================================\\

	public void actionPerformed( ActionEvent e ) {

		Object source = e.getSource();

		// Action d'ajouter afin d'ajouter une Connexion

		if( source == f.getBarreGauche().getAjouter() )
		{
			ConnexionDialog cd = new ConnexionDialog(null, "Nouvelle connection", true);

			if(cd.isDone() && cd.isCorrect())
			{
				try
				{
					ConnectDB newCon = new ConnectDB(cd.getNameCo(), "jdbc:oracle:thin:@" + cd.getHost() + ":" + cd.getPortNumber() + ":" + cd.getSid(), cd.getUserName(), cd.getPassword());
					f.getController().saveConnexion(newCon);
					f.getController().getConnexions().add(newCon);

					DefaultMutableTreeNode connexion = new DefaultMutableTreeNode(cd.getNameCo());
					DefaultMutableTreeNode tables = new DefaultMutableTreeNode("Tables");
					DefaultMutableTreeNode view = new DefaultMutableTreeNode("View");
					DefaultMutableTreeNode trigger = new DefaultMutableTreeNode("Trigger");
					f.getBarreGauche().getM().insertNodeInto(connexion, f.getBarreGauche().getRacine1(), indexTree);
					indexTree++;

					connexion.add(tables);
					connexion.add(view);
					connexion.add(trigger);
					f.getBarreGauche().getM().reload();			
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(new JFrame(), err.getMessage(), "Impossible de créer la connection", JOptionPane.ERROR_MESSAGE);
				}
		}
		else if(cd.isDone() && !cd.isCorrect())
		{
			JOptionPane.showMessageDialog(new JFrame(), "Tous les champs doivent être completés", "Impossible de créer la connection", JOptionPane.ERROR_MESSAGE);
		}

		// ACtion de deleteCo afin de supprimer une connection
			
		} else if(source == this.deleteCo ) {

			lenoeud = (DefaultMutableTreeNode)f.getBarreGauche().getJtree().getLastSelectedPathComponent();

			if(!lenoeud.isRoot()) {

				JOptionPane jop = new JOptionPane();

				int m = jop.showConfirmDialog(null,"Voulez-vous supprimer cette connexion ?","Supprimer Connection", JOptionPane.YES_NO_OPTION);
      	
      			if (m == JOptionPane.YES_OPTION ) {

      				try
					{
						f.getController().deleteConnexion(f.getBarreGauche().getJtree().getLastSelectedPathComponent().toString());
					}
					catch(Exception err){}

      				indexTree--;
					f.getBarreGauche().getRacine1().remove(lenoeud);
					f.getBarreGauche().getM().reload();
				}

			}

		}

		// Action sur les MenuItem

		else if( source == this.openTable )
		{
			new Thread()
			{
				public void run()
				{
					try
					{
						JFrame tabFrame = new JFrame("Données");
						Query query = new Query(((Codable)f.getBarreGauche().getList().getSelectedValue()).getCode());
						Result res = ((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendQuery(query);
						tabFrame.add(new JScrollPane(Converter.convertResultToJTable(res)));
						tabFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						tabFrame.pack();
						tabFrame.setVisible(true);
					}
					catch(Exception err)
					{
						JOptionPane.showMessageDialog(new JFrame(), err.getMessage(), "Impossible d'afficher les données", JOptionPane.ERROR_MESSAGE);
					}
				}
        	}.start();


		}
		else if( source == this.openCode )
		{

			String code = ((Codable)f.getBarreGauche().getList().getSelectedValue()).getCode();
			f.getFenetre().text().setText(f.getFenetre().text().getText() + code);
		}
		else if( source == this.createTable )
		{
			new Thread()
			{
				public void run()
				{
					TableBuilder tb = new TableBuilder();
					Query q = tb.run();
					tb.close();

					if(q != null)
					{
						f.getFenetre().text().setText(f.getFenetre().text().getText() + q.toString());

					}
				}
        	}.start();
		}

		else if ( source == this.deleteTable)
		{
			try
			{
				Query q = ((Deletable)f.getBarreGauche().getList().getSelectedValue()).delete();
				((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendUpdate(q);
				((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).getTables().remove(f.getBarreGauche().getList().getSelectedValue());
				
				((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).fillIntoTables();
				((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).fillIntoTriggers();
				((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).fillIntoViews();

				this.valueChanged(null);
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(new JFrame(), err.getMessage(), "Impossible de supprimer l'element", JOptionPane.ERROR_MESSAGE);
			}

		}
		else if ( source == this.co )
		{
			try
			{
				this.treeWillExpand(null);
			}
			catch(Exception err){}
		}
		else if ( source == this.deco )
		{
			try
			{
				this.treeWillCollapse(null);
			}
			catch(Exception err){}
		}

		f.getBarreGauche().getM().reload();

	}

//==================================== MouseListener ====================================\\

	public void mouseClicked(MouseEvent e){


					
	}

	public void mouseEntered(MouseEvent e) {


	}

	public void mouseExited(MouseEvent e) {


	}

	public void mousePressed(MouseEvent e) {

		if (e != null && SwingUtilities.isRightMouseButton ( e ) )
        {
            TreePath path = f.getBarreGauche().getJtree().getPathForLocation ( e.getX (), e.getY () );
            Rectangle pathBounds = null;
            if(path != null)
            	pathBounds = f.getBarreGauche().getJtree().getUI ().getPathBounds ( f.getBarreGauche().getJtree(), path );

            if ( pathBounds != null && pathBounds.contains ( e.getX (), e.getY () ) ) 
            {	
            	if(!lenoeud.isLeaf()) 
            	{

                    JPopupMenu menu = new JPopupMenu ("Connections");
                    menu.setPopupSize(new Dimension(300,80));
                    menu.add(co);
                    menu.add(deco);
                    menu.add(deleteCo);
                    menu.show ( f.getBarreGauche().getJtree(), pathBounds.x, pathBounds.y + pathBounds.height );
                } 
            }

            else if( !f.getBarreGauche().getList().isSelectionEmpty() && f.getBarreGauche().getList().locationToIndex(e.getPoint()) == f.getBarreGauche().getList().getSelectedIndex()) 
            {  
               			
               	if(bTable) 
               	{
               		JPopupMenu listMenu = new JPopupMenu("Table");

               		listMenu.setPopupSize(new Dimension(300, 60));

               		listMenu.add(openTable);
               		listMenu.add(createTable);
               		listMenu.add(deleteTable);

               		listMenu.show(f.getBarreGauche().getList(), e.getX(), e.getY());
               	}

                else if(bView)
               	{
               		JPopupMenu listMenu = new JPopupMenu("View");

               		listMenu.setPopupSize(new Dimension(300, 60));

               		listMenu.add(openTable);
               		listMenu.add(openCode);
               		listMenu.add(delete);

               		listMenu.show(f.getBarreGauche().getList(), e.getX(), e.getY());
               	}

               	else if(bTrigger)
               	{
               		JPopupMenu listMenu = new JPopupMenu("Trigger");

               		listMenu.setPopupSize(new Dimension(300, 40));

               		listMenu.add(openCode);
               		listMenu.add(delete);

               		listMenu.show(f.getBarreGauche().getList(), e.getX(), e.getY());

               	}

            }

        }

	}

	public void mouseReleased(MouseEvent e) {


	}

//==================================== TreeSelectionListener ====================================\\

    public void valueChanged(TreeSelectionEvent e) 
    {
    	lenoeud = (DefaultMutableTreeNode)f.getBarreGauche().getJtree().getLastSelectedPathComponent();

    	if(lenoeud != null && lenoeud.isLeaf())
    	{
    		ConnectDB con = (ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem();
    		DefaultListModel<Object> model = new DefaultListModel<Object>();

    		// table
    		if(lenoeud == lenoeud.getParent().getChildAt(0) ) {
    			for(Table t : con.getTables())
    				model.addElement(t);
    				bTable = true;
    				bView = false;
    				bTrigger = false;
    		}
    		// view
    		else if(lenoeud == lenoeud.getParent().getChildAt(1) ) {
    			for(View v : con.getViews())
    				model.addElement(v);
    				bTable = false;
    				bView = true;
    				bTrigger = false;
    				
    		}
    		else if(lenoeud == lenoeud.getParent().getChildAt(2) ) {
    			for(Trigger t : con.getTriggers())
    				model.addElement(t);
    				bTable = false;
    				bView = false;
    				bTrigger = true;
               		
    		}

    		f.getBarreGauche().getList().setModel(model);
    		f.getBarreGauche().getList().setCellRenderer(new MonRenderer());

    			
    	}
	}


//==================================== TreeWillExpandListener ====================================\\


	@Override
	public void treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException
	{
		if(lenoeud != null && lenoeud.getFirstChild().isLeaf()) 
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
		if(lenoeud != null && lenoeud.getFirstChild().isLeaf()) 
		{
			int index = lenoeud.getParent().getIndex(lenoeud);

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