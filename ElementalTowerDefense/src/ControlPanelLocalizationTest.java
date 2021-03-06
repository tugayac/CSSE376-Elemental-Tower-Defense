import static org.junit.Assert.assertEquals;

import java.util.Locale;
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
		String[] temp = { "en", "US" };
		this.cp = new ControlPanel(new Frame(60, temp), new Map(),
				new Player(0, 0), new Locale("en", "US"));
	}

	@After
	public void tearDown() throws Exception {
		this.cp = null;
	}

	@Test
	public void testLocalityEN() {
		this.cp.setResourceBundle("en", "US");
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

	@Test
	public void testLocalityTR() {
		this.cp.setResourceBundle("tr", "TR");
		ResourceBundle strings = this.cp.getResourceBundle();

		assertEquals("Oyuncu Bilgileri", strings.getString("playerInfo"));
		assertEquals("Puan:", strings.getString("playerScore"));
		assertEquals("Mana:", strings.getString("playerMana"));
		assertEquals("Dalga:", strings.getString("playerWave"));
		assertEquals("Can:", strings.getString("playerHealth"));
		assertEquals("Zaman:", strings.getString("playerTime"));

		assertEquals("D�\u015Fman Dalga Bilgileri",
				strings.getString("waveInfo"));
		assertEquals("Seviye:", strings.getString("waveLevel"));
		assertEquals("Can:", strings.getString("waveHealth"));
		assertEquals("\u0130sim:", strings.getString("waveName"));
		assertEquals("Z\u0131rh:", strings.getString("waveArmor"));
		assertEquals("H\u0131z:", strings.getString("waveSpeed"));

		assertEquals("Se�ili D�\u015Fman", strings.getString("selEnemyInfo"));
		assertEquals("Can:", strings.getString("selEnemyHealth"));
		assertEquals("Z\u0131rh:", strings.getString("selEnemyArmor"));
		assertEquals("H\u0131z:", strings.getString("selEnemySpeed"));

		assertEquals("Kule Bilgileri", strings.getString("towerInfo"));
		assertEquals("Seviye:", strings.getString("towerLevel"));
		assertEquals("Hasar Miktar\u0131:", strings.getString("towerDamage"));
		assertEquals("H\u0131z:", strings.getString("towerSpeed"));
		assertEquals("Geli\u015Ftir", strings.getString("towerUpgrade"));
		assertEquals("Sat", strings.getString("towerSell"));

		assertEquals("Se�ilebilir Kuleler", strings.getString("availTowers"));
		assertEquals("Fiyat:", strings.getString("availTowersCost"));
		assertEquals("Mana", strings.getString("availTowersLabel"));
	}
}
