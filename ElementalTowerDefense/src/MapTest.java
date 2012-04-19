import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;


public class MapTest {
	private static Map map;
	
	@Before
	public void setUp(){
		map = new Map();
	}
	
	@After
	public void tearDown(){
		map = null;
	}
	
	@Test
	public void testPathGenerationIsRandom(){
		ArrayList<Point2D.Double> path1 = map.generatePath();
		boolean same = true;
		
		for(int i = 0; i < 100; i++)
			same = same && (path1.equals(map.generatePath()));
		
		assertFalse(same);
	}
	
	@Test
	public void testPathDoesNotGoBackOnItself(){
		ArrayList<Point2D.Double> path;
		ArrayList<Point2D.Double> pastLocations = new ArrayList<Point2D.Double>();
		
		for(int i = 0; i < 100; i++){
			path = map.generatePath();
			pastLocations.clear();
			pastLocations.add(new Point2D.Double(0,0));
			for(Point2D.Double dir : path){
				Point2D.Double prevLoc = pastLocations.get(pastLocations.size()-1);
				Point2D.Double nextLocation = new Point2D.Double(prevLoc.x, prevLoc.y);
				nextLocation.x += dir.x;
				nextLocation.y += dir.y;
				
				if(pastLocations.indexOf(nextLocation) != -1)
					fail();
			}
		}
	}
	
	@Test
	public void testThatPathStaysInBoundaries(){
		ArrayList<Point2D.Double> path;
		Point2D.Double currentLocation;
		
		for(int i = 0; i < 100; i++){
			path = map.generatePath();
			currentLocation = new Point2D.Double(0,0);
			for(Point2D.Double dir : path){
				currentLocation.x += dir.x;
				currentLocation.y += dir.y;
				
				if(currentLocation.x < 0)
					fail();
				if(currentLocation.x > 50)
					fail();
				if(currentLocation.y < -25)
					fail();
				if(currentLocation.y > 50)
					fail();
			}
		}
	}
	
	@Test
	public void testThatMapCanAcceptNewTower(){
		Tower fire = new Tower_Fire(0, new Point2D.Double(1,1));
		
		assertEquals(0, map.getTowers().size());
		map.addTower(fire);
		assertEquals(1, map.getTowers().size());
		assertSame(fire, map.getTowers().get(0));
	}
	
	@Test
	public void testThatMapWontAcceptTwoTowersAtTheSameSpot(){
		Tower fire = new Tower_Fire(0, new Point2D.Double(1,1));
		Tower water = new Tower_Water(0, new Point2D.Double(1,1));
		
		assertEquals(0, map.getTowers().size());
		map.addTower(fire);
		assertEquals(1, map.getTowers().size());
		int returnVal = map.addTower(water);
		assertEquals(1, map.getTowers().size());
		assertEquals(-1, returnVal);
	}
	
	@Test
	public void testThatMapCanGenerateEnemies(){
		assertEquals(0, map.getEnemies().size());
		map.generateEnemy(1, Frame.element.FIRE);
		assertEquals(1, map.getEnemies().size());
	}
	
}
