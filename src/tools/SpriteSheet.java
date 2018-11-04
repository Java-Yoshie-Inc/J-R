package tools;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private final BufferedImage image;
	private final BufferedImage[] images;
	private final int cooldown;
	private final boolean repeat;
	
	public SpriteSheet(BufferedImage image, int rows, int columns, int cooldown, boolean repeat) {
		this.image = image;
		this.repeat = repeat;
		this.cooldown = cooldown;
		
		images = new BufferedImage[rows * columns];
		int width = image.getWidth() / rows;
		int height = image.getHeight() / columns;
		
		int i=0;
		for(int y=0; y<columns; y++) {
			for(int x=0; x<rows; x++) {
				images[i] = image.getSubimage(x*width, y*height, width, height);
				i++;
			}
		}
	}
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
		this.images = new BufferedImage[] {image};
		repeat = true;
		cooldown = 99999999;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public BufferedImage[] getImages() {
		return images;
	}

	public int getCooldown() {
		return cooldown;
	}

	public boolean isRepeat() {
		return repeat;
	}
	
}
