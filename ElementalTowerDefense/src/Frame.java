import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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

	public static HashMap<String, URL> sounds;
	public static AudioPlayer ap;

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
	private element ele = null;

	/**
	 * creates the frame, setting it to be the size of the screen and setting
	 * the requested fps
	 * 
	 * @param fps
	 *            FPS to run the paint loop
	 * @param locale
	 * @throws InterruptedException
	 * @throws LineUnavailableException
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 */
	public Frame(int fps, String[] locale) {
		sounds = new HashMap<String, URL>();
		loadSounds();
		ap = new AudioPlayer();
		ap.playClip("music", true, -4.0f);

		this.map = new Map();
		this.player = new Player(2000, 50);
		this.map.setPlayer(this.player);
		this.controlPanel = new ControlPanel(this, this.map, this.player,
				new Locale(locale[0], locale[1]));
		this.rectSize = 30;
		this.requestedFPS = fps;
		this.thr = new Thread(this);
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.buffImg = new BufferedImage(21 * this.rectSize,
				15 * this.rectSize, BufferedImage.TYPE_4BYTE_ABGR);
		this.g = this.buffImg.createGraphics();
		this.g.setClip(0, 0, 21 * this.rectSize, 15 * this.rectSize);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Frame.this.ele != null) {
					int x = (e.getX() - (800 - 21 * Frame.this.rectSize) / 2)
							/ Frame.this.rectSize;
					int y = (e.getY() - (600 - 15 * Frame.this.rectSize) / 2)
							/ Frame.this.rectSize;
					Frame.this.map.addTower(new Point2D.Double(x, y),
							Frame.this.ele);

					Frame.this.player.decCurrency(1000);
					Frame.this.ele = null;
				}
			}
		});

		this.setResizable(false);
		this.setVisible(true);

		this.thr.start();
	}

	private void loadSounds() {
		sounds.put("music",
				AudioPlayer.class.getResource("/resources/sounds/pra.wav"));
		sounds.put("die", AudioPlayer.class
				.getResource("/resources/sounds/explosion.wav"));
		sounds.put("fire", AudioPlayer.class
				.getResource("/resources/sounds/single_fire.wav"));
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param e
	 */
	public void setElement(element e) {
		this.ele = e;
	}

	/**
	 * Returns the value of the field called 'controlPanel'.
	 * 
	 * @return Returns the controlPanel.
	 */
	public ControlPanel getControlPanel() {
		return this.controlPanel;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	public void update() {
		this.map.update();
		this.controlPanel.update();
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
			// int index = this.map.getPath().indexOf(dir);
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

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @return
	 */
	public Frame.element genEle() {
		Random r = new Random();
		int num = r.nextInt(5);
		if (num == 0) {
			return Frame.element.AIR;
		}
		if (num == 1) {
			return Frame.element.EARTH;
		}
		if (num == 2) {
			return Frame.element.FIRE;
		}
		if (num == 3) {
			return Frame.element.WATER;
		}
		if (num == 4) {
			return Frame.element.LIGHT;
		}
		return null;
	}

	public void run() {
		long time;
		long timeDiff;
		Random r = new Random(System.currentTimeMillis());

		while (true) {
			// TODO: Enemy is generated here. Change so that enemies are
			// generated from a list of waves.
			time = System.currentTimeMillis();
			if (r.nextInt(100) == 0) {
				this.map.generateEnemy(1, genEle());
			}
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
