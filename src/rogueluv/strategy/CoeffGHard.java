package rogueluv.strategy;

import rogueluv.framework.Vector2;

import rogueluv.model.Cell;
import rogueluv.model.Floor;

public class CoeffGHard extends BasicGStrategy {
    
    public CoeffGHard() {
        minPlayerStrength = 10;
        maxPlayerStrength = 15;
        minMonsterStrength = 15;
        maxMonsterStrength = 25;
        minPotionStats = -15;
        maxPotionStats = +14;
    }
}
