import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class Enemy {

//	public enum element {
//		FIRE, WATER, LIGHT, EARTH, AIR
//	}
	
	protected static int enemy_ids = 0;

	protected int id;
	protected String name;
	protected float speed;
	protected int armor;
	protected int hp;
	protected Point2D.Double location;
	protected Frame.element elem;

	public Enemy(int id, String name, float speed, int armor, int hp,
			Point2D.Double location, Frame.element elem) {
		this.id = id;
		this.name = name;
		this.speed = speed;
		this.armor = armor;
		this.hp = hp;
		this.location = location;
		this.elem = elem;
	}

	public void move(ArrayList<Point2D.Double> path){
		if(this.location.x < 0){
			this.location.x+=speed * 0.01;
			return;
		}
		
		Point2D.Double loc = new Point2D.Double(0,7);
		
		for(Point2D.Double dir : path){
			loc.x+=dir.x;
			loc.y+=dir.y;
			if(loc.x > this.location.x){
				this.location.x += speed *0.01;
				return;
			}
			if(loc.x+1 > this.location.x){
				if(loc.y > this.location.y && dir.y > 0){
					this.location.y += speed * 0.01;
					return;
				}
				if(loc.y < this.location.y && dir.y < 0){
					this.location.y -= speed * 0.01;
					return;
				}
			}
			
		}
		this.location.x += speed *0.01;
		
		if(this.location.x >= 21)
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

	
	public void draw(Graphics2D g, int width){
		g.setColor(Color.BLACK);
		g.fillOval((int)(this.location.x*width), (int)(this.location.y*width), width, width);
	}
}
