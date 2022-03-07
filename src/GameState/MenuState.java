package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import Audio.AudioPlayer;
import TileMap.Background;

public class MenuState extends GameState{
	private Background bg;
	private int currentChoice=0;
	private String[] options= {
			"Start",
			"Help",
			"Quit"
	};
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private AudioPlayer bgm;
	public MenuState(GameStateManager gsm) {
		
		this.gsm=gsm;
		try {
			init();
			bg=new Background("/Background/Background.png",1);
			bg.setVector(0.3, 0);
			titleColor=new Color(128, 0, 0);
			titleFont=new Font("Century Gothic", Font.PLAIN,28);
			font=new Font("Arial", Font.PLAIN,12);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void init() {
		bgm=new AudioPlayer("/BGM/Menu.mp3");
		bgm.play();
	}
	public void update() {
		bg.update();
	}
	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Gravity Smash", 80, 70);
		g.setFont(font);
		for(int i=0; i<options.length;i++) {
			if(i==currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 145, 140+i*15);
		}
	}
	private void select() {
		if(currentChoice==0) {
			gsm.setState(GameStateManager.STARTSTATE);
		}
		if(currentChoice==1) {
			gsm.setState(GameStateManager.HELPSTATE);
		}
		if(currentChoice==2) {
			System.exit(0);
		}
	}
	public void keyPressed(int k) {
		if (k==KeyEvent.VK_ENTER) {
			select();
			bgm.stop();
		}
		if (k==KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice==-1) {
				currentChoice=options.length-1;
			}
		}
		if (k==KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice==options.length) {
				currentChoice=0;
			}
		}
	}
	public void keyReleased(int k) {}
	
}
