package rogueluv.view;
 
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rogueluv.model.Player;
import rogueluv.model.Floor;
 
/**
 * Classe vue, affiche les stats pour RogueLuv
 * @author germain
 * @version 1.0
 */
public class ViewStats extends JPanel {
   
    private JLabel playerGold;
    private JLabel playerStrength;
    private JLabel currentLevel;
       
    /**
     * Constructeur de la classe
     */
    public ViewStats(KeyListener listener) {
        initialize(listener);
    }
   
    /**
     * Initialise le JPanel `this`
     * @return void
     */
    private void initialize(KeyListener listener) {
        //TODO: Set up the size of the JLabels
        playerGold = new JLabel();
        playerStrength = new JLabel();
        currentLevel = new JLabel();
        JLabel jlTitre = new JLabel();
        jlTitre.setText("<html><u>Statistiques</u></html>");
        //jlTitre.setDefaultCursor(Cursor.HAND_CURSOR);
       
        playerGold.addKeyListener(listener);
        playerStrength.addKeyListener(listener);
        currentLevel.addKeyListener(listener);
       
        this.add(jlTitre);
        this.add(playerGold);
        this.add(playerStrength);
        this.add(currentLevel);
        this.setLayout(new GridLayout(6,1));
    }
   
    /**
     * Met à jour l'affichage des stats
     * @return void
     */
    public void updateStats() {
        Player player = Player.getInstance();
        Floor floor = player.getCurrentFloor();
        //TODO: Display the player's stats in JLabels`player[...]`
        playerGold.setText("Argent : $" + Integer.toString(player.getGold()));
        playerStrength.setText("Force : " + Integer.toString(player.getStrength()));
        currentLevel.setText("Niveau : " + Integer.toString(floor.getLevel()));
    }
}