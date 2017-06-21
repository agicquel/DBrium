package util;

import java.awt.BorderLayout;

import java.awt.*;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import javax.swing.ImageIcon;

import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.JDialog;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JRadioButton;

import javax.swing.ButtonGroup;

import javax.swing.JTextField;

import javax.swing.*;


public class ConnexionDialog extends JDialog {


  private boolean sendData;

  private JLabel nameUser, nameConnexion, mdp, nomHote, nomPort, sid, listeConnexion;

  private JTextField nameUserField, nameConnexionField, mdpField, sidField, nomHoteField, nomPortField;

  private JPanel centre, panConnexion, panTaille, panelBouton, panHaut, panBas, panInfoCo;

  private JCheckBox ch;

  private JButton save, help, connect, effacer, cancel;

  private String nameCo;


  public ConnexionDialog(JFrame parent, String title, boolean modal){

    super(parent, title, modal);

    this.setPreferredSize(new Dimension(800, 500));

    this.setResizable(true);

    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    this.initComponent();

    this.pack();
    this.setVisible(true);

  }


  private void initComponent(){


    //Le nom

    panConnexion = new JPanel(new BorderLayout());
    panHaut = new JPanel(new GridLayout(3,3,20,20));
    panBas = new JPanel(new FlowLayout()); 

    //ch = new JCheckBox("Enregistrer le mot de passe");

    //panConnexion.setBackground(Color.white);

    //panConnexion.setPreferredSize(new Dimension(220, 60));

    nameUserField = new JTextField();
    nameConnexionField = new JTextField();
    mdpField = new JTextField();

    nameUser = new JLabel("Nom d'utilisateur : ");
    nameConnexion = new JLabel("Nom de la connexion : ");
    mdp = new JLabel("Mot de passe : ");

    panHaut.add(nameConnexion);
    panHaut.add(nameConnexionField);
    panHaut.add(nameUser);
    panHaut.add(nameUserField);
    panHaut.add(mdp);
    panHaut.add(mdpField);

    //panBas.add(ch);

    panConnexion.add(panHaut, BorderLayout.CENTER);
    //panConnexion.add(panBas, BorderLayout.SOUTH);


    panConnexion.setBorder(BorderFactory.createTitledBorder("Connexion"));


    //Les Infos

    panInfoCo = new JPanel(new GridLayout(3,3,20,20));

    //panInfoCo.setBackground(Color.white);

    //panInfoCo.setPreferredSize(new Dimension(220, 60));

    panInfoCo.setBorder(BorderFactory.createTitledBorder("Info Connexion"));

    nomPort = new JLabel("Port : ");
    sid = new JLabel("SID : ");
    nomHote = new JLabel("Nom d'hote : ");

    nomPortField = new JTextField();
    nomHoteField = new JTextField();
    sidField = new JTextField();

    panInfoCo.add(nomHote);
    panInfoCo.add(nomHoteField);
    panInfoCo.add(nomPort);
    panInfoCo.add(nomPortField);
    panInfoCo.add(sid);
    panInfoCo.add(sidField);


    //Les boutons

    panelBouton = new JPanel(new BorderLayout());

    JPanel lb = new JPanel(new FlowLayout());
    JPanel lb2 = new JPanel(new FlowLayout());
    JPanel vide = new JPanel();
    save = new JButton("Enregistrer");
    save.addActionListener(new ActionListener(){

    public void actionPerformed(ActionEvent arg0) {        

        nameCo = nameConnexionField.getText();
        setVisible(false);

    }});

    effacer = new JButton("Effacer");
    cancel = new JButton("Annuler");
    cancel.addActionListener(new ActionListener(){

      public void actionPerformed(ActionEvent arg0) {

         setVisible(false);

      }      

    });

    lb.add(save);
    lb.add(vide);
    lb.add(effacer);

    lb2.add(cancel);

    panelBouton.add(lb, BorderLayout.EAST);
    panelBouton.add(lb2, BorderLayout.WEST);

    




    //Le tableau des co

   /** panTaille = new JPanel(new GridLayout(1,1));

    listeConnexion = new JLabel("Liste des connexions");
    String [] table = {"Nom de connexion","Détails de connexion"};
    Object [][] lol = new Object[20][2]; 
    JTable listeConnexionTable = new JTable(lol,table);

    /**for(int i = 0; i < 20; i++) {

        listeEval.setRowHeight(i,45);
    }*/

    //panTaille.add(listeConnexionTable);

    centre = new JPanel(new GridLayout(2,1));

    centre.add(panConnexion);
    centre.add(panInfoCo);

    this.getContentPane().add(centre, BorderLayout.CENTER);

    this.getContentPane().add(panelBouton, BorderLayout.SOUTH);

    //this.getContentPane().setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

  }  

  public String getNameCo() { return this.nameCo; }

}