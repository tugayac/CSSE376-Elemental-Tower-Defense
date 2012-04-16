import org.junit.*;
import static org.junit.Assert.*;


public class TileTest {

	private static Tile move, place;
	
	@Before
	public void setUp(){
		move = new Tile(true);
		place = new Tile(false);
	}
	
	@After
	public void tearDown(){
		move = null;
		place = null;
	}
	
	@Test
	public void testCreatureMovement(){
		assertTrue(move.getMove());
		assertFalse(place.getMove());
	}
	
	@Test
	public void testTowerPlacement(){
		assertTrue(place.getPlace());
		assertFalse(move.getPlace());
	}
}
