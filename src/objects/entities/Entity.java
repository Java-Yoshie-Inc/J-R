package objects.entities;

import java.util.List;

import main.World;
import objects.GameObject;
import objects.blocks.Block;
import tools.TextureHandler;

public abstract class Entity extends GameObject {

	private static final float GRAVITY = 0.01f;
	private static final float GRAVITY_LIMIT = 0.2f;
	private static final float JUMP_HEIGHT = 0.15f;

	protected float ySpeed = 0;

	protected boolean falling = false;
	protected boolean jumping = false;

	public Entity(World world, float x, float y, float width, float height, TextureHandler textureHandler) {
		super(world, x, y, width, height, textureHandler);
	}

	@Override
	public void update() {
		float oldY = new Float(y);
		
		ySpeed += GRAVITY;
		if (ySpeed > GRAVITY_LIMIT) {
			ySpeed = GRAVITY_LIMIT;
		}
		y += ySpeed;
		
		/* COLLISION */
		boolean collided = false;
		
		for (Block block : world.getBlocks()) {
			if (block.getHitbox().collides(getNewHitbox())) {
				y = oldY;
				collided = true;
				falling = false;
				ySpeed = 0;
				jumping = false;
				
				float correction = block.y - y - height;
				if(correction >= 1) {
					y += correction;
				}
				break;
			}
		}
		if (!collided) {
			falling = true;
		}
		
		super.update();
	}

	public void jump() {
		ySpeed = -JUMP_HEIGHT;
		jumping = true;
	}

	public void move(float xSpeed) {
		float oldX = x;

		x += xSpeed;

		/* COLLISION */
		List<Block> blocks = getWorld().getBlocks();
		for (Block block : blocks) {
			if (block.getHitbox().collides(getNewHitbox())) {
				x = oldX;
			}
		}
	}

}
