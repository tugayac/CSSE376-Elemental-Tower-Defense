import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public class Tower_Fire extends Tower {
	public Tower_Fire (int id, Point2D.Double loc){
		
		super(id, "FIRE TOWER", 1.2f, loc, Frame.element.FIRE, true, 1000, 1);
	}
}