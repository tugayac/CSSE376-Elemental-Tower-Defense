import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class TowerEarth extends Tower {

	static final int cost = 1000;

	public TowerEarth(Point2D.Double loc) {
		super(Tower.tower_ids++, "EARTH TOWER", 1.2f, loc, Frame.element.EARTH,
				true, cost, 1);
		setImage(new ImageIcon(getClass().getResource(
				"/resources/images/towers/tower_earth.png")));
	}

	public void draw(Graphics2D g, int width) {
		// g.setColor(Color.GREEN);
		super.draw(g, width);
	}
}