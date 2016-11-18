import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


public class FlipperGame extends GameThread implements KeyListener {


	private static final long serialVersionUID = 1L;
	
	private boolean rightPressed;
	private boolean leftPressed;
	private boolean tiltPressed;
	
	private BufferedImage panneauDroite;
	private GameOptions gameOptions;

	
	public FlipperGame(GameOptions options){
		gameOptions=options;
		this.addKeyListener(this);		
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		dessinerPanneauDroite(g);
	}
	
	
	private void dessinerPanneauDroite(Graphics g) {
		g.drawImage(panneauDroite, 2*getWidth()/3,0,null);
		
	}


	@Override
	public void update(int currentFPS) {
		
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==gameOptions.getTouche(GameOptions.FLIP_DROITE)){
			rightPressed=true;
		}else if(e.getKeyCode()==gameOptions.getTouche(GameOptions.FLIP_GAUCHE)){
			leftPressed=true;
		}else if(e.getKeyCode()==gameOptions.getTouche(GameOptions.TILT)){
			tiltPressed=true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==gameOptions.getTouche(GameOptions.FLIP_DROITE)){
			rightPressed=false;
		}else if(e.getKeyCode()==gameOptions.getTouche(GameOptions.FLIP_GAUCHE)){
			leftPressed=false;
		}else if(e.getKeyCode()==gameOptions.getTouche(GameOptions.TILT)){
			tiltPressed=false;
		}	
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void resizeImages() {
		panneauDroite=ImageUtils.loadImage("panneau_droite.png",getWidth()/3,getHeight()-2);
	}


	public void setGameOptions(GameOptions gameOptions) {
		this.gameOptions=gameOptions;
	}


	public GameOptions getGameOptions() {
		// TODO Auto-generated method stub
		return gameOptions;
	}

	
}
