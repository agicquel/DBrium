package controleur;

import view.elements.*;
import view.interfaces.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ActionFenetreCreationRequete implements KeyListener {

	private int index;
	private DBFrame f;
	private String line;
	private String [] wordBDD;

	public ActionFenetreCreationRequete(DBFrame f) {

		this.f = f;
		this.index = 1;
		this.line = "1. \n";
	


	}

	public void keyPressed (KeyEvent e) {


		Object source = e.getSource();

		if (source == f.getFenetre().getWriteQuerry()) {

			String s = f.getFenetre().getWriteQuerry().getText();

			for(String t : wordBDD) {

				if( s.contains(t)) {


					//String l = new String("<html><body><font color='red'>NOM</font></body></html>");

				}
			}
		}
	}

	public void keyReleased (KeyEvent e) {

		Object source = e.getSource();

		// Vérifie que le nom ne contient pas de chiffres

		/**if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {

			index++;
			line += "" + index + ". " + "\n";
			f.getFenetre().getWriteQuerry().setText(line);
		}*/

	}

	public void keyTyped(KeyEvent e) {



	}

}
