package rogueluv.strategy;

import rogueluv.framework.Vector2;

import rogueluv.model.Cell;
import rogueluv.model.Floor;

public interface IGStrategy {
    
    public abstract Floor generateFloors();
    public abstract int generatePlayerLevel();
    
    /*
    public abstract Floor generateFloor(Floor parent);
    public abstract Cell generateCell(Vector2 position, Floor me, Floor parent);
    */
}
