package tools;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fixtures.time.Timer;
import objects.GameObject;

public class TextureHandler {
	
	private SpriteSheet spriteSheet;
	private int currentImageIndex = 0;
	private Timer cooldownTimer;
	private boolean iterated;
	private Dimension normalSize;
	
	public TextureHandler(SpriteSheet spriteSheet) {
		this.setSpriteSheet(spriteSheet);
	}
	
	public void draw(Graphics2D g, GameObject object) {
		BufferedImage image = getCurrentImage();
		int width;
		int height;
		if(normalSize == null) {
			width = object.getRealWidth();
			height = object.getRealHeight();
		} else {
			width = (int) (image.getWidth() / ((float) normalSize.width / object.getRealWidth()));
			height = (int) (image.getHeight() / ((float) normalSize.height / object.getRealHeight()));
		}
		
		if(!object.isMirrored()) {
			g.drawImage(image, object.getRealX(), object.getRealY(), width, height, null);
		} else {
			g.drawImage(image, object.getRealX()+width, object.getRealY(), -width, height, null);
		}
		
		/*if(!isMirrored) {
			g.drawImage(textureHandler.getCurrentImage(), (int) (x*blockSize), (int) (y*blockSize), (int) (width*blockSize), (int) (height*blockSize), null);
		} else {
			g.drawImage(textureHandler.getCurrentImage(), (int) (x*blockSize+width*blockSize), (int) (y*blockSize), (int) -width*blockSize, (int) height*blockSize, null);
		}*/
	}
	
	private BufferedImage getCurrentImage() {
		if(cooldownTimer.hasFinished()) {
			currentImageIndex++;
			cooldownTimer.start();
			if(currentImageIndex >= spriteSheet.getImages().length) {
				if(spriteSheet.isRepeat()) {
					currentImageIndex %= spriteSheet.getImages().length;
				}
				iterated = true;
			}
		}
		
		if(iterated && !spriteSheet.isRepeat()) {
			return null;
		}
		
		return spriteSheet.getImages()[currentImageIndex];
	}
	
	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}
	public void setSpriteSheet(SpriteSheet spriteSheet) {
		if(!spriteSheet.equals(this.spriteSheet)) {
			this.spriteSheet = spriteSheet;
			iterated = false;
			currentImageIndex = 0;
			cooldownTimer = new Timer(spriteSheet.getCooldown());
			cooldownTimer.start();
		}
	}
	public void setStandardImage(BufferedImage image) {
		this.normalSize = new Dimension(image.getWidth(), image.getHeight());
	}
	
}
