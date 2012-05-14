import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 27, 2012.
 */
public class ControlPanel extends JFrame {

	private Player player;
	private Map map;
	// private ArrayList<Tower> towers;
	// private ArrayList<Enemy> activeEnemies;
	private Locale currentLocale;
	private ResourceBundle strings;
	private Frame frame;
	private boolean[] isNotClicked = { true, true, true, true, true };

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 * @param f
	 * @param map
	 * @param player
	 * @param cl
	 */
	public ControlPanel(Frame f, Map map, Player player, Locale cl) {
		this.frame = f;
		this.player = player;
		this.map = map;
		// this.towers = map.getTowers();
		// this.activeEnemies = map.getEnemies();

		initLocalization(cl);

		initComponents();

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void initLocalization(Locale cl) {
		this.currentLocale = cl;
		this.strings = ResourceBundle.getBundle(
				"resources.localization.localization", this.currentLocale);
	}

	private void initComponents() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		this.topLeftPanel = new JPanel();
		this.topLeftPanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		this.add(this.topLeftPanel, c);

		this.topCenterPanel = new JPanel();
		this.topCenterPanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		this.add(this.topCenterPanel, c);

		this.topRightPanel = new JPanel();
		this.topRightPanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		this.add(this.topRightPanel, c);

		this.bottomLeftPanel = new JPanel();
		this.bottomLeftPanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		this.add(this.bottomLeftPanel, c);

		this.bottomRightPanel = new JPanel();
		this.bottomRightPanel.setBorder(BorderFactory
				.createLineBorder(Color.BLACK));
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 1;
		this.add(this.bottomRightPanel, c);

		initImageIcons();

		initTopLeftPanel();
		initTopCenterPanel();
		initTopRightPanel();
		initBottomLeftPanel();
		initBottomRightPanel();

		initLabelInfo();

		pack();
	}

	private void initLabelInfo() {
		/*
		 * Player Information
		 */
		this.playerScoreValueLabel.setText(Integer.toString(this.player
				.getScore()));
		this.playerManaValueLabel.setText(Integer.toString(this.player
				.getCurrency()));
		this.playerWaveValueLabel.setText(Integer.toString(this.map
				.getWaveNumber()));
		this.playerHealthValueLabel.setText(Integer.toString(this.player
				.getHealth()));

		Stopwatch s = new Stopwatch(this.playerTimeValueLabel, this.strings);
		s.start();

		/*
		 * Wave Information
		 */
		// Nothing to do

		/*
		 * Selected Enemy Information
		 */
		// Nothing to do

		/*
		 * Available Tower Cost Information
		 */
		this.fireCostValueLabel.setText(Integer.toString(TowerFire.cost));
		this.waterCostValueLabel.setText(Integer.toString(TowerWater.cost));
		this.airCostValueLabel.setText(Integer.toString(TowerAir.cost));
		this.lightCostValueLabel.setText(Integer.toString(TowerLight.cost));
		this.earthCostValueLabel.setText(Integer.toString(TowerEarth.cost));
	}

