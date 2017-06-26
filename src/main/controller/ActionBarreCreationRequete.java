package controller;

import view.elements.*;
import view.interfaces.*;
import model.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

/**
 * This class lists all they actions on they buttons of the BarreCreationRequêtes.
 * The set of actions specific to each button of this class act directly on the 
 * CreationRequests Window, such as the "New" button that creates a new tab or 
 * the "Search" button that searches for the string that the user enters the Text 
 * area in order to search it and highlight it at each occurrence of it in the RTextArea.
 * @author m.hervé
 * @version 3.0
 */
public class ActionBarreCreationRequete implements ActionListener {

	private int index;
	private DBFrame f;
    private final Highlighter.HighlightPainter hPainter = new HPainter(new Color(255, 106, 106));

    /**
     * The constructor of the class ActionBarreCreationRequete wich initializes the index at 1
     * @param f The principal frame
     */
	public ActionBarreCreationRequete (DBFrame f) {

		this.index = 1;
		this.f = f;

	}


	@Override
	/**
	 * The action when the user push a button for exemple
	 * @param e The event
	 */
	public void actionPerformed (ActionEvent e) {

		Object source = e.getSource();
		Component selected = f.getFenetre().getWrite().getSelectedComponent();

		if( source == f.getBarreRequete().getNouveau()  ) {

			if ( selected == null ) {


				index = 0;
				String ret = "untitled";

				AreaToSave jtx = new AreaToSave(30,100);
				jtx.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
  				jtx.setCodeFoldingEnabled(true);


				f.getFenetre().getWrite().addTab("untitled", new RTextScrollPane((Component)jtx));
				JButton btn = new JButton("x");
				JPanel pnlTab = new JPanel(new GridBagLayout());
				pnlTab.setOpaque(false);
				btn.setBorderPainted(false);
				btn.setContentAreaFilled(false);
				btn.setBackground(new Color(200,220,241));

				JLabel lblTitle = new JLabel("untitled");

				GridBagConstraints gbc = new GridBagConstraints();

				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.weightx = 2;

				pnlTab.add(lblTitle, gbc);

				gbc.gridx++;
				gbc.weightx = 0;
				pnlTab.add(btn, gbc);

				f.getFenetre().getWrite().setTabComponentAt(index, pnlTab);

				btn.addActionListener(new MyCloseActionHandler(f.getFenetre().getWrite(), index));

			} else {

				index = f.getFenetre().getWrite().getTabCount() + 1;
				String ret = "untitled";

				AreaToSave jtx = new AreaToSave(30,100);
				jtx.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
  			jtx.setCodeFoldingEnabled(true);

				f.getFenetre().getWrite().addTab("untitled", new RTextScrollPane((Component)jtx));
				JButton btn = new JButton("x");
				JPanel pnlTab = new JPanel(new GridBagLayout());
				pnlTab.setOpaque(false);
				btn.setBorderPainted(false);
				btn.setContentAreaFilled(false);
				btn.setBackground(new Color(200,220,241));

				JLabel lblTitle = new JLabel("untitled");

				GridBagConstraints gbc = new GridBagConstraints();

				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.weightx = 2;

				pnlTab.add(lblTitle, gbc);

				gbc.gridx++;
				gbc.weightx = 0;
				pnlTab.add(btn, gbc);

				f.getFenetre().getWrite().setTabComponentAt(index-1, pnlTab);

				btn.addActionListener(new MyCloseActionHandler(f.getFenetre().getWrite(), index-1));
			}

		} else if (source == f.getBarreRequete().getRechercher() ) {

			JOptionPane jop = new JOptionPane();

			String search = jop.showInputDialog(null,"Saisissez la cha\u00eene de caract\u00e8re que vous souhaitez rechercher","Recherche", JOptionPane.QUESTION_MESSAGE);

			if(f.getFenetre().text().getText() != null && search != null)

				addHighlight(f.getFenetre().text(), search);
		}

		else if ( source == f.getBarreRequete().getEffacer() ) {

			f.getFenetre().text().setText("");
		}
		else if(source == f.getBarreRequete().getExecuter())
		{
			if(f.getBarreRequete().getCurrentConnexion().getSelectedItem() != null)
			{
				String res = f.getFenetre().getResultQuerry().getText();
				for(Query q : f.getController().tokenArea(f.getFenetre().text().getText()))
				{
					System.out.println("query = '" + q.toString() + "'");
					try
					{
						if(q.toString().toUpperCase().contains("SELECT"))
						{
							res += ((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendQuery(q).toString();
						}
						else if (q.toString().toUpperCase().contains("INSERT"))
						{
							System.out.println("1");
							((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendUpdate(q);
							System.out.println("2");
							res += "Ligne ajout\u00e9e.";
						}
						else if (q.toString().toUpperCase().contains("UPDATE"))
						{
							((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendUpdate(q);
							res += "Mise \u00e0 jour de la ligne effectu\u00e9e.";
						}
						else if (q.toString().toUpperCase().contains("UPDATE"))
						{
							((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendUpdate(q);
							res += "Mise \u00e0 jour de la ligne effectu\u00e9e.";
						}
						else if (q.toString().toUpperCase().contains("DELETE") || q.toString().toUpperCase().contains("TRUNCATE"))
						{
							((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendUpdate(q);
							res += "Ligne supprim\u00e9e.";
						}
						else if (q.toString().toUpperCase().contains("DROP"))
						{
							((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendUpdate(q);
							res += "Table supprim\u00e9e.";
						}
						else if (q.toString().toUpperCase().contains("TRIGGER"))
						{
							((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendUpdate(q);
							res += "Trigger cr\u00e9e.";
						}
						else if (q.toString().toUpperCase().contains("VIEW"))
						{
							((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendUpdate(q);
							res += "View cr\u00e9e.";
						}
						else if (q.toString().toUpperCase().contains("CREATE TABLE"))
						{
							((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendUpdate(q);
							res += "Table cr\u00e9e.";
						}
						else if (q.toString().toUpperCase().contains("UPDATE"))
						{
							((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendUpdate(q);
							res += "Table mise \u00e0 jour.";
						}
					}
					catch(Exception err)
					{
						res += err.getMessage();
					}

					res += "\n\n-------------------------------------------------------------\n\n";
				}

				try
				{
					((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).fillIntoTables();
					((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).fillIntoTriggers();
					((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).fillIntoViews();
				}
				catch(Exception err)
				{
					err.printStackTrace();
				}

				f.getFenetre().getResultQuerry().setText(res);
				f.getBarreGauche().getActionBarreGauche().valueChanged(null);
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(), "Il faut choisir une connection pour executer un script", "Pas de connection choisie", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		// Action du boutton nouvel utilisateur
		else if( source == f.getBarreRequete().getNewUser()) 
		{
			new Thread()
			{
				public void run()
				{
					NewUserBuilder nub = new NewUserBuilder();
					nub.showIt();
					Query q = nub.getQuery();
					nub.close();

					if(q != null)
					{
						f.getFenetre().text().setText(f.getFenetre().text().getText() + q.toString());
					}
				}
        	}.start();
		}

		// Action du boutton création de table
		else if( source == f.getBarreRequete().getCreateTable() )
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

	}

	/**
	 * Set the index
	 * @param newI The the value of the index
	 */
	public void setIndex(int newI)
	{
		if (newI >= 0)
		{
			this.index = newI;
		}
	}

	/**
	 * Get the index
	 * @return The index
	 */
	public int getIndex()
	{ 
		return this.index;
	}

	/**
	 * @return the actionBarreCreationRequete
	 */
	public ActionBarreCreationRequete getActionBarre()
	{
		return this;
	}

  	// Code important
  	// Ajout un HighlightPainter de type HPainter sur toutes les occurrences "word"

  	/**
  	 * This method add an Highligther
  	 * @param tcomp The component where the method higlith the text
  	 * @param word The string higligth int the TextArea
  	 */
  	public void addHighlight(final JTextComponent tcomp, final String word) 
  	{

    	// Supprime les anciens
    	removeHighlights(tcomp);

    	try {

      		final Highlighter h = tcomp.getHighlighter();
      		final Document doc = tcomp.getDocument();
      		final String fullText = doc.getText(0, doc.getLength());
      		int pos = 0;

      		// Recherche du "word"
      		while ((pos = fullText.indexOf(word, pos)) >= 0) 
      		{

        		// Ajout du nouveau painter
        		h.addHighlight(pos, pos + word.length(), hPainter);
        		// On avance pour la suite
        		pos += word.length();
      		}

    	} 
    	catch (final BadLocationException e) 
    	{

      		e.printStackTrace();
    	}

  	}

  	// Code important
  	// Supprime les HighlightPainter de type HPainter

  	/** 
  	 * This method remove the higlights
  	 * @param textComp the area where the method remove the higlight
  	 */
  	public void removeHighlights(final JTextComponent textComp) 
  	{

    	final Highlighter her = textComp.getHighlighter();
    	final Highlighter.Highlight[] h = her.getHighlights();
    	for (int i = 0; i < h.length; ++i) 
    	{

      		// Si c'est le notre on delete
      		if (HPainter.class.isInstance(h[i].getPainter()))
        		her.removeHighlight(h[i]);
    	}
  	}

  	// Code important
  	// Le passage par une classe privée permet d'isoler nos HighlightPainter au
  	// moment du remove

  	/**
  	 * this class isolated teh color of our HighlightPainter
  	 */
  	class HPainter extends DefaultHighlighter.DefaultHighlightPainter 
  	{
  		/**
  		 * The constructor of the HPainter
  		 *@param Color The color of the HPainter
  		 */
    	public HPainter(final Color color) 
    	{
      		super(color);
    	}
  	}
}
