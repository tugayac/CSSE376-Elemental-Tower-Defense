import javax.swing.JOptionPane;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author matthewmercer. Created Apr 26, 2012.
 */
public class Main {

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] locales = { "English (United States - en_US)",
				"Español (Spain - es_ES)", "Türkçe (Turkey - tr_TR)" };
		String locale = (String) JOptionPane.showInputDialog(null,
				"Choose your Language:", "Localization", JOptionPane.OK_OPTION,
				null, locales, locales[0]);

		if (locale == null) {
			System.exit(0);
		} else {
			locale = locale.split(" - ")[1].replace(")", "");
			String[] temp = locale.split("_");
			
			new Splash(temp).setVisible(true);
//			new Frame(60, temp);
		}
	}
}
