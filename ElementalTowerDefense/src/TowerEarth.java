import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class TowerEarth extends Tower {

	static final int cost = 800;

	public TowerEarth(Point2D.Double loc, int damage) {
		super(Tower.tower_ids++, "Earth Tower", 2000, loc, Frame.element.EARTH,
				true, cost, 1, damage);
		setImage(new ImageIcon(getClass().getResource(
				"/resources/images/towers/tower_earth.png")));
	}

	public void draw(Graphics2D g, int width) {
		super.draw(g, width);
	}
}