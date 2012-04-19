import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public class Tower_Water extends Tower {
	public Tower_Water (int id, Point2D.Double loc){
		
		super(id, "WATER TOWER", 1.2f, loc, Frame.element.WATER, true, 1000, 1);
	}
}
