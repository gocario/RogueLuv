package rogueluv.model;

import rogueluv.controller.RogueLuv;

/**
 * Classe mod�le d'un tr�sor pour RogueLuv
 * @author gocario
 * @version 1.0
 */
public class Treasure extends CellType {
    
    private int gold = 0;


    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }


    /**
     * Constructeur de la classe
     * @param gold Or contenu dans le tr�sor
     */
    public Treasure(int gold) {
        this.gold = gold;
    }


    /**
     * Action de la cellule: R�cup�rer le tr�sor
     * @return true Si la case est reset
     */
    @Override
    public boolean action() {
        System.out.println("Treasure.action()");
        RogueLuv.getInstance().writeConsole("Vous ramassez "+gold+"$");
        Player.getInstance().addGold(gold);
        return true;
    }

    /**
     * Renvoie l'or du tr�sor
     * @return Or contenu dans le tr�sor
     */
    @Override
    public int countGold() {
        return gold;
    }


    private static char symbol = '$';
    public char getSymbol() {
        return symbol;
    }
}
