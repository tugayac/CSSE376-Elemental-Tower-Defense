import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created May 15, 2012.
 */
abstract class Tower {

	protected static int tower_ids = 0;
	protected int id;
	protected Frame.element element;
	protected Point2D.Double location;
	protected String name;
	protected boolean available;
	protected float speed;
	protected int damage;
	protected int cost;
	protected int level;
	protected Image image;

	public Tower(int id, String name, float speed, Point2D.Double loc,
			Frame.element el, boolean avail, int cost, int level, int damage) {
		this.id = id;
		this.element = el;
		this.location = loc;
		this.name = name;
		this.available = avail;
		this.speed = speed;
		this.damage = damage;
		this.cost = cost;
		this.level = level;
		this.image = null;
	}

	public Bullet fireBulletTowards(Enemy e, ArrayList<Point2D.Double> path) {
		// Frame.ap.playClip("fire", false, 0.0f);
		return new Bullet(this.location, e, path);
	}

	public Frame.element getElement() {
		return this.element;
	}

	public Point2D getLocation() {
		return this.location;
	}

	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public boolean isAvailable() {
		return this.available;
	}

	public float getSpeed() {
		return this.speed;
	}

	public int getCost() {
		return this.cost;
	}

	public int getLevel() {
		return this.level;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param g
	 * @param width
	 * @param image
	 */
	public void draw(Graphics2D g, int width) {
		g.drawImage(this.image, (int) this.location.x * width,
				(int) this.location.y * width, width, width, null);
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param player
	 * @return
	 */
	public boolean upgrade(Player player) {
		if (player == null) {
			this.level++;
			return true;
		}

		if (player.getCurrency() >= this.cost) {
			player.decCurrency(this.cost);
			this.level++;
			return true;
		}
		return false;

	}

	protected void setImage(ImageIcon image) {
		this.image = image.getImage();
	}
}
