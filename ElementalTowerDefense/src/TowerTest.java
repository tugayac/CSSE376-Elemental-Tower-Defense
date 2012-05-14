import java.awt.geom.Point2D;

import org.junit.*;

import static org.junit.Assert.*;


public class TowerTest {

	private static Tower FireTower, WaterTower, LightTower, EarthTower, AirTower;
	
	@Before
	public void setUp(){
		FireTower = new TowerFire(new Point2D.Double(1, 1));
		WaterTower = new TowerWater(new Point2D.Double(2, 2));
		LightTower = new TowerLight(new Point2D.Double(3, 3));
		EarthTower = new TowerEarth(new Point2D.Double(4, 4));
		AirTower = new TowerAir(new Point2D.Double(5, 5));
	}
	
	@After
	public void tearDown(){
		FireTower = null;
		WaterTower = null;
		LightTower = null;
		EarthTower = null;
		AirTower = null;
	}
	
	@Test
	public void testCanGetElement(){
		assertEquals(Frame.element.FIRE, FireTower.getElement());
		assertEquals(Frame.element.WATER, WaterTower.getElement());
		assertEquals(Frame.element.LIGHT, LightTower.getElement());
		assertEquals(Frame.element.EARTH, EarthTower.getElement());
		assertEquals(Frame.element.AIR, AirTower.getElement());
	}
	
	@Test
	public void testCorrectSpawnPlacement(){
		assertEquals(new Point2D.Double(1,1), FireTower.getLocation());
		assertEquals(new Point2D.Double(2,2), WaterTower.getLocation());
		assertEquals(new Point2D.Double(3,3), LightTower.getLocation());
		assertEquals(new Point2D.Double(4,4), EarthTower.getLocation());
		assertEquals(new Point2D.Double(5,5), AirTower.getLocation());
	}
	
	@Test
	public void testIDIsIncremented(){
		int id = FireTower.getID();
		assertEquals(id, FireTower.getID());
		assertEquals(id+1, WaterTower.getID());
		assertEquals(id+2, LightTower.getID());
		assertEquals(id+3, EarthTower.getID());
		assertEquals(id+4, AirTower.getID());
	}
	
	@Test
	public void testNameisCorrect(){
		assertEquals("FIRE TOWER", FireTower.getName());
		assertEquals("WATER TOWER", WaterTower.getName());
		assertEquals("LIGHT TOWER", LightTower.getName());
		assertEquals("EARTH TOWER", EarthTower.getName());
		assertEquals("AIR TOWER", AirTower.getName());
	}
	
	@Test
	public void testTowersAreAvailable(){
		assertTrue(FireTower.isAvailable());
		assertTrue(WaterTower.isAvailable());
		assertTrue(LightTower.isAvailable());
		assertTrue(EarthTower.isAvailable());
		assertTrue(AirTower.isAvailable());
	}
	
	@Test
	public void testSpeedIsCorrect(){
		float delta = 0.01f;
		assertEquals(1.2f, FireTower.getSpeed(), delta);
		assertEquals(1.2f, WaterTower.getSpeed(), delta);
		assertEquals(1.2f, LightTower.getSpeed(), delta);
		assertEquals(1.2f, EarthTower.getSpeed(), delta);
		assertEquals(1.2f, AirTower.getSpeed(), delta);
	}
	
	@Test
	public void testCostIsCorrect(){
		assertEquals(1000, FireTower.getCost());
		assertEquals(1000, WaterTower.getCost());
		assertEquals(1000, LightTower.getCost());
		assertEquals(1000, EarthTower.getCost());
		assertEquals(1000, AirTower.getCost());
	}
	
	@Test
	public void testStartingLevelIsCorrect(){
		assertEquals(1, FireTower.getLevel());
		assertEquals(1, WaterTower.getLevel());
		assertEquals(1, LightTower.getLevel());
		assertEquals(1, EarthTower.getLevel());
		assertEquals(1, AirTower.getLevel());
	}
	
	@Test
	public void testUpgradeWorks(){
		assertEquals(1, FireTower.getLevel());
		assertEquals(1, WaterTower.getLevel());
		assertEquals(1, LightTower.getLevel());
		assertEquals(1, EarthTower.getLevel());
		assertEquals(1, AirTower.getLevel());
		
		FireTower.upgrade(null);
		WaterTower.upgrade(null);
		LightTower.upgrade(null);
		EarthTower.upgrade(null);
		AirTower.upgrade(null);
		
		assertEquals(2, FireTower.getLevel());
		assertEquals(2, WaterTower.getLevel());
		assertEquals(2, LightTower.getLevel());
		assertEquals(2, EarthTower.getLevel());
		assertEquals(2, AirTower.getLevel());
	}
	
}
