package rogueluv.model;

import rogueluv.controller.RogueLuv;

/**
 * Classe mod�le d'une potion pour RogueLuv
 * @author gocario
 * @version 1.0
 */
public class Potion extends rogueluv.model.CellType {
    
    private int strength = 0;


    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }


    /**
     * Constructeur de la classe
     * @param strength Puissance donn�e
     */
    public Potion(int strength) {
        this.strength = strength;
    }


    /**
     * Action de la cellule: R�cup�rer la potion
     * @return true Si la case est reset
     */
    @Override
    public boolean action() {
        System.out.println("Potion.action()");
        RogueLuv.getInstance().writeConsole("Votre force a �t� "+(strength >= 0 ? "augment�e" : "diminu�e")+" de "+Math.abs(strength));
        Player.getInstance().addStrength(strength);
        return true;
    }

    
    private static char symbol = 'p';
    public char getSymbol() {
        return symbol;
    }
}
