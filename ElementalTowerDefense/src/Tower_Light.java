import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public class Tower_Light extends Tower {
	public Tower_Light (Point2D.Double loc){
		
		super(Tower.tower_ids++, "LIGHT TOWER", 1.2f, loc, Frame.element.LIGHT, true, 1000, 1);
	}
}
