import java.awt.geom.Point2D;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 19, 2012.
 */
public class EnemyAir extends Enemy {

	public EnemyAir(Point2D.Double location, int health, int armor) {
		super(Enemy.enemy_ids++, "Tornadus", 2.3f, 3, 500, location,
				Frame.element.AIR, 100);
	}

	@Override
	public void damage(int i) {
		this.hp -= i - (2 * this.armor) - ((int) this.speed);
		if (this.hp < 0) {
			this.hp = 0;
		}
	}
}
