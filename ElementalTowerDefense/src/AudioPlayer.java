import java.io.IOException;

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

	private boolean done = false;

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 */
	public AudioPlayer() {

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
	public void playClip(final String name, final boolean loop,
			final float volume) {
		Thread thread = new Thread() {

			@Override
			public void run() {
				AudioPlayer player = new AudioPlayer();

				AudioInputStream audioInputStream = null;
				try {
					audioInputStream = AudioSystem
							.getAudioInputStream(Frame.sounds.get(name));
				} catch (UnsupportedAudioFileException exception1) {
					// TODO Auto-generated catch-block stub.
					exception1.printStackTrace();
				} catch (IOException exception1) {
					// TODO Auto-generated catch-block stub.
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
