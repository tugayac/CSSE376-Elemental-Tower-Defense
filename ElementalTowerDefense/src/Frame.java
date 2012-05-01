import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Locale;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * The frame for the application, it has a paint loop and guarentees a specified
 * fps
 * 
 * @author matthewmercer. Created Apr 18, 2012.
 */
public class Frame extends JFrame implements Runnable {

	/**
	 * The elements for use throughout the game
	 */
	static enum element {
		/** The elemnt Fire */
		FIRE,
		/** The elemnt Water */
		WATER,
		/** The elemnt Light */
		LIGHT,
		/** The elemnt Earth */
		EARTH,
		/** The elemnt Air */
		AIR
	}

	private int fps;
	private Thread thr;
	private int frameSkips;
	private int sleepSkips;
	private int requestedFPS;
	private int timeOverhead;
	private Map map;
	private BufferedImage buffImg;
	private Graphics2D g;
	private Player player;
	private ControlPanel controlPanel;
	private int rectSize;

	/**
	 * creates the frame, setting it to be the size of the screen and setting
	 * the requested fps
	 * 
	 * @param fps
	 *            FPS to run the paint loop
	 */
	public Frame(int fps) {
		this.map = new Map();
		this.player = new Player();
		this.controlPanel = new ControlPanel(this.map, this.player, new Locale(
				"en", "US"));
		this.rectSize = 30;
		this.requestedFPS = fps;
		this.thr = new Thread(this);
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.buffImg = new BufferedImage(21 * this.rectSize,
				15 * this.rectSize, BufferedImage.TYPE_4BYTE_ABGR);
		this.g = this.buffImg.createGraphics();
		this.g.setClip(0, 0, 21 * this.rectSize, 15 * this.rectSize);

		this.setVisible(true);

		this.thr.start();

	}

	/**
	 * Returns the value of the field called 'controlPanel'.
	 * 
	 * @return Returns the controlPanel.
	 */
	public ControlPanel getControlPanel() {
		return this.controlPanel;
	}

	public void update() {
		this.map.update();
		this.controlPanel.updatePlayerInfo();
	}

	@Override
	public void paint(Graphics arg) {

		this.g.setClip(0, 0, 21 * this.rectSize, 15 * this.rectSize);
		this.g.setColor(Color.LIGHT_GRAY);
		this.g.fillRect(0, 0, 21 * this.rectSize, 15 * this.rectSize);
		this.g.setColor(Color.GREEN);
		Point2D.Double d = new Point2D.Double(0, 7);
		this.g.fillRect((int) d.x * this.rectSize, (int) d.y * this.rectSize,
				this.rectSize, this.rectSize);
		this.g.setColor(Color.BLACK);
		this.g.drawLine(0, 7 * this.rectSize, this.rectSize, 7 * this.rectSize);
		this.g.drawLine(0, 8 * this.rectSize, this.rectSize, 8 * this.rectSize);
		for (Point2D.Double dir : this.map.getPath()) {
			d.x += dir.x;
			d.y += dir.y;
			this.g.setColor(Color.GREEN);
			int xLoc = (int) d.x * this.rectSize;
			int yLoc = (int) d.y * this.rectSize;

			this.g.fillRect(xLoc, yLoc, this.rectSize, this.rectSize);
			this.g.setColor(Color.BLACK);
			int index = this.map.getPath().indexOf(dir);
			this.g.drawRect(xLoc, yLoc, this.rectSize, this.rectSize);
			this.g.setColor(Color.GREEN);
			if (dir.x == 1) {
				this.g.drawLine(xLoc, yLoc + 1, xLoc, yLoc + this.rectSize - 1);
			} else if (dir.y == 1) {
				this.g.drawLine(xLoc + 1, yLoc, xLoc + this.rectSize - 1, yLoc);
			} else {
				this.g.drawLine(xLoc + 1, yLoc + this.rectSize, xLoc
						+ this.rectSize - 1, yLoc + this.rectSize);
			}
		}
		d.x += 1;
		int xLoc = (int) d.x * this.rectSize;
		int yLoc = (int) d.y * this.rectSize;
		this.g.drawLine(xLoc, yLoc + 1, xLoc, yLoc + this.rectSize - 1);

		this.g.setColor(Color.BLACK);
		this.g.drawString(new String("FPS: " + this.fps), 0, 15);

		this.map.draw(this.g, this.rectSize);

		arg.setColor(Color.BLACK);
		arg.drawImage(this.buffImg, (800 - 21 * this.rectSize) / 2,
				(600 - 15 * this.rectSize) / 2, this.rectSize * 21,
				this.rectSize * 15, null);
		/*
		 * for (int i = 0; i < 100; i++) { this.g.drawLine(0, i *
		 * (this.getHeight() / 100), i (this.getWidth() / 100),
		 * this.getHeight()); this.g.drawLine(0, (100 - i) * (this.getHeight() /
		 * 100), i * (this.getWidth() / 100), 0);
		 * this.g.drawLine(this.getWidth(), i * (this.getHeight() / 100), (100 -
		 * i) (this.getWidth() / 100), this.getHeight());
		 * this.g.drawLine(this.getWidth(), (100 - i) * (this.getHeight() /
		 * 100), (100 - i) * (this.getWidth() / 100), 0); }
		 */

	}

	public void run() {
		long time;
		long timeDiff;
		Random r = new Random(System.currentTimeMillis());

		while (true) {
			time = System.currentTimeMillis();
			if (r.nextInt(100) == 0)
				this.map.generateEnemy(1, Frame.element.AIR);
			this.update();
			this.repaint();
			timeDiff = System.currentTimeMillis() - time;
			if (timeDiff < (1000 / this.requestedFPS)) {
				try {
					Thread.sleep((1000 / this.requestedFPS) - timeDiff);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			else {
				if (this.sleepSkips < 10) {
					this.sleepSkips++;
				} else {
					this.update();
					this.update();
					this.frameSkips++;
					this.sleepSkips = 0;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			time = System.currentTimeMillis() - time;
			if (time != 0)
				this.fps = (int) (1000 / time);
		}
	}

	/**
	 * Returns the value of the field called 'fps'.
	 * 
	 * @return Returns the fps.
	 */
	public int getFPS() {
		return this.fps;
	}

	/**
	 * Returns the value of the field called 'requestedFPS'.
	 * 
	 * @return Returns the fps.
	 */
	public int getRequestedFPS() {
		return this.requestedFPS;
	}

	/**
	 * Returns the value of the field called 'frameSkips'.
	 * 
	 * @return Returns the frameSkips.
	 */
	public int getFrameSkips() {
		return this.frameSkips;
	}

	/**
	 * Returns the value of the field called 'sleepSkips'.
	 * 
	 * @return Returns the sleepSkips.
	 */
	public int getSleepSkips() {
		return this.sleepSkips;
	}

}
