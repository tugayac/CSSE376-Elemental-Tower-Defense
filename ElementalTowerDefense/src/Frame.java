import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * The frame for the application, it has a paint loop and guarentees a specified
 * fps
 * 
 * @author matthewmercer. Created Apr 18, 2012.
 */
public class Frame extends JFrame implements Runnable {

	/**
	 * TODO Put here a description of what this method does.
	 *
	 */
	public void save(){
		try{
			File f = new File("GameState.dat");
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeInt(this.map.getTowers().size());
			for(Tower t : this.map.getTowers())
				out.writeObject(t);
			out.writeInt(this.map.getWaveNumber());
			out.writeInt(this.map.getPath().size());
			for(Point2D.Double d : this.map.getPath())
				out.writeObject(d);
			out.writeObject(this.player);
			out.flush();
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 */
	public void load(){
		
		
		try{
			File f = new File("GameState.dat");
			if(!f.exists()){
				JOptionPane.showMessageDialog(null, "No game state is saved");
				return;
			}
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			int numTowers = in.readInt();
			for(int i = 0; i < numTowers; i++)
				this.map.addTower((Tower) in.readObject());
			this.map.setWaveNumber(in.readInt());
			int numPath = in.readInt();
			this.map.getPath().clear();
			for(int i = 0; i < numPath; i++)
				this.map.getPath().add((Point2D.Double)in.readObject());
			this.player = (Player)in.readObject();
			this.map.setPlayer(this.player);
			this.controlPanel.setPlayer(this.player);
			in.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * The elements for use throughout the game
	 */
	static enum element {
		/** The elemnt Fire */
		FIRE ("FIRE"),
		/** The elemnt Water */
		WATER ("WATER"),
		/** The elemnt Light */
		LIGHT ("DARK"),
		/** The elemnt Earth */
		EARTH ("EARTH"),
		/** The elemnt Air */
		AIR ("AIR");
		
		private final String name;
		
		public String toString() {
			return name;
		}
		
		private element(String name){
			this.name = name;
		}
	}
	
	

	private static final long WAVE_WAIT = 5000;
	private static final long SPAWN_WAIT = 2000;

	public static AudioPlayer ap;

	private int fps;
	private Thread thr;
	private int frameSkips;
	private int sleepSkips;
	private int requestedFPS;
	private Map map;
	private BufferedImage buffImg;
	private Graphics2D g;
	private Player player;
	private ControlPanel controlPanel;
	private int rectSize;
	private element ele = null;
	private ArrayList<Enemy> enemiesToCreate;
	private long seconds = 0;
	private Tower selectedTower;

	

	private int width = 800;
	private int height = 600;
	private Color pathColor = new Color(0, 150, 0);
	private Color backColor = new Color(85, 107, 47);
	private Color tileColor = new Color(30, 70, 30);

	/**
	 * creates the frame, setting it to be the size of the screen and setting
	 * the requested fps
	 * 
	 * @param fps
	 *            FPS to run the paint loop
	 * @param locale
	 */
	public Frame(int fps, String[] locale) {
		ap = new AudioPlayer();				
		ap.playClipName("background", true, -4.0f);

		this.map = new Map();
		this.enemiesToCreate = new ArrayList<Enemy>(10);
		this.player = new Player(2000, 50);
		this.map.setPlayer(this.player);
		this.controlPanel = new ControlPanel(this, this.map, this.player,
				new Locale(locale[0], locale[1]));
		this.rectSize = 35;
		this.requestedFPS = fps;
		this.thr = new Thread(this);
		this.setSize(new Dimension(width, height));
		this.setBackground(backColor);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent arg0) {
				Frame.this.save();
				System.exit(0);
			}
			
		});

		this.buffImg = new BufferedImage(21 * this.rectSize,
				15 * this.rectSize, BufferedImage.TYPE_4BYTE_ABGR);
		this.g = this.buffImg.createGraphics();
		this.g.setClip(0, 0, 21 * this.rectSize, 15 * this.rectSize);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = (e.getX() - (800 - 21 * Frame.this.rectSize) / 2)
					/ Frame.this.rectSize;
				int y = (e.getY() - (600 - 15 * Frame.this.rectSize) / 2)
					/ Frame.this.rectSize;
				
				if (Frame.this.ele != null) {
					

					int waveNum = Frame.this.map.getWaveNumber();
					int damageFunc = (int) (Math.pow(waveNum, 2) - waveNum);
					
					int placed = 1;
					
					switch (Frame.this.ele) {
					case FIRE:
						placed = Frame.this.map.addTower(new Point2D.Double(x, y),
								Frame.element.FIRE, damageFunc + 100);
						if(placed == 0)
							Frame.this.player.decCurrency(TowerFire.cost);
						break;
					case WATER:
						placed = Frame.this.map.addTower(new Point2D.Double(x, y),
								Frame.element.WATER, damageFunc + 60);
						if(placed == 0)
							Frame.this.player.decCurrency(TowerWater.cost);
						break;
					case AIR:
						placed = Frame.this.map.addTower(new Point2D.Double(x, y),
								Frame.element.AIR, damageFunc + 50);
						if(placed == 0)
							Frame.this.player.decCurrency(TowerAir.cost);
						break;
					case LIGHT:
						placed = Frame.this.map.addTower(new Point2D.Double(x, y),
								Frame.element.LIGHT, damageFunc + 90);
						if(placed == 0)
							Frame.this.player.decCurrency(TowerLight.cost);
						break;
					case EARTH:
						placed = Frame.this.map.addTower(new Point2D.Double(x, y),
								Frame.element.EARTH, damageFunc + 80);
						if(placed == 0)
							Frame.this.player.decCurrency(TowerEarth.cost);
						break;
					default:
						break;
					}
					ap.playClipName("place", false, -4.0f);

					Frame.this.ele = null;
				}
				else{
					for (Tower t : Frame.this.map.getTowers()){
						if(t.getLocation().equals(new Point2D.Double(x,y))){
							Frame.this.selectedTower = t;
							return;
						}
					}
					Frame.this.selectedTower = null;
				}
			}
		});

		this.setResizable(false);
		this.setVisible(true);

		this.thr.start();
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
	public synchronized void update() {
		this.map.update();
		this.controlPanel.update();
	}

	@Override
	public void paint(Graphics arg) {

		this.g.setClip(0, 0, 21 * this.rectSize, 15 * this.rectSize);
		this.g.setColor(tileColor);
		this.g.fillRect(0, 0, 21 * this.rectSize, 15 * this.rectSize);
		this.g.setColor(pathColor);
		Point2D.Double d = new Point2D.Double(0, 7);
		this.g.fillRect((int) d.x * this.rectSize, (int) d.y * this.rectSize,
				this.rectSize, this.rectSize);
		this.g.setColor(Color.BLACK);
		this.g.drawLine(0, 7 * this.rectSize, this.rectSize, 7 * this.rectSize);
		this.g.drawLine(0, 8 * this.rectSize, this.rectSize, 8 * this.rectSize);
		for (Point2D.Double dir : this.map.getPath()) {
			d.x += dir.x;
			d.y += dir.y;
			this.g.setColor(pathColor);
			int xLoc = (int) d.x * this.rectSize;
			int yLoc = (int) d.y * this.rectSize;

			this.g.fillRect(xLoc, yLoc, this.rectSize, this.rectSize);
			this.g.setColor(Color.BLACK);
			// int index = this.map.getPath().indexOf(dir);
			this.g.drawRect(xLoc, yLoc, this.rectSize, this.rectSize);
			this.g.setColor(pathColor);
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

		this.map.draw(this.g, this.rectSize, this.seconds);

		arg.setColor(Color.BLACK);
		arg.drawImage(this.buffImg, (width - 21 * this.rectSize) / 2,
				(height - 15 * this.rectSize) / 2, this.rectSize * 21,
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
		Stopwatch s = new Stopwatch();
		s.start();

		int index = 0;
		long oldSec = System.currentTimeMillis();
		long resetWaveSec = System.currentTimeMillis() - WAVE_WAIT;

		boolean stopGen = false, generating = false;

		while (true) {
			time = System.currentTimeMillis();

			this.seconds = System.currentTimeMillis();
			if (!generating && (this.seconds - resetWaveSec >= WAVE_WAIT)) {
				generating = true;
				this.map.incWave();

				this.enemiesToCreate = this.map.generateEnemyList();
				index = this.enemiesToCreate.size() - 1;
			}

			if (!stopGen && (this.seconds - oldSec >= SPAWN_WAIT)) {
				stopGen = true;
				oldSec = this.seconds;
				if (this.enemiesToCreate.isEmpty()) {
					if (generating) {
						resetWaveSec = oldSec + WAVE_WAIT;
					}
					generating = false;
					index = 0;
				} else {
					this.map.addEnemy(this.enemiesToCreate.remove(index--));
				}
			}

			if (stopGen && (this.seconds - oldSec >= SPAWN_WAIT)) {
				stopGen = false;
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
	
	/**
	 * Returns the value of the field called 'selectedTower'.
	 * @return Returns the selectedTower.
	 */
	public Tower getSelectedTower() {
		return this.selectedTower;
	}

	/**
	 * Sets the field called 'selectedTower' to the given value.
	 * @param selectedTower The selectedTower to set.
	 */
	public void setSelectedTower(Tower selectedTower) {
		if(this.selectedTower != null)
			this.selectedTower.selected = false;
		this.selectedTower = selectedTower;
		if(this.selectedTower != null)
			this.selectedTower.selected = true;
	}

	
}
