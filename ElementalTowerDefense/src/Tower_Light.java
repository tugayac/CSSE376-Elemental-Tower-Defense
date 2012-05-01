import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Tower_Light extends Tower {

	static final int cost = 1000;

	public Tower_Light(Point2D.Double loc) {
		super(Tower.tower_ids++, "LIGHT TOWER", 1.2f, loc, Frame.element.LIGHT,
				true, cost, 1);
	}
}
