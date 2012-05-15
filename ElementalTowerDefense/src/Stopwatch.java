import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author tugayac. Created Apr 27, 2012.
 */
public class Stopwatch {

	private JLabel updateLabel;
	private long watchStart;
	private Timer chronometer;
	private int sec;

	public Stopwatch() {
		this.chronometer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seconds = (int) (System.currentTimeMillis() - Stopwatch.this.watchStart) / 1000;
				int days = seconds / 86400;
				int hours = (seconds / 3600) - (days * 24);
				int min = (seconds / 60) - (days * 1440) - (hours * 60);
				Stopwatch.this.sec = seconds % 60;
			}
		});
	}

	public int getSeconds() {
		return this.sec;
	}

	public Stopwatch(JLabel updateLabel, ResourceBundle strings) {
		this.updateLabel = updateLabel;
		final String[] labels = { strings.getString("timeHours"),
				strings.getString("timeMins"), strings.getString("timeSecs") };

		this.chronometer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seconds = (int) (System.currentTimeMillis() - Stopwatch.this.watchStart) / 1000;
				int days = seconds / 86400;
				int hours = (seconds / 3600) - (days * 24);
				int min = (seconds / 60) - (days * 1440) - (hours * 60);
				int sec = seconds % 60;
				Stopwatch.this.updateLabel.setText(new String("" + hours + " "
						+ labels[0] + " " + min + " " + labels[1] + " " + sec
						+ " " + labels[2] + " "));
			}
		});
	}

	public void start() {
		this.watchStart = System.currentTimeMillis();
		this.chronometer.start();
	}

	public void stop() {
		this.chronometer.stop();
	}
}