package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.Hitbox;
import main.World;
import tools.TextureHandler;

public abstract class GameObject {
	
	public float x, y;
	public float width, height;
	protected Hitbox hitbox;
	protected TextureHandler textureHandler;
	protected World world;
	protected boolean isMirrored = false;
	private boolean showHitbox = false;
	protected final int blockSize;
	
	public GameObject(World world, float x, float y, float width, float height, TextureHandler textureHandler) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.world = world;
		this.textureHandler = textureHandler;
		this.blockSize = world.getBlockSize();
		hitbox = new Hitbox(new Rectangle(getRealX(), getRealY(), getRealWidth(), getRealHeight()));
	}
	
	public void draw(Graphics2D g) {
		textureHandler.draw(g, this);
		
		if(true) {
			g.setColor(Color.RED);
			g.draw(hitbox.getHitbox().getBounds2D());
		}
	}
	
	public void update() {
		hitbox = getNewHitbox();
	}
	
	protected Hitbox getNewHitbox() {
		return new Hitbox(new Rectangle(getRealX(), getRealY(), getRealWidth(), getRealHeight()));
	}
	
	public World getWorld() {
		return world;
	}
	public Hitbox getHitbox() {
		return hitbox;
	}
	public int getRealWidth() {
		return Math.round(width * blockSize);
	}
	public int getRealHeight() {
		return Math.round(height * blockSize);
	}
	public int getRealX() {
		return Math.round(x * blockSize);
	}
	public int getRealY() {
		return Math.round(y * blockSize);
	}

	public TextureHandler getTextureHandler() {
		return textureHandler;
	}

	public void setTextureHandler(TextureHandler textureHandler) {
		this.textureHandler = textureHandler;
	}

	public boolean isMirrored() {
		return isMirrored;
	}

	public void setMirrored(boolean isMirrored) {
		this.isMirrored = isMirrored;
	}

	public boolean isShowHitbox() {
		return showHitbox;
	}

	public void setShowHitbox(boolean showHitbox) {
		this.showHitbox = showHitbox;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
}
