import javax.swing.JPanel;

public abstract class GameThread extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	private static final int FPS_CIBLE=60;
	
	
	public abstract void update(int currentFPS);
	
	@Override
	public void run() {
		try {
			long time = 0;
			long frameCount=0;
			int currentFPS=FPS_CIBLE;
			
			while(true){
				
				if(System.currentTimeMillis()-time>=1000){
					currentFPS=(int) (frameCount/((System.currentTimeMillis()-time)/1000));
					time=System.currentTimeMillis();
					frameCount=0;
				}
				this.update(currentFPS);
				this.repaint();
				
				Thread.sleep(1000/FPS_CIBLE);
				frameCount++;
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
