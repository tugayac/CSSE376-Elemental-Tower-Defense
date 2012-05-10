import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Enemy {

	protected static int enemy_ids = 0;

	protected int id;
	protected String name;
	protected float speed;
	protected int armor;
	protected int hp;
	protected int worth;
	protected Point2D.Double location;
	protected Frame.element elem;

	private boolean targeted = false;

	static protected Image earthImage;
	static protected Image airImage;
	static protected Image waterImage;
	static protected Image fireImage;
	static protected Image lightImage;

	static boolean loaded = false;

	public void target() {
		this.targeted = true;
	}

	public boolean targeted() {
		return this.targeted;
	}

	public void initImages() {
		// System.out.println("I should only be called once");
		Image tempImage;
		ImageIcon tempIcon;

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/enemies/Enemy_Earth.png"));
		tempImage = tempIcon.getImage();
		this.earthImage = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/enemies/Enemy_Air.png"));
		tempImage = tempIcon.getImage();
		this.airImage = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/enemies/Enemy_Water.png"));
		tempImage = tempIcon.getImage();
		this.waterImage = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/enemies/Enemy_Fire.png"));
		tempImage = tempIcon.getImage();
		this.fireImage = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		tempIcon = new ImageIcon(getClass().getResource(
				"/resources/images/enemies/Enemy_Light.png"));
		tempImage = tempIcon.getImage();
		this.lightImage = tempImage.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);

		loaded = true;
	}

	// tempIcon = new ImageIcon(getClass().getResource(
	// "/resources/images/towers/earth_clicked.png"));
	// tempImage = tempIcon.getImage();
	// this.earthClicked = tempImage.getScaledInstance(100, 100,
	// Image.SCALE_SMOOTH);

	public Enemy(int id, String name, float speed, int armor, int hp,
			Point2D.Double location, Frame.element elem) {
		this.id = id;
		this.name = name;
		this.speed = speed;
		this.armor = armor;
		this.hp = hp;
		this.location = location;
		this.elem = elem;
		this.worth = hp * 2;

		if (!loaded) {
			initImages();
		}
	}

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
	public int getID() {
		return this.id;
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

	public void draw(Graphics2D g, int width) {

		if (this.elem == Frame.element.AIR) {
			g.drawImage(this.airImage, (int) (this.location.x * width),
					(int) (this.location.y * width), width, width, null);

			// g.setColor(Color.WHITE);
		} else if (this.elem == Frame.element.EARTH) {
			g.drawImage(this.earthImage, (int) (this.location.x * width),
					(int) (this.location.y * width), width, width, null);

			// g.setColor(Color.DARK_GRAY);
		} else if (this.elem == Frame.element.WATER) {
			g.drawImage(this.waterImage, (int) (this.location.x * width),
					(int) (this.location.y * width), width, width, null);

			// g.setColor(Color.BLUE);
		} else if (this.elem == Frame.element.FIRE) {
			g.drawImage(this.fireImage, (int) (this.location.x * width),
					(int) (this.location.y * width), width, width, null);

			// g.setColor(Color.RED);
		} else if (this.elem == Frame.element.LIGHT) {
			g.drawImage(this.lightImage, (int) (this.location.x * width),
					(int) (this.location.y * width), width, width, null);

			// g.setColor(Color.YELLOW);
		}

		// g.fillOval((int) (this.location.x * width),
		// (int) (this.location.y * width), width, width);

	}

	public int getWorth() {
		return this.worth;
	}

}
