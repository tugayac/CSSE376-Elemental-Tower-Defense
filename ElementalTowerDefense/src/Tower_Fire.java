import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Tower_Fire extends Tower {

	static final int cost = 1000;

	public Tower_Fire(Point2D.Double loc) {
		super(Tower.tower_ids++, "FIRE TOWER", 1.2f, loc, Frame.element.FIRE,
				true, cost, 1);
	}
	
	public void draw(Graphics2D g, int width){
		g.setColor(Color.RED);
		super.draw(g,width);
	}
}