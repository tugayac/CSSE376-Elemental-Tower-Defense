import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public class Tower_Air extends Tower {
	public Tower_Air (int id, Point2D.Double loc){
		
		super(id, "AIR TOWER", 1.2f, loc, Frame.element.AIR, true, 1000, 1);
	}
}
