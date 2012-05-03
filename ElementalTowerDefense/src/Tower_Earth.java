import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Tower_Earth extends Tower {

	static final int cost = 1000;

	public Tower_Earth(Point2D.Double loc) {
		super(Tower.tower_ids++, "EARTH TOWER", 1.2f, loc, Frame.element.EARTH,
				true, cost, 1);
	}
	
	public void draw(Graphics2D g, int width){
		g.setColor(Color.GREEN);
		super.draw(g,width);
	}
}