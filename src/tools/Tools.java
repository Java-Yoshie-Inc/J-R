package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tools {
	
	public static BufferedImage readFile(String file) {
		try {
			return ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static BufferedImage[] readFolder(String path) {
		File directory = new File(path);
		BufferedImage[] images = new BufferedImage[directory.list().length];
		for(int i=0; i<directory.list().length; i++) {
			images[i] = readFile(directory.listFiles()[i].getPath());
		}
		return images;
	}
	
}
