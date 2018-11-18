package tools;

public class Timer {
	
	private long startTime = -1;
	private boolean isRunning = false;
	private int time = 0;
	
	public Timer(int time) {
		this.time = time;
	}
	
	public void start() {
		if(!isRunning) {
			isRunning = true;
			startTime = System.currentTimeMillis();
		}
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public boolean hasFinished() {
		return !isRunning();
	}
	
	public boolean isRunning() {
		if(getRemainingTime() < 0 && isRunning) {
			isRunning = false;
		} else if(getRemainingTime() > 0 && !isRunning) {
			isRunning = true;
		}
		return this.isRunning;
	}
	
	public int getRemainingTime() {
		if(startTime == -1) {
			return 0;
		} else {
			return (int) (time - (System.currentTimeMillis() - startTime));
		}
	}
	
	public int getProgress() {
		return (int) (100d / ((double) time / (time - getRemainingTime())));
	}
	
	public int getTime() {
		return this.time;
	}
	
}
