package view.elements;

import javax.swing.*;
import java.awt.*;
import view.interfaces.*;
import controller.*;
import model.ConnectDB;


public class BarreCreationRequetes extends JPanel 
{
	
	private JButton nouveau, rechercher, executer, effacer, indenter, texte, graphique, compilation, execution;
	private ActionBarreCreationRequete a;
	private DBFrame f;
	private JToolBar jtb;
	private JComboBox<ConnectDB> currentConnexion;

	public BarreCreationRequetes (DBFrame f) 
	{

		// In initialisation du JPanel de la classe BarreCreationRequete

		super(new GridLayout(1,13));

		this.f = f;

		// Création de l'ActionListener affecté aux boutons de la Barre

		a = new ActionBarreCreationRequete(f);

		// Création des boutons, personnalisation et ajout de l'ActionListener

		nouveau = new JButton(new ImageIcon("Image/New.png"));
		nouveau.addActionListener(a);
		nouveau.setBorderPainted(false);
		nouveau.setToolTipText("New Script");

		executer = new JButton(new ImageIcon("Image/Execution.png"));
		executer.addActionListener(a);
		executer.setBorderPainted(false);
		executer.setToolTipText("Script Execution");

		indenter = new JButton(new ImageIcon("Image/indent.png"));
		indenter.addActionListener(a);
		indenter.setBorderPainted(false);
		indenter.setToolTipText("Indent Script");

		rechercher = new JButton(new ImageIcon("Image/Search.png"));
		rechercher.addActionListener(a);
		rechercher.setBorderPainted(false);
		rechercher.setToolTipText("Search");

		texte = new JButton(new ImageIcon("Image/Texte.png"));
		texte.addActionListener(a);
		texte.setBorderPainted(false);
		texte.setToolTipText("Texte Mode");

		graphique = new JButton(new ImageIcon("Image/Graph.png"));
		graphique.addActionListener(a);
		graphique.setBorderPainted(false);
		graphique.setToolTipText("Graphique Mode");

		compilation = new JButton(new ImageIcon("Image/compile.png"));
		compilation.addActionListener(a);
		compilation.setBorderPainted(false);
		compilation.setToolTipText("Compilation");

		execution = new JButton("Execution");
		execution.addActionListener(a);
		execution.setBorderPainted(false);
		execution.setToolTipText("Execution");

		effacer = new JButton(new ImageIcon("Image/8.png"));
		effacer.addActionListener(a);
		effacer.setBorderPainted(false);
		effacer.setToolTipText("Effacer");

		currentConnexion = new JComboBox<ConnectDB>();
		for(ConnectDB c : f.getController().getConnexions())
			if(c.isConnected())
				currentConnexion.addItem(c);


		JPanel space1, space2, space3, space4;
		
		jtb = new JToolBar();
		space1 = new JPanel();
		space2 = new JPanel();
		space3 = new JPanel();
		space4 = new JPanel();

		jtb.add(nouveau);
		jtb.add(executer);
		jtb.add(indenter);
		jtb.add(rechercher);
		jtb.add(effacer);
		jtb.add(space1);
		jtb.add(space2);
		jtb.add(texte);
		jtb.add(graphique);
		jtb.add(space3);
		jtb.add(space4);
		jtb.add(compilation);
		jtb.add(execution);
		jtb.add(currentConnexion);

		this.add(jtb);

	}

	public JButton getNouveau () { return this.nouveau; }
	public JButton getExecuter () { return this.executer; }
	public JButton getIndenter () { return this.indenter; }
	public JButton getRechercher () { return this.rechercher; }
	public JButton getEffacer () {return this.effacer; }

	public JButton getTexte () { return this.texte; }
	public JButton getGraphique () { return this.graphique; }

	public JButton getCompilation () { return this.compilation; }
	public JButton getExecution () { return this.execution; }

	public ActionBarreCreationRequete getActionBarre() { return this.a; }

	public DBFrame getFrame() { return this.f; }
	public JComboBox<ConnectDB> getCurrentConnexion() { return this.currentConnexion;}
}
