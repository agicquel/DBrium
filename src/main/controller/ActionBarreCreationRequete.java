package controleur;

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

public class ActionBarreCreationRequete implements ActionListener {

	private int index;
	private DBFrame f;
	private static final long serialVersionUID = 2017911805149871896L;
    private final Highlighter.HighlightPainter hPainter = new HPainter(Color.YELLOW);

	public ActionBarreCreationRequete (DBFrame f) {

		this.index = 1;
		this.f = f;

	}

	public void actionPerformed (ActionEvent e) {

		Object source = e.getSource();
		Component selected = f.getFenetre().getWrite().getSelectedComponent();

		if( source == f.getBarreRequete().getNouveau()  ) {

			if ( selected == null ) {


				index = 0;
				String ret = "untitled";

				RSyntaxTextArea jtx = new RSyntaxTextArea(30,100);
				jtx.setFont(new Font("TimesRoman",Font.BOLD,15));

				f.getFenetre().getWrite().addTab("untitled", new RTextScrollPane(jtx));
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

				System.out.println(index);

				f.getFenetre().getWrite().addTab("untitled", new RTextScrollPane(new RSyntaxTextArea(30,100)));
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
		/*} else if ( source == f.getBarreRequete().getRechercher()) {

			JOptionPane jop = new JOptionPane();

			String search = jop.showInputDialog(null,"Voulez-vous supprimer cette connexion ?","Supprimer Connection", JOptionPane.QUESTION_MESSAGE);

			String s1 = f.getFenetre().getWriteQuerry().getText();*/

		} else if (source == f.getBarreRequete().getRechercher() ) {

			JOptionPane jop = new JOptionPane();

			String search = jop.showInputDialog(null,"Saisissez la chaine de caractere que vous souhaitez rechercher","Search", JOptionPane.QUESTION_MESSAGE);

			String s1 = f.getFenetre().getWriteQuerry().getText();

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
							res += ((ConnectDB)f.getBarreRequete().getCurrentConnexion().getSelectedItem()).sendQuery(q).toString();
					}
					catch(Exception err)
					{
						res += err.getMessage();
					}

					res += "\n\n-------------------------------------------------------------\n\n";
				}

				f.getFenetre().getResultQuerry().setText(res);
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(), "Il faut choisir une connection pour executer un script", "Pas de connection choisie", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

		public void setIndex(int newI){
			if (newI >= 0){
				this.index = newI;
			}
		}
		public int getIndex(){ return this.index;}
		public ActionBarreCreationRequete getActionBarre(){return this;}


		/**else if ( source == f.getBarreRequete().getTexte() ) {

			//f.getCard().show(f.getPanelCard(), "ModeTexte");
			System.out.println("ok");
			f.getFenetre().getB().remove(f.getFenetre().getAffichageDiagramme());
			f.setVisible(false);
			f.setVisible(true);

		}

		else if ( source == f.getBarreRequete().getGraphique()) {

			f.getFenetre().getB().add(f.getFenetre().getAffichageDiagramme());
			f.setVisible(false);
			f.setVisible(true);
		}*/











  // Code important
  // Ajout un HighlightPainter de type HPainter sur toutes les occurrences "word"
  public void addHighlight(final JTextComponent tcomp, final String word) {
    // Supprime les anciens
    removeHighlights(tcomp);

    try {
      final Highlighter h = tcomp.getHighlighter();
      final Document doc = tcomp.getDocument();
      final String fullText = doc.getText(0, doc.getLength());
      int pos = 0;

      // Recherche du "word"
      while ((pos = fullText.indexOf(word, pos)) >= 0) {
        // Ajout du nouveau painter
        h.addHighlight(pos, pos + word.length(), hPainter);
        // On avance pour la suite
        pos += word.length();
      }
    } catch (final BadLocationException e) {
      e.printStackTrace();
    }

  }

  // Code important
  // Supprime les HighlightPainter de type HPainter
  public void removeHighlights(final JTextComponent textComp) {
    final Highlighter her = textComp.getHighlighter();
    final Highlighter.Highlight[] h = her.getHighlights();
    for (int i = 0; i < h.length; ++i) {
      // Si c'est le notre on delete
      if (HPainter.class.isInstance(h[i].getPainter()))
        her.removeHighlight(h[i]);
    }
  }
  // Code important
  // Le passage par une classe privée permet d'isoler nos HighlightPainter au
  // moment du remove
  class HPainter extends DefaultHighlighter.DefaultHighlightPainter {
    public HPainter(final Color color) {
      super(color);
    }
  }
}
