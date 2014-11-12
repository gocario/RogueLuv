package rogueluv.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Properties;

public class Scores {
    public static String[] getBestScores(){
        Properties prop = new Properties();
        InputStream input = null;
        String[] res = new String[10];
        try {
            input = new FileInputStream("config.properties");
            // Chargement du fichier
            prop.load(input);
            // Scores de 1 � 10
            for(int i=0;i<10;i++) 
            {
                String playerName;
                String playerScore;
                try {
                     playerName = prop.getProperty("player" + i);
                     playerScore = prop.getProperty("score" + i);
                     if (playerName != null) {
                         res[i] = playerName + ":" + playerScore; 
                     }
                }
                catch (Exception ex) 
                {
                    System.out.println("Il y a moins de 10 scores enregistr�s");
                    break;
                }
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("Aucun score encore enregistr�.");
        } 
        finally {
            if (input != null) {
                try {
                    //On ferme le fichier
                    input.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    public static void saveBestScores(String[] scores){
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("config.properties");
            for(int i=0; i<10; i++){
                if(scores[i] != null) {
                    prop.setProperty("player" + i, scores[i].split(":")[0]);
                    prop.setProperty("score" + i, scores[i].split(":")[1]);
                    System.out.println("Score " + scores[i] + " enregistr�.");
                }
            }
            // save properties to project root folder
            prop.store(output, null);
        } 
        catch (IOException io) 
        {
            io.printStackTrace();
        } 
        finally
        {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static Boolean addScore(String score) {
        Boolean res = false;
        try 
        {
            //On r�cup�re la nouvelle valeur du score
            Integer newScore = Integer.parseInt(score.split(":")[1]);
            //On r�cup�re l'ensemble des scores enregistr�s
            String[] scores = getBestScores();
            for(int i=0;i<10;i++) {
                if(scores[i] != null) {
                    //L'ancien score enregistr�
                    Integer oldScore = Integer.parseInt(scores[i].split(":")[1]);
                    //Si on a fait un nouveau record
                    if(newScore > oldScore) {
                        scores[i] = score;
                        //On d�cale les scores qui suivent si il y en a encore
                        if(i != 9)
                        {
                            for(int j = i;j<9;j++) {
                                scores[j+1] = scores [j];
                            }
                        }
                        saveBestScores(scores);
                        return true;
                    }
                }
                else 
                {
                    //On ajoute le score car il y en a moins que 10
                    scores[i] = score;
                    saveBestScores(scores);
                    return true;
                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
