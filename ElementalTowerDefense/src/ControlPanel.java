import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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

		initLocalization(cl);

		initComponents();

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
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
		this.topLeftPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(this.topLeftPanel, c);

		this.topCenterPanel = new JPanel();
		this.topCenterPanel.setBorder(BorderFactory
				.createLineBorder(Color.BLUE));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 3;
		c.gridx = 1;
		c.gridy = 0;
		this.add(this.topCenterPanel, c);

		this.topRightPanel = new JPanel();
		this.topRightPanel.setBorder(BorderFactory
				.createLineBorder(Color.ORANGE));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 3;
		c.gridx = 2;
		c.gridy = 0;
		this.add(this.topRightPanel, c);

		this.bottomPanel = new JPanel();
		this.bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		this.add(this.bottomPanel, c);

		initImageIcons();

		initTopLeftPanel();
		initTopCenterPanel();
		initTopRightPanel();
		initBottomPanel();

		initLabelInfo();

		pack();
	}

	private void initLabelInfo() {
		/*
		 * Player Information
		 */

		Stopwatch s = new Stopwatch(this.playerTimeValueLabel, this.strings);
		s.start();

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
		tempImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

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
		tempImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

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
		tempImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

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
		tempImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

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
		tempImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	}

	private void initTopLeftPanel() {
		this.topLeftPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Global for all components
		c.insets = new Insets(5, 10, 10, 10);

		this.playerInfoLabel = new JLabel(this.strings.getString("playerInfo"));
		this.playerInfoLabel.setFont(new Font("Tahoma", 0, 18));
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

		this.playerScoreValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		this.topLeftPanel.add(this.playerScoreValueLabel, c);

		this.playerManaLabel = new JLabel(this.strings.getString("playerMana"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 2;
		this.topLeftPanel.add(this.playerManaLabel, c);

		this.playerManaValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		this.topLeftPanel.add(this.playerManaValueLabel, c);

		this.playerWaveLabel = new JLabel(this.strings.getString("playerWave"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 3;
		this.topLeftPanel.add(this.playerWaveLabel, c);

		this.playerWaveValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 3;
		this.topLeftPanel.add(this.playerWaveValueLabel, c);

		this.playerHealthLabel = new JLabel(
				this.strings.getString("playerHealth"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 4;
		this.topLeftPanel.add(this.playerHealthLabel, c);

		this.playerHealthValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 4;
		this.topLeftPanel.add(this.playerHealthValueLabel, c);

		this.playerTimeLabel = new JLabel(this.strings.getString("playerTime"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 5;
		this.topLeftPanel.add(this.playerTimeLabel, c);

		this.playerTimeValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 5;
		this.topLeftPanel.add(this.playerTimeValueLabel, c);
	}

	private void initTopCenterPanel() {
		this.topCenterPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Global for all components
		c.insets = new Insets(5, 10, 10, 10);

		this.waveInfoLabel = new JLabel(this.strings.getString("waveInfo"));
		this.waveInfoLabel.setFont(new Font("Tahoma", 0, 18));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		this.topCenterPanel.add(this.waveInfoLabel, c);

		this.waveNameLabel = new JLabel(this.strings.getString("waveName"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		this.topCenterPanel.add(this.waveNameLabel, c);

		this.waveNameValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		this.topCenterPanel.add(this.waveNameValueLabel, c);

		this.waveHealthLabel = new JLabel(this.strings.getString("waveHealth"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 2;
		this.topCenterPanel.add(this.waveHealthLabel, c);

		this.waveHealthValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		this.topCenterPanel.add(this.waveHealthValueLabel, c);

		this.waveArmorLabel = new JLabel(this.strings.getString("waveArmor"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 3;
		this.topCenterPanel.add(this.waveArmorLabel, c);

		this.waveArmorValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 3;
		this.topCenterPanel.add(this.waveArmorValueLabel, c);

		this.waveSpeedLabel = new JLabel(this.strings.getString("waveSpeed"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 4;
		this.topCenterPanel.add(this.waveSpeedLabel, c);

		this.waveSpeedValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 4;
		this.topCenterPanel.add(this.waveSpeedValueLabel, c);
	}

	private void initBottomPanel() {
		this.bottomPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Global for all components
		c.insets = new Insets(0, 5, 5, 5);

		this.availableTowersLabel = new JLabel(
				this.strings.getString("availTowers"));
		this.availableTowersLabel.setFont(new Font("Tahoma", 0, 18));
		c.fill = GridBagConstraints.RELATIVE;
		// For heading
		c.gridwidth = 10;
		c.gridx = 0;
		c.gridy = 0;
		this.bottomPanel.add(this.availableTowersLabel, c);

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
		this.bottomPanel.add(this.fireButton, c);
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
		this.bottomPanel.add(this.waterButton, c);
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
		this.bottomPanel.add(this.airButton, c);
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
		this.bottomPanel.add(this.lightButton, c);
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
		this.bottomPanel.add(this.earthButton, c);
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
		this.bottomPanel.add(this.fireCostLabel, c);

		this.fireCostValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 1;
		c.gridy = 2;
		this.bottomPanel.add(this.fireCostValueLabel, c);

		this.waterCostLabel = new JLabel(
				this.strings.getString("availTowersCost"));
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		this.bottomPanel.add(this.waterCostLabel, c);

		this.waterCostValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 3;
		c.gridy = 2;
		this.bottomPanel.add(this.waterCostValueLabel, c);

		this.airCostLabel = new JLabel(
				this.strings.getString("availTowersCost"));
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 4;
		c.gridy = 2;
		this.bottomPanel.add(this.airCostLabel, c);

		this.airCostValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 5;
		c.gridy = 2;
		this.bottomPanel.add(this.airCostValueLabel, c);

		this.lightCostLabel = new JLabel(
				this.strings.getString("availTowersCost"));
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 6;
		c.gridy = 2;
		this.bottomPanel.add(this.lightCostLabel, c);

		this.lightCostValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 7;
		c.gridy = 2;
		this.bottomPanel.add(this.lightCostValueLabel, c);

		this.earthCostLabel = new JLabel(
				this.strings.getString("availTowersCost"));
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 8;
		c.gridy = 2;
		this.bottomPanel.add(this.earthCostLabel, c);

		this.earthCostValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 9;
		c.gridy = 2;
		this.bottomPanel.add(this.earthCostValueLabel, c);
	}

	private void initTopRightPanel() {
		this.topRightPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// Global for all components
		c.insets = new Insets(0, 10, 10, 10);

		this.towerInfoLabel = new JLabel(this.strings.getString("towerInfo"));
		this.towerInfoLabel.setFont(new Font("Tahoma", 0, 18));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.topRightPanel.add(this.towerInfoLabel, c);

		this.towerLevelLabel = new JLabel(this.strings.getString("towerLevel"));
		c.fill = GridBagConstraints.RELATIVE;
		// Set once
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		this.topRightPanel.add(this.towerLevelLabel, c);

		this.towerLevelValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 1;
		this.topRightPanel.add(this.towerLevelValueLabel, c);

		this.towerDamageLabel = new JLabel(
				this.strings.getString("towerDamage"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 2;
		this.topRightPanel.add(this.towerDamageLabel, c);

		this.towerDamageValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 2;
		this.topRightPanel.add(this.towerDamageValueLabel, c);

		this.towerSpeedLabel = new JLabel(this.strings.getString("towerSpeed"));
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 3;
		this.topRightPanel.add(this.towerSpeedLabel, c);

		this.towerSpeedValueLabel = new JLabel();
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.gridy = 3;
		this.topRightPanel.add(this.towerSpeedValueLabel, c);

		this.towerUpgradeButton = new JButton(
				this.strings.getString("towerUpgrade"));
		this.towerUpgradeButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				if(ControlPanel.this.frame.getSelectedTower() != null){
					ControlPanel.this.frame.getSelectedTower().upgrade(ControlPanel.this.player);
				}
			}
			
		});
		c.fill = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 4;
		this.topRightPanel.add(this.towerUpgradeButton, c);

		this.towerSellButton = new JButton(this.strings.getString("towerSell"));
		this.towerSellButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(ControlPanel.this.frame.getSelectedTower() != null){
					int level = ControlPanel.this.frame.getSelectedTower().getLevel();
					int cost = ControlPanel.this.frame.getSelectedTower().getCost()/2;
					ControlPanel.this.player.incCurrency(level * cost);
					ControlPanel.this.map.removeTower(ControlPanel.this.frame.getSelectedTower());
					ControlPanel.this.frame.setSelectedTower(null);
				}
			}
		});
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.gridy = 5;
		this.topRightPanel.add(this.towerSellButton, c);
	}

	/**
	 * Calls all the update methods in the ControlPanel class.
	 * 
	 */
	public void update() {
		updatePlayerInfo();
		updateWaveInfo();
		updateButtons();
		updateEnemyInfo();
	}

	private void updateEnemyInfo(){
		Tower t = this.frame.getSelectedTower();
		if(t == null){
			this.towerLevelValueLabel.setText("");
			this.towerDamageValueLabel.setText("");
			this.towerSpeedValueLabel.setText("");
		}
		else{
			this.towerLevelValueLabel.setText(""+t.getLevel());
			this.towerDamageValueLabel.setText(""+t.getDamage());
			this.towerSpeedValueLabel.setText(""+t.getSpeed());
		}
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	private void updatePlayerInfo() {
		this.playerScoreValueLabel.setText(Integer.toString(this.player
				.getScore()));
		this.playerManaValueLabel.setText(Integer.toString(this.player
				.getCurrency()));
		this.playerWaveValueLabel.setText(Integer.toString(this.map
				.getWaveNumber()));
		this.playerHealthValueLabel.setText(Integer.toString(this.player
				.getHealth()));
	}

	private void updateWaveInfo() {
		Frame.element[] currEnemies = this.map.getCurrEnemies();
		String text = "";
		for (int i = 0; i < currEnemies.length; i++) {
			if (i + 1 != currEnemies.length) {
				text += currEnemies[i] + ", ";
			} else {
				text += currEnemies[i];
			}
		}
		this.waveNameValueLabel.setText(text);
		
	
		
		if(this.map.getEnemies().size() > 0)
			displayAverages(this.map.getEnemies());
	}
	
	private void displayAverages(ArrayList<Enemy> enemies){
		double size = enemies.size();
		int sumArmor = 0;
		int sumHealth = 0;
		int sumSpeed = 0;
		double avgArmor = 0;
		double avgHealth = 0;
		double avgSpeed = 0;
		
		int counter = 0;
		
		
		for (Enemy e: enemies){
			sumArmor += this.map.getEnemies().get(counter).getArmor();
			sumHealth += this.map.getEnemies().get(counter).getHP();
			sumSpeed += this.map.getEnemies().get(counter).getSpeed();
			
			avgArmor = sumArmor/size;
			avgHealth = sumHealth/size;
			avgSpeed = sumSpeed/size;
			
			counter++;
		}

		this.waveArmorValueLabel.setText(String.format("%.2f", avgArmor));
		this.waveHealthValueLabel.setText(String.format("%.2f",  avgHealth));
		this.waveSpeedValueLabel.setText(String.format("%.2f", avgSpeed));
		
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	private void updateButtons() {
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
	private JLabel waveNameLabel;
	private JLabel waveHealthLabel;
	private JLabel waveArmorLabel;
	private JLabel waveSpeedLabel;
	private JLabel waveNameValueLabel;
	private JLabel playerScoreLabel;
	private JLabel waveHealthValueLabel;
	private JLabel waveArmorValueLabel;
	private JLabel waveSpeedValueLabel;
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
	private JPanel bottomPanel;
	private JPanel topRightPanel;
	// End of declarations

	/*
	 * ImageIcon declerations
	 */
	// Fire
	private Image fireClicked;
	private Image fireUnclicked;
	private Image fireInsuffMana;
	// Water
	private Image waterClicked;
	private Image waterUnclicked;
	private Image waterInsuffMana;
	// Air
	private Image airClicked;
	private Image airUnclicked;
	private Image airInsuffMana;
	// Light
	private Image lightClicked;
	private Image lightUnclicked;
	private Image lightInsuffMana;
	// Earth
	private Image earthClicked;
	private Image earthUnclicked;
	private Image earthInsuffMana;

	// End of declerations
}