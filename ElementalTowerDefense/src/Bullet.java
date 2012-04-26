import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


/**
 * TODO Put here a description of what this class does.
 *
 * @author matthewmercer.
 *         Created Apr 20, 2012.
 */
public class Bullet {
	private Point2D.Double loc, vector;
	private int numMoved = 0;
	private Enemy enemy;
	private int power;

	public Bullet(Point2D.Double loc, Enemy e){
		this.power = 10;
		this.enemy = e;
		this.loc = loc;
		this.vector = new Point2D.Double();
		this.vector.x = e.getLocation().x - this.loc.x;
		this.vector.y = e.getLocation().y - this.loc.y;
		
	}
	
	public void move(){
		this.numMoved++;
		this.loc.x += this.vector.x/100;
		this.loc.y += this.vector.y/100;
		
		if(numMoved == 100){
			this.loc = new Point2D.Double(-100,-100);
			this.vector = new Point2D.Double(0,0);
			this.enemy.damage(this.power);
		}
	}
	
	/**
	 * Returns the value of the field called 'loc'.
	 * @return Returns the loc.
	 */
	public Point2D.Double getLocation() {
		return this.loc;
	}



	/**
	 * Returns the value of the field called 'vector'.
	 * @return Returns the vector.
	 */
	public Point2D.Double getVector() {
		return normalize(this.vector);
	}



	private Point2D.Double normalize(Point2D.Double d){
		double norm = Math.sqrt(d.x*d.x+d.y*d.y);
		if(norm == 0)
			return new Point2D.Double(0,0);
		return new Point2D.Double(d.x/norm, d.y/norm);
	}
}
