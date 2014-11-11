package rogueluv.strategy;

import rogueluv.model.Floor;

public class CoeffGHardcore extends BasicGStrategy {
    
    public CoeffGHardcore() {
        minPlayerStrength = 5;
        maxPlayerStrength = 10;
        minMonsterStrength = 15;
        maxMonsterStrength = 30;
        minPotionStats = -30;
        maxPotionStats = +25;
    }
}
