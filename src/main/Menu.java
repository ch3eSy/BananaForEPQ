package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import menuEnt.menuBackdrop;
import javax.swing.*;
import java.security.*;

public class Menu implements Runnable{
	private MenuPanel menuPanel;
	private MenuWindow menuWindow;
	private Thread menuThread;
	private menuBackdrop backg;
	private final int fps_max = 240;
	private final int ups_max = 200;
	private JButton startbutton;
	private ImageIcon startIcon;
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public Menu() {

		initclasses();
		menuPanel = new MenuPanel(this);
		menuPanel.setLayout(null);
		menuWindow = new MenuWindow(menuPanel);
		startIcon = new ImageIcon(getClass().getResource("/startButton.png"));
		if (startIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
		    System.out.println("Image failed to load!");
		}
		Image scaledImage = startIcon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
		startIcon = new ImageIcon(scaledImage);
		startbutton = new JButton(startIcon);
		startbutton.setBounds(885, 640, 250,150);
		startbutton.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				 menuWindow.kill();
				 startgame();

				
			}
		});
		menuPanel.add(startbutton);



		menuPanel.requestFocus();
		
		
		startGameLoop();

	}
	public void connectToDB() {
	      try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	      Connection connection = null;
	      try
	      {
	         // create a database connection
	         connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(30);  // set timeout to 30 sec.


	         statement.executeUpdate("DROP TABLE IF EXISTS person");
	         statement.executeUpdate("CREATE TABLE person (id INTEGER, name STRING)");

	         int ids [] = {1,2,3,4,5};
	         String names [] = {"Peter","Pallar","William","Paul","James Bond"};

	         for(int i=0;i<ids.length;i++){
	              statement.executeUpdate("INSERT INTO person values(' "+ids[i]+"', '"+names[i]+"')");   
	         }

	         //statement.executeUpdate("UPDATE person SET name='Peter' WHERE id='1'");
	         //statement.executeUpdate("DELETE FROM person WHERE id='1'");

	           ResultSet resultSet = statement.executeQuery("SELECT * from person");
	           while(resultSet.next())
	           {
	              // iterate & read the result set
	              System.out.println("name = " + resultSet.getString("name"));
	              System.out.println("id = " + resultSet.getInt("id"));
	           }
	          }

	     catch(SQLException e){  System.err.println(e.getMessage()); }       
	      finally {         
	            try {
	                  if(connection != null)
	                     connection.close();
	                  }
	            catch(SQLException e) {  // Use SQLException class instead.          
	               System.err.println(e); 
	             }
	      }
	}
	
	public void startgame() {
		System.out.println("BEANS");
		MainClass.startGame();

	}
	private void initclasses() {

		backg = new menuBackdrop(0,0,1920,1080);

	}

	public void render(Graphics g) {
		backg.render(g);
		
	}
	
	public void startGameLoop() {
		menuThread = new Thread(this);
		menuThread.start();
	}
	public void update() {
		backg.update();
	}
	
	public void run() { //function for fps
		
		double timePerFrame = 1000000000/fps_max;
		double timePerUpdate = 1000000000/ups_max;
		long prevtime = System.nanoTime();
		
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltau = 0;
		double deltaf = 0;
		
		
		
		while(true) {
			
			long currentTime = System.nanoTime();
			
			deltau += (currentTime - prevtime) / timePerUpdate;
			deltaf +=(currentTime - prevtime) / timePerFrame;
			prevtime = currentTime;
			
			if(deltau>=1) {
				update();
				updates++;
				deltau--;
			}
			
			if(deltaf >= 1){
				menuPanel.repaint();
				frames++;
				deltaf--;
			}
			
/*			if(now - lastFrame >= timePerFrame) {
				
				gamePanel.repaint();
				lastFrame = now;
				frames++;
			}
			
			
*/
			if (System.currentTimeMillis() - lastCheck >=1000) {
				lastCheck = System.currentTimeMillis();
				frames=0;
				updates=0;
			}
		}
		
	}
	
	public void windowFocusLost() {

	}
}
