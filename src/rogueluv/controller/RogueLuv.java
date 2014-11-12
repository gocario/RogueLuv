package rogueluv.controller;

import java.awt.event.KeyEvent;

import java.lang.reflect.Field;

import java.nio.charset.Charset;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import rogueluv.framework.Difficulty;
import rogueluv.framework.Direction;

import rogueluv.framework.Random;
import rogueluv.framework.Vector2;

import rogueluv.model.Cell;
import rogueluv.model.CellType;
import rogueluv.model.Downstairs;
import rogueluv.model.Floor;
import rogueluv.model.Player;
import rogueluv.model.Stairs;

import rogueluv.model.Starway;
import rogueluv.model.Upstairs;

import rogueluv.strategy.BasicGStrategy;
import rogueluv.strategy.CoeffGHard;
import rogueluv.strategy.CoeffGHardcore;
import rogueluv.strategy.CoeffGStrategy;
import rogueluv.strategy.IGStrategy;

import rogueluv.view.WindowView;

/**
 * Classe controlleur pour RogueLuv
 * @author gocario
 * @version 1.0
 */
public class RogueLuv {

    private static RogueLuv instance = null;
    
    public static RogueLuv getInstance() {
        if (instance == null) {
            instance = new RogueLuv();
        }
        return instance;
    }
    
    private WindowView windowView;
    private IGStrategy strategy;
    private Difficulty difficulty = Difficulty.Normal;
    private boolean isRunning;

    public void setWindowView(WindowView windowView) {
        this.windowView = windowView;
    }

    public WindowView getWindowView() {
        return windowView;
    }

    public void setCurrentFloor(Floor currentFloor) {
        Player.getInstance().setCurrentFloor(currentFloor);
    }

