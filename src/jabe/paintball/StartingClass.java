package jabe.paintball;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class StartingClass extends Applet implements Runnable, KeyListener {
	
	private Ball ball;
	private Image image;
	private Graphics second;
	
	private boolean moveRight;
	private boolean moveLeft;
	
	
	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.white);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Paint Ball");
		
	}

	@Override
	public void start() {
		ball = new Ball(400, 400, 20, this.getWidth(), this.getHeight());
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void run() {
		while (true) {
			ball.update();
			repaint();
			try {
				Thread.sleep(170);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("pressed: Key Up");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("pressed: Key Down");
			break;
		case KeyEvent.VK_LEFT:
			if(!moveLeft){
				moveLeft = true;
				ball.move(moveRight, moveLeft);
				System.out.println("pressed: Key Left");
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(!moveRight){
				moveRight = true;
				ball.move(moveRight, moveLeft);
				System.out.println("pressed: Key Right");
			}
			break;
		case KeyEvent.VK_SPACE:
			ball.furtherjump = true;
			ball.jump();
			System.out.println("pressed: Jump");
			break;
		default:
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("released: Key Up");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("released: Key Down");
			break;
		case KeyEvent.VK_LEFT:
			moveLeft = false;
			ball.move(moveRight, moveLeft);
			System.out.println("released: Key Left");
			break;
		case KeyEvent.VK_RIGHT:
			moveRight = false;
			ball.move(moveRight, moveLeft);
			System.out.println("released: Key Right");
			break;
		case KeyEvent.VK_SPACE:
			ball.furtherjump = false;
			System.out.println("released: Jump");
			break;
		default:
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Image doublebuffer() {
		if(this.image == null){
			this.image = createImage(this.getWidth(), this.getHeight());
		}
		second = image.getGraphics();
		
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		second.drawLine(0, ball.centerY, this.getWidth(), ball.centerY);
		second.drawLine(ball.centerX, 0, ball.centerX, this.getHeight());
		if(ball.isInAir()){
			second.fillOval(ball.centerX - (int)(0.5*ball.radius), ball.centerY - (int)(1.5*ball.radius), (int) (1*ball.radius), (int)(3*ball.radius));
		}else{
			if(ball.isOnEarth()){
				second.fillOval(ball.centerX - (int)(1.5*ball.radius), ball.centerY , (int) 3*ball.radius, (int)(1*ball.radius));
			} else {
				second.fillOval(ball.centerX - ball.radius, ball.centerY - ball.radius, 2*ball.radius,2*ball.radius);
				//TODO: Move Rotation
				if(ball.isMoveRight()){
					
					//g.rotate(AffineTransform.getRotateInstance(Math.toRadians(45), ball.centerX, ball.centerY));
				}
			}
		}
		return image;
	}
	
	@Override
	public void paint(Graphics graph) {
		Graphics2D g = (Graphics2D) graph;
		g.drawImage(this.doublebuffer(), 0, 0, this);
		
		// TODO g.drawImage(this.doublebuffer(), 0, 0, this);
		
		//Graphics2D g2 = (Graphics2D) g;
		//g2.fill(new Ellipse2D.Double());
	}

}
