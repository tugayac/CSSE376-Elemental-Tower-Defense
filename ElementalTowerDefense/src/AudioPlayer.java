import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created May 1, 2012.
 */
public class AudioPlayer {

	private boolean done = false;
	private HashMap<String, AudioInputStream> sounds;

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 */
	public AudioPlayer() {
		this.sounds = new HashMap<String, AudioInputStream>();

		loadSounds();
	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	private void loadSounds() {
		try {
			this.sounds.put("music", AudioSystem
					.getAudioInputStream(AudioPlayer.class
							.getResource("/resources/sounds/pra.wav")));
		} catch (UnsupportedAudioFileException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
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
	 * @param name
	 * @param loop
	 */
	public void playClip(final String name, final boolean loop) {
		Thread thread = new Thread() {

			@Override
			public void run() {
				AudioPlayer player = new AudioPlayer();

				AudioInputStream audioInputStream = AudioPlayer.this.sounds
						.get(name);
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
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
