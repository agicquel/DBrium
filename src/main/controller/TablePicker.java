package controller;

import model.ConnectDB;
import model.Table;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class TablePicker
{
	private ArrayList<ConnectDB> cons;

	public TablePicker(ConnectDB ... cdb)
	{
		for(ConnectDB c : cdb)
			this.cons.add(c);

		JFrame frame = new JFrame("Choisir sa table");
		
		



	}
}