package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import Audio.AudioPlayer;
import Main.GamePanel;

public class WinState extends GameState{
	private ImageIcon bg;
	private int currentChoice=2;
	private Color textColor;
	private Font titleFont;
	private AudioPlayer bgm;
	private String[] instructions= {
		"You Won",
		"Do you want to Try Again?",
		"Yes",
		"No"
	};
	public WinState(GameStateManager gsm) {
		this.gsm=gsm;
		try {
		init();
		bg=new ImageIcon("Resources/Background/helpBackground.png");
		textColor=new Color(150, 50, 0);
		titleFont= new Font("Century Gothic",Font.ITALIC,20);
		new Font("Calibri", Font.PLAIN,12);}
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
		bgm=new AudioPlayer("/BGM/applause.mp3");
		bgm.play();
		bgm=new AudioPlayer("/BGM/Help.mp3");
		bgm.play();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		BufferedImage si=getScaledImage();
		g.drawImage(si, 0, 0, null);
		g.setColor(textColor);
		g.setFont(titleFont);
		g.drawString(instructions[0], 60, 40);
		g.drawString(instructions[1], 60, 80);
		for(int i=2; i<instructions.length;i++) {
			if(i==currentChoice) {
				g.setColor(Color.GREEN);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(instructions[i], 145, 100+(i*25));
		}
		
	}

	@Override
	public void keyPressed(int k) {
		if (k==KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice<=1) {
				currentChoice=3;
			}
		}
		if (k==KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice==instructions.length) {
				currentChoice=2;
			}
		}
		if (k==KeyEvent.VK_ENTER) {
			bgm.stop();
			if(currentChoice==2) {
				gsm.setState(GameStateManager.STARTSTATE);
			}
			if(currentChoice==3) {
				gsm.setState(GameStateManager.MENUSTATE);
			}
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
