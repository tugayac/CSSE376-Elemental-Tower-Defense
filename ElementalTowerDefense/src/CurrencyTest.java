import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests Currency interactions between player, towers, and enemies.
 * 
 */
public class CurrencyTest {

	private Player player;
	
	@Before
	public void setUp() {
		this.player = new Player();
	}

	@After
	public void tearDown() {
		this.player = null;
	}

	@Test
	public void testIsInstantiated() {
		assertNotNull(this.player);
	}

	public void testIncrementAndDecrementCurrency(){
		this.player.setCurrency(1000);
		if (this.player.decCurrency(200)){
			assertEquals(800, this.player.getCurrency());
		}
		if (this.player.decCurrency(800)){
			assertEquals(0, this.player.getCurrency());
		}

		// If a user tries to decrement Currency past what a player has, decCurrency
		// should return false.
		assertFalse(this.player.decCurrency(100));
		
		this.player.incCurrency(1000);
		assertEquals(1000, this.player.getCurrency());
		
		assertTrue(this.player.decCurrency(100));
		
	}
	
	public void testCurrencyIncrementsWhenEnemyDies() {
		Enemy enemy_fire;
		enemy_fire = new Enemy_Fire(new Point2D.Double(0, 0));

		
		int oldCurrency = 1000;
		this.player.setCurrency(oldCurrency);
		
		enemy_fire.kill(player); // Kills the enemy. Only one element type needs to be tested b/c kill is a static method. 
		
		assertEquals(this.player.getCurrency(), (oldCurrency + enemy_fire.getWorth()));
		
		enemy_fire = null;
	}
	
	public void testCurrencyDecrementWhenPlayerUpgradesATower() {
		Tower tower_fire;
		tower_fire = new Tower_Fire(new Point2D.Double(0, 0));

		
		int oldCurrency = 1000;
		this.player.setCurrency(oldCurrency);
		
		assertTrue(tower_fire.upgrade(player)); // Return true if the tower can be upgraded
		assertEquals(this.player.getCurrency(), (oldCurrency - tower_fire.cost));
		
		oldCurrency = tower_fire.cost - 1;
		assertFalse(tower_fire.upgrade(player)); // Return false if the tower can't be upgraded
		assertEquals(this.player.getCurrency(), oldCurrency); // Make sure the player's currency didn't change.
		
		tower_fire = null;
	}
	
	
	
}
