package GameState;
import java.awt.Graphics2D;
import java.util.ArrayList;
public class GameStateManager {
	private GameState[] gameStates;
	private int currentState;
	public static final int NUMGAMESTATES=5;
	public static final int MENUSTATE=0;
	public static final int STARTSTATE=1;
	public static final int HELPSTATE=2;
	public static final int DEATHSTATE=3;
	public static final int WINSTATE=4;
	public GameStateManager() {
		
		gameStates= new GameState[NUMGAMESTATES];
		currentState=MENUSTATE;
		loadState(currentState);
	}
	private void loadState(int state) {
		if (state==MENUSTATE) {
			gameStates[state]=new MenuState(this);
		}
		if (state==STARTSTATE) {
			gameStates[state]=new StartState(this);
		}
		if (state==HELPSTATE) {
			gameStates[state]=new HelpState(this);
		}
		if (state==DEATHSTATE) {
			gameStates[state]=new DeathState(this);
		}
		if (state==WINSTATE) {
			gameStates[state]=new WinState(this);
		}
	}
	private void unloadState(int state) {
		gameStates[state]=null;
	}
	public void setState(int state) {
		unloadState(currentState);
		currentState=state;
		loadState(currentState);
//		gameStates[currentState].init();
	}
	
	public void update() {
		try{
			gameStates[currentState].update();
		}catch(Exception e) {}
		
	}
	
	public void draw(Graphics2D g) {
		try{
		gameStates[currentState].draw(g);
		}catch(Exception e) {}
	}
	
	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}
	
	

}
