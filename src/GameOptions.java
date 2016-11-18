import java.awt.event.KeyEvent;

public class GameOptions {
	
	public static final int FLIP_GAUCHE=0;
	public static final int FLIP_DROITE=1;
	public static final int TILT=2;
	
	private int[] touches;
	private String[] labels=new String[]{"FLIP GAUCHE","FLIP DROIT","TILT"};
	
	public GameOptions(int... touches){
		this.touches=touches;
	}
	
	

	public void setTouche(int idTouche,int val) {
		 touches[idTouche]=val;
	}
	public int getTouche(int idTouche) {
		return touches[idTouche];
	}

	public String getLabelsTouche(int i) {
		// TODO Auto-generated method stub
		return labels[i];
	}
	
	
	public static GameOptions getDefaultGameOptions() {
		return new GameOptions(KeyEvent.VK_A,KeyEvent.VK_P,KeyEvent.VK_SPACE);
	}
}
