package view.interfaces;

import view.elements.*;
import controleur.*;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * This is the class Accueil which extends the class JFrame.
 * In this class, we create a JFrame where we add several components like a JTextField and 
 * a JPasswordField without forgetting the DBrium logo.
 * This page is the first page that the user sees when opening the software.
 * In order to access the main frame, it will need a DBrium account.
 * @author M.Hervé, T.Furno, A.Gicquel
 * @version 1.0
 */

public class Accueil extends JFrame {

    /**
     * Theyr JPanel are they elements of they container of the Frame.
     */

    private JPanel mdp, pan, debut, panMdp, password, users;

    /**
     * Theyr JLabel show where we must write our password and our Login.
     */

    private JLabel user, pass;

    /**
     * The JPasswordField ( so where we enter our password )
     */

    private JPasswordField jps;

    /**
     * When we push this JButton, The logiciel see if your login and your
     * password are good or wrong. If they are good, the start button 
     * launch the software.
     */

    private JButton start;

    /**
     * The space where we enter our login
     */

    private JTextField jt;

    /**
     * The principal Frame
     */

    private DBFrame f;

    /**
     * The constructor of the Accueil.
     * @param f - The Frame which is open after the Accueil
     */

    public Accueil(DBFrame f) {

        super("Dbrium : Le gestionnaire de base de donnees");

        try {

            setIconImage(ImageIO.read(new File("Image/DBrium.png")));
        } 

        catch (IOException e) {

            e.printStackTrace();
        }

        this.f = f;

        this.getContentPane().setLayout(new BorderLayout());

        this.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        this.setPreferredSize(new Dimension(1000, 1000));

        this.setBackground(new Color(6, 24, 33));

        this.setResizable(false);

        // They JPanel

        mdp = new JPanel(new GridLayout(1,1,200,200)); 
        mdp.setBackground(new Color(0, 33, 68));

        pan = new JPanel(new GridLayout(2,1,100,100)); 
        pan.setBackground(new Color(0, 33, 68));

        debut = new JPanel(new FlowLayout());
        debut.setBackground(new Color(0, 33, 68));

        panMdp = new JPanel(new GridLayout(4,2,20,20));
        panMdp.setBackground(new Color(0, 33, 68));
        panMdp.setBorder(BorderFactory.createEmptyBorder(110,100,110,100));

        password = new JPanel(new FlowLayout());
        password.setBackground(new Color(0, 33, 68));

        users = new JPanel(new FlowLayout());
        users.setBackground(new Color(0, 33, 68));

        // The JPasswordField and the JTextField

        jps = new JPasswordField();
        jt = new JTextField();

        // They JLabel

        pass = new JLabel("Password");
        pass.setForeground(new Color(0,204,204));

        user = new JLabel("Login");
        user.setForeground(new Color(0,204,204));

        // The JButton 

        start = new JButton("Connexion");
        start.addActionListener(new ActionDebut(f));

        // We add they differents elements at the Frame

        this.add(pan, BorderLayout.CENTER);
        this.add(debut, BorderLayout.SOUTH);

        debut.add(start);

        pan.add(new JLabel(new ImageIcon("Image/DBrium.png")));
        pan.add(mdp);

        mdp.add(panMdp);

        JPanel vide1 = new JPanel();
        vide1.setBackground(new Color(0, 33, 68));
        JPanel vide2 = new JPanel();
        vide2.setBackground(new Color(0, 33, 68));
        JPanel vide3 = new JPanel();
        vide3.setBackground(new Color(0, 33, 68));
        JPanel vide4 = new JPanel();
        vide4.setBackground(new Color(0, 33, 68));

        panMdp.add(vide1);
        panMdp.add(vide2);
        panMdp.add(users);
        panMdp.add(jt);
        panMdp.add(password);
        panMdp.add(jps);
        panMdp.add(vide3);
        panMdp.add(vide4);

        password.add(pass);
        users.add(user);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * The getter of the Accueil class
     */

    public JPanel getPan () { return this.pan; }
    public JPanel getMdp () { return this.mdp; }

    public JPasswordField getJps () { return this.jps; }

    public JTextField getJt () { return this.jt; }

    public JButton getStart () { return this.start; }

}