package objects.blocks;

import main.World;
import objects.GameObject;
import tools.TextureHandler;

public abstract class Block extends GameObject {

	public Block(World world, Float x, Float y, Integer width, Integer height, TextureHandler textureHandler) {
		super(world, x, y, width, height, textureHandler);
	}
	
}