	private void initImageIcons() {
		/*
		 * Fire button image initialization
		 */
		ImageIcon tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/fire_clicked.png"));
		Image tempImage = tempIcon.getImage();
		this.fireClicked = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/fire_unclicked.png"));
		tempImage = tempIcon.getImage();
		this.fireUnclicked = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/fire_grey1.png"));
		tempImage = tempIcon.getImage();
		this.fireInsuffMana = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/fire_grey.png"));
		tempImage = tempIcon.getImage();
		this.fireNotAvailable = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		/*
		 * Water button image initialization
		 */
		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/water_clicked.png"));
		tempImage = tempIcon.getImage();
		this.waterClicked = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/water_unclicked.png"));
		tempImage = tempIcon.getImage();
		this.waterUnclicked = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/water_grey1.png"));
		tempImage = tempIcon.getImage();
		this.waterInsuffMana = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/water_grey.png"));
		tempImage = tempIcon.getImage();
		this.waterNotAvailable = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		/*
		 * Air button image initialization
		 */
		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/air_clicked.png"));
		tempImage = tempIcon.getImage();
		this.airClicked = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/air_unclicked.png"));
		tempImage = tempIcon.getImage();
		this.airUnclicked = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/air_grey1.png"));
		tempImage = tempIcon.getImage();
		this.airInsuffMana = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/air_grey.png"));
		tempImage = tempIcon.getImage();
		this.airNotAvailable = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		/*
		 * Light button image initialization
		 */
		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/light_clicked.png"));
		tempImage = tempIcon.getImage();
		this.lightClicked = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/light_unclicked.png"));
		tempImage = tempIcon.getImage();
		this.lightUnclicked = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/light_grey1.png"));
		tempImage = tempIcon.getImage();
		this.lightInsuffMana = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/light_grey.png"));
		tempImage = tempIcon.getImage();
		this.lightNotAvailable = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		/*
		 * Earth button image initialization
		 */
		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/earth_clicked.png"));
		tempImage = tempIcon.getImage();
		this.earthClicked = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/earth_unclicked.png"));
		tempImage = tempIcon.getImage();
		this.earthUnclicked = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/earth_grey1.png"));
		tempImage = tempIcon.getImage();
		this.earthInsuffMana = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/buttonimages/earth_grey.png"));
		tempImage = tempIcon.getImage();
		this.earthNotAvailable = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);
	}

	private void initTopLeftPanel() {
		this.topLeftPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Global for all components
		c.insets = new Insets(5, 10, 10, 10);

		this.playerInfoLabel = new JLabel(this.strings.getString("playerInfo"));
		this.playerInfoLabel.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		this.topLeftPanel.add(this.playerInfoLabel, c);

		this.playerScoreLabel = new JLabel(
				this.strings.getString("playerScore"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		this.topLeftPanel.add(this.playerScoreLabel, c);

		this.playerScoreValueLabel = new JLabel("scoreValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		this.topLeftPanel.add(this.playerScoreValueLabel, c);

		this.playerManaLabel = new JLabel(this.strings.getString("playerMana"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 2;
		c.gridy = 1;
		this.topLeftPanel.add(this.playerManaLabel, c);

		this.playerManaValueLabel = new JLabel("manaValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 3;
		c.gridy = 1;
		this.topLeftPanel.add(this.playerManaValueLabel, c);

		this.playerWaveLabel = new JLabel(this.strings.getString("playerWave"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 2;
		this.topLeftPanel.add(this.playerWaveLabel, c);

		this.playerWaveValueLabel = new JLabel("waveValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		this.topLeftPanel.add(this.playerWaveValueLabel, c);

		this.playerHealthLabel = new JLabel(
				this.strings.getString("playerHealth"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 2;
		c.gridy = 2;
		this.topLeftPanel.add(this.playerHealthLabel, c);

		this.playerHealthValueLabel = new JLabel("healthValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 3;
		c.gridy = 2;
		this.topLeftPanel.add(this.playerHealthValueLabel, c);

		this.playerTimeLabel = new JLabel(this.strings.getString("playerTime"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 3;
		this.topLeftPanel.add(this.playerTimeLabel, c);

		this.playerTimeValueLabel = new JLabel("timeValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 3;
		this.topLeftPanel.add(this.playerTimeValueLabel, c);
	}

	private void initTopCenterPanel() {
		this.topCenterPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Global for all components
		c.insets = new Insets(5, 10, 10, 10);

		this.waveInfoLabel = new JLabel(this.strings.getString("waveInfo"));
		this.waveInfoLabel.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		this.topCenterPanel.add(this.waveInfoLabel, c);

		this.waveLevelLabel = new JLabel(this.strings.getString("waveLevel"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		this.topCenterPanel.add(this.waveLevelLabel, c);

		this.waveLevelValueLabel = new JLabel("levelValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		this.topCenterPanel.add(this.waveLevelValueLabel, c);

		this.waveHealthLabel = new JLabel(this.strings.getString("waveHealth"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 2;
		c.gridy = 1;
		this.topCenterPanel.add(this.waveHealthLabel, c);

		this.waveHealthValueLabel = new JLabel("healthValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 3;
		c.gridy = 1;
		this.topCenterPanel.add(this.waveHealthValueLabel, c);

		this.waveNameLabel = new JLabel(this.strings.getString("waveName"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 2;
		this.topCenterPanel.add(this.waveNameLabel, c);

		this.waveNameValueLabel = new JLabel("nameValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		this.topCenterPanel.add(this.waveNameValueLabel, c);

		this.waveArmorLabel = new JLabel(this.strings.getString("waveArmor"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 2;
		c.gridy = 2;
		this.topCenterPanel.add(this.waveArmorLabel, c);

		this.waveArmorValueLabel = new JLabel("armorValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 3;
		c.gridy = 2;
		this.topCenterPanel.add(this.waveArmorValueLabel, c);

		this.waveSpeedLabel = new JLabel(this.strings.getString("waveSpeed"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 3;
		this.topCenterPanel.add(this.waveSpeedLabel, c);

		this.waveSpeedValueLabel = new JLabel("speedValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 3;
		this.topCenterPanel.add(this.waveSpeedValueLabel, c);
	}

	private void initTopRightPanel() {
		this.topRightPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Global for all components
		c.insets = new Insets(5, 10, 10, 10);

		this.selectedEnemyLabel = new JLabel(
				this.strings.getString("selEnemyInfo"));
		this.selectedEnemyLabel.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.topRightPanel.add(this.selectedEnemyLabel, c);

		this.selectedHealthLabel = new JLabel(
				this.strings.getString("selEnemyHealth"));
		c.fill = GridBagConstraints.RELATIVE;
		// Set once
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		this.topRightPanel.add(this.selectedHealthLabel, c);

		this.selectedHealthValueLabel = new JLabel("healthValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		this.topRightPanel.add(this.selectedHealthValueLabel, c);

		this.selectedArmorLabel = new JLabel(
				this.strings.getString("selEnemyArmor"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 2;
		this.topRightPanel.add(this.selectedArmorLabel, c);

		this.selectedArmorValueLabel = new JLabel("armorValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		this.topRightPanel.add(this.selectedArmorValueLabel, c);

		this.selectedSpeedLabel = new JLabel(
				this.strings.getString("selEnemySpeed"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 3;
		this.topRightPanel.add(this.selectedSpeedLabel, c);

		this.selectedSpeedValueLabel = new JLabel("speedValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 3;
		this.topRightPanel.add(this.selectedSpeedValueLabel, c);
	}

	private void initBottomLeftPanel() {
		this.bottomLeftPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Global for all components
		c.insets = new Insets(0, 5, 5, 5);

		this.availableTowersLabel = new JLabel(
				this.strings.getString("availTowers"));
		this.availableTowersLabel.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		c.fill = GridBagConstraints.RELATIVE;
		// For heading
		c.gridwidth = 10;
		c.gridx = 0;
		c.gridy = 0;
		this.bottomLeftPanel.add(this.availableTowersLabel, c);

		c.insets = new Insets(0, 0, 0, 0);
		this.fireButton = new JButton(new ImageIcon(this.fireUnclicked));
		this.fireButton.setBorderPainted(false);
		this.fireButton.setContentAreaFilled(false);
		this.fireButton.setFocusable(false);
		c.fill = GridBagConstraints.RELATIVE;
		// For buttons
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		this.bottomLeftPanel.add(this.fireButton, c);
		this.fireButton.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				if (ControlPanel.this.player.getCurrency() >= TowerFire.cost) {
					ControlPanel.this.fireButton.setIcon(new ImageIcon(
							ControlPanel.this.fireUnclicked));
					ControlPanel.this.frame.setElement(Frame.element.FIRE);
				} else {
					ControlPanel.this.fireButton.setIcon(new ImageIcon(
							ControlPanel.this.fireInsuffMana));
				}
				ControlPanel.this.isNotClicked[0] = true;
			}

			public void mousePressed(MouseEvent e) {
				if (ControlPanel.this.player.getCurrency() >= TowerFire.cost) {
					ControlPanel.this.fireButton.setIcon(new ImageIcon(
							ControlPanel.this.fireClicked));
				} else {
					ControlPanel.this.fireButton.setIcon(new ImageIcon(
							ControlPanel.this.fireInsuffMana));
				}
				ControlPanel.this.isNotClicked[0] = false;
			}

			public void mouseExited(MouseEvent e) {
				// Nothing to do
			}

			public void mouseEntered(MouseEvent e) {
				// Nothing to do
			}

			public void mouseClicked(MouseEvent e) {
				// Nothing to do
			}
		});

		this.waterButton = new JButton(new ImageIcon(this.waterUnclicked));
		this.waterButton.setBorderPainted(false);
		this.waterButton.setContentAreaFilled(false);
		this.waterButton.setFocusable(false);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridx = 2;
		c.gridy = 1;
		this.bottomLeftPanel.add(this.waterButton, c);
		this.waterButton.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				if (ControlPanel.this.player.getCurrency() >= TowerWater.cost) {
					ControlPanel.this.waterButton.setIcon(new ImageIcon(
							ControlPanel.this.waterUnclicked));
					ControlPanel.this.frame.setElement(Frame.element.WATER);
				} else {
					ControlPanel.this.waterButton.setIcon(new ImageIcon(
							ControlPanel.this.waterInsuffMana));
				}
				ControlPanel.this.isNotClicked[1] = true;
			}

			public void mousePressed(MouseEvent e) {
				if (ControlPanel.this.player.getCurrency() >= TowerWater.cost) {
					ControlPanel.this.waterButton.setIcon(new ImageIcon(
							ControlPanel.this.waterClicked));
				} else {
					ControlPanel.this.waterButton.setIcon(new ImageIcon(
							ControlPanel.this.waterInsuffMana));
				}
				ControlPanel.this.isNotClicked[1] = false;
			}

			public void mouseExited(MouseEvent e) {
				// Nothing to do
			}

			public void mouseEntered(MouseEvent e) {
				// Nothing to do
			}

			public void mouseClicked(MouseEvent e) {
				// Nothing to do
			}
		});

		this.airButton = new JButton(new ImageIcon(this.airUnclicked));
		this.airButton.setBorderPainted(false);
		this.airButton.setContentAreaFilled(false);
		this.airButton.setFocusable(false);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridx = 4;
		c.gridy = 1;
		this.bottomLeftPanel.add(this.airButton, c);
		this.airButton.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				if (ControlPanel.this.player.getCurrency() >= TowerAir.cost) {
					ControlPanel.this.airButton.setIcon(new ImageIcon(
							ControlPanel.this.airUnclicked));
					ControlPanel.this.frame.setElement(Frame.element.AIR);
				} else {
					ControlPanel.this.airButton.setIcon(new ImageIcon(
							ControlPanel.this.airInsuffMana));
				}
				ControlPanel.this.isNotClicked[2] = true;
			}

			public void mousePressed(MouseEvent e) {
				if (ControlPanel.this.player.getCurrency() >= TowerAir.cost) {
					ControlPanel.this.airButton.setIcon(new ImageIcon(
							ControlPanel.this.airClicked));
				} else {
					ControlPanel.this.airButton.setIcon(new ImageIcon(
							ControlPanel.this.airInsuffMana));
				}
				ControlPanel.this.isNotClicked[2] = false;
			}

			public void mouseExited(MouseEvent e) {
				// Nothing to do
			}

			public void mouseEntered(MouseEvent e) {
				// Nothing to do
			}

			public void mouseClicked(MouseEvent e) {
				// Nothing to do
			}
		});

		this.lightButton = new JButton(new ImageIcon(this.lightUnclicked));
		this.lightButton.setBorderPainted(false);
		this.lightButton.setContentAreaFilled(false);
		this.lightButton.setFocusable(false);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridx = 6;
		c.gridy = 1;
		this.bottomLeftPanel.add(this.lightButton, c);
		this.lightButton.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				if (ControlPanel.this.player.getCurrency() >= TowerLight.cost) {
					ControlPanel.this.lightButton.setIcon(new ImageIcon(
							ControlPanel.this.lightUnclicked));
					ControlPanel.this.frame.setElement(Frame.element.LIGHT);
				} else {
					ControlPanel.this.lightButton.setIcon(new ImageIcon(
							ControlPanel.this.lightInsuffMana));
				}
				ControlPanel.this.isNotClicked[3] = true;
			}

			public void mousePressed(MouseEvent e) {
				if (ControlPanel.this.player.getCurrency() >= TowerLight.cost) {
					ControlPanel.this.lightButton.setIcon(new ImageIcon(
							ControlPanel.this.lightClicked));
				} else {
					ControlPanel.this.lightButton.setIcon(new ImageIcon(
							ControlPanel.this.lightInsuffMana));
				}
				ControlPanel.this.isNotClicked[3] = false;
			}

			public void mouseExited(MouseEvent e) {
				// Nothing to do
			}

			public void mouseEntered(MouseEvent e) {
				// Nothing to do
			}

			public void mouseClicked(MouseEvent e) {
				// Nothing to do
			}
		});

		this.earthButton = new JButton(new ImageIcon(this.earthUnclicked));
		this.earthButton.setBorderPainted(false);
		this.earthButton.setContentAreaFilled(false);
		this.earthButton.setFocusable(false);
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridx = 8;
		c.gridy = 1;
		this.bottomLeftPanel.add(this.earthButton, c);
		this.earthButton.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				if (ControlPanel.this.player.getCurrency() >= TowerEarth.cost) {
					ControlPanel.this.earthButton.setIcon(new ImageIcon(
							ControlPanel.this.earthUnclicked));
					ControlPanel.this.frame.setElement(Frame.element.EARTH);
				} else {
					ControlPanel.this.earthButton.setIcon(new ImageIcon(
							ControlPanel.this.earthInsuffMana));
				}
				ControlPanel.this.isNotClicked[4] = true;
			}

			public void mousePressed(MouseEvent e) {
				if (ControlPanel.this.player.getCurrency() >= TowerEarth.cost) {
					ControlPanel.this.earthButton.setIcon(new ImageIcon(
							ControlPanel.this.earthClicked));
				} else {
					ControlPanel.this.earthButton.setIcon(new ImageIcon(
							ControlPanel.this.earthInsuffMana));
				}
				ControlPanel.this.isNotClicked[4] = false;
			}

			public void mouseExited(MouseEvent e) {
				// Nothing to do
			}

			public void mouseEntered(MouseEvent e) {
				// Nothing to do
			}

			public void mouseClicked(MouseEvent e) {
				// Nothing to do
			}
		});

		c.insets = new Insets(0, 5, 0, 5);

		this.fireCostLabel = new JLabel(
				this.strings.getString("availTowersCost"));
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_END;
		// For labels
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		this.bottomLeftPanel.add(this.fireCostLabel, c);

		this.fireCostValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 1;
		c.gridy = 2;
		this.bottomLeftPanel.add(this.fireCostValueLabel, c);

		this.waterCostLabel = new JLabel(
				this.strings.getString("availTowersCost"));
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		this.bottomLeftPanel.add(this.waterCostLabel, c);

		this.waterCostValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 3;
		c.gridy = 2;
		this.bottomLeftPanel.add(this.waterCostValueLabel, c);

		this.airCostLabel = new JLabel(
				this.strings.getString("availTowersCost"));
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = 2;
		this.bottomLeftPanel.add(this.airCostLabel, c);

		this.airCostValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 5;
		c.gridy = 2;
		this.bottomLeftPanel.add(this.airCostValueLabel, c);

		this.lightCostLabel = new JLabel(
				this.strings.getString("availTowersCost"));
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 6;
		c.gridy = 2;
		this.bottomLeftPanel.add(this.lightCostLabel, c);

		this.lightCostValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 7;
		c.gridy = 2;
		this.bottomLeftPanel.add(this.lightCostValueLabel, c);

		this.earthCostLabel = new JLabel(
				this.strings.getString("availTowersCost"));
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 8;
		c.gridy = 2;
		this.bottomLeftPanel.add(this.earthCostLabel, c);

		this.earthCostValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 9;
		c.gridy = 2;
		this.bottomLeftPanel.add(this.earthCostValueLabel, c);
	}

	private void initBottomRightPanel() {
		this.bottomRightPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Global for all components
		c.insets = new Insets(5, 10, 10, 10);

		this.towerInfoLabel = new JLabel(this.strings.getString("towerInfo"));
		this.towerInfoLabel.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.bottomRightPanel.add(this.towerInfoLabel, c);

		this.towerLevelLabel = new JLabel(this.strings.getString("towerLevel"));
		c.fill = GridBagConstraints.RELATIVE;
		// Set once
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		this.bottomRightPanel.add(this.towerLevelLabel, c);

		this.towerLevelValueLabel = new JLabel("levelValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		this.bottomRightPanel.add(this.towerLevelValueLabel, c);

		this.towerDamageLabel = new JLabel(
				this.strings.getString("towerDamage"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 2;
		this.bottomRightPanel.add(this.towerDamageLabel, c);

		this.towerDamageValueLabel = new JLabel("damageValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		this.bottomRightPanel.add(this.towerDamageValueLabel, c);

		this.towerSpeedLabel = new JLabel(this.strings.getString("towerSpeed"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 3;
		this.bottomRightPanel.add(this.towerSpeedLabel, c);

		this.towerSpeedValueLabel = new JLabel("speedValue");
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 3;
		this.bottomRightPanel.add(this.towerSpeedValueLabel, c);

		this.towerUpgradeButton = new JButton(
				this.strings.getString("towerUpgrade"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 4;
		this.bottomRightPanel.add(this.towerUpgradeButton, c);

		this.towerSellButton = new JButton(this.strings.getString("towerSell"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 5;
		this.bottomRightPanel.add(this.towerSellButton, c);
	}

	/**
	 * Calls all the update methods in the ControlPanel class.
	 * 
	 */
	public void update() {
		updatePlayerInfo();
		updateButtons();
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	public void updatePlayerInfo() {
		this.playerScoreValueLabel.setText(Integer.toString(this.player
				.getScore()));
		this.playerManaValueLabel.setText(Integer.toString(this.player
				.getCurrency()));
		this.playerWaveValueLabel.setText(Integer.toString(this.map
				.getWaveNumber()));
		this.playerHealthValueLabel.setText(Integer.toString(this.player
				.getHealth()));
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	public void updateButtons() {
		int currency = this.player.getCurrency();

		updateFireButtons(currency);
		updateWaterButtons(currency);
		updateAirButtons(currency);
		updateLightButtons(currency);
		updateEarthButtons(currency);
	}

	private void updateFireButtons(int currency) {
		// Check fire tower against player currency
		if (this.isNotClicked[0] && currency >= TowerFire.cost) {
			this.fireButton.setIcon(new ImageIcon(this.fireUnclicked));
		} else if (currency < TowerFire.cost) {
			this.fireButton.setIcon(new ImageIcon(this.fireInsuffMana));
		}
	}

	private void updateWaterButtons(int currency) {
		// Check water tower against player currency
		if (this.isNotClicked[1] && currency >= TowerWater.cost) {
			this.waterButton.setIcon(new ImageIcon(this.waterUnclicked));
		} else if (currency < TowerWater.cost) {
			this.waterButton.setIcon(new ImageIcon(this.waterInsuffMana));
		}
	}

	private void updateAirButtons(int currency) {
		// Check air tower against player currency
		if (this.isNotClicked[2] && currency >= TowerAir.cost) {
			this.airButton.setIcon(new ImageIcon(this.airUnclicked));
		} else if (currency < TowerAir.cost) {
			this.airButton.setIcon(new ImageIcon(this.airInsuffMana));
		}
	}

	private void updateLightButtons(int currency) {
		// Check light tower against player currency
		if (this.isNotClicked[3] && currency >= TowerLight.cost) {
			this.lightButton.setIcon(new ImageIcon(this.lightUnclicked));
		} else if (currency < TowerLight.cost) {
			this.lightButton.setIcon(new ImageIcon(this.lightInsuffMana));
		}
	}

	private void updateEarthButtons(int currency) {
		// Check earth tower against player currency
		if (this.isNotClicked[4] && currency >= TowerEarth.cost) {
			this.earthButton.setIcon(new ImageIcon(this.earthUnclicked));
		} else if (currency < TowerEarth.cost) {
			this.earthButton.setIcon(new ImageIcon(this.earthInsuffMana));
		}
	}

	/**
	 * Method used by the tests to check if resource bundle is initialized
	 * correctly.
	 * 
	 * @return the current resource bundle
	 */
	public ResourceBundle getResourceBundle() {
		return this.strings;
	}

	/**
	 * Method used by the tests to set the language and country so that all
	 * resource bundles can be tested.
	 * 
	 * @param lang
	 *            - language to be tested.
	 * @param country
	 *            - country the language is used in.
	 */
	public void setResourceBundle(String lang, String country) {
		this.currentLocale = new Locale(lang, country);
		this.strings = ResourceBundle.getBundle(
				"resources.localization.localization", this.currentLocale);
	}

	// Button, Label, and Panel declerations
	private JButton towerUpgradeButton;
	private JButton towerSellButton;
	private JLabel playerInfoLabel;
	private JLabel playerHealthValueLabel;
	private JLabel playerTimeValueLabel;
	private JLabel selectedEnemyLabel;
	private JLabel waveLevelLabel;
	private JLabel waveNameLabel;
	private JLabel waveHealthLabel;
	private JLabel waveArmorLabel;
	private JLabel waveSpeedLabel;
	private JLabel waveLevelValueLabel;
	private JLabel waveNameValueLabel;
	private JLabel playerScoreLabel;
	private JLabel waveHealthValueLabel;
	private JLabel waveArmorValueLabel;
	private JLabel waveSpeedValueLabel;
	private JLabel selectedHealthLabel;
	private JLabel selectedArmorLabel;
	private JLabel selectedSpeedLabel;
	private JLabel selectedHealthValueLabel;
	private JLabel selectedArmorValueLabel;
	private JLabel selectedSpeedValueLabel;
	private JLabel availableTowersLabel;
	private JLabel playerManaLabel;
	private JLabel fireCostLabel;
	private JLabel fireCostValueLabel;
	private JLabel waterCostLabel;
	private JLabel waterCostValueLabel;
	private JLabel waveInfoLabel;
	private JLabel airCostLabel;
	private JLabel airCostValueLabel;
	private JLabel lightCostLabel;
	private JLabel lightCostValueLabel;
	private JLabel earthCostLabel;
	private JLabel playerWaveLabel;
	private JLabel earthCostValueLabel;
	private JLabel towerInfoLabel;
	private JLabel towerLevelLabel;
	private JLabel towerDamageLabel;
	private JLabel towerSpeedLabel;
	private JLabel towerLevelValueLabel;
	private JLabel towerDamageValueLabel;
	private JLabel towerSpeedValueLabel;
	private JButton fireButton;
	private JButton waterButton;
	private JButton airButton;
	private JButton lightButton;
	private JButton earthButton;
	private JLabel playerHealthLabel;
	private JLabel playerTimeLabel;
	private JLabel playerScoreValueLabel;
	private JLabel playerManaValueLabel;
	private JLabel playerWaveValueLabel;
	private JPanel topLeftPanel;
	private JPanel topCenterPanel;
	private JPanel topRightPanel;
	private JPanel bottomLeftPanel;
	private JPanel bottomRightPanel;
	// End of declaration

	/*
	 * ImageIcon declerations
	 */
	// Fire
	private Image fireClicked;
	private Image fireUnclicked;
	private Image fireNotAvailable;
	private Image fireInsuffMana;
	// Water
	private Image waterClicked;
	private Image waterUnclicked;
	private Image waterNotAvailable;
	private Image waterInsuffMana;
	// Air
	private Image airClicked;
	private Image airUnclicked;
	private Image airNotAvailable;
	private Image airInsuffMana;
	// Light
	private Image lightClicked;
	private Image lightUnclicked;
	private Image lightNotAvailable;
	private Image lightInsuffMana;
	// Earth
	private Image earthClicked;
	private Image earthUnclicked;
	private Image earthNotAvailable;
	private Image earthInsuffMana;

	// End of declerations

	/*
	 * public static void main(String[] args) {
	 * javax.swing.SwingUtilities.invokeLater(new Runnable() { public void run()
	 * { new ControlPanel(new Map(), new Player(), new Locale("en", "US")); }
	 * }); }
	 */
}