import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FlipperFenetre extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JMenuBar menu=new JMenuBar();
	private JMenu jeuMenu=new JMenu("Jeu");
	private JMenu optionsMenu=new JMenu("Options");
	private JMenu proposMenu=new JMenu("à propos");
	
	private JMenuItem nouveauItem=new JMenuItem("Nouveau jeu");
	private JMenuItem meilleurScoreItem=new JMenuItem("Meilleur scores");
	private JMenuItem quitterItem=new JMenuItem("Quitter");
	

	private JMenuItem changeSizeItem=new JMenuItem("Plein Ecran");
	private JMenuItem controlItem=new JMenuItem("Contrôles");

	private FlipperGame flipperGame=new FlipperGame();
	public FlipperFenetre(){
		
		setTitle("Flipper de l'espace !!");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		jeuMenu.add(nouveauItem);
		jeuMenu.add(meilleurScoreItem);
		jeuMenu.add(quitterItem);
		
		optionsMenu.add(changeSizeItem);
		optionsMenu.add(controlItem);
		
		menu.add(jeuMenu);
		menu.add(optionsMenu);
		menu.add(proposMenu);
		
		quitterItem.addActionListener(this);
		changeSizeItem.addActionListener(this);
		
		setLayout(new BorderLayout());
		getContentPane().add(menu,BorderLayout.NORTH);
		getContentPane().add(flipperGame,BorderLayout.CENTER);
		setVisible(true);
	}
	public static void main(String[] args) {
		new FlipperFenetre();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==changeSizeItem ){
			this.dispose();
			
			if(changeSizeItem.getText().equals("Mode Fenêtre")){
				this.setUndecorated(false);
				setSize(800,600);
				setLocationRelativeTo(null);
				changeSizeItem.setText("Plein Ecran");
				
			}else{
				this.setUndecorated(true);
				this.setExtendedState(JFrame.MAXIMIZED_BOTH);
				changeSizeItem.setText("Mode Fenêtre");
			}
			
			this.setVisible(true);
		}else if(e.getSource()==quitterItem){
			System.exit(0);
		}
	}

}
