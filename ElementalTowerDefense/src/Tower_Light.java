import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class Tower_Light extends Tower {

	static final int cost = 1000;

	public Tower_Light(Point2D.Double loc) {
		super(Tower.tower_ids++, "LIGHT TOWER", 1.2f, loc, Frame.element.LIGHT,
				true, cost, 1);
		setImage(new ImageIcon(getClass().getResource(
				"/resources/images/towers/tower_light.png")));
	}

	public void draw(Graphics2D g, int width) {
		// g.setColor(Color.WHITE);
		super.draw(g, width);
	}
}
