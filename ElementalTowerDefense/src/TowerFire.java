import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class TowerFire extends Tower {

	static final int cost = 1000;
	int baseDamage;

	public TowerFire(Point2D.Double loc, int damage) {
		super(Tower.tower_ids++, "Fire Tower", 2500, loc, Frame.element.FIRE,
				true, cost, 1, damage);
		setImage(new ImageIcon(getClass().getResource(
				"/resources/images/towers/tower_fire.png")));
		baseDamage = damage;
	}
	
	public boolean upgrade(Player p)
	{
		boolean generic = super.upgrade(p);
		
		if(generic){
			this.damage = (int) (700 * (1/(1+Math.exp(-(.5 * this.level) + 2)))) - 120 + this.baseDamage;
		}
		
		return generic;
		
	}

	public void draw(Graphics2D g, int width) {
		super.draw(g, width);
	}
}