import java.awt.geom.Point2D;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 19, 2012.
 */
public class EnemyWater extends Enemy {

	public EnemyWater(Point2D.Double location, int health, int armor) {
		super("Magikarp", 2.0f, armor, health, location, Frame.element.WATER,
				60);
	}

	@Override
	public void damage(int i) {
		
		int damage = i - (2 * this.armor) - ((int) this.speed);
		if(damage < 1) damage = 1;
		this.hp -= damage;
		if (this.hp < 0) {
			this.hp = 0;
		}
	}
}
