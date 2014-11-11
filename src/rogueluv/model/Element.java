package rogueluv.model;

import rogueluv.framework.Vector2;

/**
 * Classe mod�le d'un �l�ment abstrait pour RogueLuv
 * @author gocario
 * @version 1.0
 */
public abstract class Element {

    public Vector2 getPosition() {
        return Vector2.Minus;
    }
    
    
    public abstract int countGold();
    public abstract int countMonster();
    
    
    public char getSymbol() {
        return ' ';
    }
}
