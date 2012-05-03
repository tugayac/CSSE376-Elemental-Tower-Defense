import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

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

	/**
	 * Creates a generic map, initializing all fields to empty lists
	 * 
	 */

	public Map(){
		this.bullets = new ArrayList<Bullet>();
		this.waveNumber = 0;
		this.path = new ArrayList<Point2D.Double>();
		generatePath();
		this.towers = new ArrayList<Tower>();
		this.activeEnemies = new ArrayList<Enemy>();
	}
	
	

	/**
	 * Returns the value of the field called 'player'.
	 * @return Returns the player.
	 */
	public Player getPlayer() {
		return this.player;
	}



	/**
	 * Sets the field called 'player' to the given value.
	 * @param player The player to set.
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
		Point2D.Double loc = new Point2D.Double(1,0);
		this.path.clear();
		this.path.add(new Point2D.Double(1, 0));
		while(loc.x < 20){
			switch(r.nextInt(3)){
				case 0: this.path.add(new Point2D.Double(1,0));
						loc.x+=1;
						break;
				case 1: if(this.path.get(this.path.size()-1).y != -1)
							if(loc.y < 7){
								this.path.add(new Point2D.Double(0,1));
								loc.y+=1;
							}
						break;
				case 2: if(this.path.get(this.path.size()-1).y != 1)
							if(loc.y > -7){
								this.path.add(new Point2D.Double(0,-1));
								loc.y-=1;
							}
						break;
			}

		}

		return new ArrayList<Point2D.Double>(this.path);
	}


	
	/**
	 * Returns the value of the field called 'path'.
	 * @return Returns the path.
	 */
	public ArrayList<Point2D.Double> getPath() {
		return this.path;
	}

	public void addBullet(Bullet b){
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
		this.towers.add(t);
		return 0;
	}
	
	public int addTower(Point2D.Double d, Frame.element e){
		switch(e){
		case FIRE:
			return this.addTower(new Tower_Fire(d));
		case WATER:
			return this.addTower(new Tower_Water(d));
		case LIGHT:
			return this.addTower(new Tower_Light(d));
		case EARTH:
			return this.addTower(new Tower_Earth(d));
		case AIR:
			return this.addTower(new Tower_Air(d));
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
	 */
	public void generateEnemy(int i, Frame.element elem) {
		switch (elem) {
		case FIRE:
			this.activeEnemies.add(new Enemy_Fire(new Point2D.Double(-1,7)));
			break;
		case WATER:
			this.activeEnemies.add(new Enemy_Water(new Point2D.Double(-1,7)));
			break;
		case LIGHT:
			this.activeEnemies.add(new Enemy_Light(new Point2D.Double(-1,7)));
			break;
		case EARTH:
			this.activeEnemies.add(new Enemy_Earth(new Point2D.Double(-1,7)));
			break;
		case AIR:
			this.activeEnemies.add(new Enemy_Air(new Point2D.Double(-1,7)));
			break;
		default:
			break;
		}
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
	
	public void update(){
		for(Bullet b : this.bullets){
			b.move();
		}
		ArrayList<Enemy> remove = new ArrayList<Enemy>();
		for(Enemy e : this.activeEnemies){
			e.move(this.path);
			if(e.getName().equals("FINISHED"))
				remove.add(e);
		}
		this.activeEnemies.removeAll(remove);
		
		
		remove = null;
		Random r = new Random();
	}
	
	public synchronized void draw(Graphics2D g, int width){
		
		ArrayList<Enemy> died = new ArrayList<Enemy>();
		
		for(Enemy e : this.activeEnemies){
			e.draw(g, width);
			if(e.getHP() <= 0)
				died.add(e);
		}
		
		for(Enemy e : died){
			this.killEnemy(e, this.player);
		}
		
		Random r = new Random();
		
		for(Tower t : this.towers){
			t.draw(g,width);
			int i = r.nextInt(200);
			if(i == 0){
				this.bullets.add(t.fireBulletTowards(chooseEnemy(), this.path));
			}
		}
		
		ArrayList<Bullet> toBeRemoved = new ArrayList<Bullet>();
		
		for(Bullet b : this.bullets){
			if(b.getVector().equals(new Point2D.Double(0,0)))
				toBeRemoved.add(b);
			else
				b.draw(g, width);
			
		}
		
		this.bullets.removeAll(toBeRemoved);
		
		
	}
	
	private Enemy chooseEnemy(){
		for(Enemy e : this.activeEnemies){
			if(!e.targeted())
				return e;
		}
		return null;
	}
	
	public int getWaveNumber() {
		return this.waveNumber;
	}

	public void addEnemy(Enemy enemy) {
		
		activeEnemies.add(enemy);
		
	}

	public void killEnemy(Enemy enemy, Player player) {
			
		if (activeEnemies.contains(enemy)){
			player.incCurrency(enemy.getWorth());
			activeEnemies.remove(enemy);
		}
		
	}

}
