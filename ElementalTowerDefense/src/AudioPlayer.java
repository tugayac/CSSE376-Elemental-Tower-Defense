import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created May 1, 2012.
 */
public class AudioPlayer {

	private HashMap<String, URL> sounds;
	private boolean done = false;

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 */
	public AudioPlayer() {
		this.sounds = new HashMap<String, URL>(19);
		loadSounds();
	}

	private void loadSounds() {
		// Background music
		this.sounds.put("background", AudioPlayer.class
				.getResource("/resources/sounds/background.wav"));

		// Death sounds
		this.sounds.put("death1",
				AudioPlayer.class.getResource("/resources/sounds/death1.wav"));
		this.sounds.put("death2",
				AudioPlayer.class.getResource("/resources/sounds/death2.wav"));
		this.sounds.put("death3",
				AudioPlayer.class.getResource("/resources/sounds/death3.wav"));
		this.sounds.put("death4",
				AudioPlayer.class.getResource("/resources/sounds/death4.wav"));
		this.sounds.put("death5",
				AudioPlayer.class.getResource("/resources/sounds/death5.wav"));
		this.sounds.put("death6",
				AudioPlayer.class.getResource("/resources/sounds/death6.wav"));

		// Firing sounds
		this.sounds.put("fire1",
				AudioPlayer.class.getResource("/resources/sounds/fire1.wav"));
		this.sounds.put("fire2",
				AudioPlayer.class.getResource("/resources/sounds/fire2.wav"));
		this.sounds.put("fire3",
				AudioPlayer.class.getResource("/resources/sounds/fire3.wav"));
		this.sounds.put("fire4",
				AudioPlayer.class.getResource("/resources/sounds/fire4.wav"));
		this.sounds.put("fire5",
				AudioPlayer.class.getResource("/resources/sounds/fire5.wav"));
		this.sounds.put("fire6",
				AudioPlayer.class.getResource("/resources/sounds/fire6.wav"));

		// Enemy being hit sounds
		this.sounds.put("hit1",
				AudioPlayer.class.getResource("/resources/sounds/hit1.wav"));
		this.sounds.put("hit2",
				AudioPlayer.class.getResource("/resources/sounds/hit2.wav"));
		this.sounds.put("hit3",
				AudioPlayer.class.getResource("/resources/sounds/hit3.wav"));

		// Tower placement sounds
		this.sounds.put("place1",
				AudioPlayer.class.getResource("/resources/sounds/place1.wav"));
		this.sounds.put("place2",
				AudioPlayer.class.getResource("/resources/sounds/place2.wav"));
		this.sounds.put("place3",
				AudioPlayer.class.getResource("/resources/sounds/place3.wav"));
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @throws InterruptedException
	 */
	private synchronized void waitUntilDone() throws InterruptedException {
		while (!this.done) {
			wait();
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param genericSoundName
	 * @param loop
	 * @param volume
	 */
	public void playClipName(String genericSoundName, boolean loop, float volume) {
		Random r = new Random();
		if (genericSoundName.equals("background")) {
			playClip(genericSoundName, loop, volume);
		} else if (genericSoundName.equals("death")
				|| genericSoundName.equals("fire")) {
			int i = r.nextInt(6) + 1;
			playClip(genericSoundName + i, loop, volume);
		} else {
			int i = r.nextInt(3) + 1;
			playClip(genericSoundName + i, loop, volume);
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param name
	 * @param loop
	 */
	public void playClip(final String name, final boolean loop,
			final float volume) {
		Thread thread = new Thread() {

			@Override
			public void run() {
				AudioPlayer player = new AudioPlayer();

				AudioInputStream audioInputStream = null;
				try {
					audioInputStream = AudioSystem
							.getAudioInputStream(AudioPlayer.this.sounds
									.get(name));
				} catch (UnsupportedAudioFileException exception1) {
					exception1.printStackTrace();
				} catch (IOException exception1) {
					exception1.printStackTrace();
				}
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					FloatControl gainControl = (FloatControl) clip
							.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(volume);
					try {
						clip.start();
						if (loop) {
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						} else {
							// Do Nothing
						}
						player.waitUntilDone();
					} catch (InterruptedException exception) {
						exception.printStackTrace();
					} finally {
						clip.close();
					}
				} catch (LineUnavailableException exception) {
					exception.printStackTrace();
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					try {
						audioInputStream.close();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				}
			}
		};

		thread.start();
	}
}
