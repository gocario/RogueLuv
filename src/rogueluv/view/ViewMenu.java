package rogueluv.view;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import rogueluv.controller.RogueLuv;

import rogueluv.framework.Difficulty;

import rogueluv.strategy.BasicGStrategy;
import rogueluv.strategy.CoeffGHard;
import rogueluv.strategy.CoeffGHardcore;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import rogueluv.controller.Scores;

import rogueluv.model.Starway;

/**
 * Classe vue, affiche le menu
 * @author germain
 * @version 1.0
 */

public class ViewMenu extends JMenuBar {
    
    /**
     * Constructeur de la classe
     */
    public ViewMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu, submenu;
        JMenuItem menuItem;
        
        menu = new JMenu("Partie");
        menuBar.add(menu);

        //Menu nouvelle partie
        menuItem = new JMenuItem("Nouvelle partie");
        menuItem.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("Création d'une nouvelle partie");
            RogueLuv.getInstance().createGame();
        }
        });
        menu.add(menuItem);

        //Menu difficulté
        menu.addSeparator();
        
        submenu = new JMenu("Difficulté");
        menuItem = new JMenuItem("Normal");
        menuItem.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("Difficulté => Normal");
            RogueLuv.getInstance().setDifficulty(Difficulty.Normal);
            RogueLuv.getInstance().writeConsole("Difficulté => Normal");
        }
        });
        submenu.add(menuItem);
        
        menuItem = new JMenuItem("Difficile");
        menuItem.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("Difficulté => Difficile");
            RogueLuv.getInstance().setDifficulty(Difficulty.Hard);
            RogueLuv.getInstance().writeConsole("Difficulté => Difficile");
        }
        });
        submenu.add(menuItem);
        menuItem = new JMenuItem("H4RDC0R3");
        menuItem.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("Difficulté => Hardcore");
            RogueLuv.getInstance().setDifficulty(Difficulty.Hardcore);
            RogueLuv.getInstance().writeConsole("Difficulté => Hardcore");
        }
        });
        
        submenu.add(menuItem);
        menu.add(submenu);
        
        //Menu meilleurs scores  
        menu.addSeparator();
        menuItem = new JMenuItem("Meilleurs scores");
        menuItem.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("Affichage des meilleurs scores");
            showBestScores();
        }
        });
        menu.add(menuItem);

        this.add(menuBar);
    }
    
    /**
     * Affiche les meilleurs scores
     * @return void
     */
    public void showBestScores(){
        String[] scores = Scores.getBestScores();
        String meilleursScores = "";
        if(scores != null)
        {
            for(int i=0;i<scores.length;i++) {
                if(scores[i] != null) {
                    meilleursScores += "\n" + scores[i];                
                }
            }
        }
        else 
        {
            meilleursScores = "\nAucun score enregistré pour le moment";
        }
        //TODO: Recuperer les scores
       JOptionPane.showConfirmDialog(null, "Meilleurs scores : \n" + meilleursScores.toString(), 
        "Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }
}


