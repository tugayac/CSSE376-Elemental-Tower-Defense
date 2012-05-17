import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * A class designed to hold the map, it holds and generates the path as well as
 * holding the towers and enemies
 * 
 * @author matthewmercer. Created Apr 18, 2012.
 */
public class Map {
	private int waveNumber;
	private Player player;
	private ArrayList<Point2D.Double> path;
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> activeEnemies;
	private ArrayList<Bullet> bullets;
	private ArrayList<Frame.element[]> enemyWaves;
	private Frame.element[] currEnemies;

	/**
	 * Creates a generic map, initializing all fields to empty lists
	 * 
	 */
	public Map() {
		this.bullets = new ArrayList<Bullet>();
		this.waveNumber = 0;
		this.path = new ArrayList<Point2D.Double>();

		generatePath();

		this.towers = new ArrayList<Tower>();
		this.activeEnemies = new ArrayList<Enemy>();

		generateWaves();
	}

	private void generateWaves() {
		this.enemyWaves = new ArrayList<Frame.element[]>(10);

		this.enemyWaves.add(addEnemies(Frame.element.FIRE));
		this.enemyWaves.add(addEnemies(Frame.element.WATER));
		this.enemyWaves.add(addEnemies(Frame.element.AIR));
		this.enemyWaves.add(addEnemies(Frame.element.LIGHT));
		this.enemyWaves.add(addEnemies(Frame.element.EARTH));
		this.enemyWaves
				.add(addEnemies(Frame.element.FIRE, Frame.element.LIGHT));
		this.enemyWaves.add(addEnemies(Frame.element.WATER, Frame.element.AIR));
		this.enemyWaves
				.add(addEnemies(Frame.element.EARTH, Frame.element.WATER));
		this.enemyWaves.add(addEnemies(Frame.element.LIGHT, Frame.element.FIRE,
				Frame.element.EARTH));
		this.enemyWaves.add(addEnemies(Frame.element.FIRE, Frame.element.WATER,
				Frame.element.AIR, Frame.element.LIGHT, Frame.element.EARTH));
	}

	private Frame.element[] addEnemies(Frame.element... elements) {
		return elements;
	}

	/**
	 * Returns the value of the field called 'player'.
	 * 
	 * @return Returns the player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Sets the field called 'player' to the given value.
	 * 
	 * @param player
	 *            The player to set.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * generates a random path which the enemies will walk on, it will not cross
	 * itself or leave the boundaries of the field
	 * 
	 * @return The path that is generated
	 */
	public ArrayList<Point2D.Double> generatePath() {
		Random r = new Random();
		Point2D.Double loc = new Point2D.Double(1, 0);
		this.path.clear();
		this.path.add(new Point2D.Double(1, 0));
		while (loc.x < 20) {
			switch (r.nextInt(3)) {
			case 0:
				this.path.add(new Point2D.Double(1, 0));
				loc.x += 1;
				break;
			case 1:
				if (this.path.get(this.path.size() - 1).y != -1)
					if (loc.y < 7) {
						this.path.add(new Point2D.Double(0, 1));
						loc.y += 1;
					}
				break;
			case 2:
				if (this.path.get(this.path.size() - 1).y != 1)
					if (loc.y > -7) {
						this.path.add(new Point2D.Double(0, -1));
						loc.y -= 1;
					}
				break;
			}

		}

		return new ArrayList<Point2D.Double>(this.path);
	}

