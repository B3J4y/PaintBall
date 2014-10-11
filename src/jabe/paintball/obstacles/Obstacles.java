package jabe.paintball.obstacles;

import jabe.paintball.helper.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Obstacles {
	private int size;
	private int speed;
	//direction == true ==> Up
	private boolean direction;
	//distance between midpoints
	private int distance;
	
	public int length;
	public List<Coordinates> centers;
	public int height;
	
	public Obstacles(int length, int size, int speed, boolean direction, int height){
		this.length = length;
		this.size = size;
		this.length = speed;
		
		centers = new ArrayList<Coordinates>();
		distance = (int) Math.ceil(height/size);
	}
	
	public void update(int weight, int height, int ballSpeed){
		if(centers.size() == size){
			List<Coordinates> temps = new ArrayList<Coordinates>();
			for(Coordinates coord : centers){
				//sort out outbounding obstacles and move centers
				if(direction){
					if(coord.y + Math.round(this.height/2) >= 0){
						temps.add(coord);
					}
					coord.y-= (this.speed - ballSpeed );
				} else {
					if(coord.y - Math.round(this.height/2) <= 0){
						temps.add(coord);
					}
					coord.y+= (this.speed + ballSpeed);
				}
			}
			centers.removeAll(temps);
		} else {
			
			for(Coordinates coord : centers){
				//try to initiate a new one
				
			}
		}
	}
	
//	public Coordinates getNearestSpawningObstacle(){
//		Coordinates temp ;
//		if(direction){
//			for(Coordinates coord : centers){
//				
//			}
//		}
//		
//	}
}
