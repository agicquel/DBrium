/**
* This class is use to make all actions of the menu
* @author Tristan Furno
* @version 1.0
*/
package controller;

import view.interfaces.*;
import view.elements.*;
import util.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.*;
import java.util.StringTokenizer;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.RTextAreaEditorKit.*;

import java.io.*;

public class ActionMenu implements ActionListener{

  /**
  * Frame use for the action
  */
  private DBFrame f;

  /**
  * HighLightPainter use in to highlign the word search
  */
  private final Highlighter.HighlightPainter hPainter = new HPainter(new Color(255, 106, 106));



  /**
  * Constructor of the class
  * @param frame the frame use in the GUI
  */
  public ActionMenu(DBFrame frame){
    this.f = frame;
  }

  public void actionPerformed(ActionEvent e){

    Object source = e.getSource();

    //Action listener for the menu item save As
    if( source == this.f.getMenu().getSaveAs()){

      AreaToSave tamp = this.f.getFenetre().text();

      this.saveAs(tamp);
    }


    //Action listener for the menu item new File
    if (source.equals(this.f.getMenu().getNewFile())){

      ActionBarreCreationRequete a = this.f.getBarreRequete().getActionBarre();
      a.setIndex(f.getFenetre().getWrite().getTabCount() + 1);
      String ret = "untitled";

      //System.out.println(a.getIndex());
      AreaToSave tamp = new AreaToSave(30, 100);
      tamp.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
  		tamp.setCodeFoldingEnabled(true);

      f.getFenetre().getWrite().addTab("untitled", new RTextScrollPane((Component)tamp));
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


    }
    //Action listener for the menu item save
    else if(source.equals(this.f.getMenu().getSave())){
      AreaToSave tamp = this.f.getFenetre().text();

      this.save(tamp);
    }
    //Action listener for the menu item open
    else if(source.equals(this.f.getMenu().getOpen())){
      this.open();

    }
    //Action listener for the menu item new window

    //Action listener for the menu item settings
    else if(source.equals(this.f.getMenu().getSettings())){
      System.out.println("Test settings");
    }

    //Action listener for the menu item undo
    else if(source.equals(this.f.getMenu().getUndo())){
      UndoAction ua = new UndoAction();
      ua.actionPerformedImpl(e, (RTextArea)this.f.getFenetre().text());
    }

    //Action listener for the menu item redo
    else if(source.equals(this.f.getMenu().getRedo())){
      RedoAction ra = new RedoAction();
      ra.actionPerformedImpl(e, (RTextArea)this.f.getFenetre().text());
    }

    //Action listener for the menu item copy
    else if(source.equals(this.f.getMenu().getCopy())){
      CopyAction ca = new CopyAction();
      ca.actionPerformedImpl(e, (RTextArea)this.f.getFenetre().text());
    }

    //Action listener for the menu item past
    else if(source.equals(this.f.getMenu().getPaste())){
      PasteAction pa = new PasteAction();
      pa.actionPerformedImpl(e, (RTextArea)this.f.getFenetre().text());
    }

    //Action listener for the menu item cut
    else if(source.equals(this.f.getMenu().getCut())){
      CutAction ca = new CutAction();
      ca.actionPerformedImpl(e, (RTextArea)this.f.getFenetre().text());
    }

    //Action listener for the menu item selectA
    else if(source.equals(this.f.getMenu().getSelectA())){
      SelectAllAction sa = new SelectAllAction();
      sa.actionPerformedImpl(e, (RTextArea)this.f.getFenetre().text());
    }

    //Action listener for the menu item full screen
    else if(source.equals(this.f.getMenu().getFullScreen())){
      f.setExtendedState(JFrame.MAXIMIZED_BOTH);
      f.setUndecorated(true);
      f.setVisible(true);
    }

    //Action listener for the menu item find
    else if(source.equals(this.f.getMenu().getFind())){
      JOptionPane jop = new JOptionPane();

			String search = jop.showInputDialog(null,"Saisissez la cha\u00eene de caract\u00e8re que vous souhaitez rechercher","Recherche", JOptionPane.QUESTION_MESSAGE);

			String s1 = f.getFenetre().getWriteQuerry().getText();

			addHighlight(f.getFenetre().getWriteQuerry(), search);
    }

    //Action listener for the menu item manual
    else if(source.equals(this.f.getMenu().getManual())){
      FrameForMenus ffm = new FrameForMenus(getClass().getResourceAsStream("/res/txt/manuel.txt"), "Manuel");
    }

    //Action listener for the menu item about
    else if(source.equals(this.f.getMenu().getAbout())){
      FrameForMenus ffm = new FrameForMenus(getClass().getResourceAsStream("/res/txt/about.txt"), "A propos");
    }

    //Action listener for the menu item quit
    else if(source.equals(this.f.getMenu().getQuit())){
      System.exit(0);
    }


  }

