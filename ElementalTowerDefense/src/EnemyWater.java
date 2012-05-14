import java.awt.geom.Point2D;
import java.util.Random;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 19, 2012.
 */
public class EnemyWater extends Enemy {

	public EnemyWater(Point2D.Double location) {
		super(Enemy.enemy_ids++, "Magikarp", 2.0f, 1, 100, location, Frame.element.WATER);
	}

	@Override
	public void damage(int i) {
		this.hp -= i - (2 * this.armor) - ((int) this.speed);
		if (this.hp < 0) {
			this.hp = 0;
		}
	}
}
