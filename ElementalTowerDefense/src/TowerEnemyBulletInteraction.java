import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test suit for interaction between Towers, Bullets, and Enemies
 * 
 * @author matthewmercer. Created Apr 20, 2012.
 */
public class TowerEnemyBulletInteraction {

	private Tower tower;
	private Enemy enemy;
	private Map map;

	/**
	 * Creates a tower and enemy, setting their locations
	 * 
	 */
	@Before
	public void setUp() {
		this.tower = new TowerFire(new Point2D.Double(1, 1));
		this.map = new Map();
		this.map.addTower(this.tower);
		this.map.generateEnemyList();
		this.enemy = this.map.getEnemies().get(0); // loc = -1 7

	}

	/**
	 * Set the variables to null
	 * 
	 */
	@After
	public void tearDown() {
		this.tower = null;
		this.map = null;
		this.enemy = null;
	}

	/**
	 * Tests that given a target, the tower can shoot a bullet towards it
	 * 
	 */
	@Test
	public void testThatTowerShootsBulletTowardsEnemy() {
		Bullet b = this.tower.fireBulletTowards(this.enemy,
				new ArrayList<Point2D.Double>());

		assertEquals(b.getVector(),
				normalize(new Point2D.Double(-1 - 1, 7 - 1)));
		assertEquals(b.getLocation(), this.tower.getLocation());
	}

	/**
	 * Test that, given an enemy is hit by the bullet (which it should) the
	 * enemy takes damage
	 * 
	 */
	@Test
	public void testThatEnemyTakesDamage() {
		int health = this.enemy.getHP();
		Bullet b = this.tower.fireBulletTowards(this.enemy,
				new ArrayList<Point2D.Double>());
		this.map.addBullet(b);
		for (int i = 0; i < 100; i++)
			b.move();
		assertTrue(this.enemy.getHP() < health);
	}

	/**
	 * Test that a bullet, once hitting an enemy, is removed from the screen.
	 * 
	 */
	@Test
	public void testThatBulletIsRemovedAfterHitsEnemy() {
		Bullet b = this.tower.fireBulletTowards(this.enemy,
				new ArrayList<Point2D.Double>());
		this.map.addBullet(b);
		for (int i = 0; i < 100; i++)
			b.move();
		assertEquals(new Point2D.Double(0, 0), b.getVector());
		assertEquals(new Point2D.Double(-100, -100), b.getLocation());
	}

	private Point2D.Double normalize(Point2D.Double d) {
		double norm = Math.sqrt(d.x * d.x + d.y * d.y);
		return new Point2D.Double(d.x / norm, d.y / norm);
	}
}
