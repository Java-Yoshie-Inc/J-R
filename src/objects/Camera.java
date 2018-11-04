package objects;

public class Camera {
	
	private GameObject object;
	
	public Camera(GameObject object) {
		this.object = object;
	}
	
	public float getX() {
		return object.x+object.width/2;
	}
	
	public float getY() {
		return object.y+object.height/2;
	}

}
