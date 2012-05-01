import static org.junit.Assert.*;

import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created May 1, 2012.
 */
public class ControlPanelLocalizationTest {

	private ControlPanel cp;

	@Before
	public void setUp() throws Exception {
		this.cp = new ControlPanel(new Map(), new Player());
		this.cp.setResourceBundle("en", "US");
	}

	@After
	public void tearDown() throws Exception {
		this.cp = null;
	}

	@Test
	public void testLocalityEN() {
		ResourceBundle strings = this.cp.getResourceBundle();

		assertEquals("Player Information", strings.getString("playerInfo"));
		assertEquals("Score:", strings.getString("playerScore"));
		assertEquals("Mana:", strings.getString("playerMana"));
		assertEquals("Wave:", strings.getString("playerWave"));
		assertEquals("Health:", strings.getString("playerHealth"));
		assertEquals("Time:", strings.getString("playerTime"));
		
		assertEquals("Wave Information", strings.getString("waveInfo"));
		assertEquals("Level:", strings.getString("waveLevel"));
		assertEquals("Health:", strings.getString("waveHealth"));
		assertEquals("Name:", strings.getString("waveName"));
		assertEquals("Armor:", strings.getString("waveArmor"));
		assertEquals("Speed:", strings.getString("waveSpeed"));
		
		assertEquals("Selected Enemy", strings.getString("selEnemyInfo"));
		assertEquals("Health:", strings.getString("selEnemyHealth"));
		assertEquals("Armor:", strings.getString("selEnemyArmor"));
		assertEquals("Speed:", strings.getString("selEnemySpeed"));
		
		assertEquals("Tower Information", strings.getString("towerInfo"));
		assertEquals("Level:", strings.getString("towerLevel"));
		assertEquals("Damage:", strings.getString("towerDamage"));
		assertEquals("Speed:", strings.getString("towerSpeed"));
		assertEquals("Upgrade", strings.getString("towerUpgrade"));
		assertEquals("Sell", strings.getString("towerSell"));
		
		assertEquals("Available Towers", strings.getString("availTowers"));
		assertEquals("Cost:", strings.getString("availTowersCost"));
		assertEquals("Mana", strings.getString("availTowersLabel"));
	}
}
