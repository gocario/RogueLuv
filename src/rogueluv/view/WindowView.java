package rogueluv.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import rogueluv.controller.RogueLuv;

/**
 * Classe vue, affiche la fen�tre principale pour RogueLuv
 * @author germain
 * @version 1.0
 */
public class WindowView extends JFrame implements KeyListener {

    private ViewConsole vConsole;
    private ViewStats vStats;
    private ViewFloor vFloor;
    private ViewMenu vMenu;


    /**
     * Constructeur de la classe
     */
    public WindowView() {
        initialize();
    }
    
    /**
     * Initialise la JFrame `this`
     * @return void
     */
    private void initialize() {
        //TODO: Set up childs JPanels
        
        //Ajout de la barre de menu
        vMenu = new ViewMenu();
        this.setJMenuBar((JMenuBar)vMenu);
        
        //JpanelEast
        JPanel jpEast = new JPanel();
        vStats = new ViewStats(this);
        jpEast.add(vStats);
        
        //JpanelCenter
        JPanel jpCenter = new JPanel(new FlowLayout());
        vFloor = new ViewFloor(this);
        jpCenter.add(vFloor);
        
        //JpanelSouth
        JPanel jpSouth = new JPanel(new FlowLayout());
        vConsole = new ViewConsole(this);
        vConsole.setBackgroundColor(Color.black);
        vConsole.setForegroundColor(Color.white);
        jpSouth.add(vConsole);
        
        //Ajout des key listener
        vConsole.addKeyListener(this);
        vStats.addKeyListener(this);
        vFloor.addKeyListener(this);
        this.addKeyListener(this);
        
        //Cr�ation de l'interface
        JPanel jpInterface = new JPanel(new BorderLayout());
        jpInterface.add(jpCenter, BorderLayout.CENTER);
        jpInterface.add(jpSouth, BorderLayout.SOUTH);
        jpInterface.add(jpEast, BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(jpInterface);
        this.setTitle("RogueLuv - 2014");
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.setSize(400,400);
        this.setLocationRelativeTo(null); //On centre la fen�tre sur l'�cran
        this.setVisible(true);
    }

    /**
     * Affiche un message de demande � l'utilisateur pour le changement d'�tage
     * @return void
     */
    public void askUpstairs() {

    }

    /**
     * Met � jour tous les Views*`v*`.
     * @return void
     */
    public void update() {
        vStats.updateStats();
        vFloor.updateFloor();
        //this.pack();
    }

    /**
     * Met � jour le nombre de monstres adjacents.
     * @param count Nombre de monstres proches
     * @return void
     */
    public void updateNearMonsters(int count) {
        vStats.updateNearMonsters(count);
    }


    /**
     * KeyListener.keyPressed
     * @param e
     * @return void
     */
    public void keyPressed(KeyEvent e) {
        RogueLuv.getInstance().keyPressed(e);
        //System.out.println(e.toString());
    }

    /**
     * KeyListener.keyReleased
     * @param e
     * @return void
     */
    public void keyReleased(KeyEvent e) {
        //TODO: ... ?
    }

    /**
     * KeyListener.keyTyped
     * @param e
     * @return void
     */
    public void keyTyped(KeyEvent e) {
        //TODO: ... ?
    }

    /**
     * Ecris une ligne dans ViewConsole`vConsole`
     * @return void
     */
    public void writeConsole(String string) {
        vConsole.writeLine(string);
    }
    
    
    /**
     * Vide la console
     * @return void
     */
    public void clearConsole() {
        vConsole.clear();
    }
    
    /**
     * Affiche les meilleurs scores
     * @return void
     */
    public void showScores() {
        vMenu.showBestScores();
    }
}
