package objects.blocks;

import main.World;
import tools.SpriteSheet;
import tools.TextureHandler;
import tools.Tools;

public class Grass extends WholeBlock {
	
	private static final SpriteSheet SPRITE_SHEET = new SpriteSheet(Tools.readFile("res/images/blocks/grass.png"));
	
	public Grass(World world, Float x, Float y, int size) {
		super(world, x, y, size, new TextureHandler(SPRITE_SHEET));
	}

}
