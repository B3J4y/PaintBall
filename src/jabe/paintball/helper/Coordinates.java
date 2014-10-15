package jabe.paintball.helper;

public class Coordinates implements Comparable<Coordinates> {
	public int x;
	public int y;
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Coordinates o) {
		// compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than 
        // other and 0 if they are supposed to be equal
		return new Integer(this.x).compareTo(o.x) + new Integer(this.y).compareTo(o.y);
	}
	
}
