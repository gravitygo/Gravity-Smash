package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import Audio.AudioPlayer;
import Main.GamePanel;

public class HelpState extends GameState{
	private ImageIcon bg;
	private int currentHelp=0;
	private Color textColor;
	private Font titleFont;
	private AudioPlayer bgm;
	private Font insFont;
	private String[] instructions= {
		"Press Space to jump",
		"Dodge the Platform",
		"Listen to the beat"
	};
	public HelpState(GameStateManager gsm) {
		this.gsm=gsm;
		try {
		init();
		bg=new ImageIcon("Resources/Background/helpBackground.png");
		textColor=new Color(150, 50, 0);
		titleFont= new Font("Century Gothic",Font.ITALIC,28);
		insFont=new Font("Calibri", Font.PLAIN,12);}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private BufferedImage getScaledImage() {
		BufferedImage gs=new BufferedImage(GamePanel.WIDTH, GamePanel.HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d=(Graphics2D) gs.createGraphics();
		g2d.drawImage(bg.getImage(), 0,0, GamePanel.WIDTH, GamePanel.HEIGHT,null);
		return gs;
	}
	@Override
	public void init() {
		bgm=new AudioPlayer("/BGM/Help.mp3");
		bgm.play();
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g) {
		BufferedImage si=getScaledImage();
		g.drawImage(si, 0, 0, null);
		g.setColor(textColor);
		g.setFont(titleFont);
		g.drawString("Gravity Smash", 80, 30);
		g.setFont(insFont);
		if (currentHelp==0) {
			g.drawString(instructions[0], 40, 100);
		}
		if (currentHelp==1) {
			g.drawString(instructions[1], 40, 100);
		}
		if (currentHelp==2) {
			g.drawString(instructions[2], 40, 100);
		}
	}
	private void getHelpSelect() {
		if (currentHelp==3) {
			bgm.stop();
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}
	public void keyPressed(int k) {
		if (k==KeyEvent.VK_LEFT) {
			currentHelp--;
			if (currentHelp==-1) {
				currentHelp=1;
			}
		}
		if (k==KeyEvent.VK_RIGHT) {
			currentHelp++;
			if(currentHelp==instructions.length) {
				getHelpSelect();
				currentHelp=0;
			}
			
		}
		
	}
	public void keyReleased(int k) {
		
		
	}
}
