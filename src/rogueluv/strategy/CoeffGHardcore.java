package rogueluv.strategy;

import rogueluv.model.Floor;

public class CoeffGHardcore extends BasicGStrategy {
    
    public CoeffGHardcore() {
        coeffGMonster = 8;
        coeffGPotion = 5;
        
        maxStairs = 4;
        
        minPlayerStrength = 6;
        maxPlayerStrength = 12;
        minMonsterStrength = 16;
        maxMonsterStrength = 30;
        minPotionStats = -20;
        maxPotionStats = +23;
        
        minMonsterGold = 24;
        maxMonsterGold = 34;
        minTreasureGold = 23;
        maxTreasureGold = 39;
    }
}
