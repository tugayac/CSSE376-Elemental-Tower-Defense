import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class TowerAir extends Tower {

	static final int cost = 500;

	public TowerAir(Point2D.Double loc, int damage) {
		super(Tower.tower_ids++, "Air Tower", 1000, loc, Frame.element.AIR,
				true, cost, 1, damage);
		setImage(new ImageIcon(getClass().getResource(
				"/resources/images/towers/tower_air.png")));
	}

	public void draw(Graphics2D g, int width) {
		super.draw(g, width);
	}
}
