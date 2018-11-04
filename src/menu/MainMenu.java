package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import fixtures.gui.components.GuiButton;
import fixtures.gui.components.GuiLabel;
import fixtures.gui.components.GuiLabel.LabelHorizontalAlignment;
import fixtures.gui.components.GuiLabel.LabelVerticalAlignment;
import main.Game;
import main.GameMode;
import main.World;

public class MainMenu implements GameMode {
	
	private GuiLabel title;
	private GuiButton playButton;
	
	public MainMenu() {
		title = new GuiLabel(Game.getGameName(), 0, 0, new Font("System", Font.BOLD, 50));
		title.setAlignmentX(LabelHorizontalAlignment.CENTER);
		title.setAlignmentY(LabelVerticalAlignment.CENTER);
		
		playButton = new GuiButton(0, 0, 300, 50);
		playButton.setLabel(new GuiLabel("PLAY"));
		playButton.setBorderWidth(5);
		playButton.setBorderColor(Color.BLACK);
		playButton.setFillColor(Color.BLUE);
		playButton.addListener(new Runnable() {
			@Override
			public void run() {
				Game.setMode(new World("world.dat"));
			}
		}, true);
	}
	
	@Override
	public void update() {
		title.setPosition(Game.getPanel().getWidth()/2, Game.getPanel().getHeight()/4);
		playButton.setX(Game.getPanel().getWidth()/2-playButton.getWidth()/2);
		playButton.setY(Game.getPanel().getHeight()/2-playButton.getHeight()/2);
		playButton.updateListener(Game.getMouseListener());
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Game.getPanel().getWidth(), Game.getPanel().getHeight());
		
		title.draw(g);
		playButton.draw(g);
	}
	
}
