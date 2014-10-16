package jabe.paintball.obstacles;

import jabe.paintball.helper.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Obstacles {
	//how many obstacles
	private int size;
	//how fast do the obstacles move
	private int speed;
	//direction == true ==> Up
	private boolean direction;
	//distance between midpoints
	private int distance;
	
	//how long are the obstacles
	public int length;
	//all coordinates of the obstacles
	public List<Coordinates> centers;
	//how height is an obstacle
	public int height;
	
	private int position;
	
	public Obstacles(int length, int obtHeight, int size, int speed, boolean direction, int position,int height){
		this.length = length;
		this.size = size;
		this.speed = speed;
		this.height = obtHeight;
		this.direction = direction;
		
		centers = new ArrayList<Coordinates>();
		distance = (int) Math.ceil(height/size);
		
		this.position = position;
	}
	
	public void update(int weight, int height, int ballSpeed){
		if(centers.size() == size){
			List<Coordinates> temps = new ArrayList<Coordinates>();
			for(Coordinates coord : centers){
				//sort out outbounding obstacles and move centers
				if(direction){
					if(coord.y + Math.round(this.height/2) <= 0){
						temps.add(coord);
					}
					coord.y-= (this.speed - ballSpeed );
				} else {
					if(coord.y - Math.round(this.height/2) >= height){
						temps.add(coord);
					}
					coord.y+= (this.speed + ballSpeed);
				}
			}
			centers.removeAll(temps);
		} else {
			if(centers.size() == 0){
				if(direction){
					centers.add(new Coordinates(position, height));
				} else {
					centers.add(new Coordinates(position, 0));
				}
				return;
			}
			if(direction){
				Coordinates max = Collections.max(centers);
				if(max.y <= height-distance){
					centers.add(new Coordinates(max.x, height));
				}
				for(Coordinates coord : centers){
					coord.y-= (this.speed - ballSpeed );
				}
			} else {
				Coordinates min = Collections.min(centers);
				if(min.y >= distance){
					centers.add(new Coordinates(min.x, 0));
				}
				for(Coordinates coord : centers){
					coord.y+= (this.speed + ballSpeed);
				}
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
