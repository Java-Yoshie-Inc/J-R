package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fixtures.listeners.KeyListener;
import fixtures.listeners.MouseListener;
import fixtures.time.Loop;
import menu.MainMenu;

public class Game {
	
	private static JFrame frame;
	private static JPanel panel;
	
	private static Loop timer;
	
	private static GameMode currentScene;
	private static final String GAME_NAME = "Jump & Run Game";
	
	private static KeyListener keyListener;
	private static MouseListener mouseListener;
	
	public Game() {
		frame = new JFrame();
		frame.setTitle(GAME_NAME);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		keyListener = new KeyListener();
		frame.addKeyListener(keyListener);
		
		mouseListener = new MouseListener();
		frame.addMouseListener(mouseListener);
		frame.addMouseMotionListener(mouseListener);
		frame.addMouseWheelListener(mouseListener);
		
		currentScene = new MainMenu();
		//currentScene = new World("world.dat");
		
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paint(Graphics graphics) {
				Graphics2D g = (Graphics2D) graphics;
				
				//Call current update and draw methods
				try {
					currentScene.getClass().getMethod("draw", Graphics2D.class).invoke(currentScene, g);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		};
		
		frame.add(panel);
		frame.setVisible(true);
		
		timer = new Loop(10, new Runnable() {
			@Override
			public void run() {
				try {
					currentScene.getClass().getMethod("update").invoke(currentScene);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				panel.repaint();
			}
		});
		timer.setRequiredFps(64);
		timer.setAdaptDelayToFps(true);
		timer.start();
	}
	
	public static KeyListener getKeyListener() {
		return keyListener;
	}
	
	public static MouseListener getMouseListener() {
		return mouseListener;
	}
	
	public static JFrame getFrame() {
		return frame;
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public static JPanel getPanel() {
		return panel;
	}
	
	public static String getGameName() {
		return GAME_NAME;
	}
	public static void setMode(GameMode mode) {
		currentScene = mode;
	}
	
}
