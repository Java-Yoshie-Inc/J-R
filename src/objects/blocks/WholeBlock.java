package objects.blocks;

import main.World;
import tools.TextureHandler;

public abstract class WholeBlock extends Block {

	public WholeBlock(World world, Float x, Float y, Integer size, TextureHandler textureHandler) {
		super(world, x, y, size, size, textureHandler);
	}

}