  /**
  * Method to change the title of the curent file when is saved
  * @param title The name of the file to change
  * @param contain The contain of the file to save
  */
  public void changeTitleTab(String title,String contain){
    ActionBarreCreationRequete a = this.f.getBarreRequete().getActionBarre();
    a.setIndex(f.getFenetre().getWrite().getTabCount() + 1);
    String ret = this.tokenizeFileName(title);

    //System.out.println(a.getIndex());
    AreaToSave tamp = new AreaToSave(30,100);
    tamp.setText(contain);
    tamp.setSavePath(title);
    tamp.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
    tamp.setCodeFoldingEnabled(true);
    f.getFenetre().getWrite().addTab(ret, new RTextScrollPane(tamp));
    JButton btn = new JButton("x");
    JPanel pnlTab = new JPanel(new GridBagLayout());
    pnlTab.setOpaque(false);
    btn.setBorderPainted(false);
    btn.setContentAreaFilled(false);
    btn.setBackground(new Color(200,220,241));

    JLabel lblTitle = new JLabel(ret);

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
  }


  /**
  * Method to open a file from a file chooser, open only the sql files
  */
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
          String line = in.readLine();
					while(line != null){
            fileContain += ""+line+"\n";
						line = in.readLine();

					}

          //Creation of a new title
          AreaToSave tamp = new AreaToSave(30,100);
          tamp.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
      		tamp.setCodeFoldingEnabled(true);
          tamp.setFont(new Font("TimesRoman",Font.BOLD,15));
          tamp.setText(fileContain);
          tamp.setSavePath(theFile);
          RTextScrollPane scrollTamp = new RTextScrollPane(tamp);
          ActionBarreCreationRequete a = this.f.getBarreRequete().getActionBarre();
          a.setIndex(f.getFenetre().getWrite().getTabCount() + 1);
          String ret = this.tokenizeFileName(theFile);


          f.getFenetre().getWrite().addTab(ret,scrollTamp);
          JButton btn = new JButton("x");
          JPanel pnlTab = new JPanel(new GridBagLayout());
          pnlTab.setOpaque(false);
          btn.setBorderPainted(false);
          btn.setContentAreaFilled(false);
          btn.setBackground(new Color(200,220,241));

