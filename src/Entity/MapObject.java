package Entity;

import java.awt.Rectangle;

import GameState.GameStateManager;
import Main.GamePanel;
import TileMap.*;

public abstract class MapObject {
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap, ymap;
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	protected int width,height,cwidth,cheight,currRow,currCol;
	protected double xdes,ydes,xtemp,ytemp;
	protected boolean topLeft,topRight,botLeft,botRight,death;
	protected Animation animation;
	protected int currentAction,previousAction;
	protected boolean jump,fall,move;
	protected double fallSpeed,maxFallSpeed,jumpStart,stopJumpSpeed,movespeed,maxspeed;
	public MapObject(TileMap tm) {
		tileMap=tm;
		tileSize=tm.getTileSize();
		
	}
	public boolean intersects(MapObject o) {
		Rectangle r1=getRectangle();
		Rectangle r2=o.getRectangle();
		return r1.intersects(r2);
	}
	public Rectangle getRectangle() {
		return new Rectangle((int)x-cwidth,(int)y-cheight,cwidth,cheight);
	}
	public void calculateCorners(double x, double y) {
		int leftTile=(int)(x-cwidth/2)/tileSize;
		int rightTile=(int)(x+cwidth/2-1)/tileSize;
		int topTile=(int)(y-cheight/2)/tileSize;
		int botTile=(int)(y+cheight/2-1)/tileSize;
		int tl=tileMap.getType(topTile, leftTile);
		int tr=tileMap.getType(topTile, rightTile);
		int bl=tileMap.getType(botTile, leftTile);
		int br=tileMap.getType(botTile, rightTile);
		
		topLeft=tl==Tile.BLOCKED;
		botLeft=bl==Tile.BLOCKED;
		topRight=tr==Tile.BLOCKED;
		botRight=br==Tile.BLOCKED;
		
	}
	public void checkTileMapCollision() {
		currCol=(int)x/tileSize;
		currRow=(int)y/tileSize;
		xdes = x + dx;
		ydes=y+dy;
		xtemp=x;
		ytemp=y;
		calculateCorners(x,ydes);
		if(dy<0){
			if(topLeft || topRight) {
				dy=0;
				ytemp=currRow*tileSize+cheight/2;
			}
			else {
				ytemp+=dy;
			}
		}
		if(dy>0) {
			if(botLeft || botRight) {
				dy=0;
				fall=false;
				ytemp=(currRow+1)*tileSize-cheight/2;
			}
			else {
				ytemp+=dy;
			}
		}
		
		calculateCorners(xdes,y);
			if(dx<0) {
				if(topLeft || botLeft) {
					xtemp=currCol*tileSize+cwidth/2;
				}
			}
			else {
				xtemp+=dx;
		}
			if(dx>0) {
				if(topRight || botRight) {
					xtemp=(currCol+1)*tileSize-cwidth/2;
					setdeath(true);
				}
			}
			else {
				xtemp+=dx;
				setdeath(false);
		}
			if(!fall) {
				calculateCorners(x,ydes+1);
				if(!botLeft && !botRight) {
					fall=true;
				}
			}
		
	}
	public int getx() {return (int)x;}
	public int gety() {return (int)y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getCWidth() {return cwidth;}
	public int getCHeight() {return cheight;}
	public void setPosition(double x, double y) {
		this.x=x;
		this.y=y;
	}
	public void setmove(boolean b) { move = b; }
	public void setVector(double dx, double dy) {
		this.dx=dx;
		this.dy=dy;
	}
	public void setMapPosition() {
		xmap=tileMap.getx();
		ymap=tileMap.gety();
	}
	public void setdeath(boolean a) {death=a;}
	public void setJump(boolean b) {jump=b;}
	public boolean getdeath(boolean c) {return death;}
	public boolean notOnScreen() {
		return x+xmap+width<0||x+xmap+width>GamePanel.WIDTH ||y+ymap+height<0|| y+ymap+height>GamePanel.HEIGHT;
	}
}

















