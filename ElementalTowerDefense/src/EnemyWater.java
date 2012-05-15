import java.awt.geom.Point2D;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 19, 2012.
 */
public class EnemyWater extends Enemy {

	public EnemyWater(Point2D.Double location, int health, int armor) {
		super(Enemy.enemy_ids++, "Magikarp", 2.0f, armor, health, location,
				Frame.element.WATER, 60);
	}

	@Override
	public void damage(int i) {
		this.hp -= i - (2 * this.armor) - ((int) this.speed);
		if (this.hp < 0) {
			this.hp = 0;
		}
	}
}
