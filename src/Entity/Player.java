package Entity;

import TileMap.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Player extends MapObject{
	private boolean move=true;
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames= {4, 1, 1};
	private static final int WALKING=0;
	private static final int JUMPING=1;
	private static final int FALLING=2;
	public Player(TileMap tm) {
		super(tm);
		height=56;
		width=64;
		cwidth=40;
		cheight=54;
		fallSpeed=0.14;
		maxFallSpeed=4;
		jumpStart=-5.8;
		stopJumpSpeed=0.3;
		movespeed=0.01;
		maxspeed=4;
		try {
			BufferedImage spritesheet= ImageIO.read(getClass().getResourceAsStream("/Entity/Player.png"));
			sprites=new ArrayList<BufferedImage[]>();
		for (int i=0;i<3;i++) {
			BufferedImage[] bi=new BufferedImage[numFrames[i]];
			for (int j=0;j<numFrames[i];j++) {
				bi[j]=spritesheet.getSubimage(j*width, i*height, width, height);
			}
			sprites.add(bi);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation=new Animation();
		currentAction=FALLING;
		animation.setFrames(sprites.get(FALLING));
		animation.setDelay(300);
		
	}
	
	private void getNextPosition() {
		if (jump && !fall) {
			dy=jumpStart;
			fall=true;
		}
		if (fall) {
			dy+=fallSpeed;
			
			if(dy>0) fall=false;
			if(dy<0 && !jump) dy+=stopJumpSpeed;
			if(dy>maxFallSpeed) dy=maxFallSpeed;
		}
		if (move) {
			dx += movespeed;
			if(dx>maxspeed) {
				dx=maxspeed;
			}
		}
	}
	public void update() {
		
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp,ytemp);
		if (dy>0) {
			if(currentAction!=FALLING) {
				currentAction=FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width=64;
				
			}
		}
		else if(dy<0) {
			if(currentAction!=JUMPING) {
				currentAction=JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width=64;
			}
		}
		else {
			if(currentAction!=WALKING) {
				currentAction=WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(100);
				width=64;
			}
			
		}
		animation.update();
	}
	public void draw(Graphics2D g) {
		setMapPosition();
		g.drawImage(animation.getImage(),(int)(x+xmap-width/2),(int)(y+ymap-height/2),null);
	}

}
