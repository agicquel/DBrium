package controleur;

import view.elements.*;
import view.interfaces.*;

import javax.swing.*;
import java.awt.event.*;
import util.*;

public class ActionDebut implements ActionListener {
	
	private DBFrame f;

	public ActionDebut (DBFrame f) {

		this.f = f;

	}

	public void actionPerformed (ActionEvent e) {

		Object source = e.getSource();
		boolean ok = false;

		if(source == f.getAccueil().getStart()) {

			System.out.println("OK");

			if( f.getAccueil().getJt().getText().equals("admin") ) {

				char [] leChar = f.getAccueil().getJps().getPassword();
				String password = new String(leChar);

				if ( password.equals("admin")) {

					f.getAccueil().dispose();
					Window w = new Window();


				}

			} else { System.out.println("Wrong account !");}
		}

	}
}