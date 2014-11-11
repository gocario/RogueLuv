package rogueluv.model;

import rogueluv.controller.RogueLuv;

/**
 * Classe modèle d'un escalier ascendant pour RogueLuv
 * @author gocario
 * @version 1.0
 */
public class Upstairs extends Stairs {

    /**
     * Constructeur de la classe
     * @param floor Palier ascendant
     */
    public Upstairs(Floor floor) {
        super(floor);
    }


    /**
     * Action de la cellule: Monter les escalier
     * @return true Si la case est reset
     */
    @Override
    public boolean action() {
        System.out.println(floor);
        System.out.println("Upstairs.action()");
        
        return super.action();
    }
    
    
    private static char symbol = '>';
    public char getSymbol() {
        return symbol;
    }
}
