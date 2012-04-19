import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.*;


public class FrameTest {

	@Test
	public void testThatJUnitLoads(){
		assertTrue("JUnit did not load properly...", true);
	}
	
	@Test
	public void testThatFrameCanBeInstantiated(){
		Frame f = new Frame(60);
		assertNotNull(f);
	}
	
	@Test
	public void testThatFrameHoldsStableAround60FPS (){
		Frame f = new Frame(60);
		try{Thread.sleep(1000);}
		catch(InterruptedException ie){
			// Sleep Interrupted
		}
		assertTrue(50 < f.getFPS());
		assertTrue(70 > f.getFPS());
		assertTrue(50 < f.getFPS());
		assertTrue(70 > f.getFPS());
		assertTrue(50 < f.getFPS());
		assertTrue(70 > f.getFPS());
		assertTrue(50 < f.getFPS());
		assertTrue(70 > f.getFPS());
		
		f.setVisible(false);
		f = null;
	}
	
	@Test
	public void testThatSleepSkipsIsMinimallized (){
		Frame f = new Frame(10000);
		try{Thread.sleep(1000);}
		catch(InterruptedException ie){
			// Sleep Interrupted
		}
		assertTrue(10 > f.getSleepSkips());
	}
	
	@Test
	public void testThatFramesAreSkipedToDecreaseLag(){
		Frame f = new Frame(10000);
		System.out.println(f.getRequestedFPS());
		try{Thread.sleep(1000);}
		catch(InterruptedException ie){
			// Sleep Interrupted
		}
		assertTrue(0 < f.getFrameSkips());
	}
}
