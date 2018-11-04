package main;

import java.awt.Shape;

public class Hitbox {
	
	private Shape shape;
	
	public Hitbox(Shape shape) {
		this.shape = shape;
	}
	
	public boolean collides(Hitbox hitbox) {
		return this.shape.getBounds2D().intersects(hitbox.getHitbox().getBounds2D());
	}
	
	public boolean collides(Shape hitbox) {
		return this.shape.getBounds2D().intersects(hitbox.getBounds2D());
	}
	
	public Shape getHitbox() {
		return this.shape;
	}
	
	public void setHitbox(Shape shape) {
		this.shape = shape;
	}
	
}