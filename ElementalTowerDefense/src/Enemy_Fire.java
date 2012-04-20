import java.awt.geom.Point2D;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 19, 2012.
 */
public class Enemy_Fire extends Enemy {

	public Enemy_Fire(int id, Point2D.Double location) {
		super(id, "Firebat", 1.2f, 2, 300, location, Frame.element.FIRE);
	}

	@Override
	public void damage(int i) {
		this.hp -= i - (2 * this.armor) - ((int) this.speed);
		if (this.hp < 0) {
			this.hp = 0;
		}
	}
}
