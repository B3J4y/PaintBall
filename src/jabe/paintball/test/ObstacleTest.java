package jabe.paintball.test;

import static org.junit.Assert.*;

import jabe.paintball.helper.Coordinates;
import jabe.paintball.obstacles.Obstacles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ObstacleTest {

	@Test
	public void testCoordinates() {
		List<Coordinates> coords = new ArrayList<Coordinates>();
		coords.add(new Coordinates(1, 1));
		coords.add(new Coordinates(1, 5));
		coords.add(new Coordinates(1, 2));
		coords.add(new Coordinates(1, 4));
		coords.add(new Coordinates(1, 3));

		Collections.sort(coords);
		assertEquals(1, coords.get(0).y);
		assertEquals(5, coords.get(4).y);

		coords = new ArrayList<Coordinates>();
		coords.add(new Coordinates(1, 1));
		coords.add(new Coordinates(5, 1));
		coords.add(new Coordinates(2, 1));
		coords.add(new Coordinates(4, 1));
		coords.add(new Coordinates(3, 1));

		Collections.sort(coords);
		assertEquals(1, coords.get(0).x);
		assertEquals(5, coords.get(4).x);

		coords = new ArrayList<Coordinates>();
		coords.add(new Coordinates(1, 1));
		coords.add(new Coordinates(5, 7));
		coords.add(new Coordinates(2, 0));
		coords.add(new Coordinates(4, 3));
		coords.add(new Coordinates(3, 6));

		Collections.sort(coords);
		assertEquals(1, coords.get(0).x);
		assertEquals(2, coords.get(1).x);
		assertEquals(4, coords.get(2).x);
		assertEquals(3, coords.get(3).x);
		assertEquals(5, coords.get(4).x);
	}
	
	@Test
	public void testObstacles(){
		System.out.println("testObstacles: start");
		Obstacles obst = new Obstacles(5, 4, 5, 4, true, 10,200);
		
		for (int i = 0; i < 200; i++) {
			obst.update(200, 200, 0);
			System.out.println(obst.centers.size());
		}
		System.out.println("testObstacles: stop");
	}

}
