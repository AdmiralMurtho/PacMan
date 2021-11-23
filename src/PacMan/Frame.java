package PacMan;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Frame extends JFrame implements KeyListener {

	private GameScore gameScore = GameScore.getInstance();
	private String spieleErklaerung = "In dem Spiel geht es darum, möglichst schnell viele Punkte zu sammeln. \n "
			+ "Die blauen Punkte stellen die Spielpunkte dar. Diese können mit der Spielfigur eingesammelt werden. Sind alle Punkte eingesammelt, \n"
			+ "wird das nächste Level geladen. \n"
			+ "Auf der Rechten Seite werden die Statis des Spiels angezeigt. Neben den angesammelten Punkten seit Spielstart \n"
			+ "werden hier auch die Punkte pro Minute angezeigt, welche erlangt wurden.";
	private String steuerung = "Das Spiel wird über die Pfeiltasten der Tastatur gesteuert. Anfänglich bewegt sich die Figur nicht, startet aber nach dem ersten Tastendruck. \n"
			+ "Anschließend läuft die Figur bis sie entweder den Spielrand oder eine Mauer erreicht hat, dann muss die Richtung geändert werden. Alternativ kann die \n"
			+ "Spielrichtung auch zwischendurch immer geändert werden.";
	
	final int TASTE_LEFT = 37;
	final int TASTE_UP = 38;
	final int TASTE_RIGHT = 39;
	final int TASTE_DOWN = 40;
	
	/*
	 * In der Methode wird das gesammte Menü sammt der Anzeige angelegt.
	 * Für die Spielfläche wird das JPanel aus der Klasse Panel verwendet.
	 * Die einzelnen Interaktiven Elemente bekommen direkt nach der Deklaration die ActionListener mit 
	 * den dementsprechenden Anweisungen zugewiesen
	 */
	public Frame() {
		this.setVisible(true);
		Panel gamePanel = new Panel();
		
		//Menuebar
		JMenuBar leiste = new JMenuBar();
		setJMenuBar(leiste);
			JMenu datei = new JMenu("Datei");
			leiste.add(datei);
				JMenuItem dateiQuit = new JMenuItem("Quit");
				dateiQuit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {						
						System.exit(0);
					}
				});
				datei.add(dateiQuit);
			JMenu level = new JMenu("Level");
			leiste.add(level);
				JMenuItem levelLevel1 = new JMenuItem("Level 1");
				levelLevel1.addActionListener(e -> {gamePanel.newGame(1);});
				level.add(levelLevel1);
				JMenuItem levelLevel2 = new JMenuItem("Level 2");
				levelLevel2.addActionListener(e -> {gamePanel.newGame(2);});
				level.add(levelLevel2);
				JMenuItem levelLevel3 = new JMenuItem("Level 3");
				levelLevel3.addActionListener(e -> {gamePanel.newGame(3);});
				level.add(levelLevel3);
			JMenu hilfe = new JMenu("Hilfe");
			leiste.add(hilfe);
				JMenuItem hilfeErklaerung = new JMenuItem("Spielerklaerung");
				hilfeErklaerung.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, spieleErklaerung,
								"Spielerklärung", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				hilfe.add(hilfeErklaerung);
				JMenuItem hilfeSteuerung = new JMenuItem("Steuerung");
				hilfeSteuerung.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, steuerung,
								"Spielsteuerung", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				hilfe.add(hilfeSteuerung);
		this.setLayout(new BorderLayout());
		
		//Spielflaeche
		
		gamePanel.setPreferredSize(new Dimension(600,600));
		gamePanel.setMinimumSize(new Dimension(600,600));
		this.add(BorderLayout.CENTER,gamePanel);
		
		//Statsflaeche auf der Rechten Seite für Punkte
	
		JPanel stats = new JPanel();
		stats.setBackground(Color.DARK_GRAY);	
			stats.setLayout(new GridLayout(4,1));
				//erstes Gridfeld
				JPanel punktePanel = new JPanel();
				stats.add(punktePanel);
				FlowLayout flowCentered = new FlowLayout();
				flowCentered.setAlignment(FlowLayout.CENTER);
				flowCentered.setVgap(80);
				punktePanel.setLayout(flowCentered);
				punktePanel.setBackground(Color.DARK_GRAY);
				JLabel punkte = gameScore.getPktsLabel();
				punkte.setForeground(Color.white);
				punktePanel.add(punkte);
				//zweites GridFeld
				JPanel zeitPanel = new JPanel();
				stats.add(zeitPanel);
				FlowLayout flowJPanelCentred = new FlowLayout();
				flowJPanelCentred.setAlignment(FlowLayout.CENTER);
				flowJPanelCentred.setVgap(80);
				zeitPanel.setLayout(flowCentered);
				zeitPanel.setBackground(Color.DARK_GRAY);
				JLabel zeit = gameScore.getPktsMinLabel();
				zeit.setForeground(Color.white);
				zeitPanel.add(zeit);
		stats.setPreferredSize(new Dimension(200,600));
		this.add(BorderLayout.EAST, stats);
		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.DARK_GRAY);
		westPanel.setPreferredSize(new Dimension(50,600));
		this.add(BorderLayout.WEST, westPanel);
		this.addKeyListener(this);
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/*
	 * Zur Bewegung der Figur durch das Spielfeld. Dies wird mit den vier Pfeiltasten realisiert.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == TASTE_LEFT) {
			Spiel.richtungAendern(4);
		} else if(e.getKeyCode() == TASTE_UP) {
			Spiel.richtungAendern(1);
		} else if(e.getKeyCode() == TASTE_RIGHT) {
			Spiel.richtungAendern(2);
		} else if(e.getKeyCode() == TASTE_DOWN) {
			Spiel.richtungAendern(3);
		}
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
