package view.elements;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import controleur.*;
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
 * @author M.Hervé, T.Furno, A.Gicquel
 * @version 1.0
 */

public class FenetreCreationRequetes extends JPanel 
{

	private JTextArea resultQuerry, errorQuery;
	private RSyntaxTextArea writeQuerry;
	private JPanel pnlTab;
	private JLabel lblTitle;
	private JTabbedPane result, write;
	private JScrollPane scrollResult, scrollError;
	private RTextScrollPane scrollCreation;
	private JSplitPane split;
	private int index;
	private JButton btnClose;


	public FenetreCreationRequetes(DBFrame f) 
	{

		super(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Creation requetes"));

		// Création de JTextArea et du RSyntaxTextArea

		writeQuerry = new RSyntaxTextArea();
		writeQuerry.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
		writeQuerry.setCodeFoldingEnabled(true);


		resultQuerry = new JTextArea();
		resultQuerry.setBackground(Color.lightGray);
		resultQuerry.setEditable(false);

		errorQuery = new JTextArea();
		errorQuery.setBackground(Color.lightGray);
		errorQuery.setEditable(false);

		// Création des JScrollPAne


		scrollCreation = new RTextScrollPane(writeQuerry);
		scrollError = new JScrollPane(errorQuery, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollResult = new JScrollPane(resultQuerry, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Création des JTabbedPane

		write = new JTabbedPane();
		write.setBackground(Color.lightGray);

		result = new JTabbedPane();
		result.setBackground(Color.lightGray);

		// Création  du split

		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, write, result);
		split.setOneTouchExpandable(true);


		// Ajout des composants

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
		//result.addTab("Error", scrollError);

		this.add(split, BorderLayout.CENTER);

		this.repaint();


    }

    // /!\ Methode a utiliser pour chaque utilisation des onglets !!!!

    /**
     * This method allows at the user to take the target tab of the
     * JTabbedPane.
     * @return area The JTextArea target in the JTabbedPane.
     */
    public JTextArea text() 
    {

		JScrollPane scroll = (JScrollPane)getWrite().getSelectedComponent();
		JViewport viewport = scroll.getViewport();
		JTextArea area = (JTextArea)viewport.getView();

		return area;
	}

	public RSyntaxTextArea getWriteQuerry() { return this.writeQuerry; }
	public JTextArea getResultQuerry() { return this.resultQuerry; }
	public JTextArea getErrorQuerry() { return this.errorQuery; }

	public JPanel getPnlTab() { return this.pnlTab; }

	public JTabbedPane getWrite() { return this.write; }
	public JTabbedPane getResult() { return this.result; }

	public JScrollPane getScrollCreation() { return this.scrollCreation; }
	public JScrollPane getscrollResult() { return this.scrollResult; }
	public JScrollPane getscrollError() { return this.scrollError; }

	public JButton getBtnClose() { return this.btnClose; }

}
