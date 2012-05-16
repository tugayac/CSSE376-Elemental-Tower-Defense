import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created May 14, 2012.
 */
public abstract class Enemy {

	protected String name;
	protected float speed;
	protected int armor;
	protected int hp;
	protected int worth;
	protected int scoreValue;
	protected Point2D.Double location;
	protected Frame.element elem;

	private boolean targeted = false;

	static protected Image earthImage;
	static protected Image airImage;
	static protected Image waterImage;
	static protected Image fireImage;
	static protected Image lightImage;

	static boolean loaded = false;

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 * @param id
	 * @param name
	 * @param speed
	 * @param armor
	 * @param hp
	 * @param location
	 * @param elem
	 * @param scoreValue
	 */
	public Enemy(String name, float speed, int armor, int hp,
			Point2D.Double location, Frame.element elem, int scoreValue) {
		this.name = name;
		this.speed = speed;
		this.armor = armor;
		this.hp = hp;
		this.location = location;
		this.elem = elem;
		this.worth = hp / 2;
		this.scoreValue = scoreValue;

		if (!loaded) {
			initImages();
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	public void target() {
		this.targeted = true;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public boolean targeted() {
		return this.targeted;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	public void initImages() {
		Image tempImage;
		ImageIcon tempIcon;

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/enemies/Enemy_Earth.png"));
		tempImage = tempIcon.getImage();
		Enemy.earthImage = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/enemies/Enemy_Air.png"));
		tempImage = tempIcon.getImage();
		Enemy.airImage = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/enemies/Enemy_Water.png"));
		tempImage = tempIcon.getImage();
		Enemy.waterImage = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/enemies/Enemy_Fire.png"));
		tempImage = tempIcon.getImage();
		Enemy.fireImage = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/enemies/Enemy_Light.png"));
		tempImage = tempIcon.getImage();
		Enemy.lightImage = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		loaded = true;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param path
	 */
	public void move(ArrayList<Point2D.Double> path) {
		if (this.location.x < 0) {
			this.location.x += this.speed * 0.01;
			return;
		}

		Point2D.Double loc = new Point2D.Double(0, 7);

		for (Point2D.Double dir : path) {
			loc.x += dir.x;
			loc.y += dir.y;
			if (loc.x > this.location.x) {
				this.location.x += this.speed * 0.01;
				return;
			}
			if (loc.x + 1 > this.location.x) {
				if (loc.y > this.location.y && dir.y > 0) {
					this.location.y += this.speed * 0.01;
					return;
				}
				if (loc.y < this.location.y && dir.y < 0) {
					this.location.y -= this.speed * 0.01;
					return;
				}
			}

		}
		this.location.x += this.speed * 0.01;

		if (this.location.x >= 21)
			this.name = "FINISHED";
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param location
	 */
	public void setLocation(Point2D.Double location) {
		this.location = location;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public Point2D.Double getLocation() {
		return this.location;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public Frame.element getElement() {
		return this.elem;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public float getSpeed() {
		return this.speed;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public int getArmor() {
		return this.armor;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public int getHP() {
		return this.hp;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param i
	 */
	public abstract void damage(int i);

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param g
	 * @param width
	 */
	public void draw(Graphics2D g, int width) {
		if (this.elem == Frame.element.AIR) {
			g.drawImage(Enemy.airImage, (int) (this.location.x * width),
					(int) (this.location.y * width), width, width, null);
		} else if (this.elem == Frame.element.EARTH) {
			g.drawImage(Enemy.earthImage, (int) (this.location.x * width),
					(int) (this.location.y * width), width, width, null);
		} else if (this.elem == Frame.element.WATER) {
			g.drawImage(Enemy.waterImage, (int) (this.location.x * width),
					(int) (this.location.y * width), width, width, null);
		} else if (this.elem == Frame.element.FIRE) {
			g.drawImage(Enemy.fireImage, (int) (this.location.x * width),
					(int) (this.location.y * width), width, width, null);
		} else if (this.elem == Frame.element.LIGHT) {
			g.drawImage(Enemy.lightImage, (int) (this.location.x * width),
					(int) (this.location.y * width), width, width, null);
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public int getWorth() {
		return this.worth;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 * 
	 */
	public int getScoreValue() {
		return this.scoreValue;
	}

}
