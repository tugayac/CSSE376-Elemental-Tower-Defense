import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Splash.java
 *
 * Created on May 10, 2012, 3:36:18 PM
 */

/**
 *
 * @author moyessa
 */
public class Splash extends javax.swing.JFrame{

    /** Creates new form Splash 
     * @param temp */
	
	String[] localeString;
	Locale locale;
	ResourceBundle strings;
	
    public Splash(String[] temp) {
    	localeString = temp;
    	locale = new Locale(localeString[0], localeString[1]);
    	
    	this.strings = ResourceBundle.getBundle(
				"resources.localization.localization", this.locale);
    	
    	this.setResizable(false);
    	
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        startButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        
        splash = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        ImageIcon tempIcon = new ImageIcon(getClass().getResource(
		"/resources/images/splash.png"));
        
        splash.setIcon(tempIcon);

        startButton.setText(this.strings.getString("splashStart"));
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        loadButton.setText(this.strings.getString("splashLoad"));
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        exitButton.setText(this.strings.getString("splashExit"));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        aboutButton.setText(this.strings.getString("splashAbout"));
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });
        
//        .addComponent(splash)

        startButton.setBounds(0, 0, 100, 100);
        startButton.setSize(100, 100);
        
        int width = 700;
        int preferedSize = 100;
        int plac = width/2;

        System.out.println(plac);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(598-preferedSize, Short.MAX_VALUE)
                .addComponent(aboutButton, javax.swing.GroupLayout.PREFERRED_SIZE, preferedSize, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, preferedSize, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(plac, plac, plac)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, preferedSize, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, preferedSize, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(width-plac-preferedSize, Short.MAX_VALUE))
                .addComponent(splash)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(startButton)
                .addGap(18, 18, 18)
                .addComponent(loadButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(aboutButton))
                .addContainerGap())
                .addComponent(splash)
        );
      

        pack();
    }// </editor-fold>

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	this.setVisible(false);
    	new Frame(60, localeString);
    	
    }

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        new Frame(60, localeString).load();

    }

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ImageIcon icon = new ImageIcon(getClass().getResource(
		"/resources/images/aboutUs.png"));
    	
    	JOptionPane.showMessageDialog(null, null, "About", JOptionPane.INFORMATION_MESSAGE, icon);
    	
    	//JOptionPane.showMessageDialog(null, "About Us: Not yet implemented.");
    }

    // Variables declaration - do not modify
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton startButton;
    
    private javax.swing.JLabel splash;
    // End of variables declaration

}
