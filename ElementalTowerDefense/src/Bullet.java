import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author matthewmercer. Created Apr 20, 2012.
 */
public class Bullet {
	private Point2D.Double loc, vector;
	private int numMoved = 0;
	private Enemy enemy;
	private int power;

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 * @param loc
	 * @param e
	 * @param path
	 */
	public Bullet(Point2D.Double loc, Enemy e, ArrayList<Point2D.Double> path) {
		this.power = 100000;
		this.enemy = e;

		this.loc = new Point2D.Double(loc.x + 0.5, loc.y + 0.5);
		this.vector = new Point2D.Double();
		if (this.enemy == null)
			return;
		this.enemy.target();
		this.calcVector(path);
	}

	public void move() {
		this.numMoved++;
		this.loc.x += this.vector.x / 100;
		this.loc.y += this.vector.y / 100;

		if (numMoved == 100) {
			this.loc = new Point2D.Double(-100, -100);
			this.vector = new Point2D.Double(0, 0);
			this.enemy.damage(this.power);
		}
	}

	private void calcVector(ArrayList<Point2D.Double> path) {
		Point2D.Double testLoc = new Point2D.Double(this.enemy.getLocation().x,
				this.enemy.getLocation().y);
		loop: for (int i = 0; i < 100; i++) {
			if (testLoc.x < 0) {
				testLoc.x += this.enemy.speed * 0.01;
				continue loop;
			}

			Point2D.Double loc = new Point2D.Double(0, 7);

			for (Point2D.Double dir : path) {
				loc.x += dir.x;
				loc.y += dir.y;
				if (loc.x > testLoc.x) {
					testLoc.x += this.enemy.speed * 0.01;
					continue loop;
				}
				if (loc.x + 1 > testLoc.x) {
					if (loc.y > testLoc.y && dir.y > 0) {
						testLoc.y += this.enemy.speed * 0.01;
						continue loop;
					}
					if (loc.y < testLoc.y && dir.y < 0) {
						testLoc.y -= this.enemy.speed * 0.01;
						continue loop;
					}
				}

			}
			testLoc.x += this.enemy.speed * 0.01;
		}

		this.vector.x = (testLoc.x + 0.5) - this.loc.x;
		this.vector.y = (testLoc.y + 0.5) - this.loc.y;

	}

	/**
	 * Returns the value of the field called 'loc'.
	 * 
	 * @return Returns the loc.
	 */
	public Point2D.Double getLocation() {
		return this.loc;
	}

	/**
	 * Returns the value of the field called 'vector'.
	 * 
	 * @return Returns the vector.
	 */
	public Point2D.Double getVector() {
		return normalize(this.vector);
	}

	public void draw(Graphics2D g, int width) {
		g.setColor(Color.BLACK);
		g.fill(new Ellipse2D.Double(this.loc.x * width - 5, this.loc.y * width
				- 5, 10, 10));
	}

	private Point2D.Double normalize(Point2D.Double d) {
		double norm = Math.sqrt(d.x * d.x + d.y * d.y);
		if (norm == 0)
			return new Point2D.Double(0, 0);
		return new Point2D.Double(d.x / norm, d.y / norm);
	}
}
