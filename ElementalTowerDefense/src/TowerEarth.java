import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class TowerEarth extends Tower {

	static final int cost = 800;
	int baseDamage;

	public TowerEarth(Point2D.Double loc, int damage) {
		super(Tower.tower_ids++, "Earth Tower", 2000, loc, Frame.element.EARTH,
				true, cost, 1, damage);
		setImage(new ImageIcon(getClass().getResource(
				"/resources/images/towers/tower_earth.png")));
		baseDamage = damage;
	}
	
	public boolean upgrade(Player p)
	{
		boolean generic = super.upgrade(p);
		
		if(generic){
			this.speed /= .8;
			this.damage = (int) (700 * (1/(1+Math.exp(-(.7 * this.level) + 3)))) - 40 + this.baseDamage;
		}
		
		return generic;
		
	}

	public void draw(Graphics2D g, int width) {
		super.draw(g, width);
	}
}