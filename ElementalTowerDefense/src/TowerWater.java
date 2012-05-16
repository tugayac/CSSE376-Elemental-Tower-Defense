import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class TowerWater extends Tower {

	static final int cost = 600;

	public TowerWater(Point2D.Double loc, int damage) {
		super(Tower.tower_ids++, "Water Tower", 1500, loc, Frame.element.WATER,
				true, cost, 1, damage);
		setImage(new ImageIcon(getClass().getResource(
				"/resources/images/towers/tower_water.png")));
	}

	public void draw(Graphics2D g, int width) {
		super.draw(g, width);
	}
}