    public Floor getCurrentFloor() {
        return Player.getInstance().getCurrentFloor();
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isIsRunning() {
        return isRunning;
    }


    /**
     * Constructeur de la classe
     */
    private RogueLuv() {
        //TODO: Generate the whole labyrinth and the views
        createGame();
    }
    
    /**
     * Cr��e un nouveau jeu
     * @return void
     */
    public void createGame() {
        
        switch (difficulty) {
            case Normal:
                strategy = new BasicGStrategy();
                break;
            case Hard:
                strategy = new CoeffGHard();
                break;
            case Hardcore:
                strategy = new CoeffGHardcore();
            default:
                strategy = new BasicGStrategy();
            
        }
        
        
        
        Random rand = new Random();
        Player player = Player.resetInstance();
        player.setCurrentFloor(strategy.generateFloors());
        player.setStrength(strategy.generatePlayerStrength());
        player.setPosition(new Vector2(
            rand.rint(0, player.getCurrentFloor().getSize().getWidth()),
            rand.rint(0, player.getCurrentFloor().getSize().getHeight())
        ));
        
        //TODO: Si une partie avait d�j� �t� cr�ee on r�initialise la vue
        if (windowView == null) {
            windowView = new WindowView();
        } else {
            windowView.clearConsole();
        }
        
        windowView.writeConsole("Bienvenue sur RogueLuv - Version 1.1.15");
        windowView.writeConsole("Vous entrez dans une zone sombre ...");
        windowView.setSize(640, 480);
        windowView.setVisible(true);
        
        System.out.println("Floor.element.size(): " + player.getCurrentFloor().getElements().size());
        System.out.println("Floor.size: " + player.getCurrentFloor().getSize());
        System.out.println("Player.position: " + player.getPosition());
        

        windowView.updateNearMonsters(player.discover());
        windowView.update();
        
        isRunning = true;
    }
    
    /**
     * Fais gagner le jeu
     * @return void
     */
    public void winGame() {

        String pseudo = JOptionPane.showInputDialog(null,"Pseudo :");
        writeConsole("F�licitations " + pseudo + " vous avez fini le jeu.");
        Boolean scoreAdded = G_Score.addScore(pseudo + ":" + (Player.getInstance().getGold()+500));
        if(scoreAdded) {
            writeConsole(pseudo + " � �tabli un nouveau record de " + (Player.getInstance().getGold()+500) + " points !");
        }
        windowView.showScores();
        isRunning = false;
    }
    
    /**
     * Fais perdre le jeu
     * @return void
     */
    public void loseGame(String monsterName) {
        
        writeConsole("Vous avez �t� tu� par "+monsterName+"...");
        String pseudo = JOptionPane.showInputDialog(null,"Pseudo :");
        Boolean scoreAdded = G_Score.addScore(pseudo + ":" + (Player.getInstance().getGold()));
        if(scoreAdded) {
            writeConsole(pseudo + " � �tabli un nouveau record de " + (Player.getInstance().getGold()) + " points !");
        }
        windowView.showScores();
        isRunning = false;
    }
    
    
    /**
     * D�place le joueur
     * @param direction Direction dans laquelle le d�placer
     * @return true Si le joueur a boug�
     */
    public boolean movePlayer(Direction direction) {
        //TODO: D�placer le personnage
        //TODO: D�couvrir les cases alentours
        
        System.out.println(direction);
        Player player = Player.getInstance();
        boolean hasMove = player.move(direction);
        
        if (hasMove) {

            System.out.println("Player moved to: " + player.getPosition().toString());

            //TODO: Int�ragir avec la nouvelle case
            Cell cell =  player.getCurrentFloor().getCell(player.getPosition());
            cell.action();


            //TODO: D�couvrir les case proches 8x8
            windowView.updateNearMonsters(player.discover());

            windowView.update();
        }
        return hasMove;
    }

    /**
     * Actionne la cellule sous l'utilisateur
     * @return void
     */
    public void cellAction() {
        Player.getInstance().getActualCell().action();
    }


    /**
     * Change le palier actuel
     * @param stairs Escaliers menant au nouveau palier
     * @return void
     */
    public void moveToFloor(Stairs stairs) {
        this.moveToFloor(stairs.getFloor());
    }

    /**
     * Change le palier actuel
     * @param floor Nouveau palier
     * @return void
     */
    public void moveToFloor(Floor floor) {
        //TODO: Chercher la position de l'escalier descendant dans 'floor'
        //TODO: R�cup�rer se position et l'affecter � 'Player'
        Player player = Player.getInstance();

        
        if (floor.getLevel() < player.getCurrentFloor().getLevel()) {
            player.setPosition(new Vector2(floor.getUpstairs(player.getCurrentFloor()).getPosition()));
        } else {
            player.setPosition(new Vector2(floor.getDownstairs().getPosition()));
        }
        
        player.setCurrentFloor(floor);
        windowView.updateNearMonsters(player.discover());
        windowView.update();
    }
    
    /**
     * Ecris une nouvelle ligne dans la vue Console
     * @param Cha�ne de caract�res � �crire
     * @return void
     */
    public void writeConsole(String string) {
        //TODO: Call the method ViewConsole.addRow(string);

        windowView.writeConsole(string);
    }

    /**
     * G�re les touches appuy�es
     * @param e
     * @return void
     */
    public void keyPressed(KeyEvent e) {
        //TODO: Interpr�te les entr�es claviers
        if (isRunning) {
            switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
            case KeyEvent.VK_UP:
                movePlayer(Direction.Up);
                break;
    
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                movePlayer(Direction.Down);
                break;
    
            case KeyEvent.VK_Q:
            case KeyEvent.VK_LEFT:
                movePlayer(Direction.Left);
                break;
    
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                movePlayer(Direction.Right);
                break;
            
            case KeyEvent.VK_Y:
                Player player = Player.getInstance();
                ArrayList<Cell> cells = player.getCurrentFloor().getUpstairs();  
                cells.add(player.getCurrentFloor().getDownstairs());
                for (int i = 0; i < (cells != null ? cells.size() : 0); i++) {
                    if (player.getPosition().equals(cells.get(i).getPosition())) {
                        moveToFloor(((Stairs) cells.get(i).getCellType()).getFloor());
                        cells = null;
                    }
                }
                break;
            }
        } else {
            switch (e.getKeyCode()) {
            case KeyEvent.VK_N:
                createGame();
                break;
            }
        }
    }


    /**
     * Fonction main
     * @param args Arguments d'entr�s du programme
     * @return void
     */
    public static void main(String[] args) {
        RogueLuv rogueluv = RogueLuv.getInstance();
    }
}
