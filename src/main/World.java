package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import fixtures.geo.Vector2D;
import objects.Camera;
import objects.GameObject;
import objects.blocks.Block;
import objects.entities.Player;

public class World implements GameMode {
	
	public static final int blockSize = 50;
	
	private static List<GameObject> objects = new ArrayList<GameObject>();
	private static List<GameObject> addableObjects = new ArrayList<GameObject>();
	private static List<GameObject> removableObjects = new ArrayList<GameObject>();
	
	private Player player;
	private Camera camera;
	
	private float scale = 1;
	
	public World(String path) {
		load(path);
	}
	
	private void load(String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String playerLine = reader.readLine();
			String[] playerData = playerLine.split(";");
			float x = Float.parseFloat(playerData[0]);
			float y = Float.parseFloat(playerData[1]);
			player = new Player(this, x, y, 0.85f, 1.8f);
			
			camera = new Camera(player);
			
			String line = "";
			while((line = reader.readLine()) != null) {
				String[] objectData = line.split(";");
				
				String objectType = objectData[0];
				float objectX = Float.parseFloat(objectData[1]);
				float objectY = Float.parseFloat(objectData[2]);
				
				Class<?> type = Class.forName(objectType);
				GameObject object = (GameObject) type.getConstructors()[0].newInstance(this, objectX, objectY, 1);
				objects.add(object);
			}
			
			reader.close();
		} catch (Exception e) {
			System.err.println("World with path " + path + " cannot be created!");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	@SuppressWarnings("unused")
	private void save(String path) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			String playerData = player.x + ";" + player.y + ";" + player.width + ";" + player.height;
			writer.write(playerData);
			
			for(GameObject object : objects) {
				writer.newLine();
				String className = object.getClass().getName();
				String objectData = className + ";" + object.x + ";" + object.y;
				writer.write(objectData);
			}
			
			writer.close();
		} catch (Exception e) {
			System.err.println("World with path " + path + " cannot be created!");
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, Game.getFrame().getWidth(), Game.getFrame().getHeight());
		
		/* Translation */
		Vector2D translation = new Vector2D(camera.getX()*blockSize - Game.getPanel().getWidth()/2, camera.getY()*blockSize - Game.getPanel().getHeight()/2);
		graphics.translate(-translation.x, -translation.y);
		
		/* Objects */
		for(GameObject object : getVisibleObjects()) {
			object.draw(graphics);
		}
		player.draw(graphics);
		
		
		/* Back Translation */
		graphics.translate(translation.x, translation.y);
		 
	}
	
	public void update() {
		//Add Objects
		for(GameObject object : addableObjects) {
			objects.add(object);
		}
		
		//Remove Objects
		for(GameObject object : removableObjects) {
			objects.remove(object);
		}
		
		player.update();
		
		for(GameObject object : objects) {
			object.update();
		}
	}
	
	public List<Block> getBlocks() {
		List<Block> result = new ArrayList<Block>();
		for(GameObject object : objects) {
			if(object instanceof Block) {
				Block block = (Block) object;
				result.add(block);
			}
		}
		return result;
	}
	
	public List<GameObject> getVisibleObjects() {
		List<GameObject> visibleObjects = new ArrayList<GameObject>();
		for (GameObject object : objects) {
			if (object.getHitbox().collides(new Rectangle(
							(int) Math.round((camera.getX()*blockSize - (Game.getPanel().getWidth()/2) / scale)), 
							(int) Math.round((camera.getY()*blockSize - (Game.getPanel().getHeight()/2) / scale)), 
							Math.round(Game.getPanel().getWidth() / scale),
							Math.round(Game.getPanel().getHeight() / scale)
							))) {
				visibleObjects.add(object);
			}
		}
		return visibleObjects;
	}
	
	public void addObject(GameObject object) {
		addableObjects.add(object);
	}
	
	public void removeObject(GameObject object) {
		removableObjects.add(object);
	}
	public int getBlockSize() {
		return blockSize;
	}
	
}
