/**
* This class is use to make all actions of the menu
* @author SomeOne
* @version 1.0
*/
package controleur;

import view.elements.*;
import view.interfaces.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import view.elements.*;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

import java.io.*;
public class ActionMenu implements ActionListener{

  /**
  * Frame use for the action
  */
  private DBFrame f;

  /**
  * Way of the last saveAS
  */
  private String way;

  /**
  * HighLightPainter use in to highlign the word sherch
  */
  private final Highlighter.HighlightPainter hPainter = new HPainter(Color.YELLOW);



  /**
  * Constructor of the class
  * @param frame the frame use in the GUI
  */
  public ActionMenu(DBFrame frame){
    this.f = frame;
    this.way = null;
  }

  public void actionPerformed(ActionEvent e){

    Object source = e.getSource();


    //System.out.println(e);



    //Action listener for the menu item save As
    if( source == this.f.getMenu().getSaveAs()){
      /*try{
        JFileChooser filechoose = new JFileChooser();
        filechoose.setCurrentDirectory(new File("."));
        String approve = new String("Enregistrer sous");
        int resultatEnregistrer = filechoose.showDialog(filechoose, approve);
        if (resultatEnregistrer == JFileChooser.APPROVE_OPTION){
          String theFile = new String(filechoose.getSelectedFile().toString());
          if(!theFile.endsWith(".txt") && !theFile.endsWith(".TXT")){
            theFile = theFile + ".txt";
          }
          this.saveAs(theFile, this.f.getFenetre().getWriteQuerry().getText());
        }
      } catch (Exception er) {
        er.printStackTrace();
      }*/
      JTextArea tamp = (JTextArea)this.f.getFenetre().text();

      this.saveAs(tamp.getText());
    }


    //Action listener for the menu item new File
    if (source.equals(this.f.getMenu().getNewFile())){
      ActionBarreCreationRequete a = this.f.getBarreRequete().getActionBarre();
      a.setIndex(f.getFenetre().getWrite().getTabCount() + 1);
      String ret = "untitled";

      System.out.println(a.getIndex());

      f.getFenetre().getWrite().addTab("untitled", new JScrollPane(new JTextArea(30,100)));
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

      f.getFenetre().getWrite().setTabComponentAt(a.getIndex()-1, pnlTab);

      btn.addActionListener(new MyCloseActionHandler(f.getFenetre().getWrite(), a.getIndex()-1));

      //f.getBarreGauche().getOngletGauche().add(new JScrollPane(new JTextArea()));
      //f.getBarreGauche().getOngletGauche().setTitleAt((a.getIndex() - 1), ret);
    }


    //Action listener for the menu item save
    if(source.equals(this.f.getMenu().getSave())){
      System.out.println("Test save");
    }

    //Action listener for the menu item open
    if(source.equals(this.f.getMenu().getOpen())){
      /*try{
        JFileChooser filechoose = new JFileChooser();
        // Créer un JFileChooser
        filechoose.setCurrentDirectory(new File("."));
        // Le répertoire source du JFileChooser est le répertoire d'où est lancé notre programme
        String approve = new String("Ouvrir");
        // Le bouton pour valider l'enregistrement portera la mention OUVRIR
        String theFile = null; //On ne sait pas pour l'instant quel sera le fichier à ouvrir
        int resultatOuvrir = filechoose.showDialog(filechoose, approve); // Pour afficher le JFileChooser...
        if(resultatOuvrir == filechoose.APPROVE_OPTION){
          theFile = filechoose.getSelectedFile().toString();

          this.f.getFenetre().setWriteQuerry(this.open(theFile));
        }
      } catch (Exception er) {
        er.printStackTrace();
      }*/

      open();
      //tamp = new JTextArea(this.open());
    }

    //Action listener for the menu item new window
    if(source.equals(this.f.getMenu().getNewWindow())){
      DBFrame f = new DBFrame();
      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Action listener for the menu item settings
    if(source.equals(this.f.getMenu().getSettings())){
      System.out.println("Test settings");
    }

    //Action listener for the menu item undo
    if(source.equals(this.f.getMenu().getUndo())){
      System.out.println("Test undo");
    }

    //Action listener for the menu item redo
    if(source.equals(this.f.getMenu().getRedo())){
      System.out.println("Test redo ");
    }

    //Action listener for the menu item full screen
    if(source.equals(this.f.getMenu().getFullScreen())){
      f.setExtendedState(JFrame.MAXIMIZED_BOTH);
      f.setUndecorated(true);
      f.setVisible(true);
    }

    //Action listener for the menu item find
    if(source.equals(this.f.getMenu().getFind())){
      JOptionPane jop = new JOptionPane();

			String search = jop.showInputDialog(null,"Saisissez la chaine de caractere que vous souhaitez rechercher","Search", JOptionPane.QUESTION_MESSAGE);

			String s1 = f.getFenetre().getWriteQuerry().getText();

			addHighlight(f.getFenetre().getWriteQuerry(), search);
    }

    //Action listener for the menu item manual
    if(source.equals(this.f.getMenu().getManual())){
      FrameForMenus f = new FrameForMenus("Files\\manuel.txt", "Manuel");
    }

    //Action listener for the menu item about
    if(source.equals(this.f.getMenu().getAbout())){
      FrameForMenus f = new FrameForMenus("File\\about.txt", "A propos");
    }

    //Action listener for the menu item quit
    if(source.equals(this.f.getMenu().getQuit())){
      System.exit(0);
    }


  }

  public void open(){

		String fileContain="";

		try{

			JFileChooser choice = new JFileChooser();
			int retour=choice.showOpenDialog(choice);
			if(retour==JFileChooser.APPROVE_OPTION){
				String theFile = choice.getSelectedFile().toString();
				if(theFile.matches(".*\\.sql")){
					FileReader file=new FileReader(theFile);
					BufferedReader in=new BufferedReader(file);
					while(in.readLine()!=null){

						fileContain += ""+in.readLine()+"\n";
            JTextArea tamp = (JTextArea)this.f.getFenetre().text();
            tamp.setText(fileContain);

					}

				}
				else{

					JOptionPane option=new JOptionPane();
					option.showMessageDialog(choice,"Erreur sur le fichier selectionne");

				}

			}

		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void saveAs(String fileContain){

		try{

			JFileChooser choice =new JFileChooser();
			int back = choice.showDialog(choice,"Enregistrer sous");
			if(back == JFileChooser.APPROVE_OPTION){

				String theFile = choice.getSelectedFile().toString()+".sql";
				FileWriter file = new FileWriter(theFile);
				BufferedWriter in = new BufferedWriter(file);
				PrintWriter er = new PrintWriter(in);
				er.println(fileContain);
				er.close();
				this.way = theFile;

			}

		}

		catch(Exception ex){

			System.out.println(ex.getMessage());

		}

	}

	public void save(String fileContain){

		try{

				FileWriter file=new FileWriter(this.way);
				BufferedWriter in=new BufferedWriter(file);
				PrintWriter er=new PrintWriter(in);
				er.println(fileContain);
				er.close();

			}	catch(Exception ex){

			ex.printStackTrace();

		}

	}

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
