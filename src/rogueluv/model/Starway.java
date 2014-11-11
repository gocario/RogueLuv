package rogueluv.model;

import javax.swing.JOptionPane;

import rogueluv.controller.RogueLuv;
import rogueluv.controller.Scores;

/**
 * Classe modèle d'un escalier finissant pour RogueLuv
 * @author gocario
 * @version 1.0
 */
public class Starway extends CellType {

    /**
     * Action de la cellule: Finir le jeu
     * @return true Si la case est reset
     */
    @Override
    public boolean action() {
        //TODO: Finir le jeu
        System.out.println("Starway.action()");
        String pseudo = JOptionPane.showInputDialog(null,"Pseudo :");
        RogueLuv.getInstance().writeConsole("Félicitations " + pseudo + " vous avez fini le jeu.");
        Boolean scoreAdded = Scores.addScore(pseudo + ":" + Player.getInstance().getGold());
        if(scoreAdded) {
            RogueLuv.getInstance().writeConsole(pseudo + " à établi un nouveau record de " + Player.getInstance().getGold() + " points!");
        }
        return false;
    }
    

    
    private static char symbol = 'O';
    public char getSymbol() {
        return symbol;
    }
}
