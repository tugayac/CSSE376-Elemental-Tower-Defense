import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 22, 2012.
 */
public class PlayerTest {

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

	@Test
	public void testCanSetAndGetScore() {
		this.player.setScore(100);
		assertEquals(100, this.player.getScore());

		this.player.setScore(-100);
		assertEquals(0, this.player.getScore());
	}

	@Test
	public void testIncrementAndDecrementScore() {
		this.player.setScore(50);
		this.player.decScore(100);
		assertEquals(0, this.player.getScore());

		this.player.setScore(50);
		this.player.incScore(150);
		assertEquals(200, this.player.getScore());
	}

	@Test
	public void testCanSetAndGetMana() {
		this.player.setMana(1000);
		assertEquals(1000, this.player.getMana());

		this.player.setMana(-1000);
		assertEquals(0, this.player.getMana());
	}

	@Test
	public void testIncrementAndDecrementMana() {
		this.player.setMana(1000);
		this.player.decMana(1100);
		assertEquals(0, this.player.getMana());

		this.player.setMana(1000);
		this.player.incMana(100);
		assertEquals(1100, this.player.getMana());
	}

	@Test
	public void testCanSetAndGetHealth() {
		this.player.setHealth(200);
		assertEquals(200, this.player.getHealth());

		this.player.setHealth(-200);
		assertEquals(0, this.player.getHealth());
	}

	@Test
	public void testIncrementAndDecrementHealth() {
		this.player.setHealth(200);
		this.player.decHealth(300);
		assertEquals(0, this.player.getHealth());

		this.player.setHealth(200);
		this.player.incHealth(100);
		assertEquals(300, this.player.getHealth());
	}
}
