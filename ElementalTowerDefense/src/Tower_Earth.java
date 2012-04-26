import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public class Tower_Earth extends Tower {
	public Tower_Earth (Point2D.Double loc){
		
		super(Tower.tower_ids++, "EARTH TOWER", 1.2f, loc, Frame.element.EARTH, true, 1000, 1);
	}
}