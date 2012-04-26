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
		for(Tower t : this.towers){
			
		}
	}
	
	public void draw(Graphics2D g, int width){
		for(Bullet b : this.bullets){
			b.move();
		}
		for(Enemy e : this.activeEnemies){
			e.draw(g, width);
		}
		for(Tower t : this.towers){
			
		}
	}
	
	public int getWaveNumber() {
		return this.waveNumber;
	}

}
