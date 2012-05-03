import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created May 1, 2012.
 */
public class AudioPlayer implements LineListener {

	private boolean done = false;

	public AudioPlayer() {

	}

	public synchronized void update(LineEvent event) {
		Type eventType = event.getType();
		if (eventType == Type.STOP || eventType == Type.CLOSE) {
			this.done = true;
			notifyAll();
		}
	}

	public synchronized void waitUntilDone() throws InterruptedException {
		while (!this.done) {
			wait();
		}
	}

	private static void playClip(URL clipFile) throws IOException,
			UnsupportedAudioFileException, LineUnavailableException,
			InterruptedException {
		AudioPlayer player = new AudioPlayer();
		AudioInputStream audioInputStream = AudioSystem
				.getAudioInputStream(clipFile);
		try {
			Clip clip = AudioSystem.getClip();
			clip.addLineListener(player);
			clip.open(audioInputStream);
			try {
				clip.start();
				player.waitUntilDone();
			} finally {
				clip.close();
			}
		} finally {
			audioInputStream.close();
		}
	}

	public static void main(String[] args) throws IOException,
			UnsupportedAudioFileException, LineUnavailableException,
			InterruptedException {
		playClip(AudioPlayer.class.getResource("/resources/sounds/ding.wav"));
	}
}
