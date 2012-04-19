import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public class Tower_Light extends Tower {
	public Tower_Light (int id, Point2D.Double loc){
		
		super(id, "LIGHT TOWER", 1.2f, loc, Frame.element.LIGHT, true, 1000, 1);
	}
}
