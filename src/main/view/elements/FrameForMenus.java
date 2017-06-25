/**
* This frame is use when the button about is use in the menu help
* @author DBrium
* @version 1.0
*/
package view.elements;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class FrameForMenus extends JFrame{

	/**
	* Panel to add elements
	*/
	private JPanel pane;


	/**
	* label to show the text
	*/
	private JTextArea tArea;

	/**
	* Scroll bar
	*/
	private JScrollPane scroll;

	/**
	* Button to close the frame
	*/
	private JButton buttonOk;

	/**
	* The name of the file contain the informations
	*/
	private InputStream stream;

	/**
	* The informations to show in the panel
	*/
	private String info;


	/**
	* Constructor of the class
	* @param fileName the file name to take the informations to show
	* @param frameName the name to show in the frame
	*/
	public FrameForMenus(InputStream stream, String frameName){
		super(frameName);

		this.pane = new JPanel();
		JPanel d = new JPanel(new FlowLayout());

		this.buttonOk = new JButton("Ok");
		buttonOk.setPreferredSize(new Dimension(50, 25));
		buttonOk.setMaximumSize(new Dimension(50, 25));
		this.buttonOk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
						dispose();
     	   }
    	});

		this.info = "Text";
		this.stream = stream;
		this.tArea = new JTextArea(info);
		this.scroll = new JScrollPane(tArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


		try {

			Scanner scannerFile = new Scanner(this.stream);
			this.info = scannerFile.nextLine();
			while (scannerFile.hasNextLine()){
				this.info += "\n "+ scannerFile.nextLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.tArea = new JTextArea(this.info);
			this.tArea.setEditable(false);
			
			this.scroll = new JScrollPane(this.tArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		}

		d.add(buttonOk);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(d, BorderLayout.SOUTH);
		this.getContentPane().add(this.scroll, BorderLayout.CENTER);

    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    	this.setPreferredSize(new Dimension(400,250));

   		this.setResizable(true);

   		this.pack();
   		this.setVisible(true);


	}


}
