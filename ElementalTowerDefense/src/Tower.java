import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

abstract class Tower {

	protected static int tower_ids = 0;
	protected int id;
	protected Object element;
	protected Point2D.Double location;
	protected String name;
	protected boolean available;
	protected float speed;
	protected int cost;
	protected int level;

	public Tower(int id, String name, float speed, Point2D.Double loc,
			Frame.element el, boolean avail, int cost, int level) {

		this.id = id;
		this.element = el;
		this.location = loc;
		this.name = name;
		this.available = avail;
		this.speed = speed;
		this.cost = cost;
		this.level = level;

	}

	public Bullet fireBulletTowards(Enemy e, ArrayList<Point2D.Double> path) {
		return new Bullet(this.location, e, path);
	}

	public Object getElement() {
		// TODO Auto-generated method stub
		return this.element;
	}

	public Point2D getLocation() {
		// TODO Auto-generated method stub
		return this.location;
	}

	public int getID() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return this.available;
	}

	public float getSpeed() {
		// TODO Auto-generated method stub
		return this.speed;
	}

	public int getCost() {
		// TODO Auto-generated method stub
		return this.cost;
	}

	public int getLevel() {
		// TODO Auto-generated method stub
		return this.level;
	}
	
	public void draw(Graphics2D g, int width){
		g.fillOval((int) this.location.x*width, (int) this.location.y*width, width, width);
	}

	public boolean upgrade(Player player) {
		// TODO Auto-generated method stub
		if (player == null){
			this.level++;
			return true;
		}
		
		if (player.getCurrency() >= this.cost){
			player.decCurrency(this.cost);
			this.level++;
			return true;
		}
		return false;
		

	}

	// public static Tower createTower(Frame.element el, Double loc) {
	// // TODO Auto-generated method stub
	//
	// return new Tower(el, loc);
	// // return new Tower(){
	// //
	// // };
	// }

}
