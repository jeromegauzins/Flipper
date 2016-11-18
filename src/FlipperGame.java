import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class FlipperGame extends JPanel implements KeyListener {

	
	private static final long serialVersionUID = 1L;
	private boolean rightPressed;
	private boolean leftPressed;

	public FlipperGame(){
		this.addKeyListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.gray);
		g.fillRect(2*getWidth()/3, 10, getWidth()/3-20 , getHeight()-20);

		g.setColor(Color.black);
		g.fillRect(2*getWidth()/3+8, 18, getWidth()/3-36 , getHeight()-36);

		g.setColor(Color.gray);
		g.fillRect(2*getWidth()/3, getHeight()/3, getWidth()/3 , 8);
		g.fillRect(2*getWidth()/3, getHeight()/3+200, getWidth()/3 , 8);
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_P){
			rightPressed=true;
		}else if(e.getKeyCode()==KeyEvent.VK_A){
			leftPressed=true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_P){
			rightPressed=true;
		}else if(e.getKeyCode()==KeyEvent.VK_A){
			leftPressed=true;
		}		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
