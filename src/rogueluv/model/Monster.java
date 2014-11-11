package rogueluv.model;

import rogueluv.controller.RogueLuv;

import rogueluv.framework.Random;

/**
 * Classe modèle d'un monstre pour RogueLuv
 * @author gocario
 * @version 1.0
 */
public class Monster extends CellType {
    
    private int strength = 0;
    private int gold = 0;


    public void addStrength(int strength) {
        this.strength += strength;
    }
    
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }
    
    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }
    
    
    /**
     * Constructeur de la classe
     */
    public Monster(int strength, int gold) {
        setStrength(strength);
        setGold(gold);
    }


    /**
     * Action de la cellule: Récupérer le trésor
     * @return true Si la case est reset
     */
    @Override
    public boolean action() {
        //TODO: Faire un lancer aléatoire pour dï¿½terminer l'issue du combat
        System.out.println("Monster.action()");
        
        int fP = Player.getInstance().getStrength();
        int fM = strength;
            
        if (new Random().rint(0, fP + fM) - fP <= 0) {
            
        } else {
            //RogueLuv.getInstance().lose();
        }
        
        return true;
    }


    /**
     * Renvoie le nombre de monstre
     * @return int Nombre de monstre
     */
    @Override
    public int countMonster() {
        return 1;
    }
    
    
    private static char symbol = '&';
    public char getSymbol() {
        return symbol;
    }
}
