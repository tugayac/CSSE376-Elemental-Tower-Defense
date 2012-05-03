import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Tower_Water extends Tower {

	static final int cost = 1000;

	public Tower_Water(Point2D.Double loc) {
		super(Tower.tower_ids++, "WATER TOWER", 1.2f, loc, Frame.element.WATER,
				true, cost, 1);
	}
	
	public void draw(Graphics2D g, int width){
		g.setColor(Color.BLUE);
		super.draw(g,width);
	}
}
