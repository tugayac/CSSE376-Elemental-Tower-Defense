import java.awt.geom.Point2D;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 19, 2012.
 */
public class EnemyFire extends Enemy {

	public EnemyFire(Point2D.Double location, int health, int armor) {
		super(Enemy.enemy_ids++, "Firebat", 1.2f, armor, health, location,
				Frame.element.FIRE, 90);
	}

	@Override
	public void damage(int i) {
		this.hp -= i - (2 * this.armor) - ((int) this.speed);
		if (this.hp < 0) {
			this.hp = 0;
		}
	}
}
