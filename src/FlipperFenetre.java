import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class FlipperFenetre extends JFrame implements ActionListener, AncestorListener, Observer{

	private static final long serialVersionUID = 1L;
	
	private JMenuBar menu=new JMenuBar();
	
	private JMenu jeuMenu=new JMenu("Jeu");
	private JMenu optionsMenu=new JMenu("Options");
	private JMenu proposMenu=new JMenu("à propos");
	
	private JMenuItem nouveauItem=new JMenuItem("Nouveau jeu");
	private JMenuItem meilleurScoreItem=new JMenuItem("Meilleur scores");
	private JMenuItem quitterItem=new JMenuItem("Quitter");
	
	private JMenuItem changeSizeItem=new JMenuItem("Plein Ecran");
	private JMenuItem controlesItem=new JMenuItem("Contrôles");

	private FlipperGame flipperGame=new FlipperGame(GameOptions.getDefaultGameOptions());
	
	private boolean modeFenetre=true;
	
	public FlipperFenetre(){
		
		this.setTitle("Flipper de l'espace !!");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		jeuMenu.add(nouveauItem);
		jeuMenu.addSeparator();
		jeuMenu.add(meilleurScoreItem);
		jeuMenu.add(quitterItem);
		
		optionsMenu.add(changeSizeItem);
		optionsMenu.add(controlesItem);
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		
		menu.add(jeuMenu);
		menu.add(optionsMenu);
		menu.add(proposMenu);
		
		quitterItem.addActionListener(this);
		
		changeSizeItem.addActionListener(this);
		changeSizeItem.setMnemonic(KeyEvent.VK_B);
		
		controlesItem.addActionListener(this);

		flipperGame.addAncestorListener(this);
		
		this.setJMenuBar(menu);
		this.setLayout(new BorderLayout());
		this.add(flipperGame,BorderLayout.CENTER);
		this.setVisible(true);
		
		new Thread(flipperGame).start();
	}
	public static void main(String[] args) {
		new FlipperFenetre();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==changeSizeItem ){
			modeFenetre=!modeFenetre;
			mettreAJourInterface();
		}else if(e.getSource()==controlesItem){
			GameOptionsFrame gameOptionsFrame=new GameOptionsFrame(flipperGame.getGameOptions());
			gameOptionsFrame.addObserver(this);
		}else if(e.getSource()==quitterItem){
			System.exit(0);
		}
	}
	
	
	
	public void mettreAJourInterface(){

		this.dispose();
		if(modeFenetre){
			this.setUndecorated(false);
			this.setSize(800,600);
			this.setLocationRelativeTo(null);
			changeSizeItem.setText("Plein Ecran");
		}else{
			this.setUndecorated(true);
			this.setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
			changeSizeItem.setText("Mode Fenêtre");
		}
		this.setVisible(true);
		

		flipperGame.resizeImages();
	}
	

	@Override
	public void ancestorAdded(AncestorEvent arg0) {
		flipperGame.resizeImages();
		
	}
	@Override
	public void ancestorMoved(AncestorEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void ancestorRemoved(AncestorEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Observable arg0, Object o) {
		if(o instanceof GameOptionsFrame){
			flipperGame.setGameOptions(((GameOptionsFrame)o).getGameOptions());
		}
	}

}