	/**
	 * Returns the value of the field called 'path'.
	 * 
	 * @return Returns the path.
	 */
	public ArrayList<Point2D.Double> getPath() {
		return this.path;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param b
	 */
	public void addBullet(Bullet b) {
		this.bullets.add(b);
	}

	/**
	 * Adds a tower to the field if another tower does not occupy that locaiton
	 * 
	 * @param t
	 *            The tower to be added
	 * @return 0 on success, 1 if a tower occupies that spot
	 */
	public int addTower(Tower t) {
		for (Tower tow : this.towers)
			if (tow.getLocation().equals(t.getLocation()))
				return -1;
		Point2D.Double path = new Point2D.Double(0,7);
		
		for(Point2D.Double d : this.getPath()){
			if(path.getX() == t.getLocation().getX() && path.getY() == t.getLocation().getY())
				return -1;
			path.x+=d.x;
			path.y+=d.y;
		}
		if(path.getX() == t.getLocation().getX() && path.getY() == t.getLocation().getY())
			return -1;
		this.towers.add(t);
		return 0;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param d
	 * @param e
	 * @param damage
	 * @return
	 */
	public int addTower(Point2D.Double d, Frame.element e, int damage) {
		switch (e) {
		case FIRE:
			return this.addTower(new TowerFire(d, damage));
		case WATER:
			return this.addTower(new TowerWater(d, damage));
		case LIGHT:
			return this.addTower(new TowerLight(d, damage));
		case EARTH:
			return this.addTower(new TowerEarth(d, damage));
		case AIR:
			return this.addTower(new TowerAir(d, damage));
		default:
			return -1;
		}
	}

	/**
	 * Generates an enemy
	 * 
	 * @param i
	 *            wave number
	 * @param elem
	 *            element
	 * @return
	 */
	public ArrayList<Enemy> generateEnemyList() {
		Random r = new Random();

		// Returns a number between 0 and 9
		int rand = r.nextInt(10);

		this.currEnemies = this.enemyWaves.get(rand);
		ArrayList<Enemy> toBeCreated = new ArrayList<Enemy>();

		// System.out.println("Wave  = " + this.waveNumber);
		int healthFunc = (int) ((Math.pow(this.waveNumber, 3) - Math.pow(
				this.waveNumber, 2)));
		int armorFunc = (int) (Math.log(Math.pow(this.waveNumber, 10)) / Math
				.log(2));
		// System.out.println("Health = " + (healthFunc + 100));
		// System.out.println("Armor = " + (armorFunc + 3));

		int enemyCount = 10;
		int length = this.currEnemies.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < (enemyCount / length); j++) {
				switch (this.currEnemies[i]) {
				case FIRE:
					toBeCreated.add(new EnemyFire(new Point2D.Double(-1, 7),
							healthFunc + 120, armorFunc + 3));
					break;
				case WATER:
					toBeCreated.add(new EnemyWater(new Point2D.Double(-1, 7),
							healthFunc + 150, armorFunc + 4));
					break;
				case LIGHT:
					toBeCreated.add(new EnemyLight(new Point2D.Double(-1, 7),
							healthFunc + 100, armorFunc + 2));
					break;
				case EARTH:
					toBeCreated.add(new EnemyEarth(new Point2D.Double(-1, 7),
							healthFunc + 80, armorFunc + 1));
					break;
				case AIR:
					toBeCreated.add(new EnemyAir(new Point2D.Double(-1, 7),
							healthFunc + 200, armorFunc + 5));
					break;
				default:
					break;
				}
			}
		}

		return toBeCreated;
	}

	public Frame.element[] getCurrEnemies() {
		return this.currEnemies;
	}

	/**
	 * Returns the value of the field called 'towers'.
	 * 
	 * @return Returns the towers.
	 */
	public ArrayList<Tower> getTowers() {
		return this.towers;
	}

	/**
	 * Returns the value of the field called 'enemies'.
	 * 
	 * @return Returns the enemies.
	 */
	public ArrayList<Enemy> getEnemies() {
		return this.activeEnemies;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	public synchronized void update() {
		// End game if player's health reaches 0.
		if (this.player.getHealth() <= 0) {
			String message = "You are dead! Game Over!\n" + "Your score: "
					+ this.player.getScore();
			JOptionPane.showMessageDialog(null, message, "Game Over!",
					JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

		for (Bullet b : this.bullets) {
			b.move();
		}

		ArrayList<Enemy> remove = new ArrayList<Enemy>();
		for (Enemy e : this.activeEnemies) {
			e.move(this.path);
			if (e.getName().equals("FINISHED")) {
				this.player.decHealth(1);
				remove.add(e);
			}
		}
		this.activeEnemies.removeAll(remove);

		remove = null;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param g
	 * @param width
	 * @param seconds
	 */
	public synchronized void draw(Graphics2D g, int width, long seconds) {

		ArrayList<Enemy> died = new ArrayList<Enemy>();

		for (Enemy e : this.activeEnemies) {
			e.draw(g, width);
			if (e.getHP() <= 0)
				died.add(e);
		}

		for (Enemy e : died) {
			this.killEnemy(e, this.player);
		}

		// Random r = new Random();

		for (Tower t : this.towers) {
			t.draw(g, width);

			if ((seconds - t.getInitSecs()) >= t.getSpeed()) {
				this.bullets.add(t.fireBulletTowards(chooseEnemy(), this.path,
						t.getDamage()));
				t.setInitSecs(System.currentTimeMillis());
			}
		}

		ArrayList<Bullet> toBeRemoved = new ArrayList<Bullet>();

		for (Bullet b : this.bullets) {
			if (b.getVector().equals(new Point2D.Double(0, 0))) {
				Frame.ap.playClipName("hit", false, -4.0f);
				toBeRemoved.add(b);
			} else {
				b.draw(g, width);
			}

		}

		this.bullets.removeAll(toBeRemoved);

	}

	private Enemy chooseEnemy() {
		for (Enemy e : this.activeEnemies) {
			return e;
		}
		return null;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public int getWaveNumber() {
		return this.waveNumber;
	}

	public void incWave() {
		this.waveNumber++;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param enemy
	 */
	public void addEnemy(Enemy enemy) {
		this.activeEnemies.add(enemy);

	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param enemy
	 * @param player
	 */
	public void killEnemy(Enemy enemy, Player player) {
		if (this.activeEnemies.contains(enemy)) {
			Frame.ap.playClipName("death", false, 0.0f);
			player.incCurrency(enemy.getWorth());
			player.incScore(enemy.getScoreValue());
			this.activeEnemies.remove(enemy);
		}
	}
}