          JLabel lblTitle = new JLabel(ret);

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

				}
				else{

					JOptionPane option=new JOptionPane();
					option.showMessageDialog(choice,"Erreur sur le fichier selectionn\u00e9");

				}

			}

		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

  /**
  * Method to save a file with a filechooser
  * @param ats the text Area to save
  */
	public void saveAs(AreaToSave ats){


		try{

      String fileContain = ats.getText();
			JFileChooser choice = new JFileChooser();
      choice.setCurrentDirectory(new File("."));
			int back = choice.showDialog(choice,"Enregistrer-sous");
      String theFile = null;
      File f = null;
			if(back == JFileChooser.APPROVE_OPTION){
        theFile = choice.getSelectedFile().toString();
        f = choice.getSelectedFile();
        if(!theFile.endsWith(".sql")){
          theFile = theFile+".sql";
        }
        int c = JOptionPane.NO_OPTION;
					if (f.exists()) {
						c = JOptionPane.showConfirmDialog(null, "Le fichier existe d\u00e9ja voulez-vous l'\u00e9craser ?");
						if(f.getName() != null && c == JOptionPane.YES_OPTION){
							/*String title = file.getName().split("[.]")[0];
							changeTitleTab(title);
							RWFile.writeFile(textPane.getText(), file.toString());
							gui.getActionBar().getAction().setText("<html>Fichier enregistr&eacute;</html>");
							currentPath = file.getParent();
							savePath = file.toString();*/
      			  this.changeTitleTab(theFile, fileContain);
      				FileWriter file = new FileWriter(theFile);
      				BufferedWriter in = new BufferedWriter(file);
      				PrintWriter er = new PrintWriter(in);
      				er.println(fileContain);
      				er.close();
						}
			} else {

        this.changeTitleTab(theFile, fileContain);
        FileWriter file = new FileWriter(theFile);
        BufferedWriter in = new BufferedWriter(file);
        PrintWriter er = new PrintWriter(in);
        er.println(fileContain);
        er.close();
      }

		}

		} catch(Exception ex){

			System.out.println(ex.getMessage());

		}

	}



    /**
  * Method to save a file aleready saved as, if it's not saved save as is called
  * @param ats the area to save
  */
	public void save(AreaToSave ats){

		try{
        if(ats.getSavePath() != null){
          String fileContain = ats.getText();
          FileWriter file=new FileWriter(ats.getSavePath());
          BufferedWriter in=new BufferedWriter(file);
          PrintWriter er=new PrintWriter(in);
          er.println(fileContain);
          er.close();
        } else {
          this.saveAs(ats);
        }

			}	catch(Exception ex){

			ex.printStackTrace();

		}

	}

  /**
  * Method to get just the last part of the name of the fileMenu
  * @param name the name of the file to tokenize
  * @return the name cut
  */
  private String tokenizeFileName(String name){
    StringTokenizer firstToken;
    String ret = "Untitle";
    if(name.contains("\\")){
      firstToken = new StringTokenizer(name, "\\", false);
    } else {
      firstToken = new StringTokenizer(name, "/", false);
    }
    while(firstToken.hasMoreTokens()){
      ret = firstToken.nextToken();
    }
    return ret;
  }


  /**
  * This method add an Highligther
  * @param tcomp The component where the method higlith the text
  * @param word The string higligth int the TextArea
  */
  public void addHighlight(final JTextComponent tcomp, final String word) {

    removeHighlights(tcomp);

    try {
      final Highlighter h = tcomp.getHighlighter();
      final Document doc = tcomp.getDocument();
      final String fullText = doc.getText(0, doc.getLength());
      int pos = 0;

      while ((pos = fullText.indexOf(word, pos)) >= 0) {

        h.addHighlight(pos, pos + word.length(), hPainter);

        pos += word.length();
      }
    } catch (final BadLocationException e) {
      e.printStackTrace();
    }

  }

  /** 
  * This method remove the higlights
  * @param textComp the area where the method remove the higlight
  */
  public void removeHighlights(final JTextComponent textComp) {
    final Highlighter her = textComp.getHighlighter();
    final Highlighter.Highlight[] h = her.getHighlights();
    for (int i = 0; i < h.length; ++i) {
      // Si c'est le notre on delete
      if (HPainter.class.isInstance(h[i].getPainter()))
        her.removeHighlight(h[i]);
    }
  }

  /**
  * this class isolated teh color of our HighlightPainter
  */
  class HPainter extends DefaultHighlighter.DefaultHighlightPainter {
    public HPainter(final Color color) {
      super(color);
    }
  }
}
