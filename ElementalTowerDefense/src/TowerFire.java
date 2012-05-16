import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class TowerFire extends Tower {

	static final int cost = 1000;

	public TowerFire(Point2D.Double loc) {
		super(Tower.tower_ids++, "Fire Tower", 1.2f, loc, Frame.element.FIRE,
				true, cost, 1, 100);
		setImage(new ImageIcon(getClass().getResource(
				"/resources/images/towers/tower_fire.png")));
	}

	public void draw(Graphics2D g, int width) {
		super.draw(g, width);
	}
}