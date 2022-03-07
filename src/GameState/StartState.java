package GameState;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Audio.AudioPlayer;
import Entity.*;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class StartState extends GameState{
	private TileMap tilemap;
	private Background bg;
	private Player player;
	private AudioPlayer bgm;
	public StartState(GameStateManager gsm) {
		this.gsm= gsm;
		init();
		
	}
	@Override
	public void init() {
		tilemap=new TileMap(30);
		tilemap.loadTiles("/Background/tile.png");
		tilemap.loadMap("/Background/tile.map");
		tilemap.setPosition(0, 0);
		tilemap.setTween(1);
		bg=new Background("/Background/movingBg.png",1);
		bg.setPostition(-0.1, 0);
		player=new Player(tilemap);
		player.setdeath(false);
		player.setPosition(100, 100);
		player.setmove(true);
		bgm=new AudioPlayer("/BGM/Game.mp3");
		bgm.play();
		
	}

	@Override
	public void update() {
		if(player.getdeath(true)) {
			gsm.setState(GameStateManager.DEATHSTATE);
			bgm.stop();
			
		}
		if (player.getx()>=tilemap.getWidth()-30) {
			gsm.setState(GameStateManager.WINSTATE);
			bgm.stop();
		}
		bg.update();
		player.update();
		tilemap.setPosition(GamePanel.WIDTH/2-player.getx(),GamePanel.HEIGHT/2-player.gety());
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		tilemap.draw(g);
		player.draw(g);
	}

	@Override
	public void keyPressed(int k) {
			if (k==KeyEvent.VK_SPACE) player.setJump(true);
		}

	@Override
	public void keyReleased(int k) {
		if (k==KeyEvent.VK_SPACE) player.setJump(false);
	}

}
