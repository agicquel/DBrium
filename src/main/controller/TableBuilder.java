package controller;

import model.Query;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

/**
* Graphical tool to create SQL Table for Oracle
* To use it, you have to initialize a TableBuilder object
* and execute run like this : 
* TableBuilder tb = new TableBuilder();
* Query q = tb.run();
* tb.close();
* The current process will be paused until the sql is generate
* @author Antoine Gicquel
*/
public class TableBuilder extends JFrame implements ActionListener, WindowListener
{
	private JButton addBtn;
	private JButton delBtn;
	private JButton generateBtn;
	private JTextField nameTxtField;
	private JTable table;
	private DefaultTableModel model;

	private boolean done;
	private Query query;

	/**
	* Constructor : nothing is requiered
	*/
	public TableBuilder()
	{
		super("Créer une table");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		this.getContentPane().setLayout(new BorderLayout());
		this.done = false;

		JPanel tableNamePanel = new JPanel();
		tableNamePanel.setLayout(new GridLayout(1,2));
		tableNamePanel.add(new JLabel("Nom de la table : "));
		this.nameTxtField = new JTextField();
		tableNamePanel.add(this.nameTxtField);

		
		this.model = new DefaultTableModel() {
	      public Class getColumnClass(int columnIndex) {
	        Object o = getValueAt(0, columnIndex);
	        if (o == null) {
	          return Object.class;
	        } else {
	          return o.getClass();
	        }
	      }
	    };
	    this.table = new JTable(model);
	    model.addColumn("Nom", new Object[] { new String() });
	    model.addColumn("Type", new Object[] { new String() });
	    model.addColumn("Taille", new Object[] { new Integer(1) });
	   	model.addColumn("Primary Key", new Object[]{});
	    model.addColumn("Not Null", new Object[]{});
	    model.addColumn("Unique", new Object[]{});

	    setUpTypeColumn(table.getColumnModel().getColumn(1));
	    setCheckBoxColumn(table.getColumnModel().getColumn(3));
	    setCheckBoxColumn(table.getColumnModel().getColumn(4));
	    setCheckBoxColumn(table.getColumnModel().getColumn(5));
	    this.table.updateUI();


	    Container buttonContainer = new Container();
	    buttonContainer.setLayout(new GridLayout(2,1));
	    this.addBtn = new JButton("Ajouter");
	    this.addBtn.addActionListener(this);
	    this.delBtn = new JButton("Supprimer");
	    this.delBtn.addActionListener(this);
	    buttonContainer.add(this.addBtn);
	    buttonContainer.add(this.delBtn);

		JPanel generatePanel = new JPanel();
		this.generateBtn = new JButton("Générer");
		this.generateBtn.addActionListener(this);
		generatePanel.add(this.generateBtn);

		this.getContentPane().add(tableNamePanel, BorderLayout.NORTH);
		this.getContentPane().add(buttonContainer, BorderLayout.WEST);
		this.getContentPane().add(new JScrollPane(this.table), BorderLayout.CENTER);
		this.getContentPane().add(generatePanel, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);
	}

	/**
	* Initilize a "data type" column
	* @param dataTypeColumn the column to set
	*/
	private void setUpTypeColumn(TableColumn dataTypeColumn)
	{
		String[] types = {"VARCHAR2", "NVARCHAR2", "NUMBER", "LONG", "DATE"};
		JComboBox<String> typesComboBox = new JComboBox<String>(types);
		dataTypeColumn.setCellEditor(new DefaultCellEditor(typesComboBox));

	}

	/**
	* Initilize a checkbox column
	* @param column the column to set
	*/
	private void setCheckBoxColumn(TableColumn column)
	{
		column.setCellEditor(new DefaultCellEditor(new JCheckBox()));
		column.setCellRenderer(new CheckBoxRenderer());
	}

	/**
	* Launch run and process will be stoped until the class is generate
	* @return the generated query
	*/
	public synchronized Query run()
	{
		this.query = null;
		this.setVisible(true);
		while(!this.done)
		{
			try
			{
				wait();
			}
			catch(Exception e){}
		}
		this.setVisible(false);
		return query;
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == addBtn)
		{
			model.addRow(new Object[model.getColumnCount()]);
		}
		else if(e.getSource() == delBtn)
		{
			if(table.getSelectedRow() >= 0 && table.getSelectedRow() < table.getRowCount())
				model.removeRow(table.getSelectedRow());
		}
		else if(e.getSource() == generateBtn)
		{
			boolean check = true;

			if(nameTxtField.getText() == null || nameTxtField.getText().equals(""))
			{
				check = false;
				JOptionPane.showMessageDialog(new JFrame(), "Nom de la table invalide");
			}

			for(int i = 0; i < table.getRowCount() && check; i++)
			{
				for(int j = 0; j < 3 && check; j++)
				{
					if(model.getValueAt(i, j) == null || model.getValueAt(i, j).toString().isEmpty())
					{
						check = false;
						JOptionPane.showMessageDialog(new JFrame(), "Donnée invalide en (" + i + ", " + j + ")");
					}
				}
			}

			if(check)
				generateQuery();
		}
		this.table.updateUI();
	}

	/**
	* Create the query from info of the table
	*/
	public synchronized void generateQuery()
	{
		String s = "CREATE TABLE " + nameTxtField.getText() + " (\n";

		for(int i = 0; i < table.getRowCount(); i++)
		{
			s += "\n\t" + model.getValueAt(i, 0).toString();
			s += "\t" + model.getValueAt(i, 1).toString() + "(" + model.getValueAt(i, 2).toString() + ")";

			if(model.getValueAt(i, 4) != null && model.getValueAt(i, 4).toString() == "true")
				s += "\n\tCONSTRAINT NN" + model.getValueAt(i, 0).toString() + "\n\tNOT NULL";
			if(model.getValueAt(i, 5) != null && model.getValueAt(i, 5).toString() == "true")
				s += "\n\tCONSTRAINT UQ" + model.getValueAt(i, 0).toString() + "\n\tUNIQUE";

			if(i != table.getRowCount()-1)
				s += ",\n";
		}

		int nbOfPk = 0;
		int nbOfPkDone = 0;

		for(int i = 0; i < table.getRowCount(); i++)
			if(model.getValueAt(i, 3) != null && model.getValueAt(i, 3).toString() == "true")
				nbOfPk++;

		if(nbOfPk > 0)
		{
			s += ",\n\n\tCONSTRAINT PK" + nameTxtField.getText() + "\n\tPRIMARY KEY(";
			for(int i = 0; i < table.getRowCount(); i++)
			{
				if(model.getValueAt(i, 3) != null && model.getValueAt(i, 3).toString() == "true")
				{
					s += model.getValueAt(i, 0).toString();
					nbOfPkDone++;
					if(nbOfPk > nbOfPkDone)
						s += ", ";
				}
			}
			s += ")";
		}

		s += "\n)";

		this.query = new Query(s);
		this.done = true;
		notifyAll();
	}

	/**
	* Close the TableBuilder frame
	*/
	public void close()
	{
		dispose();
	}

	@Override
	public synchronized void windowClosing(WindowEvent e)
	{
        this.done = true;
        notifyAll();
	}
	public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	
}