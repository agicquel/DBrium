package view.elements;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import controller.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

import view.interfaces.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

/**
 * Here is the class that initializes the middle part of the main window.
 * This part is mainly composed of a text box where the user can write queries in order to query the database.
 * It is important to note that most buttons of the software will have a direct effect on this part.
 * @author M.Hervé;
 * @version 3.0;
 */

public class FenetreCreationRequetes extends JPanel
{


	/**
	 * The area where we write querry
	 */
	private AreaToSave writeQuerry;

	private JTextArea resultQuerry;

	/**
	 * The Panel where we add component of the JTabbedPane
	 */
	private JPanel pnlTab;

	/**
	 * The title of the tab
	 */
	private JLabel lblTitle;

	/**
	 * They differents JTabbedPane
	 */
	private JTabbedPane result, write;

	/**
	 * The JScrollPane of the Area resultQuerry
	 */
	private JScrollPane scrollResult;

	/**
	 * The RTextScrollPane of the AreaToSave writeQuerry
	 */
	private RTextScrollPane scrollCreation;

	/**
	 * The JSplitPane between they two JTabbedPane
	 */
	private JSplitPane split;

	/**
	 * The index of the tab add
	 */
	private int index;

	/**
	 * The "cross" of they tab
 	 */
	private JButton btnClose;

	/**
	 * This is the constructor of the class FenetreCreationRequetes which initialize the 
	 * space where we write they querry and we show the result.
	 * @param f The principal Frame where this component will be add.
	 */
	public FenetreCreationRequetes(DBFrame f)
	{

		super(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Creation requetes"));

		// Création de JTextArea et du RSyntaxTextArea

		writeQuerry = new AreaToSave();
		writeQuerry.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
		writeQuerry.setCodeFoldingEnabled(true);


		resultQuerry = new JTextArea();
		resultQuerry.setBackground(new Color(201, 201, 201));
		resultQuerry.setEditable(false);

		// Création des JScrollPAne


		scrollCreation = new RTextScrollPane(writeQuerry);
		scrollResult = new JScrollPane(resultQuerry, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Création des JTabbedPane

		write = new JTabbedPane();
		write.setBackground(Color.lightGray);
		write.setPreferredSize(new Dimension(250, 600));

		result = new JTabbedPane();
		result.setBackground(Color.lightGray);

		// Création  du split

		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, write, result);
		split.setOneTouchExpandable(true);


		// Ajout des composants \\


		// Ajout du première onglet

		write.addTab("untitled", scrollCreation);
		index = write.indexOfTab("untitled");
		pnlTab = new JPanel(new GridBagLayout());
		pnlTab.setOpaque(false);
		lblTitle = new JLabel("untitled");
		btnClose = new JButton("x");
		btnClose.setBorderPainted(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setBackground(new Color(200,220,241));

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 2;

		pnlTab.add(lblTitle, gbc);

		gbc.gridx++;
		gbc.weightx = 0;
		pnlTab.add(btnClose, gbc);

		write.setTabComponentAt(index, pnlTab);

		btnClose.addActionListener(new MyCloseActionHandler(write, index));

		// Ajout des différents composants au JPanel principal


		result.addTab("Result", scrollResult);

		this.add(split, BorderLayout.CENTER);

		this.repaint();


    }

    // /!\ Methode a utiliser pour chaque utilisation des onglets !!!!

    /**
     * This method allows at the user to take the target tab of the
     * JTabbedPane.
     * @return area The AreaToSave target in the JTabbedPane.
     */
    public AreaToSave text()
    {

		RTextScrollPane scroll = (RTextScrollPane)getWrite().getSelectedComponent();
		JViewport viewport = scroll.getViewport();
		AreaToSave area = (AreaToSave)viewport.getView();

		return area;
	}

   /**
    * @return the AreaToSave where they Query are write.
    */
	public AreaToSave getWriteQuerry() 
	{ 
		return this.writeQuerry; 
	}

   /**
    * @return the JTextArea where we can show the result of the Query.
    */
	public JTextArea getResultQuerry() 
	{ 
		return this.resultQuerry; 
	}

   /**
    * @return the Panel pnlTab.
    */
	public JPanel getPnlTab() 
	{ 
		return this.pnlTab; 
	}

   /**
    * @return the JTabbedPaned which contains the AreaToSave writeQuerry.
    */
	public JTabbedPane getWrite() 
	{ 
		return this.write; 
	}

   /**
    * @return the JTabbedPaned which contains the JTextArea ResultQuerry.
    */
	public JTabbedPane getResult() 
	{ 
		return this.result; 
	}

   /**
    * @return the JScrollPane which contains the AreaToSave writeQuerry.
    */
	public JScrollPane getScrollCreation() 
	{ 
		return this.scrollCreation; 
	}

   /**
    * @return the JScrollPane which contains the JTextArea resultQuerry.
    */
	public JScrollPane getscrollResult() 
	{ 
		return this.scrollResult; 
	}


   /**
    * @return the "cross" to close a tab.
    */
	public JButton getBtnClose() 
	{ 
		return this.btnClose; 
	}

}
