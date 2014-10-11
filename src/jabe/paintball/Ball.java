package jabe.paintball;

public class Ball {
	public int centerX;
	public int centerY;
	public int radius;
	
	
	public boolean furtherjump= false;
	private boolean jumped = false;
	private int jumpStart = 12;
	private boolean jumpEnd = false;
	private int jumpAnimation = 0;
	
	private int speedX = 0;
	private int speedY = 0;
	public int speedBackground = 0;
	
	private int borderX;
	private int borderY;
	
	
	
	public Ball(int x, int y, int r,int bX, int bY) {
		centerX = x;
		centerY = y;
		
		radius = r;
		
		borderX = bX;
		borderY =bY;
	}
	
	public void update(){
		//move X
		if(speedX!= 0){
			int temp = speedX + centerX;
			if((temp - radius > 0) && (temp + radius <borderX)){
				centerX =temp;
			} else {
				speedX = 0;
			}
		}
		//jump
		if(jumped){
			if(speedY>jumpStart){
				jumped = false;
				jumpEnd = true;
				if(furtherjump){
					jumped = true;
					speedY = -jumpStart;
				}
			} else {
				int temp = speedY + centerY;
				if((temp - radius > 0) && (temp + radius <borderY)){
					centerY =temp;
					speedY++;
				} else {
					speedY = 0;
				}
			}
		}
	}
	
	public void move(boolean right, boolean left){
		if(right){
			if(left){
				//no changes
			} else {
				moveRight();
			}
		} else {
			if(left){
				moveLeft();
			} else {
				stop();
			}
		}
	}
	
	public void moveRight() {
		speedX=6;
	}
	
	public void moveLeft() {
		speedX=-6;
	}
	
	public void stop(){
		speedX = 0;
	}
	
	public void jump() {
		if(! jumped){
			speedY = -jumpStart;
			jumped = true;
		}
	}
	
	public boolean isInAir(){
		return jumped;
	}
	
	public boolean isOnEarth(){
		if(jumpEnd){
			if(jumpAnimation == 5){
				jumpEnd = false;
				jumpAnimation = 0;
			} else {
				jumpAnimation++;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isMoveRight() {
		return speedX>0;
	}
	
	public boolean isMoveLeft() {
		return speedX<0;
	}
}
