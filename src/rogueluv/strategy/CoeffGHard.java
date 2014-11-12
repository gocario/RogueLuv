package rogueluv.strategy;

import rogueluv.framework.Vector2;

import rogueluv.model.Cell;
import rogueluv.model.Floor;

public class CoeffGHard extends BasicGStrategy {
    
    public CoeffGHard() {
        coeffGMonster = 6;
        coeffGPotion = 5;
        
        minPlayerStrength = 10;
        maxPlayerStrength = 15;
        minMonsterStrength = 10;
        maxMonsterStrength = 22;
        minPotionStats = -13;
        maxPotionStats = +16;
        
        minMonsterGold = 14;
        maxMonsterGold = 19;
        minTreasureGold = 16;
        maxTreasureGold = 23;
    }
}
