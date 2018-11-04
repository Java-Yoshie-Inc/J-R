package objects.entities;

import com.sun.glass.events.KeyEvent;

import main.Game;
import main.World;
import tools.SpriteSheet;
import tools.TextureHandler;
import tools.Tools;

public class Player extends Entity {
	
	private static final SpriteSheet SPRITE_SHEET_STANDING = new SpriteSheet(Tools.readFile("res/images/player/standing.png"));
	private static final SpriteSheet SPRITE_SHEET_RUNNING = new SpriteSheet(Tools.readFile("res/images/player/running.png"), 5, 2, 125, true);
	
//	private BufferedImage[] runTextures;

//	private int runAnimation = 0;
//	private int runAnimationTimer = 0;

	private float walkSpeed = 0.1f;
	private float runSpeed = 0.15f;
	private float acceleration = 0.005f;

	private float currentSpeed = 0f;
	
	//private float runTextureProportions;

	public Player(World world, float x, float y, float width, float height) {
		super(world, x, y, width, height, new TextureHandler(SPRITE_SHEET_STANDING));
		textureHandler.setStandardImage(SPRITE_SHEET_STANDING.getImages()[0]);

//		File[] runTextures = new File("res/images/player/run").listFiles();
//		this.runTextures = new BufferedImage[runTextures.length];
//		for (int i = 0; i < runTextures.length; i++) {
//			this.runTextures[i] = Tools.readFile(runTextures[i].getAbsolutePath());
//		}
//		runTextureProportions = (float) this.runTextures[0].getWidth() / (float) this.runTextures[0].getHeight();
	}

	@Override
	public void update() {
		/* MOVE */
		boolean sprinting = Game.getKeyListener().pressedKey(KeyEvent.VK_SHIFT);
		boolean moving = Game.getKeyListener().pressedKey(KeyEvent.VK_A) || Game.getKeyListener().pressedKey(KeyEvent.VK_D);
		boolean movingRight = Game.getKeyListener().pressedKey(KeyEvent.VK_D);

		if (moving) {
			isMirrored = !movingRight;
			textureHandler.setSpriteSheet(SPRITE_SHEET_RUNNING);
			
			if (sprinting) {
				currentSpeed += acceleration;
			} else {
				currentSpeed -= acceleration;
			}
			if (currentSpeed > runSpeed) {
				currentSpeed = runSpeed;
			}
			if (currentSpeed < walkSpeed) {
				currentSpeed = walkSpeed;
			}

//			/* RUN ANIMATION CONTROL */
//			runAnimationTimer++;
//			if (runAnimationTimer % 5 == 0) {
//				runAnimation++;
//				if (runAnimation > runTextures.length - 1) {
//					runAnimation = 0;
//				}
//				texture = runTextures[runAnimation];
//				isMirrored = !movingRight;
//			}

		} else {
			textureHandler.setSpriteSheet(SPRITE_SHEET_STANDING);
			
			currentSpeed = 0;
		}

		if (!movingRight && moving) {
			move(-currentSpeed);
		} else if (moving && movingRight) {
			move(currentSpeed);
		}

		/* JUMP */
		if (Game.getKeyListener().pressedKey(KeyEvent.VK_SPACE) && !falling) {
			jump();
		}
		super.update();
	}

}
