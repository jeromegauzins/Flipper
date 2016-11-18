import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOptionsFrame extends Observable implements ActionListener, KeyListener{

	private GameOptions gameOptions;
	private JFrame frame=new JFrame("Changer les contrôles");
	private JPanel panel=new JPanel();
	private JPanel conteneur=new JPanel();
	private JButton valider=new JButton("valider");
	private JButton[] boutons=new JButton[3];
	private JLabel infoLab=new JLabel("Cliquer sur modifier pour changer une touche");
	private GridBagLayout gridLayout=new GridBagLayout();
	private boolean attendTouche=false;
	private int idToucheAttente;
	private JLabel[] labels=new JLabel[3];
	
	public GameOptionsFrame(GameOptions gameOptions){
		this.gameOptions=gameOptions;
		
		valider.addActionListener(this);
		
		initPanels();
		
	    conteneur.setBorder( BorderFactory.createEmptyBorder(5, 5, 5, 5));
		conteneur.setLayout(new BorderLayout());
		conteneur.add(infoLab, BorderLayout.NORTH);
	    conteneur.add(panel,BorderLayout.CENTER);
	    conteneur.add(valider, BorderLayout.SOUTH);
	    
		frame.setSize(350, 200);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	    frame.setContentPane(conteneur);
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.setFocusable(true);
		frame.requestFocus();
		
	}
	
	private GridBagConstraints createGbc(int x, int y) {
	      GridBagConstraints gbc = new GridBagConstraints();
	      gbc.gridx = x;
	      gbc.gridy = y;
	      gbc.gridwidth = 1;
	      gbc.gridheight = 1;

	      gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
	      gbc.fill = (x == 0) ? GridBagConstraints.BOTH
	            : GridBagConstraints.HORIZONTAL;

	      gbc.weightx = (x == 0) ? 0.1 : 1.0;
	      gbc.weighty = 1.0;
	      return gbc;
	   }
	
	
	private void initPanels() {
		panel.setLayout(gridLayout);
        panel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createTitledBorder("Contrôles de Base"),
	            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		for(int i=0;i<labels.length;i++){

			labels[i]=new JLabel(KeyEvent.getKeyText(gameOptions.getTouche(i)));
			boutons[i]=new JButton("modifier");
			boutons[i].addActionListener(this);
			
			panel.add(new JLabel(gameOptions.getLabelsTouche(i)),createGbc(0,i));
			panel.add(labels[i],createGbc(1,i));
			panel.add(boutons[i],createGbc(2,i));
		}


	}

	
	public void mettreAJourInterface(){
		for(int i=0;i<labels.length;i++){
			labels[i].setText(KeyEvent.getKeyText(gameOptions.getTouche(i)));
			labels[i].setForeground(Color.BLACK);
		}
		if(idToucheAttente>=0)labels[idToucheAttente].setForeground(Color.red);
		if(!attendTouche){
			infoLab.setText("");
			valider.setEnabled(true);
		}else{
			infoLab.setText("Appuyer sur une touche maintenant.");
			valider.setEnabled(false);
			frame.requestFocus();
		}
	}


	public GameOptions getGameOptions() {
		return gameOptions;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==valider){
			frame.dispose();
			frame.setVisible(false);
		}else{
			for(int i=0;i<boutons.length;i++){
				if(boutons[i]==e.getSource())
				{
					idToucheAttente=i;
					attendTouche=true;
					mettreAJourInterface();
				}
			}
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(attendTouche){
			gameOptions.setTouche(idToucheAttente, e.getKeyCode());
			attendTouche=false;
			mettreAJourInterface();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
